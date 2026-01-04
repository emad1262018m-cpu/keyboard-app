package com.keyboardapp

import android.inputmethodservice.InputMethodService
import android.util.Log
import android.view.View
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.keyboardapp.core.input.TextComposer
import com.keyboardapp.core.keyboard.KeyboardView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyKeyboardService : InputMethodService(), 
    LifecycleOwner,
    ViewModelStoreOwner,
    SavedStateRegistryOwner {

    private val TAG = "MyKeyboardService"
    
    private val lifecycleRegistry = LifecycleRegistry(this)
    private val savedStateRegistryController = SavedStateRegistryController.create(this)
    private val store = ViewModelStore()
    
    private val isShiftPressed = mutableStateOf(false)
    private lateinit var textComposer: TextComposer

    override val lifecycle: Lifecycle
        get() = lifecycleRegistry

    override val viewModelStore: ViewModelStore
        get() = store

    override val savedStateRegistry: SavedStateRegistry
        get() = savedStateRegistryController.savedStateRegistry

    override fun onCreate() {
        super.onCreate()
        savedStateRegistryController.performRestore(null)
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
        Log.d(TAG, "MyKeyboardService onCreate")
    }

    override fun onCreateInputView(): View {
        Log.d(TAG, "onCreateInputView called")
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
        
        return ComposeView(this).apply {
            setViewTreeLifecycleOwner(this@MyKeyboardService)
            setViewTreeViewModelStoreOwner(this@MyKeyboardService)
            setViewTreeSavedStateRegistryOwner(this@MyKeyboardService)
            
            setContent {
                KeyboardView(
                    onKeyClick = { keyData ->
                        handleKeyPress(keyData.char, keyData.keyCode)
                    },
                    isShiftPressed = isShiftPressed.value
                )
            }
        }
    }

    override fun onStartInputView(info: android.view.inputmethod.EditorInfo?, restarting: Boolean) {
        super.onStartInputView(info, restarting)
        lifecycleRegistry.currentState = Lifecycle.State.RESUMED
        
        textComposer = TextComposer(currentInputConnection)
        isShiftPressed.value = false
        
        Log.d(TAG, "onStartInputView - Input type: ${info?.inputType}")
    }

    override fun onFinishInputView(finishingInput: Boolean) {
        super.onFinishInputView(finishingInput)
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
        Log.d(TAG, "onFinishInputView")
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
        store.clear()
        Log.d(TAG, "onDestroy")
    }

    private fun handleKeyPress(char: String, keyCode: Int) {
        Log.d(TAG, "Key pressed: $char (keyCode: $keyCode)")
        
        when (keyCode) {
            KEYCODE_SHIFT -> {
                isShiftPressed.value = !isShiftPressed.value
                Log.d(TAG, "Shift toggled: ${isShiftPressed.value}")
            }
            KEYCODE_BACKSPACE -> {
                textComposer.deleteText()
                Log.d(TAG, "Backspace pressed")
            }
            KEYCODE_SPACE -> {
                textComposer.commitText(" ")
                Log.d(TAG, "Space pressed")
            }
            else -> {
                val textToCommit = if (isShiftPressed.value) char.uppercase() else char.lowercase()
                Log.d(TAG, "Committing text: $textToCommit")
            }
        }
    }

    companion object {
        const val KEYCODE_SHIFT = -1
        const val KEYCODE_BACKSPACE = -2
        const val KEYCODE_SPACE = -3
    }
}
