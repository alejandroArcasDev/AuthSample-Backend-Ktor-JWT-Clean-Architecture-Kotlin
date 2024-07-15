package alejandroarcas.auth.presenter.auth_routing.methods

import alejandroarcas.auth.presenter.models.UserRegisterParams
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.registerUser(
    registerUser: (newEmail: String, username: String, password: String) -> Boolean
) {
    post("register") {
        val user = call.receive<UserRegisterParams>()


        val newEmail = user.email.ifEmpty {
            return@post call.respond(
                message = "Missing email",
                status = HttpStatusCode.BadRequest
            )
        }
        val userName = user.username.ifEmpty {
            return@post call.respond(
                message = "Missing username",
                status = HttpStatusCode.BadRequest
            )
        }
        val password = user.password.ifEmpty {
            return@post call.respond(
                message = "Missing password",
                status = HttpStatusCode.BadRequest
            )
        }
        if (registerUser.invoke(newEmail, userName, password)) return@post call.respond(
            message = true,
            status = HttpStatusCode.BadRequest
        )
        call.respond(
            message = false,
            status = HttpStatusCode.Created
        )
    }
}