package org;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "listeUtilisateurs")
public class Liste_utilisateurs {
    @XmlElement(name = "utilisateur")
    private List<Utilisateur> utilisateurs;

    public Liste_utilisateurs(List<Utilisateur> utilisateurs){
        this.utilisateurs = utilisateurs;
    }

    public Liste_utilisateurs(){
        utilisateurs = new ArrayList<>();
    }
    
    public List<Utilisateur> getListeUtilisateurs(){
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

}
