import minigames.GeneticAlgorithms
import minigames.Ship
import minigames.ShipsGenerator
import java.util.*
import kotlin.system.measureTimeMillis

const val BOARD_SIZE = 10
const val POPULATION_SIZE = 200
val generator = ShipsGenerator(BOARD_SIZE)
val geneticMutator = GeneticAlgorithms(BOARD_SIZE)
fun main() {
//    print(AddDigits().addDigits(22))

    val executionTime = measureTimeMillis {
        val playShips = listOf(4, 3, 3, 2, 2, 2, 1, 1, 1, 1)

        var population: SortedMap<Double, List<Ship>> = createPopulation(playShips, POPULATION_SIZE)
        println("исходная популяция:\t ${population.keys}")

        var generationCount = 0

        while (!population.containsKey(0.0)) {
            val bestHalf = selectBestHalf(population)
            println("best half:\t ${bestHalf.keys}")
            val afterMutation = mutation(bestHalf)
            println("after mutation:\t ${afterMutation.keys}")
            population = createPopulation(playShips, POPULATION_SIZE - afterMutation.size)
            population.putAll(afterMutation)
            println("new generation:\t ${population.keys}")

            generationCount++
        }
        val ssss = population.getValue(0.0)
        generator.printMask(generator.shipsFitness(ssss))
        println("сгенерировано поколений: \t $generationCount")
    }

    println("время выполнения: $executionTime")

}

//todo вынести  генератор популяции в класс генетических аглгоритмов
private fun createPopulation(playShips: List<Int>, populationSize: Int): SortedMap<Double, List<Ship>> {

    var fitnessSum = Double.MAX_VALUE
    val population = sortedMapOf<Double, List<Ship>>()

    while (population.size < populationSize && fitnessSum != 0.0) {
        val ships = generator.initShips(playShips)
        val newBoard = generator.shipsFitness(ships)
        fitnessSum = generator.getFitnessSum(newBoard)

        population[fitnessSum] = ships
    }
    return population
}

private fun mutation(bestHalf: MutableMap<Double, List<Ship>>): SortedMap<Double, List<Ship>> {
    val mutateHalf = mutableMapOf<Double, List<Ship>>()
    val keyList = bestHalf.keys.toList()

    for (i in 0 until keyList.lastIndex step 2) {
        val mather = bestHalf.getValue(keyList[i])
        val father = bestHalf.getValue(keyList[i + 1])

        val mutatePair = geneticMutator.kPointCrossing(4, mather.toMutableList(), father.toMutableList())

        for (element in mutatePair.toList()) {
            val newBoard = generator.shipsFitness(element)
            val fitnessSum = generator.getFitnessSum(newBoard)
            mutateHalf[fitnessSum] = element
            if (fitnessSum == 0.0)
                return mutateHalf.toSortedMap()
        }

    }
    return mutateHalf.toSortedMap()
}

private fun selectBestHalf(population: SortedMap<Double, List<Ship>>): MutableMap<Double, List<Ship>> {
    var halfSize = population.size / 2
    halfSize = if (halfSize % 2 == 0) halfSize else halfSize + 1
    val keys = population.keys.toList()
    val fromKey = keys.first()
    val toKey = keys[halfSize]

    return population.subMap(fromKey, toKey)
}