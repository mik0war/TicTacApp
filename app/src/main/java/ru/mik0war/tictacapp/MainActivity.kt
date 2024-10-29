package ru.mik0war.tictacapp

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TableRow
import androidx.appcompat.app.ActionBar.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import ru.mik0war.tictacapp.databinding.ActivityMainBinding
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

        val winAction: (Cell)-> Unit = { cell ->
            //TODO
        }

        val loseAction: ()-> Unit = {
            //TODO
        }

        field = Field(fieldSize, winAction, loseAction)

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