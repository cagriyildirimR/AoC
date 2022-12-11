import kotlin.math.max

fun day08Part1(){
    val input = readInput("Day08")

    var result = input.first().length * 4 - 4
    println(input)

    for (i in 1 until input.lastIndex){
        for (j in 1 until input.lastIndex) {
            if (visible(input[i], j)) {
                result++
//                println("${input[i][j]} row")
            }
            else if (visible(takeColumn(input, j), i)) {
//                println("${input[i][j]} column")
                result++
            }
        }
    }

    println(result)
}

fun visible(l: String, index: Int): Boolean {
    var i = index - 1
    var left = true
    var right = true
    while (i >= 0) {
        if (l[i] < l[index]) i-- else {
            left = false
            break
        }
    }

    i = index + 1
    while (i <= l.lastIndex) {
        if (l[i] < l[index]) i++ else {
            right = false
            break
        }
    }
    return left || right
}

fun takeColumn(l: List<String>, j: Int): String {
    var r = ""
    l.forEach{ r += it[j] }
    return r
}

fun day08Part2() {
    val input = readInput("Day08Test")

    var maxTree = 0
    for (i in input.indices) {
        for (j in input.indices) {
            val r = check(input, i, j)
            if (r > maxTree) {
                maxTree = r
                println("i: $i j: $j max: $maxTree")
            }
        }
    }
    println(maxTree)
}

fun check(input: List<String>, i: Int, j: Int): Int {
    // to right
    var left = 0
    var right = 0
    var up = 0
    var down = 0

    var c = '0'
    for (k in j+1..input[i].lastIndex) {
        c = input[i][k]
        right++
        if (c >= input[i][j]) break
    }

    for (k in j-1 downTo 0) {
        c = input[i][k]
        left++
        if (c >= input[i][j]) break
    }

    for (k in i+1..input.lastIndex) {
        c = input[k][j]
        down++
        if (c >= input[i][j]) break
    }

    for (k in i-1 downTo 0) {
        c = input[k][j]
        up++
        if (c >= input[i][j]) break
    }
    return left * down * right * up
}
