package com.example.project_miniMart.utils.uiManager

import com.example.project_miniMart.utils.uiManager.events.AuthEvent
import com.example.project_miniMart.utils.uiManager.events.HomeEvent

object AuthEventManager : EventManager<AuthEvent>()
object HomeEventManager : EventManager<HomeEvent>()