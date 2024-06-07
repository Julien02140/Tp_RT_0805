package org;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "listeGenres")
public class Liste_genres {
    @XmlElement(name = "genre")
    private List<Genre> genres;

    public Liste_genres(List<Genre> genres){
        this.genres = genres;
    }

    public Liste_genres(){
        genres = new ArrayList<>();
    }

    public List<Genre> getListeGenres(){
        return genres;
    }

}
