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
        Liste_utilisateurs users_objet = lire_user_xml();
        List<Utilisateur> users = users_objet.getListeUtilisateurs();
        for (Utilisateur user : users) {
            System.out.println("dans la boucle : pseudo user :" + user.getPseudo());
            System.out.println("valeur username :" + username);
            if (user.getPseudo().equals(username)){
                System.out.println("user pseudo :" + user.getPseudo() + "mdp : " + user.getPassword());
                //trouver le film
                Film film = trouverFilm(filmId);
                user.addVideotheque(film);
                user.register();
            }
        }

        System.out.println("Film ajouté à la vidéothèque de l'utilisateur.");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("Film ajouté à la vidéothèque de l'utilisateur.");

    }

    private Liste_Films lire_film_xml() {
        try {
            JAXBContext context = JAXBContext.newInstance(Liste_Films.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File file = new File("../films.xml");

            Liste_Films films;

            if (file.exists()) {
                films = (Liste_Films) unmarshaller.unmarshal(file);
            } else {
                System.out.println("Erreur : le fichier films.xml n'existe pas");
                films = new Liste_Films();
            }

            return films;
        } catch (JAXBException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la lecture de films.xml");
            return new Liste_Films();
        }
    }

    private Liste_utilisateurs lire_user_xml() {
        try {
            JAXBContext context = JAXBContext.newInstance(Liste_utilisateurs.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File file = new File("utilisateurs.xml");

            Liste_utilisateurs users;

            if (file.exists()) {
                users = (Liste_utilisateurs) unmarshaller.unmarshal(file);
            } else {
                System.out.println("Erreur : le fichier utilisateur.xml n'existe pas");
                users = new Liste_utilisateurs();
            }

            return users;
        } catch (JAXBException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la lecture de films.xml");
            return new Liste_utilisateurs();
        }
    }

    private Film trouverFilm(String film_id){
        System.out.println("Fonction trouverFilm");
        System.out.println("id du film recherche : " + film_id);
        Liste_Films liste_films = lire_film_xml();
        List<Film> films = liste_films.getListeFilms();
        Film film_trouve = null;
        for (Film film : films) {
            System.out.println("id du film :" + film.getId());
            if (film.getId().equals(film_id) == true) {
                film_trouve = film;
                break;
            }
        }
        if (film_trouve != null) {
            System.out.println("film trouve");
            System.out.println("film trouve nom :" + film_trouve.getTitle());
            return film_trouve;
        }
        else{
            System.out.println("film non trouve");
            return null;
        }
            }
}
