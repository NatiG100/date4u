package com.tutego.date4u.core.converters;

import java.nio.file.Path;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToPathConverter implements Converter<String, Path>{
    @Override public Path convert (String source){
        return Path.of(source);
    }
}