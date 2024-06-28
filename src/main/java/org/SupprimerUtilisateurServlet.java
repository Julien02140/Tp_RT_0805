package org;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SupprimerUtilisateurServlet extends HttpServlet  {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Boolean isAdmin = false;

        //récupérer la session de l'utilisateur
        HttpSession session = request.getSession(false);
        if (session != null) {
            isAdmin = (Boolean) session.getAttribute("isAdmin");
        }

        if (isAdmin == false){
            System.out.println("pas admin");
            response.sendRedirect("index.html");
        }
        else{
            String user_pseudo = request.getParameter("user_pseudo");
            //Lire utilisateurs.xml et trouver l'utilisateur
            Liste_utilisateurs users_objet = XmlFonctions.lire_user_xml();
            List<Utilisateur> users = users_objet.getListeUtilisateurs();
            for (Utilisateur user : users) {
                if(user.getPseudo().equals(user_pseudo)){
                    user.delete();
                    break;
                }
            }

            response.sendRedirect("page_admin.jsp");

        }
    }
}
