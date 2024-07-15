package alejandroarcas.auth.domain.usecases

import alejandroarcas.auth.domain.repository.AuthRepository
import io.ktor.server.auth.jwt.*

class TokenExpireDate(
    private val authRepository: AuthRepository
) {
    operator fun invoke(credential: JWTPrincipal): Long? {
        return authRepository.getExpireDate(credential)
    }
}