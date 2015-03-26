package friendsofmine



class Activite {
    String titre;
    String descriptif;

    Activite(String titre, String descriptif) {
        this.titre = titre;
        this.descriptif = descriptif;
    }
    static constraints = {
        titre blank: false, nullable: false
        descriptif nullable: true

    }
}