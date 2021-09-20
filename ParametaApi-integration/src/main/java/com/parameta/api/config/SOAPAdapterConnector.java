package com.parameta.api.config;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class SOAPAdapterConnector extends WebServiceGatewaySupport {

    public Object callWebService(String url, Object request){
        return getWebServiceTemplate().marshalSendAndReceive(url, request);
    }
}