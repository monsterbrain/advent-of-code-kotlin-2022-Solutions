class Dir(val name: String, var size: Long = 0, val childList: MutableList<Dir> = mutableListOf()) {
    var parentDir: Dir? = null

    fun addSize(filesize: Long) {
        println("add $filesize to $name")
        size += filesize
        parentDir?.let {
            println("add $filesize to parent ${parentDir?.name}")
            parentDir?.addSize(filesize)
        }
    }

    fun getChild(name: String): Dir? {
        return childList.find { it.name == name }
    }

    fun addChild(name: String): Dir? {
        if (getChild(name) == null) {
            val dir = Dir(name)
            dir.parentDir = this
            childList.add(dir)

            return dir
        }
        return null
    }
}
fun main() {

    fun part1(input: List<String>): Long {
        val dirList = arrayListOf<Dir>()
        var currentDir = Dir("/")
        val rootDir = currentDir
        dirList.add(currentDir)

        input.forEach {
            if (it.startsWith("$")) {
                val cmdList = it.substringAfter("$ ").split(" ")
                val cmd = cmdList[0]
                val param = try { cmdList[1] } catch (_:Exception) { null }
                println(it)
                if (cmd == "cd") {
                    when {
                        param!! == "/" -> {
                            currentDir = rootDir
                            println("change to / directory")
                        }
                        param == ".." -> {
                            currentDir = currentDir.parentDir!!
                        }
                        else -> { // cd abc
                            currentDir = currentDir.getChild(param)!!
                            println("change to ${currentDir.name}")
                        }
                    }
                } else if (cmd == "ls") {
                    // isListMode = true // ignore
                }
            } else {
                // list read mode
                println("ls:$it")
                val (a, name) = it.split(" ")
                if (a == "dir") {
                    println("add child $name")
                    val childAdded = currentDir.addChild(name)
                    childAdded?.let { child ->
                        dirList.add(child)
                    }
                } else {
                    // 14848514 b.txt
                    // filesize filename
                    currentDir.addSize(a.toLong())
                }
            }
        }

        val sum = dirList
            .map { it.size }
            .also { println(it) }
            .filter { it < 100000L }
            .also { println(it) }
            .sum()

        return sum
    }

    fun part2(input: List<String>): Long {
        val dirList = arrayListOf<Dir>()
        var currentDir = Dir("/")
        val rootDir = currentDir
        dirList.add(currentDir)

        input.forEach {
            if (it.startsWith("$")) {
                val cmdList = it.substringAfter("$ ").split(" ")
                val cmd = cmdList[0]
                val param = try { cmdList[1] } catch (_:Exception) { null }
                println(it)
                if (cmd == "cd") {
                    when {
                        param!! == "/" -> {
                            currentDir = rootDir
                            println("change to / directory")
                        }
                        param == ".." -> {
                            currentDir = currentDir.parentDir!!
                        }
                        else -> { // cd abc
                            currentDir = currentDir.getChild(param)!!
                            println("change to ${currentDir.name}")
                        }
                    }
                } else if (cmd == "ls") {
                    // isListMode = true // ignore
                }
            } else {
                // list read mode
                println("ls:$it")
                val (a, name) = it.split(" ")
                if (a == "dir") {
                    println("add child $name")
                    val childAdded = currentDir.addChild(name)
                    childAdded?.let { child ->
                        dirList.add(child)
                    }
                } else {
                    // 14848514 b.txt
                    // filesize filename
                    currentDir.addSize(a.toLong())
                }
            }
        }

        val fullSize = 70000000L
        val spaceNeeded = 30000000L

        val sorted = dirList
            .map { Pair(it.name, it.size) }
            .sortedBy { it.second }
            .also { println(it) }

        val max = sorted.maxOf { it.second }

        val spaceAvailable = fullSize - max
        println("spaceAvailable = $spaceAvailable")

        val minSizeToDelete = spaceNeeded - spaceAvailable

        return sorted.filter { it.second > minSizeToDelete }.also { println(it) }.minOf { it.second }.also { println(it) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    val part1Result = part1(testInput)
    println("part1Result = $part1Result")
    check(part1Result == 95437L) {
        println("part1Result = $part1Result")
    }

    /*val input = readInput("Day07")
    println(part1(input))*/

    val input = readInput("Day07")
    println(part2(input))
}
