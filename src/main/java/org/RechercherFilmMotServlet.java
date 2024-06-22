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

@WebServlet("/rechercher_film_mot")
public class RechercherFilmMotServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Log the search term
        String mot = request.getParameter("mot");
        System.out.println("mot recherche : " + mot);

        List<Film> films = trouverFilmMot(mot);

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

    private List<Film> trouverFilmMot(String mot){
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

}
