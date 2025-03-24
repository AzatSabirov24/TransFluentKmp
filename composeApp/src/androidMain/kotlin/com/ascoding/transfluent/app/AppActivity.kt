package com.ascoding.transfluent.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ascoding.transfluent.theme.App
import com.google.firebase.Firebase
import com.google.firebase.initialize

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Firebase.initialize(this)
        setContent { App() }
    }
}

@Preview
@Composable
fun AppPreview() { App() }
