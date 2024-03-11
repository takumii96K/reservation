package org.example.reservation.entity.converter;

/**
 *
 * @param <F> Formクラス
 * @param <T> Entityクラス
 */
public interface Converter<F, T> {
    T convertToEntity(F form);
    F convertToForm(T entity);
}
