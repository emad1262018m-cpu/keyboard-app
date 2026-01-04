package com.keyboardapp.core.keyboard

object LayoutManager {
    
    const val KEYCODE_SHIFT = -1
    const val KEYCODE_BACKSPACE = -2
    const val KEYCODE_SPACE = -3
    const val KEYCODE_ENTER = -4
    
    fun getDefaultQwertyLayout(): KeyboardLayout {
        return KeyboardLayout(
            rows = listOf(
                // Row 1: Q W E R T Y U I O P
                listOf(
                    KeyData("Q", 'Q'.code),
                    KeyData("W", 'W'.code),
                    KeyData("E", 'E'.code),
                    KeyData("R", 'R'.code),
                    KeyData("T", 'T'.code),
                    KeyData("Y", 'Y'.code),
                    KeyData("U", 'U'.code),
                    KeyData("I", 'I'.code),
                    KeyData("O", 'O'.code),
                    KeyData("P", 'P'.code)
                ),
                // Row 2: A S D F G H J K L
                listOf(
                    KeyData("A", 'A'.code),
                    KeyData("S", 'S'.code),
                    KeyData("D", 'D'.code),
                    KeyData("F", 'F'.code),
                    KeyData("G", 'G'.code),
                    KeyData("H", 'H'.code),
                    KeyData("J", 'J'.code),
                    KeyData("K", 'K'.code),
                    KeyData("L", 'L'.code)
                ),
                // Row 3: Shift Z X C V B N M Backspace
                listOf(
                    KeyData("⇧", KEYCODE_SHIFT, width = 1.0f, isSpecialKey = true),
                    KeyData("Z", 'Z'.code),
                    KeyData("X", 'X'.code),
                    KeyData("C", 'C'.code),
                    KeyData("V", 'V'.code),
                    KeyData("B", 'B'.code),
                    KeyData("N", 'N'.code),
                    KeyData("M", 'M'.code),
                    KeyData("⌫", KEYCODE_BACKSPACE, width = 1.0f, isSpecialKey = true)
                ),
                // Row 4: Space + Enter
                listOf(
                    KeyData(" ", KEYCODE_SPACE, width = 8.0f, isSpecialKey = true, displayChar = "Space"),
                    KeyData("\n", KEYCODE_ENTER, width = 2.0f, isSpecialKey = true, displayChar = "↵")
                ),
                // Row 5: Numbers
                listOf(
                    KeyData("1", '1'.code),
                    KeyData("2", '2'.code),
                    KeyData("3", '3'.code),
                    KeyData("4", '4'.code),
                    KeyData("5", '5'.code),
                    KeyData("6", '6'.code),
                    KeyData("7", '7'.code),
                    KeyData("8", '8'.code),
                    KeyData("9", '9'.code),
                    KeyData("0", '0'.code)
                )
            )
        )
    }
    
    fun getArabicLayout(): KeyboardLayout {
        return KeyboardLayout(
            rows = listOf(
                listOf(
                    KeyData("ض", 'ض'.code),
                    KeyData("ص", 'ص'.code),
                    KeyData("ث", 'ث'.code),
                    KeyData("ق", 'ق'.code),
                    KeyData("ف", 'ف'.code),
                    KeyData("غ", 'غ'.code),
                    KeyData("ع", 'ع'.code),
                    KeyData("ه", 'ه'.code),
                    KeyData("خ", 'خ'.code),
                    KeyData("ح", 'ح'.code)
                ),
                listOf(
                    KeyData("ش", 'ش'.code),
                    KeyData("س", 'س'.code),
                    KeyData("ي", 'ي'.code),
                    KeyData("ب", 'ب'.code),
                    KeyData("ل", 'ل'.code),
                    KeyData("ا", 'ا'.code),
                    KeyData("ت", 'ت'.code),
                    KeyData("ن", 'ن'.code),
                    KeyData("م", 'م'.code)
                ),
                listOf(
                    KeyData("⇧", KEYCODE_SHIFT, width = 1.0f, isSpecialKey = true),
                    KeyData("ئ", 'ئ'.code),
                    KeyData("ء", 'ء'.code),
                    KeyData("ؤ", 'ؤ'.code),
                    KeyData("ر", 'ر'.code),
                    KeyData("لا", 'ل'.code),
                    KeyData("ى", 'ى'.code),
                    KeyData("ة", 'ة'.code),
                    KeyData("⌫", KEYCODE_BACKSPACE, width = 1.0f, isSpecialKey = true)
                ),
                listOf(
                    KeyData(" ", KEYCODE_SPACE, width = 8.0f, isSpecialKey = true, displayChar = "مسافة"),
                    KeyData("\n", KEYCODE_ENTER, width = 2.0f, isSpecialKey = true, displayChar = "↵")
                ),
                listOf(
                    KeyData("١", '١'.code),
                    KeyData("٢", '٢'.code),
                    KeyData("٣", '٣'.code),
                    KeyData("٤", '٤'.code),
                    KeyData("٥", '٥'.code),
                    KeyData("٦", '٦'.code),
                    KeyData("٧", '٧'.code),
                    KeyData("٨", '٨'.code),
                    KeyData("٩", '٩'.code),
                    KeyData("٠", '٠'.code)
                )
            )
        )
    }
}
