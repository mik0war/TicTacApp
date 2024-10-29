package ru.mik0war.tictacapp.engine

class Field(
    private val fieldSize: Int,
    private val winAction: (Cell) -> Unit,
    private val loseAction: ()-> Unit
) {
    private val cells: Array<Array<Cell>> =
        Array(fieldSize) { Array(fieldSize) { Cell.Common } }

    fun makeStep(x: Int, y: Int, cell: Cell, onClickAction: (Cell)-> Unit) {

        cells[x][y].setListener {
            cells[x][y] = cell
            onClickAction.invoke(cell)

            if (checkIsWin(cell))
                winAction.invoke(cells[x][y])

            if (checkIsLose())
                loseAction.invoke()
        }
    }

    private fun checkIsWin(cell: Cell): Boolean {
        //TODO
        return false
    }

    private fun checkIsLose(): Boolean{
        //TODO
        return false
    }
}