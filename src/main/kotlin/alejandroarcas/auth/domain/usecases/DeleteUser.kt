package alejandroarcas.auth.domain.usecases

import alejandroarcas.auth.domain.repository.AuthRepository

class DeleteUser(
    private val authRepository: AuthRepository
) {
    operator fun invoke(username: String, password: String): Boolean {
        return authRepository.deleteUser(username, password)
    }
}