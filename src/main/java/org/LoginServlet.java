package org;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.util.List;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class LoginServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pseudo = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("Pseudo : " + pseudo + " Password : " + password);

        try {
            File file = new File("utilisateurs.xml");
            if (!file.exists()) {
                System.out.println("Le fichier utilisateurs.xml n'existe pas, retour sur l'index.");
                response.sendRedirect("../index.html");
                return;
            }

            JAXBContext context = JAXBContext.newInstance(Liste_utilisateurs.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Liste_utilisateurs liste_utilisateurs = (Liste_utilisateurs) unmarshaller.unmarshal(file);
            List<Utilisateur> utilisateurs = liste_utilisateurs.getListeUtilisateurs();

            for (Utilisateur user : utilisateurs) {
                if (user.getPseudo().equals(pseudo) && user.getPassword().equals(password)) {
                    System.out.println("Utilisateur trouvé et bon mot de passe");

                    // Commencer une session
                    HttpSession session = request.getSession(true);
                    session.setAttribute("username", pseudo);
                    session.setAttribute("isAdmin", user.isAdmin());

                    System.out.println("Utilisateur est admin : " + user.isAdmin());
                    
                    // Appel à FilmsPopulairesServlet
                    request.getRequestDispatcher("/rest/films_populaires").forward(request, response);
       
                    return;
                }
            }

            System.out.println("Pseudo ou mot de passe incorrect");
            response.sendRedirect("../index.html");
        } catch (JAXBException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la vérification");
            response.sendRedirect("../index.html");
        }
    }
    
}
