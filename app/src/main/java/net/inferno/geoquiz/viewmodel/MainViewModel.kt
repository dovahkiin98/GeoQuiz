package net.inferno.geoquiz.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val currentQuestion = MutableLiveData(-1)

    fun nextQuestion() {
        currentQuestion.value = currentQuestion.value!! + 1
    }

    fun previousQuestion() {
        currentQuestion.value = currentQuestion.value!! - 1
    }
}