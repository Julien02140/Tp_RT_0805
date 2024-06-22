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
import java.util.List;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

@WebServlet("/description_film")
public class DescriptionFilmServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Log the function call and the film_id parameter
        System.out.println("Function description film");
        String filmId = request.getParameter("film_id");
        System.out.println("id du film recherche : " + filmId);
        Film film = trouverFilm(filmId);

        request.setAttribute("film", film);


        RequestDispatcher dispatcher = request.getRequestDispatcher("../description_film.jsp");
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

    private Film trouverFilm(String film_id){
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

}
