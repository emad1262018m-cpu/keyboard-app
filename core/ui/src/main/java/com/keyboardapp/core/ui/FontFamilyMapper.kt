package com.keyboardapp.core.ui

import androidx.compose.ui.text.font.FontFamily

object FontFamilyMapper {
    fun map(fontName: String): FontFamily {
        return when (fontName) {
            "Roboto" -> FontFamily.SansSerif
            "Arial" -> FontFamily.SansSerif
            "Noto Sans Arabic" -> FontFamily.SansSerif // Fallback for now
            "Courier New" -> FontFamily.Monospace
            else -> FontFamily.Default
        }
    }
}
