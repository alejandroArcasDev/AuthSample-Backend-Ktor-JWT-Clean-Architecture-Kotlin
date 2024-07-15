package alejandroarcas.auth.domain.usecases

import alejandroarcas.auth.domain.repository.AuthRepository

class UpdateUser(
    private val authRepository: AuthRepository
) {
    operator fun invoke(currentUsername: String, newEmail: String, newUsername: String, newPassword: String): Boolean {
        val exist = authRepository.getUserByUsername(username = currentUsername)
        if (exist != null) {
            authRepository.updateUser(currentUsername, newEmail, newUsername, newPassword)
            return true
        }
        return false
    }
}