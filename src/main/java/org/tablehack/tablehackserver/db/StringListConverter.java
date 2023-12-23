package org.tablehack.tablehackserver.db;


import java.io.IOException;
import java.util.List;

import org.tablehack.tablehackserver.db.entities.CardTypeEntryList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

@Converter(autoApply = true)
@Slf4j
public class StringListConverter implements AttributeConverter<List<String>, String> {

  private final static ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public String convertToDatabaseColumn(List<String> meta) {
      if (meta == null) {
          return null;
      }
    try {
      return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(meta);
    } catch (JsonProcessingException ex) {
        log.warn("Failed to convert send option settings data to JSON!", ex);
      return null;
      // or throw an error
    }
  }

  @Override
  public List<String> convertToEntityAttribute(String dbData) {
      if (dbData == null) {
          return null;
      }
    try {
      return objectMapper.readValue(dbData, List.class);
    } catch (IOException ex) {
        log.warn("Failed to convert JSON to send option settings data!", ex);
      return null;
    }
  }

}