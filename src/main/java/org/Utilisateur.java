package org;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Utilisateur extends Personne implements Serializable{
    @XmlElement
    private String pseudo;
    @XmlElement
    private String email;
    @XmlElement
    private String password;

    @XmlElementWrapper(name = "videotheque")
    @XmlElement(name = "film")
    private List<Film> videotheque;

    public Utilisateur(String name, String firstname, int age,String pseudo ,String email, String password, List<Film> videotheque){
        super(name,firstname,age);
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
        this.videotheque = videotheque;
    }

    public Utilisateur(){
        super();
        this.videotheque = new ArrayList<Film>();
    }

    public String getPseudo(){
        return pseudo;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public List<Film> getVideotheque(){
        return videotheque;
    }

    public boolean isAdmin(){
        if(this.pseudo.equals("julien") && this.password.equals("juju02140")){
            return true;
        }
        else{
            return false;
        }
    }

    public void addVideotheque(Film film){
        this.videotheque.add(film);
        return;
    }

    public void removeVideotheque(String id){
        this.videotheque.removeIf(f -> f.getId().equals(id));
        this.register();
    }

    public void register(){
        try {
                    JAXBContext context = JAXBContext.newInstance(Liste_utilisateurs.class);

                    Unmarshaller unmarshaller = context.createUnmarshaller();
                    File file = new File("utilisateurs.xml");
                    Liste_utilisateurs utilisateurs;

                    if (file.exists()) {
                        utilisateurs = (Liste_utilisateurs) unmarshaller.unmarshal(file);
                    } else {
                        utilisateurs = new Liste_utilisateurs();
                    }

                    //enlever l'utilisateur si il est déja présent
                    utilisateurs.getListeUtilisateurs().removeIf(user -> user.pseudo.equals(this.pseudo));

                    utilisateurs.getListeUtilisateurs().add(this);

                    Marshaller marshaller = context.createMarshaller();
                    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                    marshaller.marshal(utilisateurs, new FileWriter(file));

                    System.out.println("Utilisateur enregistré");
                } catch (JAXBException | IOException e) {
                    e.printStackTrace();
                    System.out.println("Erreur lors de l'enregistrement de l'utilisateur");
                }
            }

    public void delete(){
        try {
                    File file = new File("utilisateurs.xml");
                    if (!file.exists()) {
                        System.out.println("Le fichier utilisateurs.xml n'existe pas.");
                        return;
                    }

                    JAXBContext context = JAXBContext.newInstance(Liste_utilisateurs.class);

                    Unmarshaller unmarshaller = context.createUnmarshaller();

                    Liste_utilisateurs liste_utilisateurs = (Liste_utilisateurs) unmarshaller.unmarshal(file);
                    List<Utilisateur> utilisateurs = liste_utilisateurs.getListeUtilisateurs();

                    for (int i = 0; i < utilisateurs.size(); i++) {
                        Utilisateur user = utilisateurs.get(i);
                        if(user.getEmail().equals(this.getEmail())){
                            utilisateurs.remove(i);
                        }
                    }

                    Marshaller marshaller = context.createMarshaller();
                    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                    marshaller.marshal(liste_utilisateurs, new FileWriter(file));

                    System.out.println("Utilisateur supprimé");
                } catch (JAXBException | IOException e) {
                    e.printStackTrace();
                    System.out.println("Erreur lors de la suppression de l'utilisateur");
                }
    }
}