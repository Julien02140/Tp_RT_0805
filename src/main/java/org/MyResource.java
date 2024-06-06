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

                        UriBuilder uriBuilder = UriBuilder.fromPath("../base_site.html");
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
}




