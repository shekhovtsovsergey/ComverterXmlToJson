package com.shekhovtsov.comverterxmltojson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class Converter {

    public void convertXmlToJson() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Выберите XML-файл");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int userSelection = fileChooser.showOpenDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File xmlFile = fileChooser.getSelectedFile();
            String xmlFilePath = xmlFile.getAbsolutePath();
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(ProductsList.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                ProductsList productList = (ProductsList) jaxbUnmarshaller.unmarshal(xmlFile);
                String jsonFolderPath = Paths.get(xmlFilePath).getParent().toString();
                String jsonFileName = Paths.get(xmlFilePath).getFileName().toString().replace(".xml", ".json");
                Path jsonFilePath = Paths.get(jsonFolderPath, jsonFileName);
                FileWriter writer = new FileWriter(jsonFilePath.toFile());
                writer.write(convertProductsListToJson(productList));
                writer.close();
            } catch (JAXBException | IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String convertProductsListToJson(ProductsList productList) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(productList.getProducts());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
