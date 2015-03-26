package friendsofmine

class Utilisateur {

    String nom;
    String prenom;
    String email
    String sexe;
    Date dateNaissance;

    Utilisateur(String nom, String prenom, String email, String sexe, Date dateNaissance) {
        this.nom = nom;
        this.prenom =prenom;
        this.email = email;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
    }

    static constraints = {
        dateNaissance nullable: true
        sexe validator: {
            return it == "F" || it == "M";
        }
        nom blank: false
        prenom blank: false
        email email: true
    }
}
