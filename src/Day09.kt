import kotlin.math.abs
import kotlin.math.sign

class Point(val x: Int, val y: Int) {
    override fun toString(): String {
        return "[$x,$y]"
    }

    override fun equals(other: Any?): Boolean {
        return x == (other as Point).x && y == (other as Point).y
    }
}

class Node(var x: Int, var y: Int, val name: String) {
    val visitedNodes = ArrayList<Point>()
    fun follow(head: Node) {
        if (abs(head.x - x) + abs(head.y - y) > 2) {
            // println("head diagonally far")
            x += (head.x - x).sign
            y += (head.y - y).sign
            // println("move $name by ${(head.x - x).sign}, ${(head.y - y).sign}")
        } else if (abs(head.x - x) > 1) {
            // println("move $name ${(head.x - x).sign}")
            x += (head.x - x).sign
        } else if (abs(head.y - y) > 1) {
            // println("move $name ${(head.y - y).sign}")
            y += (head.y - y).sign
        }
        visited()
    }
    fun visited() {
        visitedNodes.find { it.x == x && it.y == y }?: visitedNodes.add(Point(x,y))
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        val headPos = Node(0, 0, "H")
        val tailPos = Node(0, 0, "T")

        tailPos.visited() // add 0, 0
        fun move(hor: Int, ver: Int, steps: Int) {
            repeat(steps) {
                headPos.x += hor
                headPos.y += ver
                // println("- tail at ${tailPos.x}, ${tailPos.y}")
                tailPos.follow(headPos)
            }
        }

        input.forEach {
            val (dir, steps) = it.split(" ")
            val stepDir = when (dir) {
                "R" -> Point(1, 0)
                "U" -> Point(0, -1)
                "L" -> Point(-1, 0)
                "D" -> Point(0, 1)
                else -> TODO()
            }
            // println("move $it")
            move(stepDir.x, stepDir.y, steps.toInt())
        }

        return tailPos.visitedNodes.count()
    }

    fun part2(input: List<String>): Int {
        val headPos = Node(0, 0, "H")
        val tailList = arrayListOf<Node>().apply {
            repeat(9) {
                add(Node(0, 0, "${it+1}").apply { visited() })
            }
        }

        fun move(hor: Int, ver: Int, steps: Int) {
            repeat(steps) {
                headPos.x += hor
                headPos.y += ver
                // println("- tail at ${tailPos.x}, ${tailPos.y}")
                for (i in tailList.indices) {
                    if (i==0) {
                        tailList[0].follow(headPos)
                    } else {
                        tailList[i].follow(tailList[i-1])
                    }
                }
            }
        }

        input.forEach {
            val (dir, steps) = it.split(" ")
            val stepDir = when (dir) {
                "R" -> Point(1, 0)
                "U" -> Point(0, -1)
                "L" -> Point(-1, 0)
                "D" -> Point(0, 1)
                else -> TODO()
            }
            // println("move $it")
            move(stepDir.x, stepDir.y, steps.toInt())
        }

        return tailList.last().visitedNodes.count()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")

    val test1Result = part1(testInput)
    check(test1Result == 13)

    val input = readInput("Day09")
    println(part1(input))

    val testInput2 = readInput("Day09_test2")

    val test2Result = part2(testInput2)
    check(test2Result == 36)

    // val input = readInput("Day09")
    println(part2(input))
}
