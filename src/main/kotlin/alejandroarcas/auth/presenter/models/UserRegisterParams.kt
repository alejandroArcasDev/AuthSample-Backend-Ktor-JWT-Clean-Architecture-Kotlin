package alejandroarcas.auth.presenter.models

import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterParams(
    val email: String,
    val username: String,
    val password: String
)
