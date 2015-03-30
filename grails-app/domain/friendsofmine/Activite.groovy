package friendsofmine



class Activite {
    String titre;
    String descriptif;
    Utilisateur responsable;

    static constraints = {
        titre blank: false, nullable: false
        descriptif nullable: true

    }

    static belongsTo = [responsable: Utilisateur]
}