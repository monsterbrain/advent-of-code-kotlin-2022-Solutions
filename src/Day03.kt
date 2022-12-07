fun main() {
    fun String.firstHalf(): String {
        return substring(0 until length/2)
    }

    fun String.secondHalf(): String {
        return substring(length/2 until length)
    }

    fun findCommon(firstHalf: String, secondHalf: String): Char {
        firstHalf.forEach {
            if (secondHalf.contains(it)) {
                return it
            }
        }
        return ' '
    }

    fun Char.priority(): Int {
        return when (this) {
            in 'a'..'z' -> {
                (code - 'a'.code) + 1
            }
            in 'A'..'Z' -> {
                (code - 'A'.code) + 27
            }
            else -> TODO()
        }
    }

    fun part1(input: List<String>): Int {
        var prioritySum = 0
        input.forEach {
            val firstHalf = it.firstHalf()
            val secondHalf = it.secondHalf()

            val common = findCommon(firstHalf, secondHalf)
            prioritySum += common.priority()
        }
        return prioritySum
    }

    fun findIn(input: String, second: String, third: String): Char {
        input.forEach {
            if(second.contains(it) && third.contains(it)) {
                return it
            }
        }
        TODO()
    }

    fun part2(input: List<String>): Int {
        var prioritySum = 0
        input.chunked(3) {
            val common = findIn(it[0], it[1], it[2])
            // println("common $common")
            prioritySum += common.priority()
        }
        return prioritySum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
