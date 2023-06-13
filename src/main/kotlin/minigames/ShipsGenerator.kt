package minigames

import kotlin.random.Random

const val SHIP_MAX_SIZE = 4
const val OVER_SIZE_BOARD = SHIP_MAX_SIZE + 2

const val OVER_SIZE_POINT = 3.0
const val LOCKED_CELL_POINT = 0.2
const val SHIP_POINT = 1.0

class ShipsGenerator {
    fun initShips(shipsType: List<Int>, boardSize: Int): List<Ship> {
        val firstGenerationShips = mutableListOf<Ship>()
        for (ship in shipsType) {
            val row = (1..boardSize).random()
            val column = (1..boardSize).random()
            val orientation = Random.nextBoolean()
            //
//            val row = 5
//            val column = 5
//            val orientation = true
            firstGenerationShips.add(Ship(row, column, orientation, ship))
        }
        return firstGenerationShips
    }

    fun initBoard(boardSize: Int): Array<DoubleArray> {

        val normalBoard = Array(boardSize) { DoubleArray(boardSize) }

        val oversizeBoard =
            Array(boardSize + OVER_SIZE_BOARD) { DoubleArray(boardSize + OVER_SIZE_BOARD) { OVER_SIZE_POINT } }

        //добавляем значения рабочего поля на оверсайз доску
        for (i in 1..boardSize)
            for (j in 1..boardSize)
                oversizeBoard[i][j] = normalBoard[i - 1][j - 1]

        return oversizeBoard
    }

    fun shipsFitness(shipGeneration: List<Ship>, board: Array<DoubleArray>) {

        for (ship in shipGeneration) {

            val resultMask = getShipMask(ship)
            val rowEndIndex = ship.rowStart - 1 + resultMask.lastIndex
            val colEndIndex = ship.columnStart - 1 + resultMask[0].lastIndex

            for ((maskRowIterator, i) in (ship.rowStart - 1..rowEndIndex).withIndex()) {
                for ((maskColIterator, j) in (ship.columnStart - 1..colEndIndex).withIndex()) {
                    board[i][j] += resultMask[maskRowIterator][maskColIterator]
                }
            }
        }
    }

    fun getFitnessSum(board: Array<DoubleArray>): Double {
        val normalBoardSum = board.sumOf { row ->
            row.filter { selector -> SHIP_POINT < selector && selector < OVER_SIZE_POINT }.sum()
        }
        val overSizeBoardSum = board.sumOf { row ->
            row.filter { selector -> (OVER_SIZE_POINT + 4 * LOCKED_CELL_POINT) < selector }.sum()
        }
        return normalBoardSum + overSizeBoardSum
    }

    private fun getShipMask(ship: Ship): Array<DoubleArray> {
        val shipMask = DoubleArray(ship.length) { SHIP_POINT }
        return if (ship.orientationVertical) {
            val verticalShipMask = Array(ship.length + 2) { DoubleArray(3) { LOCKED_CELL_POINT } }
            for (i in 1..shipMask.size)
                verticalShipMask[i][1] = shipMask[i - 1]
            verticalShipMask
        } else {
            val horizontalShipMask = Array(3) { DoubleArray(ship.length + 2) { LOCKED_CELL_POINT } }
            for (j in 1..shipMask.size)
                horizontalShipMask[1][j] = shipMask[j - 1]
            horizontalShipMask
        }
    }

    fun printMask(mask: Array<DoubleArray>) {
        for (i in 0..mask.lastIndex) {
            println()
            for (j in 0..mask[i].lastIndex)
                print("[${mask[i][j]}] ")
        }
        println()
        println("-----")
        println()
    }

    //todo mutate fun
    fun simpleMutation(ships: List<Ship>, boardSize: Int): List<Ship> {
        val mutateShip = ships.indices.random()

        if (Random.nextBoolean() == ships[mutateShip].orientationVertical) {
            ships[mutateShip].rowStart = (1..boardSize).random()
            ships[mutateShip].columnStart = (1..boardSize).random()
        } else
            ships[mutateShip].orientationVertical = !ships[mutateShip].orientationVertical

        return ships
    }
}