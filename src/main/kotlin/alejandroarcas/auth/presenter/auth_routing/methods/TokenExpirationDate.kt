package alejandroarcas.auth.presenter.auth_routing.methods

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.tokenExpirationDate(
    tokenExpirationDate: (credential: JWTPrincipal) -> Long?
) {
    get("expiration-date") {
        val principal = call.principal<JWTPrincipal>()

        if (principal != null) {
            val expirationDate = tokenExpirationDate.invoke(principal)
            if (expirationDate != null) {
                call.respond(
                    HttpStatusCode.OK,
                    message = "Token is expired at $expirationDate seconds")
            } else {
                call.respond(
                    status = HttpStatusCode.Unauthorized,
                    message = "Expiration date is invalid"
                )
            }
        } else {
            call.respond(
                status = HttpStatusCode.BadRequest,
                message = "Missing JWT Principal"
            )
        }
    }
}