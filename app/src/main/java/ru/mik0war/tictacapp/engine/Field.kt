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
        var inMainDiagonalCount = 0
        var inSecondDiagonalCount = 0

        for (x in 0..<fieldSize) {
            if (cells[x].filter {
                    it == cell
                }.count() == fieldSize)
                return true

            var inColumnsCount = 0
            for(y in 0 ..<fieldSize){
                if(cells[y][x] == cell)
                    inColumnsCount++
            }

            if(inColumnsCount == fieldSize)
                return true

            if(cells[x][x] == cell)
                inMainDiagonalCount++

            if(cells[fieldSize-1-x][x] == cell)
                inSecondDiagonalCount++
        }
        return inMainDiagonalCount == fieldSize || inSecondDiagonalCount == fieldSize
    }

    private fun checkIsLose(): Boolean{
        var commonCount = 0
        for (line in cells)
            commonCount += line.count { it == Cell.Common }

        return commonCount == 0
    }
}