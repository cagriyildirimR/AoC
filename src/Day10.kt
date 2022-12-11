fun day10Part1(){
    val input = readInput("Day10").map { it.split(" ") }
    var cycle = 1
    val l = listOf(20, 60, 100, 140, 180, 220)
    var register = 1
    val cs = arrayOf(0,0,0,0,0,0)

    for (i in input) {
        when(i.first()) {
            "noop" -> {
                cycle++
                checking(cycle, cs, l, register)
            }
            "addx" -> {
                cycle++
                checking(cycle, cs, l, register)
                register += i.last().toInt()
                cycle++
                checking(cycle, cs, l, register)
            }
        }
    }
    println(cs.toList())
    println(cs.toList().sum())

}

fun checking(cycle: Int, cs: Array<Int>, l: List<Int>, register: Int) {
    println("cyclec $cycle, register: $register")
    if (cycle in l){
        cs[l.indexOf(cycle)] = cycle * register
    }
}

fun day10Part2(){
    val input = readInput("Day10").map { it.split(" ") }
    var cycle = 1
    var x = 1

    val result = Array(241) {"#"}

    for (i in input) {
        val instruction = i.first()

        when(instruction) {
            "noop" -> {
                result[cycle] = sprite(x, cycle)
                cycle++
            }
            "addx" -> {
                result[cycle] = sprite(x, cycle)
                cycle++
                x += i.last().toInt()
                result[cycle] = sprite(x, cycle)
                cycle++
            }
        }
    }

    for (i in result.indices) {
        print(result[i])
        if ((i+1) % 40 == 0) println()
    }
}

fun sprite(x: Int, c: Int): String {
    return if (c % 40 in x-1..x+1) "#" else "."
}

//ERCREPCJ