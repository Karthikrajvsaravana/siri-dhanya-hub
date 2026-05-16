package com.example.siridhanyahub.ui.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * Session-scoped language state.
 * selectedLanguage: "en" | "kn"
 * Compose observes this via mutableStateOf — any screen that reads it will
 * automatically recompose when it changes.
 */
object LanguageState {
    var selectedLanguage by mutableStateOf("en")
}
