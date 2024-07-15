package alejandroarcas.auth.domain.repository

import alejandroarcas.auth.data.models.User
import io.ktor.server.auth.jwt.*

interface AuthRepository {

    fun checkIfUserExists(username: String): Boolean

    fun addNewUser(newEmail: String, newUserName: String, newPassword: String)

    fun deleteUser(username: String, password: String): Boolean

    fun updateUser(currentUsername: String, newEmail: String, newUsername: String, newPassword: String): Boolean

    fun checkUserCredentials(username: String, password: String): Boolean

    fun getUserByUsernameAndPassword(username: String, password: String): User?

    fun getUserByUsername(username: String): User?

    // JWT Methods

    fun generateToken(username: String): String

    fun getExpireDate(credentials: JWTPrincipal): Long?

}