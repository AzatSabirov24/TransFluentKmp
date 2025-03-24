package com.ascoding.transfluent.features.authentication.presentation.register_login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ascoding.transfluent.common.utils.ui.Type
import com.ascoding.transfluent.common.utils.ui.TypeMode
import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthAction
import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthEvent
import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthState
import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthViewModel
import com.ascoding.transfluent.features.authentication.presentation.register_login.LoginAction
import com.ascoding.transfluent.features.authentication.presentation.register_login.ui.components.IconWithText
import com.ascoding.transfluent.navigation.route.Route
import com.ascoding.transfluent.theme.GreenRegular
import com.ascoding.transfluent.theme.LocalExtendedColorScheme
import com.ascoding.transfluent.theme.OnPrimary
import com.ascoding.transfluent.theme.Primary
import com.ascoding.transfluent.theme.RedRegular
import com.ascoding.transfluent.theme.TextFieldPlaceholderText
import com.ascoding.transfluent.theme.TextFieldText
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.viewmodel.koinViewModel
import transfluent.composeapp.generated.resources.Res
import transfluent.composeapp.generated.resources.already_have_an_account
import transfluent.composeapp.generated.resources.email
import transfluent.composeapp.generated.resources.hide_password
import transfluent.composeapp.generated.resources.ic_check
import transfluent.composeapp.generated.resources.ic_cross
import transfluent.composeapp.generated.resources.ic_visibility_off
import transfluent.composeapp.generated.resources.ic_visibility_on
import transfluent.composeapp.generated.resources.invalid_email
import transfluent.composeapp.generated.resources.minimum_eight_characters
import transfluent.composeapp.generated.resources.minimum_one_digit
import transfluent.composeapp.generated.resources.minimum_one_letter
import transfluent.composeapp.generated.resources.password
import transfluent.composeapp.generated.resources.password_does_not_have_eight_characters
import transfluent.composeapp.generated.resources.password_does_not_have_minimum_one_digit
import transfluent.composeapp.generated.resources.password_does_not_have_minimum_one_letter
import transfluent.composeapp.generated.resources.password_has_eight_characters
import transfluent.composeapp.generated.resources.password_has_minimum_one_digit
import transfluent.composeapp.generated.resources.password_has_minimum_one_letter
import transfluent.composeapp.generated.resources.show_password
import transfluent.composeapp.generated.resources.sign_in
import transfluent.composeapp.generated.resources.sign_up
import transfluent.composeapp.generated.resources.valid_email

@Composable
fun RegisterScreenRoot(
    viewModel: AuthViewModel = koinViewModel(),
    navigateFromRegister: (Route) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.events.collectLatest { event ->
            when (event) {
                is AuthEvent.Loading -> isLoading = true

                is AuthEvent.AuthSuccess -> navigateFromRegister(Route.MainGraph)

                is AuthEvent.AuthError -> {
                    isLoading = false
                    errorMessage = event.error
                }

                is AuthEvent.UserAlreadyHasAnAccount -> navigateFromRegister(Route.Login)

                AuthEvent.BackFromRegister -> navigateFromRegister(Route.BackStack)

                AuthEvent.HandlingPasswordVisibility -> isPasswordVisible = !isPasswordVisible

                else -> Unit
            }
        }
    }

    RegisterScreen(
        state = state,
        isLoading = isLoading,
        errorMessage = errorMessage,
        isPasswordVisible = isPasswordVisible,
        onAction = viewModel::onAction
    )
}

@Composable
fun RegisterScreen(
    state: AuthState = AuthState(),
    isLoading: Boolean = false,
    errorMessage: String? = "",
    isPasswordVisible: Boolean = false,
    onAction: (AuthAction) -> Unit = {}
) {
    val extendedColors = LocalExtendedColorScheme.current
    val textSelectionColors = TextSelectionColors(
        backgroundColor = Primary,
        handleColor = Primary,
    )
    val focusManager = LocalFocusManager.current
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier
            .imePadding(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(extendedColors.backgroundMain),
            contentAlignment = Alignment.Center,
        ) {
            var email by rememberSaveable { mutableStateOf("") }
            var password by rememberSaveable { mutableStateOf("") }

            IconButton(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(24.dp)
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(
                        color = Color.White
                    ),
                content = {
                    Icon(
                        modifier = Modifier
                            .alpha(0.5f),
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                },
                onClick = { onAction(AuthAction.BackFromRegisterClick) },
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                CompositionLocalProvider(
                    LocalTextSelectionColors provides textSelectionColors
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        textStyle = TextStyle(
                            fontFamily = Type(TypeMode.REGULAR),
                            fontSize = 18.sp,
                            color = TextFieldText
                        ),
                        shape = RoundedCornerShape(16.dp),
                        value = email,
                        onValueChange = {
                            email = it
                            onAction(AuthAction.OnEnterEmail(it))
                        },
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        placeholder = {
                            Text(
                                text = stringResource(Res.string.email),
                                fontSize = 18.sp,
                                style = TextStyle(
                                    fontFamily = Type(TypeMode.REGULAR),
                                    color = TextFieldPlaceholderText
                                )
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = extendedColors.backgroundTextField,
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Primary,
                            cursorColor = Primary
                        )
                    )
                }
                Spacer(modifier = Modifier.size(24.dp))
                CompositionLocalProvider(
                    LocalTextSelectionColors provides textSelectionColors
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        textStyle = TextStyle(
                            fontFamily = Type(TypeMode.REGULAR),
                            fontSize = 18.sp,
                            color = TextFieldText
                        ),
                        shape = RoundedCornerShape(16.dp),
                        value = password,
                        onValueChange = {
                            password = it
                            onAction(AuthAction.OnEnterPassword(it))
                        },
                        keyboardActions = KeyboardActions(
                            onDone = {
                                onAction(
                                    LoginAction.OnRegisterClick(
                                        email = email,
                                        password = password
                                    )
                                )
                                focusManager.clearFocus()
                            }
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = if (isPasswordVisible) KeyboardType.Text else KeyboardType.Password
                        ),
                        placeholder = {
                            Text(
                                text = stringResource(Res.string.password),
                                fontSize = 18.sp,
                                style = TextStyle(
                                    fontFamily = Type(TypeMode.REGULAR),
                                    color = TextFieldPlaceholderText
                                )
                            )
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = { onAction(AuthAction.PasswordVisibilityClick) }
                            ) {
                                Icon(
                                    imageVector = if (isPasswordVisible) vectorResource(Res.drawable.ic_visibility_off) else vectorResource(
                                        Res.drawable.ic_visibility_on
                                    ),
                                    contentDescription = if (isPasswordVisible) stringResource(Res.string.hide_password) else stringResource(
                                        Res.string.show_password
                                    )
                                )
                            }
                        },
                        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = extendedColors.backgroundTextField,
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Primary,
                            cursorColor = Primary
                        )
                    )
                }
                Spacer(modifier = Modifier.size(24.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 24.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    val annotatedString = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontFamily = Type(TypeMode.REGULAR),
                                color = LocalExtendedColorScheme.current.textRegular
                            )
                        ) {
                            append(stringResource(Res.string.already_have_an_account) + " ")
                            pushStringAnnotation(
                                tag = "clickable_text",
                                annotation = stringResource(Res.string.sign_in)
                            )
                            withStyle(
                                style = SpanStyle(
                                    fontFamily = Type(TypeMode.BOLD),
                                    color = GreenRegular,
                                )
                            ) {
                                append(stringResource(Res.string.sign_in))
                            }
                        }
                    }
                    Text(
                        text = annotatedString,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .clickable {
                                onAction(AuthAction.OnAlreadyRegisteredClick)
                            }
                    )
                }
                Spacer(modifier = Modifier.size(36.dp))
                IconWithText(
                    icon = if (state.isEmailValid) vectorResource(Res.drawable.ic_check)
                    else vectorResource(Res.drawable.ic_cross),
                    text = if (state.isEmailValid) stringResource(Res.string.valid_email)
                    else stringResource(Res.string.invalid_email),
                    iconTint = if (state.isEmailValid) GreenRegular else RedRegular,
                    contentDescription = if (state.isEmailValid) stringResource(Res.string.valid_email)
                    else stringResource(Res.string.invalid_email)
                )
                Spacer(modifier = Modifier.size(10.dp))
                IconWithText(
                    icon = if (state.hasPasswordEightSymbols) vectorResource(Res.drawable.ic_check)
                    else vectorResource(Res.drawable.ic_cross),
                    text = stringResource(Res.string.minimum_eight_characters),
                    iconTint = if (state.hasPasswordEightSymbols) GreenRegular else RedRegular,
                    contentDescription = if (state.hasPasswordEightSymbols) stringResource(Res.string.password_has_eight_characters)
                    else stringResource(Res.string.password_does_not_have_eight_characters)
                )
                Spacer(modifier = Modifier.size(10.dp))
                IconWithText(
                    icon = if (state.hasPasswordLetter) vectorResource(Res.drawable.ic_check)
                    else vectorResource(Res.drawable.ic_cross),
                    text = stringResource(Res.string.minimum_one_letter),
                    iconTint = if (state.hasPasswordLetter) GreenRegular else RedRegular,
                    contentDescription = if (state.hasPasswordLetter) stringResource(Res.string.password_has_minimum_one_letter)
                    else stringResource(Res.string.password_does_not_have_minimum_one_letter)
                )
                Spacer(modifier = Modifier.size(10.dp))
                IconWithText(
                    icon = if (state.hasPasswordDigit) vectorResource(Res.drawable.ic_check)
                    else vectorResource(Res.drawable.ic_cross),
                    text = stringResource(Res.string.minimum_one_digit),
                    iconTint = if (state.hasPasswordDigit) GreenRegular else RedRegular,
                    contentDescription = if (state.hasPasswordDigit) stringResource(Res.string.password_has_minimum_one_digit)
                    else stringResource(Res.string.password_does_not_have_minimum_one_digit)
                )
            }
            Button(
                shape = RectangleShape,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(start = 16.dp, end = 16.dp, bottom = 36.dp)
                    .height(54.dp)
                    .widthIn(
                        min = 350.dp,
                        max = 500.dp
                    )
                    .clip(RoundedCornerShape(16.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary,
                    contentColor = OnPrimary
                ),
                onClick = {
                    onAction(
                        LoginAction.OnRegisterClick(
                            email = email.trim(),
                            password = password.trim()
                        )
                    )
                    focusManager.clearFocus()
                },
                enabled = state.isEmailValid && state.isPasswordValid && !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(24.dp)
                            .testTag("register loader"),
                        color = Primary
                    )
                } else {
                    Text(
                        text = stringResource(Res.string.sign_up),
                        fontSize = 16.sp,
                        style = TextStyle(
                            fontFamily = Type(TypeMode.REGULAR)
                        )
                    )
                }
            }
        }
        errorMessage?.let {
            Text(
                text = it,
                color = Color.Red,
                fontSize = 14.sp
            )
        }
    }
}
