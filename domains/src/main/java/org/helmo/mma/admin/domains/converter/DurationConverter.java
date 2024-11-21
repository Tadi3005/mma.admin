package org.helmo.mma.admin.domains.converter;

import org.helmo.mma.admin.domains.exception.ConversionException;

import java.time.Duration;

public class DurationConverter implements Converter<Duration> {
    private final String separator;

    public DurationConverter(String separator) {
        this.separator = separator;
    }

    @Override
    public Duration convert(String value) throws ConversionException {
        try {
            String[] parts = value.split(separator);
            long hours = Long.parseLong(parts[0]);
            long minutes = Long.parseLong(parts[1]);
            return Duration.ofHours(hours).plusMinutes(minutes);
        } catch (Exception e) {
            throw new ConversionException("Invalid duration format");
        }
    }
}
