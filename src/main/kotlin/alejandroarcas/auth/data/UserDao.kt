package alejandroarcas.auth.data

import alejandroarcas.auth.data.models.User

interface UserDao {

    fun addUser(newEmail: String, newUserName: String, newPassword: String)

    fun updateUser(oldUsername: String, newEmail: String, newUserName: String, newPassword: String): Boolean

    fun deleteUser(userName: String, password: String): Boolean

    fun getUserByUsername(username: String): User?

    fun getUserByUsernameAndPassword(username: String, password: String): User?

}