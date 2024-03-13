package org.example.reservation.entity.converter;

/**
 *
 * @param <F> Formクラス
 * @param <T> Entityクラス
 */
public interface Converter<D, T> {
    T convertToEntity(D dto);
    D convertToDto(T entity);
}
