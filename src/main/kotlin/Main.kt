import minigames.ShipsGenerator

fun main() {
//    print(AddDigits().addDigits(22))

    var counter = 0
    val generator = ShipsGenerator()
    val board = generator.initBoard(10)
    var ships = generator.initShips(listOf(4, 3, 3,3), 10).toMutableList()


    val fitness = generator.shipsFitness(ships, board)
    var fitnessSum = generator.getFitnessSum(board)

    while (fitnessSum != 0.0 || counter > 100) {

        val mutateShips = generator.simpleMutation(ships,10)

        val newBoard = generator.initBoard(10)
        val newFitness = generator.shipsFitness(mutateShips, newBoard)
        val newFitnessSum = generator.getFitnessSum(newBoard)

        if (newFitnessSum < fitnessSum) {
            fitnessSum = newFitnessSum
            ships.clear()
            ships.addAll(mutateShips)
        }

        counter++
    }

    generator.printMask(board)
    println(fitnessSum)

}
