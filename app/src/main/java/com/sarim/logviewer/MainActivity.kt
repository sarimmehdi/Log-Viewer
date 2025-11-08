package com.sarim.logviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sarim.logviewer.ui.theme.LogViewerTheme
import com.sarim.nav.Navigator

internal class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LogViewerTheme {
                Navigator()
            }
        }
    }
}
