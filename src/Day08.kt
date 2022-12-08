fun main() {

    fun findOuter(treeArray: Array<IntArray>): Int {
        val cols = treeArray[0].size
        val rows = treeArray.size

        var count = 0
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                when {
                    j==0 -> count++
                    i==0 -> count++
                    i==rows-1 -> count++
                    (j+1)%cols==0 -> count++
                }
            }
        }
        return count
    }

    fun isVisibleFromAnySide(treeArray: Array<IntArray>, row: Int, col: Int): Boolean {
        val cols = treeArray[0].size
        val rows = treeArray.size

        val currentNode = treeArray[row][col]
        var isLeftBlocked = false
        var isRightBlocked = false

        println("currentNode = ${currentNode} at $row,$col")
        for (i in 0 until cols) {
            // check left side
            if (i < col) {
                if (treeArray[row][i] >= currentNode) {
                    isLeftBlocked = true
                    println("isLeftBlocked = ${isLeftBlocked}")
                }
            } else if (i > col) {
                // check right side
                if (treeArray[row][i] >= currentNode) {
                    isRightBlocked = true
                    println("isRightBlocked = ${isRightBlocked}")
                }
            }
        }

        var isTopBlocked = false
        var isBottomBlocked = false

        for (i in 0 until rows) {
            // check top side
            if (i < row) {
                if (treeArray[i][col] >= currentNode) {
                    isTopBlocked = true
                    println("isTopBlocked = ${isTopBlocked}")
                }
            } else if (i > row) {
                // check bottom side
                if (treeArray[i][col] >= currentNode) {
                    isBottomBlocked = true
                    println("isBottomBlocked = ${isBottomBlocked}")
                }
            }
        }

        return (!isLeftBlocked || !isRightBlocked || !isBottomBlocked || !isTopBlocked)
    }

    fun getScenicScore(treeArray: Array<IntArray>, row: Int, col: Int): Int {
        val cols = treeArray[0].size
        val rows = treeArray.size

        val currentNode = treeArray[row][col]
        var score = 1

        println("currentNode = ${currentNode} at $row,$col")
        var canSeeTreeCount = 0
        for (i in col-1 downTo 0) {
            // check towards left side
                println("check towards left side $row,$i")
            if (treeArray[row][i] >= currentNode) {
                canSeeTreeCount ++
                break
            } else {
                canSeeTreeCount ++
            }
        }
        println("canSeeTreeCount = ${canSeeTreeCount}")
        score *= canSeeTreeCount
        canSeeTreeCount = 0
        for (i in col+1 until cols) {
            // check towards right side
            if (treeArray[row][i] >= currentNode) {
                canSeeTreeCount ++
                break
            } else {
                canSeeTreeCount ++
            }
        }
        println("canSeeTreeCount = ${canSeeTreeCount}")
        score *= canSeeTreeCount
        canSeeTreeCount = 0

        for (i in row-1 downTo 0) {
            // check towards top
            if (treeArray[i][col] >= currentNode) {
                canSeeTreeCount ++
                break
            } else {
                canSeeTreeCount ++
            }
        }
        println("canSeeTreeCount = ${canSeeTreeCount}")
        score *= canSeeTreeCount
        canSeeTreeCount = 0

        for (i in row+1 until rows) {
            // check towards bottom side
            if (treeArray[i][col] >= currentNode) {
                canSeeTreeCount ++
                break
            } else {
                canSeeTreeCount ++
            }
        }
        println("canSeeTreeCount = ${canSeeTreeCount}")
        score *= canSeeTreeCount

        return score
    }

    fun findInner(treeArray: Array<IntArray>): Int {
        val cols = treeArray[0].size - 1
        val rows = treeArray.size - 1

        var count = 0
        for (i in 1 until rows) {
            for (j in 1 until cols) {
                if (isVisibleFromAnySide(treeArray, i, j)) {
                    count++
                }
            }
        }
        return count
    }

    fun findInnerScore(treeArray: Array<IntArray>): Int {
        val cols = treeArray[0].size - 1
        val rows = treeArray.size - 1

        val scenicScoreList = ArrayList<Int>()

        var count = 0
        for (i in 1 until rows) {
            for (j in 1 until cols) {
                val scenicScore = getScenicScore(treeArray, i, j)
                println("scenicScore ${treeArray[i][j]} [$i,$j] = $scenicScore")
                scenicScoreList.add(scenicScore)
            }
        }
        println("max = ${scenicScoreList.max()}")
        return scenicScoreList.max()
    }

    fun part1(input: List<String>): Int {
        val numRows = input.size
        val numCols = input[0].length
        println("numRows = ${numRows}, numCols = $numCols")
        val treeArray = Array(numCols) { IntArray (numRows) }
        for (i in 0 until numRows) {
            for (j in 0 until numCols) {
                treeArray[i][j] = input[i][j].digitToInt()
                print("${treeArray[i][j]},")
            }
            println()
        }

        val countOfOuter = findOuter(treeArray)
        println("countOfOuter = $countOfOuter")
        val countInner = findInner(treeArray)
        println("countInner = $countInner")
        return countOfOuter + countInner
    }

    fun part2(input: List<String>): Int {
        val numRows = input.size
        val numCols = input[0].length
        println("numRows = ${numRows}, numCols = $numCols")
        val treeArray = Array(numCols) { IntArray (numRows) }

        for (i in 0 until numRows) {
            for (j in 0 until numCols) {
                treeArray[i][j] = input[i][j].digitToInt()
                print("${treeArray[i][j]},")
            }
            println()
        }

        val countOfOuter = findOuter(treeArray)
        println("countOfOuter = $countOfOuter")
        val countInner = findInnerScore(treeArray)
        println("countInner = $countInner")
        return countInner
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")

    val test1Result = part1(testInput)
    println("test1Result = $test1Result")
    check(test1Result == 21)

    val input = readInput("Day08")
    println(part1(input))

    val test2Result = part2(testInput)
    println("test2Result = $test2Result")
    check(test2Result == 8)

    //val input = readInput("Day08")
    println(part2(input))
}
