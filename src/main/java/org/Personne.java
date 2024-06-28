package org;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;


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

