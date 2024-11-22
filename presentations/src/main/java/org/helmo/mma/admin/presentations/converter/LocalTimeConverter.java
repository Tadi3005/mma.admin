package org.helmo.mma.admin.presentations.converter;

import org.helmo.mma.admin.domains.exception.ConversionException;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Convert a string to a LocalTime.
 */
public class LocalTimeConverter implements Converter<LocalTime> {
    /**
     * Convert a string to a LocalTime.
     * @param input the string to convert
     * @return the LocalTime
     * @throws ConversionException if the input is not a valid LocalTime
     */
    @Override
    public LocalTime convert(String input) throws ConversionException {
        try {
            return LocalTime.parse(input);
        } catch (DateTimeParseException e) {
            throw new ConversionException("The input is not a valid LocalTime");
        }
    }
}
