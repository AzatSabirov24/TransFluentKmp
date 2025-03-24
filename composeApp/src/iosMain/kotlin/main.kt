import androidx.compose.ui.window.ComposeUIViewController
import com.ascoding.transfluentkmpkmp.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
