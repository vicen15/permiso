package es.sgad.trama.permiso.service.fichero;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import es.sgad.trama.common.exception.DirectoryUploadNotFoundException;
import es.sgad.trama.common.exception.InternalErrorException;
import es.sgad.trama.common.exception.ItemNotFoundException;
import es.sgad.trama.common.file.FileUtil;

@Service
public class FicheroServiceImpl implements FicheroService {


	@Value("${trama.solicitud.upload.path}")
	String DIRECTORIO_UPLOAD;
	
	private static final Logger log = LoggerFactory.getLogger(FicheroServiceImpl.class);
	
//	@Override
//	public String guardarFichero(String idSolicitudIncidencia, MultipartFile ficheros) throws IOException{
//		
//		Path rutaFichero = null;
//		
//		if(idSolicitudIncidencia == null) {
//			return null;
//		}
//		if(ficheros == null) {
//			return null;
//		}
//		if(ficheros.isEmpty()) {
//			return null;
//		}
//		//TODO Aqui debemos establecer la jerarquia de ficheros
//		// ANIO/MES/DIA/IDSOLICITUD/nombrefichero.extension
//		Path directorioUpload = Paths.get(DIRECTORIO_UPLOAD);
//		
//		if(Files.exists(directorioUpload) && Files.isDirectory(directorioUpload)) {
//			
//			//String nombreFichero = idSolicitudIncidencia + "_" + ficheros.getOriginalFilename();
//			String nombreFichero = FileUtil.generarNombreFicheroSolicitudIncidenciaDocumento(idSolicitudIncidencia, FileUtil.extraerExtension(ficheros.getOriginalFilename()));
//			
//			rutaFichero = Paths.get(DIRECTORIO_UPLOAD, nombreFichero);
//			
//			Files.write(rutaFichero, ficheros.getBytes());		
//		}
//
//		return rutaFichero.toString();
//	}

	@Override
	public String guardarFichero(LocalDate fecha,String idSolicitud, MultipartFile ficheros)
			throws IOException {
		Path rutaFichero = null;
		
		if(idSolicitud == null) {
			return null;
		}
		if(fecha == null) {
			return null;
		}
		if(ficheros == null) {
			return null;
		}
		if(ficheros.isEmpty()) {
			return null;
		}

		//Generar la jerarquia de directorios
		String rutaDirectorioUpload = crearRutaFichero(fecha,idSolicitud);
		
		if(rutaDirectorioUpload == null) {
			throw new ItemNotFoundException("No se pudo generar el directorio para guardar los documentos:", rutaDirectorioUpload);
		}
		
		Path directorioUpload = Paths.get(rutaDirectorioUpload);
		
		
		if(Files.exists(directorioUpload) && Files.isDirectory(directorioUpload)) {
			
			//String nombreFichero = idSolicitudIncidencia + "_" + ficheros.getOriginalFilename();
			//String nombreFichero = FileUtil.generarNombreFicheroSolicitudIncidenciaDocumento(idSolicitud, FileUtil.extraerExtension(ficheros.getOriginalFilename()));
			//String nombreFichero =  ficheros.getOriginalFilename();
			rutaFichero = Paths.get(directorioUpload.toString(), ficheros.getOriginalFilename());
			//si sube dos ficheros con el mismo nombre
			if(Files.exists(rutaFichero)) {
				 
				rutaFichero = Paths.get(directorioUpload.toString(),FileUtil.generarNombreFicheroSolicitudIncidenciaDocumento(ficheros.getOriginalFilename(), FileUtil.extraerExtension(ficheros.getOriginalFilename())));
			}
			
			Files.write(rutaFichero, ficheros.getBytes());		
		}

		return rutaFichero.toString();
	}
	

	@Override
	public List<String> guardarFichero(LocalDate fecha, String idSolicitudPermiso, List<MultipartFile> ficheros) throws IOException {
		Path rutaFichero = null;
		List<String> listaRutasFichero = new ArrayList();
		if(idSolicitudPermiso == null) {
			return null;
		}
		if(fecha == null) {
			return null;
		}
		if(ficheros == null) {
			return null;
		}
		if(ficheros.isEmpty()) {
			return null;
		}
		
		//Generar la jerarquia de directorios
		String rutaDirectorioUpload = crearRutaFichero(fecha,idSolicitudPermiso);
		
		if(rutaDirectorioUpload == null) {
			throw new ItemNotFoundException("No se pudo generar el directorio para guardar los documentos:", rutaDirectorioUpload);
		}
		
		Path directorioUpload = Paths.get(rutaDirectorioUpload);
		
		if(Files.exists(directorioUpload) && Files.isDirectory(directorioUpload)) {
			for(MultipartFile fichero : ficheros) {
				rutaFichero = Paths.get(directorioUpload.toString(), fichero.getOriginalFilename());
				if(Files.exists(rutaFichero)) {
					 
					rutaFichero = Paths.get(directorioUpload.toString(),FileUtil.generarNombreFicheroSolicitudIncidenciaDocumento(fichero.getOriginalFilename(), FileUtil.extraerExtension(fichero.getOriginalFilename())));
				}
				Files.write(rutaFichero, fichero.getBytes());
				listaRutasFichero.add(rutaFichero.toString());			
			}
		}
		
		return listaRutasFichero;
	}

	@Override
	public void eliminarFichero(String rutaFichero) {
		try {
			if(rutaFichero !=null && StringUtils.isNotBlank(rutaFichero)) {
				Files.delete(Paths.get(rutaFichero));
			}
		}catch(IOException e) {
			throw new InternalErrorException(e.getCause());
		}
	}
	
	@Override
	public void eliminarFichero(List<String> rutasFicheros) {
		try {
			if(rutasFicheros !=  null && !rutasFicheros.isEmpty()) {
				for(String pathFichero : rutasFicheros) {
					Files.delete(Paths.get(pathFichero));
				}
				//Tambien eliminamos el directorio que contiene los documentos, para evitar directorios vacios
				deleteDirectorio(rutasFicheros.get(0));
			}
		}catch(IOException e) {
			throw new InternalErrorException(e.getCause());
		}
	}
	/**
	 * Se genera la jerarquia de directorios completa a partir de la fecha de solicitud e idSolicitudIncidencia
	 * @param fecha	FechaSolicitud
	 * @param idSolicitud id de la solicitudPermiso
	 * @return ruta completa (DIRECTORIO_UPLOAD\ANIO\MES\DIA\IDSOLICITUDPERMISO)
	 * @throws IOException Error en el sistema de archivos
	 * @throws NoSuchFileException No se encuentra la ruta
	 */
	public String crearRutaFichero(LocalDate fecha,String idSolicitud) throws IOException, NoSuchFileException {
		
		if(fecha == null) {
			return null;
		}
		if(idSolicitud == null) {
			return null;
		}
		
		if(!Files.exists(Path.of(DIRECTORIO_UPLOAD))) {
			throw new DirectoryUploadNotFoundException();
		}
		
		Integer anio = fecha.getYear();
		
		//String mes = fecha.getMonth().getDisplayName(TextStyle.FULL, new Locale("es","ES")); //para devolver el nombre del mes
		Integer mes = fecha.getMonth().getValue(); //Para devolver el numero del mes
		Integer dia =  fecha.getDayOfMonth();
		
		String rutaCompleta = DIRECTORIO_UPLOAD + "\\" + anio; // + "\\" + mes.toUpperCase() + "\\" + dia + "\\" + idSolicitud+ "\\";
		
		
		
		rutaCompleta = crearDirectorio(rutaCompleta);
		
		//rutaCompleta = rutaCompleta + "\\" + mes.toUpperCase(); //para establecer el nombre del mes en mayusculas
		
		rutaCompleta = rutaCompleta + "\\" + mes; 
		
		rutaCompleta = crearDirectorio(rutaCompleta);
		
		rutaCompleta = rutaCompleta + "\\" + dia;
		
		rutaCompleta = crearDirectorio(rutaCompleta);
		
		rutaCompleta = rutaCompleta + "\\" + idSolicitud;
		
		rutaCompleta = crearDirectorio(rutaCompleta);
		
		
		return rutaCompleta;
	}
	/**
	 * Se crear el directorio a partir de la ruta
	 * @param ruta
	 * @return ruta del directorio creado
	 * @throws IOException Error el en el sistema de archivos
	 * @throws NoSuchFileException No se encuentra la ruta en el sistema
	 */
	public String crearDirectorio(String ruta) throws IOException, NoSuchFileException {
		if(ruta == null) {
			
			return null;
		}
		
		Path rutaCarpeta = Paths.get(ruta);
		
		if(Files.notExists(rutaCarpeta)) {
			
			Files.createDirectory(rutaCarpeta);
			log.info("Directorio creado correctamente: {} ", rutaCarpeta);
			
		}
		
		return rutaCarpeta.toString();
	}
	public void deleteDirectorio(String ruta) throws IOException {
		if(ruta != null && !StringUtils.isEmpty(ruta)) {
			Path directorioPadre = Paths.get(FileUtil.extraerRutaAbsolutaDesdeRutaConFichero(ruta));
			if(Files.isDirectory(directorioPadre)) {
				Files.delete(directorioPadre);
				log.info("Directorio eliminado correctamente: {} ", directorioPadre);
			}
		}
	}
}
