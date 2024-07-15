package alejandroarcas.auth.di

import alejandroarcas.auth.data.UserDao
import alejandroarcas.auth.data.UserDaoImpl
import alejandroarcas.auth.data.models.User
import alejandroarcas.auth.domain.repository.AuthRepository
import alejandroarcas.auth.domain.repository.AuthRepositoryImpl
import alejandroarcas.auth.domain.usecases.*
import org.koin.dsl.module

val authModule = module {
    // Dependencies
    single<UserDao> { UserDaoImpl() }
    single<AuthRepository> { AuthRepositoryImpl(get()) }


    // Use cases auth
    single { RegisterUser(get()) }
    single { LoginUser(get()) }
    single { DeleteUser(get()) }
    single { UpdateUser(get()) }
    single { GenerateToken(get()) }
    single { TokenExpireDate(get()) }

}