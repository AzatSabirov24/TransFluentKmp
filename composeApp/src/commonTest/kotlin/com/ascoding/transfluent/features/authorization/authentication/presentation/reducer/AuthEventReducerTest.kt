//package com.ascoding.transfluent.features.authorization.authentication.presentation.reducer
//
//import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthEvent
//import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthState
//import com.ascoding.transfluent.features.authentication.presentation.register_login.reducer.AuthEventReducer
//import com.ascoding.transfluent.features.authorization.authentication.data.FakeAuthManager
//import com.ascoding.transfluent.utils.beFalse
//import com.ascoding.transfluent.utils.beTrue
//import com.ascoding.transfluent.utils.should
//import kotlin.test.Test
//
//class AuthEventReducerTest {
//
//    @Test
//    fun testUserAuthenticateIfIdIsNotBlank() {
//
//        // Given
//
//        val state = AuthState()
//        val event = AuthEvent.UserAuthenticationChecking
//        val reducer = AuthEventReducer()
//
//        // When
//        val newState = reducer.reduce(
//            state = state,
//            event = event,
//            authManager = createAuthManager(
//                userAuthId = "fakeId"
//            )
//        )
//
//        // Then
//        newState.isUserAuthenticated should beTrue()
//    }
//
//    @Test
//    fun testUserAuthenticateFailIfIdIsBlank() {
//
//        // Given
//        val state = AuthState()
//        val event = AuthEvent.UserAuthenticationChecking
//        val reducer = AuthEventReducer()
//
//        // When
//        val newState = reducer.reduce(
//            state = state,
//            event = event,
//            authManager = createAuthManager(
//                userAuthId = ""
//            )
//        )
//
//        // Then
//        newState.isUserAuthenticated should beFalse()
//    }
//
//    @Test
//    fun testUserAuthenticateFailIfIdIsNull() {
//
//        // Given
//        val state = AuthState()
//        val event = AuthEvent.UserAuthenticationChecking
//        val reducer = AuthEventReducer()
//
//        // When
//        val newState = reducer.reduce(
//            state = state,
//            event = event,
//            authManager = createAuthManager(
//                userAuthId = null
//            )
//        )
//
//        // Then
//        newState.isUserAuthenticated should beFalse()
//    }
//
//    private fun createAuthManager(userAuthId: String?): FakeAuthManager {
//        return FakeAuthManager(userAuthId)
//    }
//}