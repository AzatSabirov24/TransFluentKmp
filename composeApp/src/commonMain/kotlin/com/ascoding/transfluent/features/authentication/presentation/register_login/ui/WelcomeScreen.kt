package com.ascoding.transfluent.features.authentication.presentation.register_login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ascoding.transfluent.common.utils.ui.Type
import com.ascoding.transfluent.common.utils.ui.TypeMode
import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthAction
import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthEvent
import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthViewModel
import com.ascoding.transfluent.features.authentication.presentation.register_login.LoginOrRegisterAction
import com.ascoding.transfluent.navigation.route.Route
import com.ascoding.transfluent.theme.LocalExtendedColorScheme
import com.ascoding.transfluent.theme.OnPrimary
import com.ascoding.transfluent.theme.Primary
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.viewmodel.koinViewModel
import transfluent.composeapp.generated.resources.Res
import transfluent.composeapp.generated.resources.continue_with_email
import transfluent.composeapp.generated.resources.continue_with_google
import transfluent.composeapp.generated.resources.ic_google
import transfluent.composeapp.generated.resources.or

@Composable
fun WelcomeScreenRoot(
    viewModel: AuthViewModel = koinViewModel(),
    navigateFromWelcome: (Route) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.events.collectLatest { event ->
            when (event) {
                AuthEvent.ContinuingWithEmail -> navigateFromWelcome(Route.Register)
                else -> Unit
            }
        }
    }

    WelcomeScreen(viewModel::onAction)
}

@Composable
fun WelcomeScreen(
    onAction: (AuthAction) -> Unit
) {
    val extendedColors = LocalExtendedColorScheme.current
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(extendedColors.backgroundMain),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    shape = RectangleShape,
                    modifier = Modifier
                        .padding(16.dp)
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
                    content = {
                        Text(
                            text = stringResource(Res.string.continue_with_email),
                            fontSize = 16.sp,
                            style = TextStyle(
                                fontFamily = Type(TypeMode.REGULAR)
                            )
                        )
                    },
                    onClick = { onAction(AuthAction.OnContinueWithEmailClick) },
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .weight(1f),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = stringResource(Res.string.or),
                        fontSize = 14.sp,
                        style = TextStyle(
                            fontFamily = Type(TypeMode.REGULAR)
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .weight(1f),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Button(
                    shape = RectangleShape,
                    modifier = Modifier
                        .padding(16.dp)
                        .height(54.dp)
                        .widthIn(
                            min = 350.dp,
                            max = 500.dp
                        )
                        .clip(RoundedCornerShape(16.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = OnPrimary,
                        contentColor = OnPrimary
                    ),
                    content = {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Icon(
                                imageVector = vectorResource(Res.drawable.ic_google),
                                contentDescription = stringResource(Res.string.continue_with_google),
                                modifier = Modifier.size(24.dp),
                                tint = Color.Unspecified
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = stringResource(Res.string.continue_with_google),
                                fontSize = 16.sp,
                                style = TextStyle(
                                    fontFamily = Type(TypeMode.REGULAR)
                                ),
                                color = Color.Black
                            )
                        }
                    },
                    onClick = {
                        onAction(LoginOrRegisterAction.OnContinueWithGoogleClick)
                    },
                )
//                GoogleButtonUiContainer(
//                    onGoogleSignInResult = { googleUser ->
//                        val tokenId = googleUser?.idToken
//                        println("TOKEN: $tokenId")
//                    }
//                ) {
//                    GoogleSignInButton(
//                        onClick = { this.onClick() }
//                    )
//                }

            }
        }
    }
}