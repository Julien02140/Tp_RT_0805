package org;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "listeFilms")
public class Liste_Films {
    @XmlElement(name = "film")
    private List<Film> films;

    public Liste_Films(List<Film> films){
        this.films = films;
    }

    public Liste_Films(){
        films = new ArrayList<>();
    }

    public List<Film> getListeFilms(){
        return films;
    }

    public void setFilms(List<Film> films){
        this.films = films;
    }

    
}
