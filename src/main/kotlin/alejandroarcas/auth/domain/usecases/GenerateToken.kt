package alejandroarcas.auth.domain.usecases

import alejandroarcas.auth.domain.repository.AuthRepository

class GenerateToken(
    private val authRepository: AuthRepository
) {
    operator fun invoke(username: String): String {
        return authRepository.generateToken(username)
    }
}