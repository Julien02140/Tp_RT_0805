package org;

public class Acteur extends Personne{
    private Film[] films;

    public Acteur(String name, String firstname, int age, Film[] films){
        super(name,firstname,age);
        this.films = films;
    }

    public Film[] getFilms(){
        return films;
    }

}

