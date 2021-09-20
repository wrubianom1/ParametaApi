package com.parameta.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ClientWsConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.parameta.api.client");
        return marshaller;
    }

    @Bean
    public SOAPAdapterConnector soapConnector(Jaxb2Marshaller marshaller) {
        SOAPAdapterConnector client = new SOAPAdapterConnector();
        client.setDefaultUri("http://localhost:8078/ws/");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}