fun day13Part1() {
    val input = readInput("Day13")
    var count = 0
    for (i in input.indices step 3) {
        if (compare2(input[i], input[i + 1])) count += (1 + i / 3)
    }
    println(count)
}

fun compare2(left: String, right: String): Boolean {
    val (l, ls) = firstAndRest(left)
    val (r, rs) = firstAndRest(right)

    return when {
        (left.isEmpty()) -> true
        (right.isEmpty()) -> false
        (l == "[" && r == "]") -> false
        (r == "[" && l == "]") -> true
        (l == "[" && r != "[") -> compare2(ls, "$r]$rs")
        (r == "[" && l != "[") -> compare2("$l]$ls", rs)
        (l == "]" && r != "]") -> true
        (r == "]" && l != "]") -> false
        r.length > l.length -> true
        l.length > r.length -> false
        r > l -> true
        l > r -> false
        else -> compare2(ls, rs)
    }
}


fun firstAndRest(ss: String): Pair<String, String> {
    if (ss.isEmpty()) return Pair("", "")
    val f = ss.first()
    val first = if (f in "[]") f.toString() else ss.takeWhile { it in "0123456789" }
    val rest = ss.drop(first.length).trimStart(',')
    return Pair(first, rest)
}


fun day13Part2() {
    val input = readInput("Day13").filter { !it.isEmpty() }.toMutableList()
    val two = "[[2]]"
    val six = "[[6]]"
    input.add(two)
    input.add(six)

    input.sortWith { o1, o2 -> if (compare2(o1, o2)) -1 else 1 }

    println((input.indexOf(two) + 1) * (input.indexOf(six) + 1) )
}