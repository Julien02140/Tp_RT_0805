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

@WebServlet("/page_admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Fonction pour aller vers la page Admin");

        String username = " ";
        Boolean isAdmin = false;

        //récupérer la session de l'utilisateur
        HttpSession session = request.getSession(false);
        if (session != null) {
            username = (String) session.getAttribute("username");
            isAdmin = (Boolean) session.getAttribute("isAdmin");
        }

        if (isAdmin == false){
            System.out.println("pas admin");
            response.sendRedirect("index.html");
        }
        else{
            //Lire utilisateurs.xml
            Liste_utilisateurs users_objet = XmlFonctions.lire_user_xml();
            List<Utilisateur> users = users_objet.getListeUtilisateurs();
            List<Utilisateur> user_finaux = new ArrayList<Utilisateur>();

            //trouver l'admin et l'enlever de la liste, la page admin sert à supprimmer les utilisateurs et on ne veut pas
            //supprimé l'admin par accident
            for (Utilisateur user : users){
                if(! user.getPseudo().equals(username)){
                    user_finaux.add(user);
                }
            }

            request.setAttribute("users", user_finaux);


            RequestDispatcher dispatcher = request.getRequestDispatcher("page_admin.jsp");
            dispatcher.forward(request, response);



        }


    }

}
