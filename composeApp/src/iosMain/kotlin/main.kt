import androidx.compose.ui.window.ComposeUIViewController
import com.ascoding.transfluentkmp.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
