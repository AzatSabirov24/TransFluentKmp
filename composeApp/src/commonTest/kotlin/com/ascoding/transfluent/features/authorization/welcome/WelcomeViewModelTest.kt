//@file:OptIn(ExperimentalCoroutinesApi::class)
//
//package com.ascoding.transfluent.features.authorization.welcome
//
//import com.ascoding.transfluent.features.authentication.presentation.welcome.WelcomeAction
//import com.ascoding.transfluent.features.authentication.presentation.welcome.WelcomeActionReducer
//import com.ascoding.transfluent.features.authentication.presentation.welcome.WelcomeViewModel
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.test.StandardTestDispatcher
//import kotlinx.coroutines.test.TestDispatcher
//import kotlinx.coroutines.test.advanceUntilIdle
//import kotlinx.coroutines.test.resetMain
//import kotlinx.coroutines.test.runTest
//import kotlinx.coroutines.test.setMain
//import kotlin.test.AfterTest
//import kotlin.test.BeforeTest
//import kotlin.test.DefaultAsserter.assertEquals
//import kotlin.test.Test
//import kotlin.test.assertNotEquals
//
//class WelcomeViewModelTest {
//
//    private lateinit var viewModel: WelcomeViewModel
//    private lateinit var dispatcher: TestDispatcher
//    private val reducer = WelcomeActionReducer()
//
//    @BeforeTest
//    fun setUp() {
//        dispatcher = StandardTestDispatcher()
//        Dispatchers.setMain(dispatcher)
//        viewModel = WelcomeViewModel(reducer = reducer)
//    }
//
//    @AfterTest
//    fun tearDown() {
//        Dispatchers.resetMain()
//    }
//
//    @Test
//    fun testCorrectStateFields() = runTest(StandardTestDispatcher()) {
//        // Given
//        backgroundScope.launch {
//            viewModel.state.collect {}
//        }
//
//        // When
//        viewModel.onAction(WelcomeAction.OnContinueWithEmailClick)
//        advanceUntilIdle()
//
//        // Then
//        val state = viewModel.state.value
//        assertEquals(
//            expected = state.isContinueWithEmailSelected,
//            actual = true,
//            message = "state.isContinueWithEmailSelected should be true"
//        )
//        assertEquals(
//            expected = state.isContinueWithGoogleSelected,
//            actual = false,
//            message = "state.isContinueWithGoogleSelected should be false"
//        )
//    }
//
//    @Test
//    fun testIncorrectStateFields() = runTest(StandardTestDispatcher()) {
//        // Given
//        backgroundScope.launch {
//            viewModel.state.collect {}
//        }
//
//        // When
//        viewModel.onAction(WelcomeAction.OnContinueWithGoogleClick)
//        advanceUntilIdle()
//
//        // Then
//        val state = viewModel.state.value
//        assertNotEquals(
//            illegal = state.isContinueWithEmailSelected,
//            actual = true,
//            message = "state.isContinueWithEmailSelected should be false"
//        )
//        assertEquals(
//            expected = state.isContinueWithGoogleSelected,
//            actual = true,
//            message = "state.isContinueWithGoogleSelected should be true"
//        )
//    }
//}