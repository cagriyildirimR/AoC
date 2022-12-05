import java.util.Stack

val input5 = readInput("Day05")

val crates = input5.take(8).reversed()
val instructions =input5.drop(10).map { i -> i.split("move", "from", "to", " ").filter { it != "" }.map { it.toInt() } }

fun day05Part2(){
    val conveyor = Stack<String>()

    val listOfPiles = List(9) { Stack<String>() }

    // initialize piles
    for (c in crates){
        for (i in c.indices step 4) {
            val o = c.slice(i..i+2)
            if (o != "   ")listOfPiles[i/4].push(o)
        }
    }

    // move 4 from 9 to 1
    for (i in instructions){
        repeat(i[0]){
            conveyor.push(listOfPiles[i[1]-1].pop())
        }

        repeat(i[0]){
            listOfPiles[i[2]-1].push(conveyor.pop())
        }
    }

    listOfPiles.forEach { it.peek().print() }
}

fun day05Part1(){

    val listOfPiles = List(9) { Stack<String>() }

    // initialize piles
    for (c in crates){
        for (i in c.indices step 4) {
            val o = c.slice(i..i+2)
            if (o != "   ")listOfPiles[i/4].push(o)
        }
    }

    // move 4 from 9 to 1
    for (i in instructions){
        repeat(i[0]){
            listOfPiles[i[2]-1].push(listOfPiles[i[1]-1].pop())
        }
    }

    listOfPiles.forEach { it.peek().print() }
}
