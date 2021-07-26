package org.acme;

import org.apache.camel.builder.RouteBuilder;

public class Routes extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        
        // Invokes a simple greeting endpoint every 10 seconds
        from("kafka:gcp-result?brokers={{kafka.brokers}}&groupId=gcpreader")
            .filter().jsonpath("$[?(@.platform == 'gcp')]" )
            .to("json-validator:gcpschema.json")
            .log("SENT -> ${body}")
            .to("kafka:gcp-result")
           ;
           
      

    }
}