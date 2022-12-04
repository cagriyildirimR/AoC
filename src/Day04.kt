
val input = readInput("Day04")
val pairs = input.map { it.split("-", ",").map { v -> v.toInt() } }

fun day04Part1() {
    pairs.filter { p -> (p[2] <= p[0] && p[1] <= p[3]) || (p[0] <= p[2] && p[3] <= p[1]) }.size.print()
}

fun day04Part2() {
    pairs.filter { p -> p[0] in p[2]..p[3] || p[1] in p[2]..p[3] || p[2] in p[0]..p[1] || p[3] in p[0]..p[1] }.size.print()
}