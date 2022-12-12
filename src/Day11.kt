import java.math.BigInteger

val OLD = -1L
class Monkey(val index: Int) {
    private lateinit var op: OP
    var opValue: Long? = null
    var divisibleBy: Long = -1

    var monkeyToThrowIfTrue = -1
    var monkeyToThrowIfFalse = -1

    var inspected = 0L

    val items = ArrayList<Long>()
    fun setItems(items: List<String>) {
        this.items.addAll(items.map { it.trim().toLong() })
    }

    fun setOperation(s: String) {
        op = when (s) {
            "*" -> OP.MULTI
            "+" -> OP.ADD
            "-" -> OP.SUB
            else -> TODO()
        }
    }

    fun getNewWorry(item: Long): Long {
        val opVal = if (opValue!! == OLD) item else opValue!!
        return when (op) {
            OP.ADD -> item + opVal
            OP.SUB -> TODO()
            OP.MULTI -> item * opVal
            OP.DIV -> TODO()
        }
    }

    fun isDivisibleBy(value: Long): Boolean {
        return value % divisibleBy == 0L
    }

    fun addItem(newItem: Long) {
        items.add(newItem)
    }
}

enum class OP {
    ADD,
    SUB,
    MULTI,
    DIV
}


fun main() {
    fun part1(input: List<String>): Int {
        val monkeys = ArrayList<Monkey>()
        for (rules in input.withIndex()) {
            val monkey = Monkey(rules.index)
            for (rule in rules.value.lines()) {
                if (rule.contains("starting items", true)) {
                    val items = rule.substringAfter(": ").split(",")
                    println("items to add = ${items}")
                    monkey.setItems(items)
                } else if (rule.contains("Operation")) {
                    val split = rule.substringAfter("= ").split(" ")
                    monkey.setOperation(split[1])
                    monkey.opValue = split.last().toLongOrNull() ?: OLD
                } else if (rule.contains("Test")) {
                    monkey.divisibleBy = rule.split(" ").last().toLong()
                } else {
                    if (rule.contains("true")) {
                        monkey.monkeyToThrowIfTrue = rule.split(" ").last().toInt()
                    }
                    if (rule.contains("false")) {
                        monkey.monkeyToThrowIfFalse = rule.split(" ").last().toInt()
                    }
                }
            }
            monkeys.add(monkey)
        }

        fun oneRound() {
            for (i in monkeys.indices) {
                val monkey = monkeys[i]
                val items = ArrayList(monkeys[i].items)
                println("Monkey $i:")
                for (item in items) {
                    monkey.inspected ++
                    println("inspects and item with level : $item")
                    var multiplied = monkey.getNewWorry(item)
                    println("- worry level multiplied by ${monkey.opValue} is $multiplied")
                    multiplied = multiplied/3
                    println("- gets bored div by 3 = $multiplied")
                    val isDivisible = monkey.isDivisibleBy(multiplied)
                    println("- $multiplied is ${if(isDivisible) "" else "NOT" } divisible by ${monkey.divisibleBy}")
                    monkey.items.remove(item)
                    var targetMonkey = -1
                    if (isDivisible) {
                        targetMonkey = monkey.monkeyToThrowIfTrue
                        monkeys[monkey.monkeyToThrowIfTrue].addItem(multiplied)
                    } else {
                        targetMonkey = monkey.monkeyToThrowIfFalse
                        monkeys[monkey.monkeyToThrowIfFalse].addItem(multiplied)
                    }
                    println("- item with worry $multiplied is thrown to $targetMonkey")
                }
            }
        }

        repeat(20) {
            oneRound()
        }

        println("after 20 rounds")
        for (monkey in monkeys) {
            println("monkey ${monkey.index} (inspected ${monkey.inspected} times): ${monkey.items}")
        }

        println("monkeys.sortBy { it.inspected } = ${monkeys.sortedBy { it.inspected }.map { "${it.index}>"+it.inspected }}")

        // monkey round begins

        return 0
    }

    fun part2(input: List<String>): Int {
        val monkeys = ArrayList<Monkey>()
        for (rules in input.withIndex()) {
            val monkey = Monkey(rules.index)
            for (rule in rules.value.lines()) {
                if (rule.contains("starting items", true)) {
                    val items = rule.substringAfter(": ").split(",")
                    // println("items to add = ${items}")
                    monkey.setItems(items)
                } else if (rule.contains("Operation")) {
                    val split = rule.substringAfter("= ").split(" ")
                    monkey.setOperation(split[1])
                    monkey.opValue = split.last().toLongOrNull() ?: OLD
                } else if (rule.contains("Test")) {
                    monkey.divisibleBy = rule.split(" ").last().toLong()
                } else {
                    if (rule.contains("true")) {
                        monkey.monkeyToThrowIfTrue = rule.split(" ").last().toInt()
                    }
                    if (rule.contains("false")) {
                        monkey.monkeyToThrowIfFalse = rule.split(" ").last().toInt()
                    }
                }
            }
            monkeys.add(monkey)
        }

        fun oneRound() {
            for (i in monkeys.indices) {
                val monkey = monkeys[i]
                val items = ArrayList(monkeys[i].items)
                println("Monkey $i:")
                for (item in items) {
                    monkey.inspected ++
                    println("inspects and item with level : $item")
                    val multiplied = monkey.getNewWorry(item)
                    println("- new worry level is $multiplied")
                    // multiplied = multiplied/3
                    // println("- gets bored div by 3 = $multiplied")
                    val isDivisible = monkey.isDivisibleBy(multiplied)
                    println("- $multiplied is ${if(isDivisible) "" else "NOT" } divisible by ${monkey.divisibleBy}")
                    monkey.items.remove(item)
                    var targetMonkey = -1
                    if (isDivisible) {
                        targetMonkey = monkey.monkeyToThrowIfTrue
                        monkeys[monkey.monkeyToThrowIfTrue].addItem(multiplied)
                    } else {
                        targetMonkey = monkey.monkeyToThrowIfFalse
                        monkeys[monkey.monkeyToThrowIfFalse].addItem(multiplied)
                    }
                    println("- item with worry $multiplied is thrown to $targetMonkey")
                }
            }
        }

        repeat(20) {
            oneRound()
            if (it+1==1 || it+1 == 20 || it+1 == 1000 || it+1 == 2000 || it+1 == 10000) {
                println("after ${it+1} rounds")
                for (monkey in monkeys) {
                    println("monkey ${monkey.index} inspected items ${monkey.inspected} times)")
                }
                Long.MAX_VALUE
                BigInteger.ONE
            }
        }

        /*println("after 10000 rounds")
        for (monkey in monkeys) {
            println("monkey ${monkey.index} (inspected ${monkey.inspected} times): ${monkey.items}")
        }*/

        // println("monkeys.sortBy { it.inspected } = ${monkeys.sortedBy { it.inspected }.map { "${it.index}>"+it.inspected }}")

        // monkey round begins

        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputSepByGap("Day11_test")

//    val test1Result = part1(testInput)
//    check(test1Result == 0)

    // val testInput2 = readInput("Day10_test2")

    //val test2Result = part1(testInput2)
    //check(test2Result == -1)

    val input = readInputSepByGap("Day11")
    // println(part1(input))


    val part2testResult = part2(testInput)
    //check(part2testResult == 36)

//    val input = readInput("Day10")
    //println(part2(input))
    // val input = readInput("Day09")
    //println(part2(input))
}
