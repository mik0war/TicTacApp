package ru.mik0war.tictacapp.engine

import ru.mik0war.tictacapp.R

sealed interface Cell {

    fun getImageRes(): Int
    fun changeMove() : Cell
    fun setListener(listener: () -> Unit)

    data object Common : Cell {
        override fun getImageRes() = R.drawable.common
        override fun changeMove(): Cell = Common
        override fun setListener(listener: () -> Unit) {
            listener.invoke()
        }
    }

    data object Tic : Cell {
        override fun getImageRes() = R.drawable.tic
        override fun changeMove() = Tac
        override fun toString() = "Tic"

        override fun setListener(listener: () -> Unit) {}
    }

    data object Tac : Cell {
        override fun getImageRes() = R.drawable.tac
        override fun changeMove() = Tic
        override fun setListener(listener: () -> Unit) {}
        override fun toString() = "Tac"
    }
}