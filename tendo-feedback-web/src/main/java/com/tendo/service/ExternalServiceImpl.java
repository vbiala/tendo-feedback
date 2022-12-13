package com.tendo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tendo.common.FileUtils;
import com.tendo.dto.Bundle;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class ExternalServiceImpl implements ExternalService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalServiceImpl.class);
    private static final String BUNDLE_FILE_NAME = "patient-feedback-raw-data.json";
    private ObjectMapper objectMapper;

    public ExternalServiceImpl() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    public Optional<Bundle> getBundle(UUID uuid) {
        try {
            InputStream is = FileUtils.getFileFromResourceAsStream(BUNDLE_FILE_NAME);
            Bundle bundle = objectMapper.readValue(is, Bundle.class);
            return Optional.of(bundle);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            return Optional.empty();
        }
    }

}
