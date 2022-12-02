import java.io.File


fun day02() {

    val input = File("src/Day02.txt").readLines()
    val resultTable = mapOf(
        "Rock Rock" to 1 + 3,
        "Rock Paper" to 2 + 6,
        "Rock Scissor" to 3 + 0,
        "Paper Rock" to 1 + 0,
        "Paper Paper" to 2 + 3,
        "Paper Scissor" to 3 + 6,
        "Scissor Rock" to 1 + 6,
        "Scissor Paper" to 2 + 0,
        "Scissor Scissor" to 3 + 3
    )

    val myChoice = mapOf('X' to "Rock", 'Y' to "Paper", 'Z' to "Scissor")

    val xyzTable = mapOf(
        'X' to mapOf("Rock" to "Scissor", "Paper" to "Rock", "Scissor" to "Paper"),
        'Y' to mapOf("Rock" to "Rock", "Paper" to "Paper", "Scissor" to "Scissor"),
        'Z' to mapOf("Rock" to "Paper", "Paper" to "Scissor", "Scissor" to "Rock"))

    val opTable = mapOf('A' to "Rock", 'B' to "Paper", 'C' to "Scissor")

    var result1 = 0
    var result2 = 0
    for (i in input) {
        val opponentChoice = opTable[i.first()]!!

        val key1 = opponentChoice + " " + myChoice[i.last()]!!
        result1 += resultTable[key1]!!

        val key2 = opponentChoice + " " + xyzTable[i.last()]!![opponentChoice]
        result2 += resultTable[key2]!!
    }

    result1.print()
    result2.print()
}
