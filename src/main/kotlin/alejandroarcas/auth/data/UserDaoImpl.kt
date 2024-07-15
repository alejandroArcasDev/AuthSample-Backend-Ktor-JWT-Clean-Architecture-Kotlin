package alejandroarcas.auth.data

import alejandroarcas.auth.data.models.User
import alejandroarcas.auth.data.models.Users
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction

class UserDaoImpl : UserDao {

    override fun addUser(newEmail: String, newUserName: String, newPassword: String): Unit = transaction {
        User.new {
            email = newEmail
            username = newUserName
            password = newPassword
        }
    }

    override fun updateUser(oldUsername: String, newEmail: String, newUserName: String, newPassword: String): Boolean = transaction {
        val userToUpdate = getUserByUsername(oldUsername)
        if (userToUpdate != null) {
            userToUpdate.email = newEmail
            userToUpdate.username = newUserName
            userToUpdate.password = newPassword
            true
        } else {
            false
        }
    }

    override fun deleteUser(userName: String, password: String): Boolean {
        return transaction {
            val userToDelete = getUserByUsername(userName)
            userToDelete?.delete() ?: return@transaction false
            true
        }
    }

    override fun getUserByUsername(username: String): User? = transaction {
        User.find { Users.username eq username }.singleOrNull()
    }

    override fun getUserByUsernameAndPassword(username: String, password: String): User?  = transaction {
        User.find { Users.username eq username and (Users.password eq password) }.singleOrNull()
    }

}