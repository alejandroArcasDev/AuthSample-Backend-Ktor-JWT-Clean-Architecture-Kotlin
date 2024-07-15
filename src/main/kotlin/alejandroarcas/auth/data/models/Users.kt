package alejandroarcas.auth.data.models

import org.jetbrains.exposed.dao.id.IntIdTable

object Users: IntIdTable()  {
    var email = varchar("email", 500)
    var username = varchar("username", 500).uniqueIndex()
    var password = varchar("password", 500)
}