package org;

import java.io.File;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class XmlFonctions {
     public static Liste_Films lire_film_xml() {
        try {
            JAXBContext context = JAXBContext.newInstance(Liste_Films.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File file = new File("films.xml");

            Liste_Films films;

            if (file.exists()) {
                films = (Liste_Films) unmarshaller.unmarshal(file);
            } else {
                System.out.println("Erreur : le fichier films.xml n'existe pas");
                films = new Liste_Films();
            }

            return films;
        } catch (JAXBException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la lecture de films.xml");
            return new Liste_Films();
        }
    }

    public static  Film trouverFilm(String film_id){
        System.out.println("Fonction trouverFilm");
        System.out.println("id du film recherche : " + film_id);
        Liste_Films liste_films = lire_film_xml();
        List<Film> films = liste_films.getListeFilms();
        Film film_trouve = null;
        for (Film film : films) {
            System.out.println("id du film :" + film.getId());
            if (film.getId().equals(film_id) == true) {
                film_trouve = film;
                break;
            }
        }
        if (film_trouve != null) {
            System.out.println("film trouve");
            System.out.println("film trouve nom :" + film_trouve.getTitle());
            return film_trouve;
        }
        else{
            System.out.println("film non trouve");
            return null;
        }
    }

    public static List<Film> trouverFilmGenre(String genre){
        int genre_id = Integer.parseInt(genre);
    
        System.out.println("Dans la fonction trouver_film_genre");
                Liste_Films liste_films = lire_film_xml();
                List<Film> films = liste_films.getListeFilms();
                List<Film> film_correspondant = new ArrayList<>();
                // Liste_genres liste_genres = lire_genre_xml();
                // List<Genre> genres = liste_genres.getListeGenres();
        
                for (Film film : films) {
                    List<Integer> genre_film_id = film.getGenreId();
                    for(int i=0;i<genre_film_id.size();i++){
                        if (genre_film_id.get(i) == genre_id){
                            film_correspondant.add(film);
                        }
                    }
                }
        
                for(Film film1 : film_correspondant){
                    System.out.println("titre film :" + film1.getTitle());
                }
                
                return film_correspondant;
            }

    public static List<Film> trouverFilmMot(String mot){
        Liste_Films liste_films = lire_film_xml();
                List<Film> films = liste_films.getListeFilms();
                List<Film> film_correspondant = new ArrayList<>();
         
                for (Film film : films) {
                    String tilte64 = film.getTitle();
                    byte[] decodedBytes = Base64.getDecoder().decode(tilte64);
                    String title = new String(decodedBytes);
        
                    if (title.toLowerCase().startsWith(mot.toLowerCase())) {
                        film_correspondant.add(film);
                    }
                }
        
        
                System.out.println("Liste de film correspondant trouve");
                return film_correspondant;
    }

    public static Liste_utilisateurs lire_user_xml() {
        try {
            JAXBContext context = JAXBContext.newInstance(Liste_utilisateurs.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File file = new File("utilisateurs.xml");

            Liste_utilisateurs users;

            if (file.exists()) {
                users = (Liste_utilisateurs) unmarshaller.unmarshal(file);
            } else {
                System.out.println("Erreur : le fichier utilisateur.xml n'existe pas");
                users = new Liste_utilisateurs();
            }

            return users;
        } catch (JAXBException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la lecture de films.xml");
            return new Liste_utilisateurs();
        }
    }

    //pour decoder les titres et les synopsis que j'ai convertit en base64 car problème avec XML
    public static String decoder_base_64(String mot64){
        byte[] decodedBytes = Base64.getDecoder().decode(mot64);
        String mot_decode = new String(decodedBytes);
        return mot_decode;
    }
}
