package alejandroarcas.auth.presenter.auth_routing

import alejandroarcas.auth.domain.usecases.*
import alejandroarcas.auth.presenter.auth_routing.methods.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.authRouting() {

    val registerUser by inject<RegisterUser>()
    val loginUser by inject<LoginUser>()
    val deleteUser by inject<DeleteUser>()
    val updateUser by inject<UpdateUser>()
    val tokenExpireDate by inject<TokenExpireDate>()

    route("auth") {
        registerUser { email, username, password -> registerUser(email, username, password) }
        loginUser { username, password -> loginUser(username, password) }
        deleteUser { username, password -> deleteUser(username, password) }
        updateUser { oldUsername, email, newUsername, newPassword -> updateUser(oldUsername, email, newUsername, newPassword )}
        tokenExpirationDate { credential -> tokenExpireDate(credential) }
    }

}