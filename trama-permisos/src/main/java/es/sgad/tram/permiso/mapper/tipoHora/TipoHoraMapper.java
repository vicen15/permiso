package es.sgad.trama.permiso.mapper.tipoHora;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import es.sgad.trama.permiso.domain.tipoHora.TipoHoraDTO;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.mapper.ambito.AmbitoMapper;
import es.sgad.trama.permiso.mapper.franja.FranjaMapper;
import es.sgad.trama.permiso.persistance.entity.tipoHora.TipoHoraEntity;

/**
 * Mapeador entre {@link TipoHoraDTO} y {@link TipoHoraEntity}.
 */
@Mapper(componentModel = "spring" , uses = { FranjaMapper.class , AmbitoMapper.class })
public interface TipoHoraMapper extends TramaMapper<TipoHoraDTO, TipoHoraEntity> {

	@Mappings({
		  @Mapping(target= "idAmbito" , source ="ambito.id"),
		  @Mapping(target= "listaFranjas" , source ="listaFranjas"),
		  
	  })
	  TipoHoraDTO toDomain(TipoHoraEntity entity);
	 
	  
	  @Mappings({
		  @Mapping(target= "ambito.id" , source ="idAmbito"),
		  @Mapping(target= "listaFranjas" , source ="listaFranjas"),
		  
	  })
	  TipoHoraEntity toEntity(TipoHoraDTO dto);
}
