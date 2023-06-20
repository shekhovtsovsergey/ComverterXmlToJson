package com.shekhovtsov.comverterxmltojson;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "product")
public class Product {

    private String name;
    private double price;
    public String getName() {
        return name;
    }
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    @XmlElement
    public void setPrice(double price) {
        this.price = price;
    }

}
