package tat.mukhutdinov.lesson9.ui

data class GameUiState(
    val currentScrambledWord: String = "",
    val userGuess: String = "",
    val isGuessedWordWrong: Boolean = false,
    val score: Int = 0,
    val currentWordCount: Int = 1,
    val isGameOver: Boolean = false
)

