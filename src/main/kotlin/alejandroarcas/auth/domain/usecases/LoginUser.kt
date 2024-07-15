package alejandroarcas.auth.domain.usecases

import alejandroarcas.auth.domain.repository.AuthRepository

class LoginUser(
    private val authRepository: AuthRepository
) {
    operator fun invoke(username: String, password: String): String? {
        val validUser = authRepository.checkUserCredentials(username, password)
        return if (validUser) {
            authRepository.generateToken(username)
        } else null
    }
}