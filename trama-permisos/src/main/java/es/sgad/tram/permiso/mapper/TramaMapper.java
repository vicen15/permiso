package es.sgad.trama.permiso.mapper;

import java.util.Collection;

/**
 * Base para el mapeo entre dominio y entidad.
 * 
 * @param <D>	dominio
 * @param <E>	entidad
 */
public interface TramaMapper<D, E> {
	
	/**
	 * Convierte una entidad en un objeto de dominio.
	 * 
	 * @param entity	entidad
	 * @return dominio
	 */
	D toDomain(final E entity);
	
	/**
	 * Convierte una lista de entidades en una lista de objetos de dominio.
	 * 
	 * @param entities	entidades
	 * @return dominios
	 */
	default Collection<D> toDomain(final Collection<E> entities) {
		return entities.stream().map(entity -> toDomain(entity)).toList();
	}
	
	/**
	 * Convierte un objeto de dominio en una entidad.
	 * 
	 * @param dto	dominio
	 * @return entidad
	 */
	E toEntity(final D dto);
	
	/**
	 * Convierte una lista de objetos de dominio en una lista de entidades.
	 * 
	 * @param dtos	dominios
	 * @return entidades
	 */
	default Collection<E> toEntities(final Collection<D> dtos) {
		return dtos.stream().map(dto -> toEntity(dto)).toList();
	}

}
