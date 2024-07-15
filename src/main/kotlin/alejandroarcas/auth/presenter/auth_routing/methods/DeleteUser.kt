package alejandroarcas.auth.presenter.auth_routing.methods

import alejandroarcas.auth.presenter.models.UserLoginParams
import alejandroarcas.auth.presenter.models.UserRegisterParams
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.deleteUser(
    deleteUser: (username: String, password: String) -> Boolean
) {
    delete("delete") {
        val user = call.receive<UserLoginParams>()

        val userName = user.username.ifEmpty {
            return@delete call.respond(
                message = "Missing username",
                status = HttpStatusCode.BadRequest
            )
        }
        val password = user.password.ifEmpty {
            return@delete call.respond(
                message = "Missing password",
                status = HttpStatusCode.BadRequest
            )
        }
        if (!deleteUser.invoke(userName, password)) return@delete call.respond(
            message = "User can not be delete",
            status = HttpStatusCode.NotFound
        )
        call.respond(
            message = "User has been deleted",
            status = HttpStatusCode.OK
        )
    }
}