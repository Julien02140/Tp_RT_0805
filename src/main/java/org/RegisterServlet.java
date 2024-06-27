package org;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("lastname");
        String firstname = request.getParameter("firstname");
        int age = Integer.parseInt(request.getParameter("age"));
        String pseudo = request.getParameter("pseudo");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        List<Film> videotheque = new ArrayList<>();
        
        Utilisateur utilisateur = new Utilisateur(name, firstname, age, pseudo, email, password,videotheque);
        System.out.println("Nom utilisateur : " + utilisateur.getName());

        //vérifier que le pseudo est différent
        Liste_utilisateurs users_objet = XmlFonctions.lire_user_xml();
        List<Utilisateur> users = users_objet.getListeUtilisateurs();
        Boolean verification = false;
        for(Utilisateur user : users){
            if(user.getPseudo().equals(utilisateur.getPseudo())){
                verification = true;
            }
        }

        if(verification == true){
            System.out.println("Pseudo déja utilisé, annulation de l'inscription");
            response.sendRedirect("../register.html");
        }
        else{
            utilisateur.register();
            response.sendRedirect("../login.html");
        }

    }
}
