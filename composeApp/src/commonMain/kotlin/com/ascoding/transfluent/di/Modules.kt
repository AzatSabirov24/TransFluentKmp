package com.ascoding.transfluent.di

import com.ascoding.transfluent.features.authentication.data.FirebaseAuthManager
import com.ascoding.transfluent.features.authentication.data.GoogleAuthManager
import com.ascoding.transfluent.features.authentication.domain.AuthManager
import com.ascoding.transfluent.features.authentication.domain.EmailValidator
import com.ascoding.transfluent.features.authentication.domain.PasswordValidator
import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthActionReducer
import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthManagerFactory
import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthManagerFactoryImpl
import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthViewModel
import com.ascoding.transfluent.features.profile.ProfileViewModel
import com.ascoding.transfluent.features.profile.reducer.ProfileActionReducer
import com.ascoding.transfluent.features.profile.reducer.ProfileEventReducer
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val sharedModule = module {

    // auth
    factory<AuthManager>(named("firebase")) { FirebaseAuthManager() }
    factory<AuthManager>(named("google")) { GoogleAuthManager() }
    factory<AuthManagerFactory> {
        AuthManagerFactoryImpl(
            firebaseAuthManager = get(named("firebase")),
            googleAuthManager = get(named("google"))
        )
    }
    factoryOf(::EmailValidator)
    factoryOf(::PasswordValidator)
    factoryOf(::AuthActionReducer)
    viewModelOf(::AuthViewModel)

    // profile
    factoryOf(::ProfileActionReducer)
    factoryOf(::ProfileEventReducer)
//    viewModelOf(::ProfileViewModel)
    viewModel { ProfileViewModel(get(), get(), FirebaseAuthManager()) }
}