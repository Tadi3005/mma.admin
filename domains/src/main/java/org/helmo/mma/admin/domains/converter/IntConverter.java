package org.helmo.mma.admin.domains.converter;

import org.helmo.mma.admin.domains.exception.ConversionException;

public class IntConverter implements Converter<Integer> {
    @Override
    public Integer convert(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new ConversionException("Impossible de convertir " + input + " en entier");
        }
    }
}
