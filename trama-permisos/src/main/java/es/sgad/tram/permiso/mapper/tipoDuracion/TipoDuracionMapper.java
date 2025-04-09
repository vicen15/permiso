package es.sgad.trama.permiso.mapper.tipoDuracion;

import org.mapstruct.Mapper;

import es.sgad.trama.permiso.domain.tipoOperacion.TipoDuracionDTO;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.persistance.entity.tipoDuracion.TipoDuracionEntity;


@Mapper(componentModel = "spring" )
public interface TipoDuracionMapper extends TramaMapper<TipoDuracionDTO, TipoDuracionEntity>{
	
	TipoDuracionDTO toDomain(TipoDuracionEntity entity);
	TipoDuracionEntity toEntity(TipoDuracionDTO dto);

}
