import kotlin.math.abs

fun day09Part1() {
    val input = readInput("Day09")

    var Head = Pair(0, 0)
    var Tail = Pair(0, 0)
    val visited = mutableSetOf<Pair<Int, Int>>()

    for (i in input) {
        val m = i.split(" ")
        var c = m.last().toInt()
        val dir = m.first()
        while (c > 0) {
            Head = headMove(Head, dir)
            c--
            Tail = tailMove(Head, Tail)
            visited.add(Tail)
        }
    }
    println(visited.size)
}

fun headMove(h: Pair<Int, Int>, dir: String): Pair<Int, Int> {
    return when (dir) {
        "R"  -> Pair(h.first + 1, h.second)
        "L"  -> Pair(h.first - 1, h.second)
        "U"  -> Pair(h.first, h.second + 1)
        else -> Pair(h.first, h.second - 1)
    }
}

fun tailMove(h: Pair<Int, Int>, t: Pair<Int, Int>): Pair<Int, Int> {
    return when {
        (h.first - t.first) > 1    -> if (h.second == t.second) Pair(t.first + 1, t.second) else if (h.second > t.second) Pair(t.first + 1, t.second + 1) else Pair(t.first + 1, t.second - 1) // H(2,1) T(0,0) -> T(1,1)
        (h.first - t.first) < -1   -> if (h.second == t.second) Pair(t.first - 1, t.second) else if (h.second > t.second) Pair(t.first - 1, t.second + 1) else Pair(t.first - 1, t.second - 1)
        (h.second - t.second) > 1  -> if (h.first == t.first) Pair(t.first, t.second+1) else if (h.first > t.first) Pair(t.first + 1, t.second + 1) else Pair(t.first - 1, t.second + 1)
        (h.second - t.second) < -1 -> if (h.first == t.first) Pair(t.first, t.second-1) else if (h.first > t.first) Pair(t.first + 1, t.second - 1) else Pair(t.first - 1, t.second - 1)
        else                       -> t
    }
}

fun day09Part2() {
    val input = readInput("Day09")
    val l = Array<Pair<Int, Int>>(10) { Pair(0,0) }
    val visited = mutableSetOf<Pair<Int, Int>>()

    for (i in input) {
        val m = i.split(" ")
        var c = m.last().toInt()
        val dir = m.first()
        while (c > 0) {
            l[0] = headMove(l[0], dir)
            c--
            for (j in 1..9) {
                l[j] = tailMove(l[j-1], l[j])
            }
            visited.add(l[9])
        }
    }
    println(visited.size)
}
