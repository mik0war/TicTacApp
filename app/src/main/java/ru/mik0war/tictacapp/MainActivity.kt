package ru.mik0war.tictacapp

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TableRow
import android.widget.Toast
import androidx.appcompat.app.ActionBar.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import ru.mik0war.tictacapp.databinding.ActivityMainBinding
import ru.mik0war.tictacapp.databinding.CongratulationsDialogBinding
import ru.mik0war.tictacapp.engine.Cell
import ru.mik0war.tictacapp.engine.Field

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentCell : Cell = Cell.Tic

    private val fieldSize = 3
    lateinit var field: Field

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val finishAction: (Cell)-> Unit = { cell ->

            Toast.makeText(this, "Congratulations, $cell", Toast.LENGTH_SHORT).show()


            val dialog = Dialog(this)

            val dialogBinding = CongratulationsDialogBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)

            dialogBinding.retryButton.setOnClickListener {
                startActivity(Intent(this, MainActivity::class.java))
            }

            dialog.show()

        }

        val loseAction: ()-> Unit = {
            Toast.makeText(this, "Noun", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        }

        field = Field(fieldSize, finishAction, loseAction)

        buildField(fieldSize)

        binding.currentPlayerView.setImageResource(currentCell.getImageRes())

        setContentView(binding.root)

    }

    private fun buildField(fieldSize: Int) {
        for (x in 0..< fieldSize) {
            val row = TableRow(this).apply {
                layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            }

            for (y in 0..< fieldSize) {
                val imageButton = ImageButton(this).apply {
                    layoutParams = TableRow.LayoutParams(
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT,
                        1f
                    )
                    adjustViewBounds = true
                    scaleType = ImageView.ScaleType.FIT_XY
                    setImageResource(R.drawable.common)

                    setOnClickListener {
                        field.makeStep(x, y, currentCell){
                            setImageResource(currentCell.getImageRes())
                            currentCell = currentCell.changeMove()
                            binding.currentPlayerView.setImageResource(currentCell.getImageRes())
                        }
                    }
                }

                row.addView(imageButton)
            }

            binding.field.addView(row)
        }
    }
}