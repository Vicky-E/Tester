package com.vicks.tester.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.vicks.tester.GameState
import kotlin.random.Random

class AppViewModel(application: Application): AndroidViewModel(application) {
    var gameState : GameState =  GameState()
    var guessString = mutableStateOf("")



    fun startGame(){
        gameState = GameState()
        gameState.randomNumber = Random.nextInt(10)
        gameState.isStart.value = true
    }
    fun check(){
        var guess = 0
        try{
           guess = guessString.value.toInt()
        }
        catch (e: NumberFormatException){
            gameState.isCorrect.value = false
            gameState.message.value = "Enter a valid number!"
            return
        }
        if(gameState.randomNumber == guess){
            gameState.isCorrect.value = true
            gameState.message.value = "Correct!"
        }
        else if(gameState.randomNumber > guess){
            gameState.isCorrect.value = false
            gameState.message.value = "Opps! Too Low!"
        }
        else if(gameState.randomNumber < guess){
            gameState.isCorrect.value = false
            gameState.message.value = "Opps! Too High!"
        }

    }
    fun endGame(){
        gameState = GameState()
    }
}

