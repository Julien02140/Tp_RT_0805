package org;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class Personne{
    @XmlElement
    private String name;
    @XmlElement
    private String firstname;
    @XmlElement
    private int age;


    public Personne(String name, String firstname, int age){
        this.name = name;
        this.firstname = firstname;
        this.age = age;
    }

    public Personne(){
        
    }

    public String getName(){
        return name;
    }

    public String getFirstName(){
        return firstname;
    }

    public int getAge(){
        return age;
    }
}

