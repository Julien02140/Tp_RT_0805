package org;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
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


    public Film(String title, String release_date, String moyenne, String nb_vote, String id, String overview, String poster_path,String backdrop_path){
        this.title = title;
        this.release_date = release_date;
        this.moyenne = moyenne;
        this.nb_vote = nb_vote;
        this.id = id;
        this.overview = overview;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
    }

    public Film(){

    }

    public String getTitle(){
        return title;
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