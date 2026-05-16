package com.example.siridhanyahub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.siridhanyahub.navigation.AppNavGraph
import com.example.siridhanyahub.ui.theme.SiridhanyahubTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            SiridhanyahubTheme {

                AppNavGraph()

            }
        }
    }
}