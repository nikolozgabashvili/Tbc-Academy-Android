package com.example.tbcacademyhomework.game

import java.util.UUID
import kotlin.math.sqrt

class GameManager {

    private val cellMatrix = mutableListOf<GameSquare>()
    private var currentPlayer: PlayerType = PlayerType.X
    private var cellAmount: Int = 3


    fun setCellCount(cellCount: Int) {
        repeat(cellCount) {
            cellMatrix.add(GameSquare())
        }
        cellAmount = sqrt(cellCount.toDouble()).toInt()
        cellDataListener()
    }

    fun restartGame() {
        cellMatrix.replaceAll { GameSquare() }
        cellDataListener()
    }

    fun getCells() = cellMatrix.toList()
    private var listener: (PlayerType?) -> Unit = {}

    fun onGameEndListener(listener: (PlayerType?) -> Unit) {
        this.listener = listener
    }

    private var cellDataListener: () -> Unit = {}
    fun onDataUpdateListener(listener: () -> Unit) {
        cellDataListener = listener
    }

    private fun checkWinCondition(): PlayerType? {
        fun getCell(row: Int, col: Int): PlayerType? = cellMatrix[row * cellAmount + col].playerType


        for (row in 0 until cellAmount) {
            if ((0 until cellAmount).all { col ->
                    getCell(
                        row,
                        col
                    ) == currentPlayer
                }) return currentPlayer
        }


        for (col in 0 until cellAmount) {
            if ((0 until cellAmount).all { row ->
                    getCell(
                        row,
                        col
                    ) == currentPlayer
                }) return currentPlayer
        }


        if ((0 until cellAmount).all { i -> getCell(i, i) == currentPlayer }) return currentPlayer


        if ((0 until cellAmount).all { i ->
                getCell(
                    i,
                    cellAmount - i - 1
                ) == currentPlayer
            }) return currentPlayer


        return null
    }

    fun selectCell(id: UUID) {
        val cell = cellMatrix.find { it.id == id } ?: return
        if (!cell.isActive) {
            cellMatrix.replaceAll { cell ->
                cell.copy(
                    isActive = if (cell.id == id) true else cell.isActive,
                    playerType = if (cell.id == id) currentPlayer else cell.playerType
                )
            }
            cellDataListener()
            if (checkWinCondition() != null) {
                listener(currentPlayer)
                finishGame()
                return
            }
            currentPlayer = if (currentPlayer == PlayerType.X) PlayerType.O else PlayerType.X

            if (cellMatrix.all { it.isActive }) {
                listener(null)
                finishGame()
            }

        }
    }

    private fun finishGame() {
        //setting all cells to active to make them non responsive if clicked
        cellMatrix.replaceAll { it.copy(isActive = true) }
        cellDataListener()
    }

}