package friendsofmine

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ActiviteController {

    ActiviteService activiteService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Activite.list(params), model: [activiteInstanceCount: Activite.count()]
    }

    def show(Activite activiteInstance) {
        respond activiteInstance
    }

    def create() {
        respond new Activite(params)
    }

    @Transactional
    def save(Activite activiteInstance) {
        if (activiteInstance == null) {
            notFound()
            return
        }

        if (activiteInstance.hasErrors()) {
            respond activiteInstance.errors, view: 'create'
            return
        }

        //activiteInstance.save flush: true
        print(activiteInstance.responsable)
        activiteService.insertOrUpdateActiviteForResponsable(activiteInstance, activiteInstance.responsable)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'activite.label', default: 'Activite'), activiteInstance.id])
                redirect activiteInstance
            }
            '*' { respond activiteInstance, [status: CREATED] }
        }
    }

    def edit(Activite activiteInstance) {
        respond activiteInstance
    }

    @Transactional
    def update(Activite activiteInstance) {
        if (activiteInstance == null) {
            notFound()
            return
        }

        if (activiteInstance.hasErrors()) {
            respond activiteInstance.errors, view: 'edit'
            return
        }

        //activiteInstance.save flush: true

        activiteService.insertOrUpdateActiviteForResponsable(activiteInstance, activiteInstance.responsable)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Activite.label', default: 'Activite'), activiteInstance.id])
                redirect activiteInstance
            }
            '*' { respond activiteInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Activite activiteInstance) {

        if (activiteInstance == null) {
            notFound()
            return
        }

        //activiteInstance.delete flush: true

        activiteService.deleteActivite(activiteInstance)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Activite.label', default: 'Activite'), activiteInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'activite.label', default: 'Activite'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

}
