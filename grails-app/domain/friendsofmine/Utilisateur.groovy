package friendsofmine

class Utilisateur {

    String nom;
    String prenom;
    String email
    String sexe;
    Date dateNaissance;

    static constraints = {
        dateNaissance nullable: true
        sexe inList: ["F", "M"]
        nom blank: false
        prenom blank: false
        email email: true
    }

    static hasMany = [
            activites: Activite
    ]
}
