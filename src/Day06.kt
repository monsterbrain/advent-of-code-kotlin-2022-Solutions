import java.util.*

fun main() {
    fun part1(input: String): Int {
        var firstIndex = -1
        input.forEachIndexed { index, char ->
            if (index >= 4) {
                // check last received 4 char
                val last4 = input.substring(index-4, index)
                if (firstIndex == -1 && last4.isUnique()) {
                    firstIndex = index
                }
            }
        }

        return firstIndex
    }

    fun part2(input: String): Int {
        var firstIndex = -1
        input.forEachIndexed { index, char ->
            if (index >= 14) {
                // check last received 4 char
                val last14 = input.substring(index-14, index)
                println("last14 = ${last14} when index=$index")
                if (firstIndex == -1 && last14.isUnique()) {
                    firstIndex = index
                }
            }
        }

        return firstIndex
    }

    // test if implementation meets criteria from the description, like:
    /*val testInput = readInput("Day05_test")
    val part1Result = part1(testInput)
    check(part1Result == "CMZ")*/

    /*val input = readInput("Day05")
    println(part1(input))*/

    // test if implementation meets criteria from the description, like:
    /*val testInput1 = "mjqjpqmgbljsphdztnvjfqwrcgsmlb"
    val test1Result = part1(testInput1)
    check(test1Result == 7)*/

    val testInput2 = "mjqjpqmgbljsphdztnvjfqwrcgsmlb"
    val test2Result = part2(testInput2)
    check(test2Result == 19)

    /*val input = readInput("Day06").take(1)[0]
    println("input = ${input}")
    println(part1(input))*/

    val input = readInput("Day06").take(1)[0]
    println("input = ${input}")
    println(part2(input))
}

private fun String.isUnique(): Boolean {
    return toSet().size == length
}
