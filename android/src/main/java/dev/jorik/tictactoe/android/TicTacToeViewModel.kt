package dev.jorik.tictactoe.android

import android.util.Log
import androidx.lifecycle.ViewModel
import dev.jorik.tictactoe.core.GameListener
import dev.jorik.tictactoe.core.Interactor
import dev.jorik.tictactoe.core.Player
import dev.jorik.tictactoe.core.field.FieldDto
import dev.jorik.tictactoe.core.field.OccupiedCellException
import dev.jorik.tictactoe.core.game.Result
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TicTacToeViewModel : ViewModel() {
    private var interactor = createInteractor()
    val gameState = Channel<Result>()
    private val fieldState = MutableStateFlow<FieldDto>(FieldDto())
    val fieldFlow: StateFlow<FieldDto> = fieldState.asStateFlow()

    fun move(x: Int, y: Int) {
        try {
            interactor.loop(x, y)
        } catch (ex: OccupiedCellException) {
            ex.printStackTrace()
        }
    }

    fun reset() {
        fieldState.update { FieldDto() }
        interactor = createInteractor()
    }

    private fun createInteractor(): Interactor =
        Interactor(object : GameListener {
            override fun showPlayer(player: Player) {
                //TODO("Not yet implemented")
            }

            override fun updateField(field: FieldDto) {
                fieldState.update { field }
            }

            override fun onException(exception: Exception) {
                Log.w("TicTacToe", exception.toString())
            }

            override fun onResult(result: Result) {
                gameState.trySend(result)
            }

            override fun requestNext() {
                //ignore
            }
        })
}