fun day14Part1() {
    val input = readInput("Day14")

    var ymin = 0
    var ymax = 0
    var xmin = 500
    var xmax = 0

    fun calculateMinMax() {
        input.forEach { y ->
            y.split("->").forEach { x ->
                val p = x.split(",").map { it.trim().toInt() }
                if (ymin > p.last()) ymin = p.last()
                if (ymax < p.last()) ymax = p.last()
                if (xmin > p.first()) xmin = p.first()
                if (xmax < p.first()) xmax = p.first()
            }
        }
    }
    calculateMinMax()


    val cave: MutableList<MutableList<Char>> = MutableList(ymax - ymin + 1) {
        MutableList(xmax - xmin + 1) { '.' }
    }

//    render(cave)

    fun caveSet(x: Int, y: Int, t: Char = '#') {
        cave[y - ymin][x - xmin] = t
    }

    fun caveSetN(x: Int, y: Int, t: Char = '#') {
        cave[y][x] = t
    }

    fun initialize() {
        input.forEach { y ->
            val list = y.split("->")
            for (i in 0 until list.lastIndex) {
                val A = list[i].split(",").map { it.trim().toInt() }
                val B = list[i + 1].split(",").map { it.trim().toInt() }

                val x1min = if (A.first() > B.first()) B.first() else A.first()
                val x1max = if (A.first() > B.first()) A.first() else B.first()
                val y1min = if (A.last() > B.last()) B.last() else A.last()
                val y1max = if (A.last() > B.last()) A.last() else B.last()

                if (x1min == x1max) {
                    for (v in y1min..y1max) {
                        caveSet(x1min, v)
                    }
                } else {
                    for (v in x1min..x1max) {
                        caveSet(v, y1min)
                    }
                }
            }
        }
    }

    initialize()
//    render(cave)

    val s = Pair(500 - xmin, ymin) // source
    caveSet(500, 0, 'o')
    var c = s
    var sands = 0

    fun createNewSand(){
        caveSet(500, 0, 'o')
        c = s
        sands++
    }

    fun canFallDown(o: Pair<Int, Int>): Boolean {
        return cave[c.second+1][c.first] == '.'
    }

    fun goDown(o: Pair<Int, Int>) {
        caveSetN(o.first, o.second, '.')
        c = Pair(o.first, o.second+1)
        caveSetN(c.first, c.second, 'o')
    }

    fun canFallLeft(o: Pair<Int, Int>): Boolean {
        return cave[c.second+1][c.first-1] == '.'
    }

    fun goLeft(o: Pair<Int, Int>) {
        caveSetN(o.first, o.second, '.')
        c = Pair(o.first-1, o.second+1)
        caveSetN(c.first, c.second, 'o')
    }

    fun canFallRight(o: Pair<Int, Int>): Boolean {
        return cave[c.second+1][c.first+1] == '.'
    }
    fun goRight(o: Pair<Int, Int>) {
        caveSetN(o.first, o.second, '.')
        c = Pair(o.first+1, o.second+1)
        caveSetN(c.first, c.second, 'o')
    }

    createNewSand()
    while (true) {
        try {
            when {
                canFallDown(c) -> goDown(c)
                canFallLeft(c) -> goLeft(c)
                canFallRight(c) -> goRight(c)
                else -> createNewSand()
            }
        } catch (e: IndexOutOfBoundsException){
            sands--
            break
        }
    }

//    render(cave)
    println(sands)
}

fun render(cave: MutableList<MutableList<Char>>) {
    cave.forEach { it.joinToString(separator = "").print() }
}


fun day14Part2() {
    val input = readInput("Day14")

    var ymin = 0
    var ymax = 0
    var xmin = 500
    var xmax = 0

    fun calculateMinMax() {
        input.forEach { y ->
            y.split("->").forEach { x ->
                val p = x.split(",").map { it.trim().toInt() }
                if (ymin > p.last()) ymin = p.last()
                if (ymax < p.last()) ymax = p.last()
                if (xmin > p.first()) xmin = p.first()
                if (xmax < p.first()) xmax = p.first()
            }
        }
    }
    calculateMinMax()


    val cave: MutableList<MutableList<Char>> = MutableList(ymax - ymin + 3) {
        MutableList(xmax * 2) { '.' }
    }

    cave[cave.lastIndex] = MutableList(xmax + xmax){ '#' }


//    render(cave)

    fun caveSetN(x: Int, y: Int, t: Char = '#') {
        cave[y][x] = t
    }
    fun caveSet(x: Int, y: Int, t: Char = '#') {
//        cave[y - ymin][x - xmin] = t
        caveSetN(x, y ,t)
    }


    fun initialize() {
        input.forEach { y ->
            val list = y.split("->")
            for (i in 0 until list.lastIndex) {
                val A = list[i].split(",").map { it.trim().toInt() }
                val B = list[i + 1].split(",").map { it.trim().toInt() }

                val x1min = if (A.first() > B.first()) B.first() else A.first()
                val x1max = if (A.first() > B.first()) A.first() else B.first()
                val y1min = if (A.last() > B.last()) B.last() else A.last()
                val y1max = if (A.last() > B.last()) A.last() else B.last()

                if (x1min == x1max) {
                    for (v in y1min..y1max) {
                        caveSet(x1min, v)
                    }
                } else {
                    for (v in x1min..x1max) {
                        caveSet(v, y1min)
                    }
                }
            }
        }
    }

    initialize()

//    render(cave)

    val s = Pair(500, 0) // source
//    caveSet(500, 0, 'o')
    var c = s
    var sands = 0

    fun createNewSand(){
        caveSet(s.first, s.second, 'o')
        c = s
        sands++
    }

    fun canFallDown(o: Pair<Int, Int>): Boolean {
        return cave[c.second+1][c.first] == '.'
    }

    fun goDown(o: Pair<Int, Int>) {
        caveSetN(o.first, o.second, '.')
        c = Pair(o.first, o.second+1)
        caveSetN(c.first, c.second, 'o')
    }

    fun canFallLeft(o: Pair<Int, Int>): Boolean {
        return cave[c.second+1][c.first-1] == '.'
    }

    fun goLeft(o: Pair<Int, Int>) {
        caveSetN(o.first, o.second, '.')
        c = Pair(o.first-1, o.second+1)
        caveSetN(c.first, c.second, 'o')
    }

    fun canFallRight(o: Pair<Int, Int>): Boolean {
        return cave[c.second+1][c.first+1] == '.'
    }
    fun goRight(o: Pair<Int, Int>) {
        caveSetN(o.first, o.second, '.')
        c = Pair(o.first+1, o.second+1)
        caveSetN(c.first, c.second, 'o')
    }

    createNewSand()
    while (true) {
        try {
            when {
                canFallDown(c) -> goDown(c)
                canFallLeft(c) -> goLeft(c)
                canFallRight(c) -> goRight(c)
                else -> {
                    if (cave[s.second][s.first] == 'o') break else createNewSand()
                }
            }
        } catch (e: IndexOutOfBoundsException){
            sands--
            break
        }
    }

//    render(cave)
    println(sands)

}
