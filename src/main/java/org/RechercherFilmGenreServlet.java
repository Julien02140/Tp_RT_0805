package org;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.core.Response;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

@WebServlet("/rechercher_film_genre")
public class RechercherFilmGenreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Log the search term
        String genre = request.getParameter("genre");
        System.out.println("Id du genre recherche : " + genre);

        List<Film> films = trouverFilmGenre(genre);

        request.setAttribute("films", films);

        RequestDispatcher dispatcher = request.getRequestDispatcher("../recherche.jsp");
        dispatcher.forward(request, response);

        
    }

    private Liste_Films lire_film_xml() {
        try {
            JAXBContext context = JAXBContext.newInstance(Liste_Films.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File file = new File("../films.xml");

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

    private List<Film> trouverFilmGenre(String genre){
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

}
