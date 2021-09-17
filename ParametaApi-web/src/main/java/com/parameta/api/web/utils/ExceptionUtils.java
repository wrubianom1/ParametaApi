package com.parameta.api.web.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parameta.api.exception.ParametaAppException;
import com.parameta.api.web.dto.ErrorMessageWebDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;

@Service
@Slf4j
public final class ExceptionUtils {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${spring.profiles.active}")
    private String profile;


    public String extractErrorException(ParametaAppException ex, String URI) {
        ErrorMessageWebDTO errorMessage = new ErrorMessageWebDTO();
        errorMessage.setCode(ex.getErrorCodeEx());
        errorMessage.setMessageCode(ex.getMessageCodeEx());
        errorMessage.setMessage(ex.getMessage());
        errorMessage.setLink(URI);
        errorMessage.setHttpStatus(HttpStatus.resolve(ex.getHttpStatusEx()));
        StringWriter errorStackTrace = new StringWriter();
        if ("dev".equals(this.profile)) ex.printStackTrace(new PrintWriter(errorStackTrace));
        try {
            return this.objectMapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            log.error("{}", e);
        }
        return null;
    }
}
