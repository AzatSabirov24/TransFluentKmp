package com.ascoding.transfluent

import androidx.compose.ui.window.ComposeUIViewController
import com.ascoding.transfluent.di.initKoin
import com.ascoding.transfluent.theme.App

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }