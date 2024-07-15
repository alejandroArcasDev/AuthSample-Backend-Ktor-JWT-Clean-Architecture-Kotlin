package alejandroarcas.auth.presenter.models

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginParams(
    val username: String,
    val password: String
)
