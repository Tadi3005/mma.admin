package org.helmo.mma.admin.domains.converter;

import org.helmo.mma.admin.domains.exception.ConversionException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateConverter implements Converter<LocalDate> {
    @Override
    public LocalDate convert(String input) {
        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            throw new ConversionException("Impossible de convertir " + input + " en date");
        }
    }
}
