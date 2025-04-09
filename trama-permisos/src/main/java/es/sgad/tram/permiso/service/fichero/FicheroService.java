package es.sgad.trama.permiso.service.fichero;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;


public interface FicheroService {

	
	
	//Grabar un fichero
	//public String guardarFichero(String idSolicitudIncidencia, MultipartFile ficheros) throws IOException;
	//Grabar un fichero
	public String guardarFichero(LocalDate fecha,String idSolicitud, MultipartFile ficheros) throws IOException;
	//Grabar varios ficheros List
	public List<String> guardarFichero(LocalDate fecha,String idSolicitudPermiso, List<MultipartFile> ficheros) throws IOException;
	
	
	//Elimnar fichero
	public void eliminarFichero(String rutaFichero) ;
	//Elimnar varios  ficheros
	public void eliminarFichero(List<String> rutasFicheros) ;
}
