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
import java.util.List;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.XmlFonctions;

import jakarta.servlet.http.HttpServlet;

@WebServlet("/supprimer_film")
public class SupprimerFilmVideothequeServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("fonction supprimer film videotheque");
        String filmId = request.getParameter("film_id");

        String username = " ";

        //récupérer la session de l'utilisateur
        HttpSession session = request.getSession(false);
        if (session != null) {
            username = (String) session.getAttribute("username");
            Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        }

        Liste_utilisateurs users_objet = XmlFonctions.lire_user_xml();
        List<Utilisateur> users = users_objet.getListeUtilisateurs();
        List<Film> videotheque = new ArrayList<Film>();


        for (Utilisateur user : users) {
            System.out.println("dans la boucle : pseudo user :" + user.getPseudo());
            System.out.println("valeur username :" + username);
            if (user.getPseudo().equals(username)){
                System.out.println("user pseudo :" + user.getPseudo() + "mdp : " + user.getPassword());
                System.out.println("Films " + user.getVideotheque());
                for (Film film : user.getVideotheque()){
                    System.out.println("Film : " + film.getTitle());
                }

                user.removeVideotheque(filmId);
                break;
            }
        }

        // Rediriger vers VideothequeServlet
        response.sendRedirect(request.getContextPath() + "/rest/videotheque");
        
    }
    
}
