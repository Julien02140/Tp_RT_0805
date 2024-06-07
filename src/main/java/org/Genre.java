package org;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Genre {
    @XmlElement
    private String id;
    @XmlElement
    private String name;

    public Genre(String id, String name){
        this.id = id;
        this.name = name;
    }

    public Genre(){

    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }
}
