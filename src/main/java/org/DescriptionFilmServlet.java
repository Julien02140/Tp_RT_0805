package org;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DescriptionFilmServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Fonction description film");
        String filmId = request.getParameter("film_id");
        System.out.println("id du film recherche : " + filmId);
        Film film = XmlFonctions.trouverFilm(filmId);

        String title64 = film.getTitle();
        String synopsis64 = film.getOverview();
        String title_decode = XmlFonctions.decoder_base_64(title64);
        String synopsis_decode = XmlFonctions.decoder_base_64(synopsis64);
        System.out.println("titre decode" + title_decode);
        System.out.println("synopsis decode" + synopsis_decode);
        film.setTitre(title_decode);
        film.setSynopsis(synopsis_decode);



        request.setAttribute("film", film);


        RequestDispatcher dispatcher = request.getRequestDispatcher("../description_film.jsp");
        dispatcher.forward(request, response);
        
    }
}
