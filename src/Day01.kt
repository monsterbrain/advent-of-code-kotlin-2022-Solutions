fun main() {
    fun part1MostCalories(input: List<String>): Int {
        var currentElfIndex = 0
        var elfWithMaxCalories = 0
        var maxElfCalories = 0

        var elfCalorieCount = 0
        input.forEach {
            if (it.isEmpty()) {
                if (elfCalorieCount > maxElfCalories) {
                    // println("elfCalorieCount $elfCalorieCount")
                    maxElfCalories = elfCalorieCount
                    elfWithMaxCalories = currentElfIndex
                }

                // iterate next with calorie count reset
                currentElfIndex += 1
                elfCalorieCount = 0
            } else {
                elfCalorieCount += it.toInt()
            }
        }
        return maxElfCalories
    }

    fun part2SumOfTop3CalorieElves(input: List<String>): Int {
        val calorieSumList = ArrayList<Int>()

        var elfCalorieCount = 0
        input.forEach {
            if (it.isEmpty()) {
                calorieSumList.add(elfCalorieCount)
                elfCalorieCount = 0
            } else {
                elfCalorieCount += it.toInt()
            }
        }
        println(calorieSumList)
        return calorieSumList.sortedDescending().take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    /*val testInput = readInput("Day01_test")
    check(part1MostCalories(testInput) == 1)*/

    val input = readInput("Day01")
    println(part1MostCalories(input))
    println(part2SumOfTop3CalorieElves(input))
}
