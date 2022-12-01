fun Day01() {
    val file = readInput("Day01")

    var max = 0
    var total = 0
    val l = mutableListOf<Int>()

    file.forEach {
        if (it == "") {
            if (total > max) max = total
            l.add(total)
            total = 0
        } else {
            total += it.toInt()
        }
    }
    println(l.maxOf { it })
    println(l.sorted().reversed().take(3).sum())
}
