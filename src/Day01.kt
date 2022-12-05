fun main() {
    fun part1MostCalories(input: List<String>): Int {
        var currentElfIndex = 0
        var maxElfCalories = 0

        var elfCalorieCount = 0
        input.forEach {
            if (it.isEmpty()) {
                if (elfCalorieCount > maxElfCalories) {
                    // println("elfCalorieCount $elfCalorieCount")
                    maxElfCalories = elfCalorieCount
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

    val input = readInput("Day01")
    println(part1MostCalories(input))
    println(part2SumOfTop3CalorieElves(input))
}
