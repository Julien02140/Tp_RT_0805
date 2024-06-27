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
import org.XmlFonctions;

@WebServlet("/description_film")
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
