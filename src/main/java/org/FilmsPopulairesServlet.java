package org;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.util.List;
import java.util.ArrayList;


@WebServlet("/films_populaires")
public class FilmsPopulairesServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Liste_Films liste_films = lire_film_xml();
        List<Film> films = liste_films.getListeFilms();
        List<Film> filmsPopulaires = films.subList(0, Math.min(20, films.size()));

        HttpSession session = request.getSession(false); // Use false to avoid creating a new session if one does not exist
        if (session != null) {
            String username = (String) session.getAttribute("username");
            Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
            request.setAttribute("username", username);
            request.setAttribute("isAdmin", isAdmin);
    
        }


        request.setAttribute("filmsPopulaires", filmsPopulaires);


        System.out.println("servlet films populaires");
        System.out.println("film 1 : " + filmsPopulaires.get(0).getTitle());

        
        System.out.println("servlet films populaires");
        System.out.println("film 20 : " + filmsPopulaires.get(19).getTitle());

        // Forwarder la requête à home.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("../home.jsp");
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
}
