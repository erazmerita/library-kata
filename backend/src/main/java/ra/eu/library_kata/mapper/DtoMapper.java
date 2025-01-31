package ra.eu.library_kata.mapper;

public interface DtoMapper<D, E> {

    E dtoToEntity(D dto);

    D entityToDto(E entity);
}
