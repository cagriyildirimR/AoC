data class Monkey(
    val id: Int,
    var items: MutableList<Long> = mutableListOf(),
    var op: Operation = Operation("", 0),
    var test: Long = 0,
    var ifTrue: Int = 0,
    var ifFalse: Int = 0,
    var inspection: Long = 0
)

data class Operation(val type: String, val y: Long) {
    fun calculate(x: Long): Long {
        return when (type) {
            "*" -> x * y
            "+" -> x + y
            else -> x * x
        }
    }
}

fun day11Part1() {
    val ms = parseInput()
    calculate(ms, 20) {
        it / 3
    }
}

fun day11Part2() {
    val ms = parseInput()
    calculate(ms, 10_000) {
        it % ms.map { it.test }.reduce(Long::times)
    }
}

fun calculate(ms: MutableList<Monkey>, n: Int, f: (Long) -> Long) {

    repeat(n) {
        for (monke in ms) {
            for (i in monke.items) {
                val w = f(monke.op.calculate(i))
                monke.inspection++
                if (w % monke.test == 0L) {
                    with(ms[monke.ifTrue]) {
                        items.add(w)
                    }
                } else {
                    with(ms[monke.ifFalse]) {
                        items.add(w)
                    }
                }
            }
            monke.items = mutableListOf()
        }
    }
    val x = ms.map { it.inspection }.sortedDescending()
    (x[0] * x[1]).print()

}

fun parseInput(): MutableList<Monkey> {
    val input = readInput("Day11")

    val ms = mutableListOf<Monkey>()
    var m = 0

    // parsing input
    for (i in input) {
        when {
            i.take(6) == "Monkey" -> {
                ms.add(Monkey(m))
                m++
            }

            i.trim().take(5) == "Start" -> {
                i.takeLastWhile { it != ':' }.split(",").forEach { ms[m - 1].items.add(it.trim().toLong()) }
            }

            i.trim().take(9) == "Operation" -> {
                val p = i.takeLastWhile { it != '=' }.trim().split(" ")
                ms[m - 1].op = if (p[2] == "old") Operation("", 0) else Operation(p[1], p[2].toLong())
            }

            i.trim().take(4) == "Test" -> {
                ms[m - 1].test = i.split(" ").last().toLong()
            }

            i.trim().take(7) == "If true" -> {
                ms[m - 1].ifTrue = i.split(" ").last().toInt()
            }

            i.trim().take(8) == "If false" -> {
                ms[m - 1].ifFalse = i.split(" ").last().toInt()
            }
        }
    }
    return ms
}