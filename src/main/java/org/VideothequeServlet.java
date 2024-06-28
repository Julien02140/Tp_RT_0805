package org;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VideothequeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("fonction videotheque");

        String username = " ";

        //récupérer la session de l'utilisateur
        HttpSession session = request.getSession(false);
        if (session != null) {
            username = (String) session.getAttribute("username");
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

                videotheque = user.getVideotheque();
                break;
            }
        }

        System.out.println("videotheque final " + videotheque);
        for (Film film : videotheque){
            System.out.println("Film : " + film.getTitle());
        }

        
        request.setAttribute("films", videotheque);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/videotheque.jsp");
        dispatcher.forward(request, response);

    }
    
}
