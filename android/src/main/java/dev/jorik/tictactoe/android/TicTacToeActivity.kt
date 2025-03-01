package dev.jorik.tictactoe.android

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dev.jorik.tictactoe.android.databinding.ActivityTictactoeBinding
import kotlinx.coroutines.flow.consumeAsFlow
import dev.jorik.tictactoe.core.game.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch

class TicTacToeActivity : AppCompatActivity() {
    private val viewModel: TicTacToeViewModel by viewModels()
    private val binding: ActivityTictactoeBinding by lazy { ActivityTictactoeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.field.listen(viewModel::move)
        viewModel.fieldFlow.collectOnStart(this, binding.field::setState)
        viewModel.gameState.consumeAsFlow().collectOnStart(this, ::showResultDialog)
    }

    private fun showResultDialog(result: Result) {
        AlertDialog.Builder(this@TicTacToeActivity)
            .setTitle("GameOver")
            .setMessage("Победитель: $result")
            .setPositiveButton("Заново") { _, _ -> viewModel.reset() }
            .show()
    }
}

fun<T> Flow<T>.collectOnStart(lifecycleOwner: LifecycleOwner, collector :FlowCollector<T>){
    lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
            collect(collector)
        }
    }
}