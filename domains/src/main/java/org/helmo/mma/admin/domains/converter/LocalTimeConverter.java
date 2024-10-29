package org.helmo.mma.admin.domains.converter;

import org.helmo.mma.admin.domains.exception.ConversionException;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class LocalTimeConverter implements Converter<LocalTime> {
    @Override
    public LocalTime convert(String input) throws ConversionException {
        try {
            return LocalTime.parse(input);
        } catch (DateTimeParseException e) {
            throw new ConversionException("The input is not a valid LocalTime");
        }
    }
}
