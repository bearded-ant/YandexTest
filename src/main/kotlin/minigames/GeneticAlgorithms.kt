package minigames

import kotlin.random.Random

class GeneticAlgorithms(val boardSize: Int) {
    companion object {
        var mutCounter = 0
    }

    fun simpleMutation(ships: List<Ship>): List<Ship> {
        val mutateShipIndex = ships.indices.random()
//        val mutateShips = mutableListOf<Ship>()
//        mutateShips.addAll(ships)

        val mutateShips = ships.toMutableList()

        if ((0..100).random() > 90) {
            if (Random.nextBoolean() == mutateShips[mutateShipIndex].orientationVertical) {
                mutateShips[mutateShipIndex].rowStart = (1..boardSize).random()
                mutateShips[mutateShipIndex].columnStart = (1..boardSize).random()
            } else
                mutateShips[mutateShipIndex].orientationVertical = !mutateShips[mutateShipIndex].orientationVertical
            mutCounter++
        }
        return mutateShips
    }

    fun singlePointCrossing(mather: List<Ship>, father: List<Ship>): List<Ship> {
        val crossPoint = mather.indices.random()

        val subMather = mather.subList(crossPoint, mather.size).toMutableList()
        val son: MutableList<Ship> = father.subList(0, crossPoint).toMutableList()
        son.addAll(subMather)

        return son
    }

}