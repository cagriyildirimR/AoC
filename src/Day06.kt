
fun day06Part1(){
    val s = 4
    val input = readInput("Day06").first()

    var result = 0

    for (i in 0..input.length-s){
        if (input.slice(i until i+s).toSet().size == s){
            result = i + s
            break
        }
    }
    println(result)
}

fun day06Part2(){
    val s = 14
    val input = readInput("Day06").first()

    var result = 0

    for (i in 0..input.length-s){
        if (input.slice(i until i+s).toSet().size == s){
            result = i + s
            break
        }
    }
    println(result)
}