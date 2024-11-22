package org.helmo.mma.admin.presentations.converter;

import org.helmo.mma.admin.domains.exception.ConversionException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Convert a string to a date.
 */
public class DateConverter implements Converter<LocalDate> {
    /**
     * Convert a string to a date.
     * @param input the string to convert
     * @return the date
     */
    @Override
    public LocalDate convert(String input) {
        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            throw new ConversionException("Impossible de convertir " + input + " en date");
        }
    }
}
