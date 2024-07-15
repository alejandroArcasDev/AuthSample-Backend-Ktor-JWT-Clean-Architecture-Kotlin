package alejandroarcas.auth.domain.repository

import alejandroarcas.audience
import alejandroarcas.auth.data.UserDao
import alejandroarcas.auth.data.models.User
import alejandroarcas.issuer
import alejandroarcas.secret
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.auth.jwt.*
import java.util.Date

class AuthRepositoryImpl(
    private val userDao: UserDao,
) : AuthRepository {

    // CRUD Methods

    override fun addNewUser(newEmail: String, newUserName: String, newPassword: String) {
        return userDao.addUser(newEmail, newUserName, newPassword)
    }

    override fun deleteUser(username: String, password: String): Boolean {
        return userDao.deleteUser(username, password)
    }

    override fun updateUser(currentUsername: String, newEmail: String, newUsername: String, newPassword: String): Boolean {
        return userDao.updateUser(currentUsername, newEmail, newUsername, newPassword)
    }

    override fun getUserByUsername(username: String): User? {
        return userDao.getUserByUsername(username)
    }

    override fun getUserByUsernameAndPassword(username: String, password: String): User? {
        return userDao.getUserByUsernameAndPassword(username, password)
    }

    // Other Methods

    override fun checkIfUserExists(username: String): Boolean {
        return userDao.getUserByUsername(username) != null
    }

    override fun checkUserCredentials(username: String, password: String): Boolean {
        return userDao.getUserByUsernameAndPassword(username, password) != null
    }

    override fun generateToken(username: String): String {
        return JWT
            .create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("username", username)
            .withExpiresAt(Date(System.currentTimeMillis() + 12000))
            .sign(Algorithm.HMAC256(secret))
    }

    override fun getExpireDate(credentials: JWTPrincipal): Long? {
        return credentials.expiresAt?.time?.minus(System.currentTimeMillis())
    }
}
