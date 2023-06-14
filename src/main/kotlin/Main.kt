import minigames.GeneticAlgorithms
import minigames.Ship
import minigames.ShipsGenerator
import java.util.*

const val BOARD_SIZE = 5
val generator = ShipsGenerator(BOARD_SIZE)
val geneticMutator = GeneticAlgorithms(BOARD_SIZE)
fun main() {
//    print(AddDigits().addDigits(22))

    var counter = 0
    val playShips = listOf(4, 4, 4, 4)
    var fitnessSum = 1000.0
    val population = sortedMapOf<Double, List<Ship>>()


    while (counter < 10 && fitnessSum != 0.0) {

        val ships = generator.initShips(playShips)
        val newBoard = generator.shipsFitness(ships)
        fitnessSum = generator.getFitnessSum(newBoard)

        population[fitnessSum] = ships
        counter++
    }

    println("исходная популяция:\t ${population.keys}")

    val halfMap: MutableMap<Double, List<Ship>>
    val mutatePopulation = mutableMapOf<Double, List<Ship>>()

    if (fitnessSum > 0) {

        halfMap = selectBestHalf(population)
        println("лучшая половина:\t ${halfMap.keys}")

        mutatePopulation.putAll(mutation(halfMap))

//        mutatePopulation.putAll(halfMap)
        val sortMut = mutatePopulation.toSortedMap()


        println("мутиров половина:\t ${sortMut.keys}")
        println("мутиров половина:\t ${GeneticAlgorithms.mutCounter}")
    }

}

private fun mutation(halfMap: MutableMap<Double, List<Ship>>): MutableMap<Double, List<Ship>> {
    val mutateHalf = mutableMapOf<Double, List<Ship>>()
    val dddd = halfMap.toSortedMap()
    val keyList = halfMap.keys.toList()

    for (i in 0 until keyList.lastIndex step 2) {
        val first = dddd.getValue(keyList[i + 1])
        val second = dddd.getValue(keyList[i])
        val selectionSon = geneticMutator.singlePointCrossing(
            first, second
        )
        val mutateSonBoard = generator.shipsFitness(geneticMutator.simpleMutation(selectionSon))
        generator.printMask(mutateSonBoard)
        val sonSum = generator.getFitnessSum(mutateSonBoard)
        mutateHalf.put(sonSum, selectionSon)
    }
    return mutateHalf
}

private fun selectBestHalf(population: SortedMap<Double, List<Ship>>): MutableMap<Double, List<Ship>> {
    var halfSize = population.size / 2
    halfSize = if (halfSize % 2 == 0) halfSize else halfSize + 1
    val keys = population.keys.toList()
    val fromKey = keys.first()
    val toKey = keys[halfSize]

    return population.subMap(fromKey, toKey)
}


