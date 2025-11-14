package com.example.project_miniMart.widgets.loader

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object DsLoaderView {
    private val _state = mutableStateOf(false)
    val state: State<Boolean> get() = _state

    private val _isBlocking = mutableStateOf(false)
    val isBlocking: State<Boolean> get() = _isBlocking

    fun showLoader() {
        _state.value = true
        _isBlocking.value = true
    }

    fun dismissLoader() {
        CoroutineScope(Dispatchers.Default).launch {
            _state.value = false
            _isBlocking.value = false
        }
    }
}