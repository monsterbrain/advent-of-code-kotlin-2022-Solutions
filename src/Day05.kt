import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val stackIndexStep = 4
    val stackList = ArrayList<Stack<String>>()
    data class Command(val num:Int, val from:Int, val to:Int)
    val commands = ArrayList<Command>()

    fun addCrateToList(s: String) {
        var stackPosIndex = 1
        var stackIndex = 0
        while (stackPosIndex < s.length - 1) {
            if (stackIndex >= stackList.size) {
                stackList.add(Stack<String>())
            }
            val crate = s[stackPosIndex]
            if (crate != ' ') {
                // add to stack
                stackList[stackIndex] += crate.toString()
                stackIndex ++
            } else {
                stackIndex ++
            }
            stackPosIndex += 4
        }
    }

    fun moveCrateByCommands() {
        commands.forEach { cmd ->
            repeat(cmd.num) {
                val fromStack = stackList[cmd.from-1]
                val toStack = stackList[cmd.to-1]

                toStack.push(fromStack.pop())
            }
            println("after cmd1 $cmd = $stackList")
        }
    }

    fun moveCrateByCommandsAllAtOnce() {
        commands.forEach { cmd ->
            val cratesToMove = ArrayList<String>().also { list->
                repeat(cmd.num) {
                    list.add(stackList[cmd.from-1].pop())
                }
            }
            cratesToMove.reverse()
            stackList[cmd.to-1].apply {
                cratesToMove.forEach {
                    push(it)
                }
            }
            println("after cmd1 $cmd = $stackList")
        }
    }


    fun addCommandList(it: String) {
        val digits = it.split(" ").filter { it.toIntOrNull() != null }
        commands.add(Command(
            digits[0].toInt(),
            digits[1].toInt(),
            digits[2].toInt()
        ))
    }

    fun part1(input: List<String>): String {
        input.forEach {
            println(it.replace(' ', '#'))
            if (it.contains('[')) {
                addCrateToList(it)
            }

            if (it.contains("move")) {
                addCommandList(it)
            }
        }
        stackList.forEach {
            it.reverse()
        }
        println("stackList b4 moving $stackList")
        moveCrateByCommands()
        println("stackList after moving $stackList")

        return stackList.map { it.pop() }.joinToString("")
    }

    fun part2(input: List<String>): String {
        input.forEach {
            println(it.replace(' ', '#'))
            if (it.contains('[')) {
                addCrateToList(it)
            }

            if (it.contains("move")) {
                addCommandList(it)
            }
        }
        stackList.forEach {
            it.reverse()
        }
        println("stackList b4 moving $stackList")
        moveCrateByCommandsAllAtOnce()
        println("stackList after moving $stackList")

        return stackList.map { it.pop() }.joinToString("")
    }

    // test if implementation meets criteria from the description, like:
    /*val testInput = readInput("Day05_test")
    val part1Result = part1(testInput)
    check(part1Result == "CMZ")*/

    /*val input = readInput("Day05")
    println(part1(input))*/

    // test if implementation meets criteria from the description, like:
   /* val testInput = readInput("Day05_test")
    val part2Result = part2(testInput)
    check(part2Result == "MCD")*/

    val input = readInput("Day05")
    println(part2(input))
}
