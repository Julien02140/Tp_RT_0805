package org;

public class Film {
    private String title;
    private String release_date;
    private String moyenne;
    private String nb_vote;
    private int id;
    private String overview;

    public Film(String title, String release_date, String moyenne, String nb_vote, int id, String overview){
        this.title = title;
        this.release_date = release_date;
        this.moyenne = moyenne;
        this.nb_vote = nb_vote;
        this.id = id;
        this.overview = overview;
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

    public int getId(){
        return id;
    }

    public String getOverview(){
        return overview;
    }
}