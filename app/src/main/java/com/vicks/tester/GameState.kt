package com.vicks.tester

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember

data class GameState(
    var randomNumber: Int=0,
    val isStart: MutableState<Boolean> = mutableStateOf(false),
    val message: MutableState<String> = mutableStateOf(""),
    val isCorrect: MutableState<Boolean> = mutableStateOf(false)
)