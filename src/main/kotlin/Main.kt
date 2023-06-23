import minigames.GeneticAlgorithms
import minigames.Ship
import minigames.ShipsGenerator
import java.util.*

const val BOARD_SIZE = 10
val generator = ShipsGenerator(BOARD_SIZE)
val geneticMutator = GeneticAlgorithms(BOARD_SIZE)
fun main() {
//    print(AddDigits().addDigits(22))

    var counter = 0
    val playShips = listOf(4, 3, 3, 2,2,2,1,1,1,1)
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

    val bestHalfPopulation: MutableMap<Double, List<Ship>>
    val mutatePopulation = mutableMapOf<Double, List<Ship>>()

    if (fitnessSum > 0) {

        bestHalfPopulation = selectBestHalf(population)
        println("лучшая половина:\t ${bestHalfPopulation.keys}")
        mutatePopulation.putAll(mutation(bestHalfPopulation))
//        mutatePopulation.putAll(halfMap)
        val sortMut = mutatePopulation.toSortedMap()


        println("мутиров половина:\t ${sortMut.keys}")
        println("мутиров половина:\t ${GeneticAlgorithms.mutCounter}")
    }

}

private fun mutation(population: MutableMap<Double, List<Ship>>): Map<Double, List<Ship>> {
    val mutateHalf = mutableMapOf<Double, List<Ship>>()
    val keyList = population.keys.toList()

    for (i in 0 until keyList.lastIndex step 2) {
        val mather = population.getValue(keyList[i + 1])
        val father = population.getValue(keyList[i])
        val sexMatherFatherResult = geneticMutator.singlePointCrossing(mather, father)

        geneticMutator.kPointCrossing(2, mather.toMutableList(),father.toMutableList())


        for (child in sexMatherFatherResult.toList()) {
            val mutateSonBoard = generator.shipsFitness(geneticMutator.simpleMutation(child))
            val childSum = generator.getFitnessSum(mutateSonBoard)
            mutateHalf[childSum] = child
        }
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


