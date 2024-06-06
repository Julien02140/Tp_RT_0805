package org;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


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
        Utilisateur test = new Utilisateur();
        System.out.println(utilisateur.getName());
        utilisateur.register();
        System.out.println("utilisateur creer : nom : " + utilisateur.getName() + "prenom : "+ utilisateur.getFirstName() + "age " + utilisateur.getAge() + "pseudo : " + utilisateur.getPseudo() + "email " + utilisateur.getEmail() + "password : " + utilisateur.getPassword());
        return Response.status(Response.Status.CREATED).entity(utilisateur).build();
    }
    
}




