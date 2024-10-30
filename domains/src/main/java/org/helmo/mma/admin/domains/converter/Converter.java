package org.helmo.mma.admin.domains.converter;

import org.helmo.mma.admin.domains.exception.ConversionException;

/**
 * Functional interface to convert a string to a specific type.
 * @param <T> the type to convert to
 */
@FunctionalInterface
public interface Converter<T> {
    /**
     * Convert a string to a specific type.
     * @param input the string to convert
     * @return the converted value
     * @throws ConversionException if the conversion fails
     */
    T convert(String input) throws ConversionException;
}
