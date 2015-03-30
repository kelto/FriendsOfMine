package friendsofmine

import grails.transaction.Transactional

@Transactional
class ActiviteService {

    def serviceMethod() {

    }

    Activite insertOrUpdateActiviteForResponsable(Activite activite, Utilisateur utilisateur) {
        utilisateur.addToActivites(activite);
        utilisateur.save();
        activite.save();
        return activite;
    }

    def deleteActivite(Activite activite) {
        def responsable = activite.responsable;
        responsable.removeFromActivites(activite);
        activite.delete();
        responsable.save();
    }
}
