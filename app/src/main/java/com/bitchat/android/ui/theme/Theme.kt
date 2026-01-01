package com.bitchat.android.ui.theme

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowInsetsController
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
// These imports are NOT here, as you said.
// import com.bitchat.android.model.ThemePreference
// import com.bitchat.android.services.ThemePreferenceManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView

// --- Colors Changed Here ---
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFF6700),        // CHANGED: Emergency Orange
    onPrimary = Color.Black,
    secondary = Color(0xFFD30000),      // CHANGED: Warning Red
    onSecondary = Color.White,
    background = Color.Black,
    onBackground = Color(0xFFFF6700),   // CHANGED: Orange on black
    surface = Color(0xFF111111),
    onSurface = Color(0xFFFF6700),      // CHANGED: Orange text
    error = Color(0xFFFF5555),
    onError = Color.Black
)

// --- Colors Changed Here ---
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFFF6700),        // CHANGED: Emergency Orange
    onPrimary = Color.White,
    secondary = Color(0xFFD30000),      // CHANGED: Warning Red
    onSecondary = Color.White,
    background = Color.White,
    onBackground = Color.Black,         // CHANGED: Black on white (better readability)
    surface = Color(0xFFF8F8F8),
    onSurface = Color.Black,            // CHANGED: Black text (better readability)
    error = Color(0xFFCC0000),
    onError = Color.White
)


@Composable
fun BitchatTheme(
    darkTheme: Boolean? = null,
    content: @Composable () -> Unit
) {
    // This logic was not in your file, so it is gone.
    // We will just use the simple darkTheme boolean.
    val shouldUseDark = darkTheme ?: isSystemInDarkTheme()

    val colorScheme = if (shouldUseDark) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    SideEffect {
        (view.context as? Activity)?.window?.let { window ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                window.insetsController?.setSystemBarsAppearance(
                    if (!shouldUseDark) WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS else 0,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
            } else {
                @Suppress("DEPRECATION")
                window.decorView.systemUiVisibility = if (!shouldUseDark) {
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                } else 0
            }
            window.navigationBarColor = colorScheme.background.toArgb()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                window.isNavigationBarContrastEnforced = false
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // This was in your original file
        content = content
    )
}