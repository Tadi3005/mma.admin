package org.helmo.mma.admin.domains.converter;

import org.helmo.mma.admin.domains.exception.ConversionException;

/**
 * Functional interface to convert a string to a specific type.
 */
public class IntConverter implements Converter<Integer> {
    /**
     * Convert a string to an integer.
     * @param input the string to convert
     * @return the integer
     */
    @Override
    public Integer convert(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new ConversionException("Impossible de convertir " + input + " en entier");
        }
    }
}
