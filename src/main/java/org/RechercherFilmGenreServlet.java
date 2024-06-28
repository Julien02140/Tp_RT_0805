package org;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class RechercherFilmGenreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String genre = request.getParameter("genre");
        System.out.println("Id du genre recherche : " + genre);

        List<Film> films = XmlFonctions.trouverFilmGenre(genre);

        for (Film film: films){
            String title64 = film.getTitle();
            String title_decode = XmlFonctions.decoder_base_64(title64);
            System.out.println("titre decode" + title_decode);
            film.setTitre(title_decode);
        }

        request.setAttribute("films", films);

        RequestDispatcher dispatcher = request.getRequestDispatcher("../recherche.jsp");
        dispatcher.forward(request, response);

        
    }

}
