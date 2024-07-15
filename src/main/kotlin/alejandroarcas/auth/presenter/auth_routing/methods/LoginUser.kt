package alejandroarcas.auth.presenter.auth_routing.methods

import alejandroarcas.auth.presenter.models.UserLoginParams
import alejandroarcas.auth.presenter.models.UserRegisterParams
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.loginUser(
    loginUser: (username: String, password: String) -> String?
) {
    post("login") {
        val user = call.receive<UserLoginParams>()

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
        val token = loginUser(userName, password)
        if (token == null) {
            return@post call.respond(
                status = HttpStatusCode.NotFound,
                message = "User not found"
            )
        } else {
            call.respond(
                status = HttpStatusCode.OK,
                hashMapOf("token" to token)
            )
        }
    }
}