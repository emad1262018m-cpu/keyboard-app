package com.keyboardapp

import android.inputmethodservice.InputMethodService
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
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

    private val textComposerState = mutableStateOf<TextComposer?>(null)

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

        textComposerState.value = TextComposer(currentInputConnection)

        return ComposeView(this).apply {
            setViewTreeLifecycleOwner(this@MyKeyboardService)
            setViewTreeViewModelStoreOwner(this@MyKeyboardService)
            setViewTreeSavedStateRegistryOwner(this@MyKeyboardService)

            setContent {
                KeyboardScreen(textComposer = textComposerState.value)
            }
        }
    }

    override fun onStartInputView(info: EditorInfo?, restarting: Boolean) {
        super.onStartInputView(info, restarting)
        lifecycleRegistry.currentState = Lifecycle.State.RESUMED

        textComposerState.value = TextComposer(currentInputConnection)

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
}
