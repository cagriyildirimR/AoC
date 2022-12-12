
// Create a class representing a directory
class Dir(val up: Dir? = null) {
    // Maps of subdirectories and files in this directory
    val subdirs = mutableMapOf<String, Dir>()
    val files = mutableMapOf<String, Long>()
    var sum = 0L
}

fun day07Part1() {
    // Set up the root directory
    val root = Dir()
    var currentDir = root

    // Read the input
    val input = readInput("Day07")

    // Process each line of the input
    for (i in input) {
        // Check if the i starts with the "cd" command
        when {
            i.startsWith("$ cd ") -> {
                val dir = i.substringAfter("$ cd").trim()

                // Check if the user is trying to navigate to the root directory
                currentDir = when (dir) {
                    "/" -> root
                    // Check if the user is trying to navigate to the parent directory
                    ".." -> currentDir.up!!
                    // Navigate to the specified subdirectory
                    else -> currentDir.subdirs.getOrPut(dir) { Dir(currentDir) }
                }
            }
            // Check if the i starts with the "ls" command
            i.startsWith("$ ls") -> {
                // Ignore this command
            }
            else -> {
                // Split the i into the size and name of the file/directory
                val (size, name) = i.split(" ")

                // Check if this i describes a directory
                if (size == "dir") {
                    // Create the directory and add it to the map of subdirectories
                    currentDir.subdirs.getOrPut(name) { Dir(currentDir) }
                } else {
                    // Add the file to the map of files in the current directory
                    currentDir.files[name] = size.toLong()
                }
            }
        }
    }

    // Calculate the total size of the file system
    var result = 0L
    val dirs = ArrayList<Dir>()
    fun scan(dir: Dir): Long {
        var sum = 0L
        for (d in dir.subdirs.values) {
            sum += scan(d)
        }
        sum += dir.files.values.sum()
        if (sum <= 100000) result += sum
        dir.sum = sum
        dirs.add(dir)
        return sum
    }

    val total = scan(root)
    println(result)

    // Check if the total size is greater than the maximum allowed size
    val maxAllowed = 70000000L
    val needed = 30000000L

    dirs.sortBy { it.sum }

    // Calculate the amount to delete and find the directory containing the largest amount to delete
    val delete = total - (maxAllowed - needed)
    val largestDir = dirs.find { it.sum >= delete }

    // Print the size
    largestDir?.sum?.print()
}