package com.keyboardapp.data.settings

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object LayoutInitializer {
    
    fun getDefaultQwertyLayout(): String {
        val layout = listOf(
            listOf(
                KeyPosition("Q", 0, 0, keyCode = 'Q'.code),
                KeyPosition("W", 0, 1, keyCode = 'W'.code),
                KeyPosition("E", 0, 2, keyCode = 'E'.code),
                KeyPosition("R", 0, 3, keyCode = 'R'.code),
                KeyPosition("T", 0, 4, keyCode = 'T'.code),
                KeyPosition("Y", 0, 5, keyCode = 'Y'.code),
                KeyPosition("U", 0, 6, keyCode = 'U'.code),
                KeyPosition("I", 0, 7, keyCode = 'I'.code),
                KeyPosition("O", 0, 8, keyCode = 'O'.code),
                KeyPosition("P", 0, 9, keyCode = 'P'.code)
            ),
            listOf(
                KeyPosition("A", 1, 0, keyCode = 'A'.code),
                KeyPosition("S", 1, 1, keyCode = 'S'.code),
                KeyPosition("D", 1, 2, keyCode = 'D'.code),
                KeyPosition("F", 1, 3, keyCode = 'F'.code),
                KeyPosition("G", 1, 4, keyCode = 'G'.code),
                KeyPosition("H", 1, 5, keyCode = 'H'.code),
                KeyPosition("J", 1, 6, keyCode = 'J'.code),
                KeyPosition("K", 1, 7, keyCode = 'K'.code),
                KeyPosition("L", 1, 8, keyCode = 'L'.code)
            ),
            listOf(
                KeyPosition("⇧", 2, 0, width = 1.5f, keyCode = -1, isSpecialKey = true, displayChar = "⇧"),
                KeyPosition("Z", 2, 1, keyCode = 'Z'.code),
                KeyPosition("X", 2, 2, keyCode = 'X'.code),
                KeyPosition("C", 2, 3, keyCode = 'C'.code),
                KeyPosition("V", 2, 4, keyCode = 'V'.code),
                KeyPosition("B", 2, 5, keyCode = 'B'.code),
                KeyPosition("N", 2, 6, keyCode = 'N'.code),
                KeyPosition("M", 2, 7, keyCode = 'M'.code),
                KeyPosition("⌫", 2, 8, width = 1.5f, keyCode = -2, isSpecialKey = true, displayChar = "⌫")
            ),
            listOf(
                KeyPosition(" ", 3, 0, width = 5.0f, keyCode = -3, isSpecialKey = true, displayChar = "Space"),
                KeyPosition("↵", 3, 1, width = 1.5f, keyCode = -4, isSpecialKey = true, displayChar = "↵")
            )
        )
        
        return Json.encodeToString(layout)
    }
    
    fun getDefaultArabicLayout(): String {
        val layout = listOf(
            listOf(
                KeyPosition("ض", 0, 0, keyCode = 'ض'.code),
                KeyPosition("ص", 0, 1, keyCode = 'ص'.code),
                KeyPosition("ث", 0, 2, keyCode = 'ث'.code),
                KeyPosition("ق", 0, 3, keyCode = 'ق'.code),
                KeyPosition("ف", 0, 4, keyCode = 'ف'.code),
                KeyPosition("غ", 0, 5, keyCode = 'غ'.code),
                KeyPosition("ع", 0, 6, keyCode = 'ع'.code),
                KeyPosition("ه", 0, 7, keyCode = 'ه'.code),
                KeyPosition("خ", 0, 8, keyCode = 'خ'.code),
                KeyPosition("ح", 0, 9, keyCode = 'ح'.code)
            ),
            listOf(
                KeyPosition("ش", 1, 0, keyCode = 'ش'.code),
                KeyPosition("س", 1, 1, keyCode = 'س'.code),
                KeyPosition("ي", 1, 2, keyCode = 'ي'.code),
                KeyPosition("ب", 1, 3, keyCode = 'ب'.code),
                KeyPosition("ل", 1, 4, keyCode = 'ل'.code),
                KeyPosition("ا", 1, 5, keyCode = 'ا'.code),
                KeyPosition("ت", 1, 6, keyCode = 'ت'.code),
                KeyPosition("ن", 1, 7, keyCode = 'ن'.code),
                KeyPosition("م", 1, 8, keyCode = 'م'.code)
            ),
            listOf(
                KeyPosition("⇧", 2, 0, width = 1.5f, keyCode = -1, isSpecialKey = true, displayChar = "⇧"),
                KeyPosition("ئ", 2, 1, keyCode = 'ئ'.code),
                KeyPosition("ء", 2, 2, keyCode = 'ء'.code),
                KeyPosition("ؤ", 2, 3, keyCode = 'ؤ'.code),
                KeyPosition("ر", 2, 4, keyCode = 'ر'.code),
                KeyPosition("لا", 2, 5, keyCode = 'ل'.code),
                KeyPosition("ى", 2, 6, keyCode = 'ى'.code),
                KeyPosition("ة", 2, 7, keyCode = 'ة'.code),
                KeyPosition("⌫", 2, 8, width = 1.5f, keyCode = -2, isSpecialKey = true, displayChar = "⌫")
            ),
            listOf(
                KeyPosition(" ", 3, 0, width = 8.0f, keyCode = -3, isSpecialKey = true, displayChar = "مسافة"),
                KeyPosition("↵", 3, 1, width = 2.0f, keyCode = -4, isSpecialKey = true, displayChar = "↵")
            )
        )
        
        return Json.encodeToString(layout)
    }
}