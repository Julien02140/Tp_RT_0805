package org;

public class Main {
    public static void main(String[] args) {
        api_TMDB api = new api_TMDB();
        api.getFilm();
        api.get_genre_film();
    }
}
