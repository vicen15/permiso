package es.sgad.trama.permiso.domain.solicitudPermisoDocumento;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SolicitudPermisoDocumentoValidFileExtension {
	JPG("jpg"),
	JPEG("jpeg"),
	PNG("png"),
	PDF("pdf"),
	DOCX("docx");
	
	private String extension;
	
	
	//Verificar extension
	public static Boolean isValid(String nombreFichero) {
		
		if(nombreFichero == null || !nombreFichero.contains(".")) {
			return false;
		}
		
		String extensionFichero = nombreFichero.substring((nombreFichero.lastIndexOf(".")+1)).toLowerCase();
		
		for(SolicitudPermisoDocumentoValidFileExtension extension : SolicitudPermisoDocumentoValidFileExtension.values()) {
			if(extension.getExtension().equals(extensionFichero)) {
				return true;
			}
		}
		
		return false;
	}
}
