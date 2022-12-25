import java.lang.Math.pow
import kotlin.math.pow

private const val BASEFIVE = 5.0
fun day25Part1() {
    val input = readInput("Day25Text")

    val table = mapOf(
        '2' to 2,
        '1' to 1,
        '0' to 0,
        '-' to -1,
        '=' to -2
    )

    var result = 0.0
    for (i in input) {
        var size = i.lastIndex
        var t = 0.0
        i.forEach { c ->
            t += (table[c]!! * BASEFIVE.pow(size.toDouble()))
            size--
        }
        result += t
    }
    result.toLong().print()

    val encodingChars = listOf('0', '1', '2', '=', '-')
    val adjustments = listOf(0, 1, 2, -2, -1)

    var encoded = ""

    var value = result
    while (value > 0) {
        val encodingCharIndex = (value % 5).toInt()
        value = (value - adjustments[encodingCharIndex]) / 5
        encoded = encodingChars[encodingCharIndex] + encoded
    }
    encoded.print()
}
