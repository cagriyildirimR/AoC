fun day12Part1() {
    val input = readInput("Day12")

    val visited = MutableList(input.size) { MutableList(input[0].length) { false } }
    val dist = MutableList(input.size) { MutableList(input[0].length) { 999_999 } }
    val prev = MutableList(input.size) { MutableList<Point?>(input[0].length) { null } }


    // find Start point
    var s = Point(0, 0)
    var e = Point(0, 0)
    for (rows in input.indices) {
        for (i in input[rows].indices) {
            if (input[rows][i] == 'S') {
                s = Point(rows, i)
            }
            if (input[rows][i] == 'E') {
                e = Point(rows, i)
            }
        }
    }

    fun movable(p: Point, q: Point): Boolean {
        if (input[q.first][q.second] in 'a'..input[p.first][p.second] + 1) {
            return true
        }
        if (input[p.first][p.second] == 'S') {
            return true
        }
        if (input[q.first][q.second] == 'E' && input[p.first][p.second] == 'z') {
            return true
        }
        return false
    }

    fun neighbours(p: Pair<Int, Int>): List<Pair<Int, Int>> {
        val result = mutableListOf<Pair<Int, Int>>()
        if (p.first > 0) result.add(Pair(p.first - 1, p.second)) // North
        if (p.first < input.lastIndex) result.add(Pair(p.first + 1, p.second)) // South
        if (p.second > 0) result.add(Pair(p.first, p.second - 1)) // West
        if (p.second < input.first().lastIndex) result.add(Pair(p.first, p.second + 1)) // East

        return result.filter { movable(p, it) && !visited[it.first][it.second] }
    }

    fun shortestUnvisited(): Point? {
        var min: Point? = null
        for (i in dist.indices) {
            for (j in dist[i].indices) {
                if (!visited[i][j] && (min == null || dist[i][j] < dist[min.first][min.second])) {
                    min = Point(i, j)
                }
            }
        }
        return min
    }


    fun getDist(p: Point): Int {
        return dist[p.first][p.second]
    }

    var c = s
    dist[c.first][c.second] = 0

    do {
        visited[c.first][c.second] = true
        for (n in neighbours(c)) {
            if (getDist(n) > getDist(c) + 1) {
                dist[n.first][n.second] = dist[c.first][c.second] + 1
                prev[n.first][n.second] = c
            }
        }
        val x = shortestUnvisited()
        if (x != null) {
            c = x
        }
    } while (x != null)

    println(dist[e.first][e.second])
}

typealias Point = Pair<Int, Int>

fun day12Part2() {
    var input = readInput("Day12")

    fun solve(t: Point): Int {
        val visited = MutableList(input.size) { MutableList(input[0].length) { false } }
        val dist = MutableList(input.size) { MutableList(input[0].length) { 999_999 } }
        val prev = MutableList(input.size) { MutableList<Point?>(input[0].length) { null } }


        // find Start point
        var e = Point(0, 0)
        for (rows in input.indices) {
            for (i in input[rows].indices) {
                if (input[rows][i] == 'E') {
                    e = Point(rows, i)
                }
            }
        }

        fun movable(p: Point, q: Point): Boolean {
            if (input[q.first][q.second] in 'a'..input[p.first][p.second] + 1) {
                return true
            }
            if (input[p.first][p.second] == 'S') {
                return true
            }
            if (input[q.first][q.second] == 'E' && input[p.first][p.second] == 'z') {
                return true
            }
            return false
        }

        fun neighbours(p: Pair<Int, Int>): List<Pair<Int, Int>> {
            val result = mutableListOf<Pair<Int, Int>>()
            if (p.first > 0) result.add(Pair(p.first - 1, p.second)) // North
            if (p.first < input.lastIndex) result.add(Pair(p.first + 1, p.second)) // South
            if (p.second > 0) result.add(Pair(p.first, p.second - 1)) // West
            if (p.second < input.first().lastIndex) result.add(Pair(p.first, p.second + 1)) // East

            return result.filter { movable(p, it) && !visited[it.first][it.second] }
        }

        fun shortestUnvisited(): Point? {
            var min: Point? = null
            for (i in dist.indices) {
                for (j in dist[i].indices) {
                    if (!visited[i][j] && (min == null || dist[i][j] < dist[min.first][min.second])) {
                        min = Point(i, j)
                    }
                }
            }
            return min
        }


        fun getDist(p: Point): Int {
            return dist[p.first][p.second]
        }

        var c = t
        dist[c.first][c.second] = 0

        do {
            visited[c.first][c.second] = true
            for (n in neighbours(c)) {
                if (getDist(n) > getDist(c) + 1) {
                    dist[n.first][n.second] = dist[c.first][c.second] + 1
                    prev[n.first][n.second] = c
                }
            }
            val x = shortestUnvisited()
            if (x != null) {
                c = x
            }
        } while (x != null)

        println(dist[e.first][e.second])
        return dist[e.first][e.second]
    }

    val result = mutableListOf<Int>()
    for (i in input.indices) {
        for (j in input[i].indices) {
            if (input[i][j] == 'a') result.add(solve(Point(i, j)))
        }
    }
    result.min().print()
}
