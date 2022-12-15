import kotlin.math.abs

fun day15() {
    val input = readInput("Day15").map {
        it.split("Sensor at x=", ": closest beacon is at x=", ", y=").filter { x -> x != "" }.map { s -> s.toInt() }
    }

    val Y = 2_000_000

    val abc = mutableListOf<List<List<Int>>>()

    for (j in 0..4_000_000) {
        val l = mutableListOf<Pair<Int, Int>>()
        for (i in input) {
            val x = i[0]
            val y = i[1]
            val d = norm1(x, y, i[2], i[3])

            val distanceToY = abs(y - j)

            val remain = d - distanceToY

            l.add(Pair(x - remain, x + remain))
        }
        abc.add(joinPairs(l))
    }

    var result = 0

    for (i in abc[Y]) {
        result = i.last() - i.first()
    }

    result.print()

    for ((y, i) in abc.withIndex()) {
        if (i.size > 1) {
            val x: Long = i[0][1] + 1L
            println("Tuning frequency is: ${x * 4_000_000 + y}")
        }
    }
}

fun joinPairs(l: List<Pair<Int, Int>>): MutableList<List<Int>> {
    val sorted = l.sortedWith(compareBy({ it.first }, { it.second }))
    var start = sorted.first().first
    var end = sorted.first().second
    val result = mutableListOf<List<Int>>()

    for (i in 1..sorted.lastIndex) {
        if (start < sorted[i].first && sorted[i].second < end) {
        } else if (sorted[i].first <= end && sorted[i].second >= end) end = sorted[i].second else {
            result.add(listOf(start, end))
            start = sorted[i].first
            end = sorted[i].second
        }
    }
    result.add(listOf(start, end))
    return result
}

fun norm1(x1: Int, y1: Int, x2: Int, y2: Int): Int {
    val x = abs(x1 - x2)
    val y = abs(y1 - y2)
    return x + y
}
