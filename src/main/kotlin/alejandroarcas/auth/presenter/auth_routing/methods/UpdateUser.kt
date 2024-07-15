package alejandroarcas.auth.presenter.auth_routing.methods

import alejandroarcas.auth.data.models.User
import alejandroarcas.auth.presenter.models.UserUpdateRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.updateUser(
    updateUser: (currentUserName: String, newEmail: String, newUsername: String, newPassword: String) -> Boolean
) {
    put("update") {
        val updateParams = call.receive<UserUpdateRequest>()

        val oldUserName = updateParams.oldUsername.ifEmpty {
            return@put call.respond(
                message = "Missing old username",
                status = HttpStatusCode.BadRequest
            )
        }
        val newEmail = updateParams.newEmail.ifEmpty {
            return@put call.respond(
                message = "Missing new email",
                status = HttpStatusCode.BadRequest
            )
        }
        val newUserName = updateParams.newUsername.ifEmpty {
            return@put call.respond(
                message = "Missing new username",
                status = HttpStatusCode.BadRequest
            )
        }
        val newPassword = updateParams.newPassword.ifEmpty {
            return@put call.respond(
                message = "Missing new password",
                status = HttpStatusCode.BadRequest
            )
        }

        if (!updateUser.invoke(oldUserName, newEmail, newUserName, newPassword)) {
            return@put call.respond(
                message = "User not found",
                status = HttpStatusCode.NotFound
            )
        }
        call.respond(message = "User updated correctly", status = HttpStatusCode.OK)
    }
}