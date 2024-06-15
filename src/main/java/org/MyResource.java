package org;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.JAXBException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import jakarta.ws.rs.core.Context;


@Path("/")
public class MyResource {
    @Path("test")
    @GET
	@Produces({ MediaType.APPLICATION_JSON })
    public Personne Personne(@QueryParam("name") String name, @QueryParam("firstname") String firstname, @QueryParam("age") int age){
        return new Personne(name,firstname,age);
    }

    @Path("register")
    @POST
	@Produces({ MediaType.APPLICATION_JSON })
    public Response RegisterUser(@FormParam("lastname") String name,@FormParam("firstname") String  firstname,@FormParam("age") int age,@FormParam("pseudo") String pseudo, @FormParam("email") String email, @FormParam("password") String password){
        
        Utilisateur utilisateur = new Utilisateur(name,firstname,age,pseudo,email,password);
        utilisateur.delete();
        // System.out.println(utilisateur.getName());
        // utilisateur.register();
        UriBuilder uriBuilder = UriBuilder.fromPath("../login.html");
        return Response.seeOther(uriBuilder.build()).build();
    }

    @Path("verif_login")
    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    public Response verif_login(@FormParam("username") String pseudo, @FormParam("password") String password, @Context HttpServletRequest request){
        System.out.println("name : " + pseudo + "password : " + password);
        //ouvrir le fichier utilisateurs.xml et vérifier si le nom et le password correspond
            try {
                File file = new File("utilisateurs.xml");
                if (!file.exists()) {
                    System.out.println("Le fichier utilisateurs.xml n'existe pas, retour sur la index.");
                    UriBuilder uriBuilder = UriBuilder.fromPath("../index.html");
                    return Response.seeOther(uriBuilder.build()).build();
                }

                JAXBContext context = JAXBContext.newInstance(Liste_utilisateurs.class);

                Unmarshaller unmarshaller = context.createUnmarshaller();

                Liste_utilisateurs liste_utilisateurs = (Liste_utilisateurs) unmarshaller.unmarshal(file);
                List<Utilisateur> utilisateurs = liste_utilisateurs.getListeUtilisateurs();

                for (int i = 0; i < utilisateurs.size(); i++) {
                    Utilisateur user = utilisateurs.get(i);
                    if(user.getPseudo().equals(pseudo) && user.getPassword().equals(password)){
                        System.out.println("Utilisateur trouvé et bon mot de passe");
                        //On commence une sesssion
                        HttpSession session = request.getSession(true);
                        session.setAttribute("username", pseudo);
                        session.setAttribute("isAdmin", user.isAdmin());

                        UriBuilder uriBuilder = UriBuilder.fromPath("../home.html");
                        return Response.seeOther(uriBuilder.build()).build();
                    }
                }

                System.out.println("Username ou mot de passe incorrect");
                UriBuilder uriBuilder = UriBuilder.fromPath("../index.html");
                return Response.seeOther(uriBuilder.build()).build();

            } catch (JAXBException e) {
                e.printStackTrace();
                System.out.println("Erreur lors de la vérification");
                UriBuilder uriBuilder = UriBuilder.fromPath("../index.html");
                return Response.seeOther(uriBuilder.build()).build();
            }
    }

    public Liste_Films lire_film_xml(){
        try {
            JAXBContext context = JAXBContext.newInstance(Liste_Films.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            File file = new File("../films.xml");

            Liste_Films films;

            if (file.exists()) {
                films = (Liste_Films) unmarshaller.unmarshal(file);
            } else {
                System.out.println("erreur le fichier n'existe pas");
                films = new Liste_Films();
            }

            return films;
        }catch (JAXBException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'ajout du film");
            Liste_Films films = new Liste_Films();
            return films;
        }
    }

    @GET
    @Path("films_populaires")
    @Produces(MediaType.APPLICATION_JSON)
    public Response films_populaires(){
        Liste_Films liste_films = lire_film_xml();
        List<Film> films = liste_films.getListeFilms();
        List<Film> filmsPopulaires = films.subList(0, Math.min(20, films.size()));
        System.out.println("film 1 :"  + filmsPopulaires.get(0).getTitle());
        System.out.println("https://image.tmdb.org/t/p/w342"  + filmsPopulaires.get(0).getPosterPath());
        return Response.ok(filmsPopulaires).build();
    }

    @GET
    @Path("username")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verifSession(@Context HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) {
            String username = (String) session.getAttribute("username");
            Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
            if (username != null) {
                return Response.ok("{\"username\": \"" + username + "\", \"isAdmin\": " + isAdmin + "}").build();
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @GET
    @Path("description_film")
    @Produces(MediaType.APPLICATION_JSON)
    public Response descriptioinFilm(@QueryParam("film_id") String film_id, @Context HttpServletRequest request){
        System.out.println("Fontion description film");
        System.out.println("id du film recherche : " + film_id);
        UriBuilder uriBuilder = UriBuilder.fromPath("../description_film.html").queryParam("id",film_id);
        return Response.seeOther(uriBuilder.build()).build();
    }

    @GET
    @Path("trouver_film")
    @Produces(MediaType.APPLICATION_JSON)
    public Response trouverFilm(@QueryParam("film_id") String film_id, @Context HttpServletRequest request){
        System.out.println("Fonction trouver_film");
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
            return Response.ok(film_trouve).build();
        }
        else{
            System.out.println("film non trouve");
            return Response.status(Response.Status.NOT_FOUND).entity("Film not found").build();
        }
    }

}




