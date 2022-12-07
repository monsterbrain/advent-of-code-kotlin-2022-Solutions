fun main() {
    fun String.isInside(range: String): Boolean {
        val start1 = this.split("-")[0].toInt() //6
        val end1 = this.split("-")[1].toInt() //6

        val start2 = range.split("-")[0].toInt() //4
        val end2 = range.split("-")[1].toInt()  //6

        return start1 >= start2 && end1 <= end2
    }

    fun String.overlaps(range: String): Boolean {
        val start1 = this.split("-")[0].toInt() //5
        val end1 = this.split("-")[1].toInt() //7

        val start2 = range.split("-")[0].toInt() //7
        val end2 = range.split("-")[1].toInt()  //9

        return start1 in start2..end2 || end1 in start2..end2
    }

    fun part1(input: List<String>): Int {
        var count = 0
        input.forEach {
            val ranges = it.split(",")
            val range1 = ranges[0]
            val range2 = ranges[1]

            val containsOther = range1.isInside(range2) || range2.isInside(range1)
            if (containsOther) count ++
            // println("$it has overlap $containsOther")
        }
        return count
    }

    fun part2(input: List<String>): Int {
        var count = 0
        input.forEach {
            val ranges = it.split(",")
            val range1 = ranges[0]
            val range2 = ranges[1]

            val containsOther = range1.overlaps(range2) || range2.overlaps(range1)
            if (containsOther) count ++
            // println("$it has overlap $containsOther")
        }
        return count
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
