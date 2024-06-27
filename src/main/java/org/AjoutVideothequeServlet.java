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

@WebServlet("/ajout_videotheque")
public class AjoutVideothequeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Fonction ajout videotheque");
        String filmId = request.getParameter("film_id");
        System.out.println("id du film recherche : " + filmId);

        String username = " ";

        //récupérer la session de l'utilisateur
        HttpSession session = request.getSession(false);
        if (session != null) {
            username = (String) session.getAttribute("username");
            Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        }

        //Lire utilisateurs.xml et trouver l'utilisateur
        Liste_utilisateurs users_objet = XmlFonctions.lire_user_xml();
        List<Utilisateur> users = users_objet.getListeUtilisateurs();
        for (Utilisateur user : users) {
            System.out.println("dans la boucle : pseudo user :" + user.getPseudo());
            System.out.println("valeur username :" + username);
            if (user.getPseudo().equals(username)){
                System.out.println("user pseudo :" + user.getPseudo() + "mdp : " + user.getPassword());
                //Vérifier si le film n'est pas déja dans la videotheque
                Boolean duplication = false;
                for (Film film : user.getVideotheque()){
                    if(film.getId().equals(filmId)){
                        duplication = true;
                        break;
                    }
                }

                if (duplication == true){
                    System.out.println("déja dans la vidéotheque");
                }
                else{
                    
                //trouver le film
                Film film = XmlFonctions.trouverFilm(filmId);
                user.addVideotheque(film);
                user.register();
                break;

                }
            }
        }

        response.sendRedirect(request.getContextPath() + "/rest/description_film?film_id=" + filmId);

    }

}
