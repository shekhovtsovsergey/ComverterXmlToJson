package com.shekhovtsov.comverterxmltojson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ComverterXmlToJsonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComverterXmlToJsonApplication.class, args);
        System.setProperty("java.awt.headless", "false");
        Converter converter = new Converter();
        converter.convertXmlToJson();
    }

}
