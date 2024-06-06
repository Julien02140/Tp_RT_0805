package org;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Utilisateur extends Personne implements Serializable{
    @XmlElement
    private String pseudo;
    @XmlElement
    private String email;
    @XmlElement
    private String password;

    public Utilisateur(String name, String firstname, int age,String pseudo ,String email, String password){
        super(name,firstname,age);
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
    }

    public Utilisateur(){
        super("Boutreaux","Julien",22);
        this.pseudo = "juju";
        this.email = "julien.bnoutreaux@orange.fr";
        this.password = "juju02140";
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

    public void register(){
        //on va enrister les données de l'utilisateur en XML
        try {
            // Créer un contexte JAXB pour la classe Utilisateur
            JAXBContext context = JAXBContext.newInstance(Utilisateur.class);

            // Créer un marshaller JAXB
            Marshaller marshaller = context.createMarshaller();

            // Formater la sortie XML
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Écrire les données de l'utilisateur dans un fichier XML
            File file = new File("utilisateurs.xml");
            FileWriter writer = new FileWriter(file);
            marshaller.marshal(this, writer);
            writer.close();
            System.out.println("fichier creer");
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
            System.out.println("erreur non creer");
        }
    }
}