package alejandroarcas.auth.domain.usecases

import alejandroarcas.auth.domain.repository.AuthRepository

class RegisterUser(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String, newUsername: String, newPassword: String): Boolean {
        val existUser = authRepository.checkIfUserExists(newUsername)
        if (!existUser) authRepository.addNewUser(email, newUsername, newPassword)
        return existUser
    }
}