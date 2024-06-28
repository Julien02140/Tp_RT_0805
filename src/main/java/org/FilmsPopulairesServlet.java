package org;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class FilmsPopulairesServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Liste_Films liste_films = XmlFonctions.lire_film_xml();
        List<Film> films = liste_films.getListeFilms();
        List<Film> filmsPopulaires = films.subList(0, Math.min(20, films.size()));

        HttpSession session = request.getSession(false);
        if (session != null) {
            String username = (String) session.getAttribute("username");
            Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
            request.setAttribute("username", username);
            request.setAttribute("isAdmin", isAdmin);
    
        }

        for (Film film: filmsPopulaires){
            String title64 = film.getTitle();
            String title_decode = XmlFonctions.decoder_base_64(title64);
            System.out.println("titre decode" + title_decode);
            film.setTitre(title_decode);
        }

        System.out.println("servlet films populaires");
        System.out.println("film 1 : " + filmsPopulaires.get(0).getTitle());

        
        System.out.println("servlet films populaires");
        System.out.println("film 20 : " + filmsPopulaires.get(19).getTitle());

        request.setAttribute("filmsPopulaires", filmsPopulaires);

        // Forwarder la requête à home.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("../home.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Liste_Films liste_films = XmlFonctions.lire_film_xml();
        List<Film> films = liste_films.getListeFilms();
        List<Film> filmsPopulaires = films.subList(0, Math.min(20, films.size()));

        HttpSession session = request.getSession(false);
        if (session != null) {
            String username = (String) session.getAttribute("username");
            Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
            request.setAttribute("username", username);
            request.setAttribute("isAdmin", isAdmin);
    
        }

        for (Film film: filmsPopulaires){
            String title64 = film.getTitle();
            String title_decode = XmlFonctions.decoder_base_64(title64);
            System.out.println("titre decode" + title_decode);
            film.setTitre(title_decode);
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
}
