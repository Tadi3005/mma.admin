package org.helmo.mma.admin.domains.converter;

import org.helmo.mma.admin.domains.exception.ConversionException;

@FunctionalInterface
public interface Converter<T> {
    T convert(String input) throws ConversionException;
}
