
fun day03Part1(){
    val rs = readInput("Day03")

    var priority = 0

    for (i in rs) {
        val size = i.length
        val fh = i.slice(0 until size/2)
        val sh = i.slice(size/2 until size)

        for (j in fh.indices) {
            val c = i[j]
            if (c in sh) {
                priority += priorityCalculator(c)
                break
            }
        }
    }
    println(priority)
}

fun day03Part2(){
    val rs = readInput("Day03")

    var priority = 0

    for (i in rs.indices step 3){
        for (c in rs[i]){
            if (c in rs[i+1] && c in rs[i+2]) {
                priority += priorityCalculator(c)
                break
            }
        }
    }
    println(priority)
}

fun priorityCalculator(c: Char): Int {
    return if (c in "abcdefghijklmnopqrstuvwxyz") c.code - 'a'.code + 1 else c.code - 38
}