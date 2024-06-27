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
import org.XmlFonctions;
import org.XmlFonctions;

@WebServlet("/rechercher_film_genre")
public class RechercherFilmGenreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Log the search term
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
