package alejandroarcas.auth.presenter.models

import kotlinx.serialization.Serializable

@Serializable
data class UserUpdateRequest(
    val oldUsername: String,
    val newEmail: String,
    val newUsername: String,
    val newPassword: String
)