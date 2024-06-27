package org;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Film {
    @XmlElement
    private String title;
    @XmlElement
    private String release_date;
    @XmlElement
    private String moyenne;
    @XmlElement
    private String nb_vote;
    @XmlElement
    private String id;
    @XmlElement
    private String overview;
    @XmlElement
    private String poster_path;
    @XmlElement
    private String backdrop_path;
    
    @XmlElementWrapper(name = "genre_ids")
    @XmlElement(name = "genre_id")
    private List<Integer> genre_id;
    
    


    public Film(String title, String release_date, String moyenne, String nb_vote, String id, String overview, String poster_path,String backdrop_path,List<Integer> genre_id){
        this.title = title;
        this.release_date = release_date;
        this.moyenne = moyenne;
        this.nb_vote = nb_vote;
        this.id = id;
        this.overview = overview;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.genre_id = genre_id;
    }

    public Film(){

    }

    public List<Integer> getGenreId() {
        return genre_id;
    }

    public List<Integer> getGenre(){
        return genre_id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitre(String titre_decode){
        this.title = titre_decode;
    }

    public void setSynopsis(String synopsis_decode){
        this.overview = synopsis_decode;
    }

    public String getReleaseDate(){
        return release_date;
    }

    public String getMoyenne(){
        return moyenne;
    }

    public String getNbVote(){
        return nb_vote;
    }

    public String getId(){
        return id;
    }

    public String getOverview(){
        return overview;
    }

    public String getPosterPath(){
        return poster_path;
    }

    public String getBackdropPath(){
        return  backdrop_path;
    }
}