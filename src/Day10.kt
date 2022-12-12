fun main() {

    fun part1(input: List<String>): Int {
        var cycles = 0
        var x = 1
        val cycleMap = mutableMapOf<Int, Int>()

        fun noopCycle() {
            cycles += 1
            cycleMap[cycles] = x
        }

        fun addCycle(value: Int) {
            cycles += 1
            cycleMap[cycles] = x
            cycles += 1
            cycleMap[cycles] = x
            x += value
        }

        input.forEach {
            val (cmd, value) = if (it.contains(" ")) it.split(" ") else listOf(it, null)
            when (cmd) {
                "noop" -> noopCycle()
                "addx" -> addCycle(value!!.toInt())
                else -> TODO()
            }
        }

        val cycleCheckList = listOf(20, 60, 100, 140, 180, 220)
        println("x values in")
        for (i in cycleCheckList) {
            println("at $i = ${cycleMap[i]}")
        }
        cycleCheckList.map {
            it* (cycleMap[it]!!)
        }.sum().also { println("sum = $it") }

        println("cyclemap ${cycleMap}")

        return 0
    }

    fun part2(input: List<String>): Int {
        val crtCols = 40
        val crtRows = 6

        var x = 1
        val cycleMap = mutableMapOf<Int, Int>()

        val crtGrid = Array(crtRows) { IntArray(crtCols) }

        println("CRT Grid")
        for (i in 0 until crtRows) {
            for (j in 0 until crtCols) {
                crtGrid[i][j] = '.'.code
                print(crtGrid[i][j].toChar())
            }
            println()
        }

        var crtIndex = 0

        var cycles = 0

        fun getSpriteString(): String {
            var str = ""
            for (i in 1 until x) {
                str += "."
            }
            str+= "###"
            println("X string")
            println(str)
            return str
        }

        fun setCRTPixel(str: String) {

            val crtRow = (crtIndex) / crtCols
            val crtCol = (crtIndex) % crtCols

            val crtPixel = str.getOrElse(crtCol) { '.' }
            if (crtIndex < 240) {
                crtGrid[crtRow][crtCol] = crtPixel.code
            }
        }

        fun noopCycle() {
            cycles += 1
            cycleMap[cycles] = x
            setCRTPixel(getSpriteString())
            crtIndex ++
        }

        fun addCycle(value: Int) {
            cycles += 1
            cycleMap[cycles] = x
            setCRTPixel(getSpriteString())
            crtIndex ++
            cycles += 1
            cycleMap[cycles] = x
            setCRTPixel(getSpriteString())
            crtIndex ++
            x += value
        }

        input.forEach {
            val (cmd, value) = if (it.contains(" ")) it.split(" ") else listOf(it, null)
            when (cmd) {
                "noop" -> noopCycle()
                "addx" -> addCycle(value!!.toInt())
                else -> TODO()
            }
        }

        println("CRT Grid after")
        for (i in 0 until crtRows) {
            for (j in 0 until crtCols) {
                print(crtGrid[i][j].toChar())
            }
            println()
        }

        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")

   /* val test1Result = part1(testInput)
    check(test1Result == -1)*/

    val testInput2 = readInput("Day10_test2")

    //val test2Result = part1(testInput2)
    //check(test2Result == -1)

    /*val input = readInput("Day10")
    println(part1(input))*/


    /*val part2testResult = part2(testInput2)
    check(part2testResult == 36)*/

    val input = readInput("Day10")
    println(part2(input))
    // val input = readInput("Day09")
    //println(part2(input))
}
