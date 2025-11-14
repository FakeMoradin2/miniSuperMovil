package com.example.project_miniMart.utils.uiManager

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

open class EventManager<T> {
    private val eventChannel = Channel<T>(Channel.Factory.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()

    fun triggerEvent(event: T) {
        CoroutineScope(Dispatchers.Default).launch {
            eventChannel.send(event)
        }
    }
}