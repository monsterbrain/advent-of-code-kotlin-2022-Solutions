enum class SHAPE(val score: Int, val oppCode: Char, val ourCode:Char) {
    ROCK(1, 'A', 'X'),
    PAPER(2, 'B', 'Y'),
    SCISSORS(3, 'C', 'Z');

    companion object {
        fun getOppShape(code: Char): SHAPE {
            return when (code) {
                ROCK.oppCode -> ROCK
                PAPER.oppCode -> PAPER
                SCISSORS.oppCode -> SCISSORS
                else -> TODO()
            }
        }

        fun getOurShape(code: Char): SHAPE {
            return when (code) {
                ROCK.ourCode -> ROCK
                PAPER.ourCode -> PAPER
                SCISSORS.ourCode -> SCISSORS
                else -> TODO()
            }
        }
    }
}

enum class GAMESTATE(val score: Int) {
    LOSE(0),
    DRAW(3),
    WIN(6)
}

val endStateMap = mapOf(
    'X' to GAMESTATE.LOSE,
    'Y' to GAMESTATE.DRAW,
    'Z' to GAMESTATE.WIN
)

fun main() {
    fun getGameState(oppShape: SHAPE, ourShape: SHAPE): GAMESTATE {
        return when (oppShape) {
            SHAPE.ROCK -> when (ourShape) {
                SHAPE.ROCK -> GAMESTATE.DRAW
                SHAPE.PAPER -> GAMESTATE.WIN
                SHAPE.SCISSORS -> GAMESTATE.LOSE
            }
            SHAPE.PAPER -> when (ourShape) {
                SHAPE.ROCK -> GAMESTATE.LOSE
                SHAPE.PAPER -> GAMESTATE.DRAW
                SHAPE.SCISSORS -> GAMESTATE.WIN
            }
            SHAPE.SCISSORS -> when (ourShape) {
                SHAPE.ROCK -> GAMESTATE.WIN
                SHAPE.PAPER -> GAMESTATE.LOSE
                SHAPE.SCISSORS -> GAMESTATE.DRAW
            }
        }
    }

    fun part1(input: List<String>): Int {
        var totalScore = 0
        input.forEach {
            val oppShape = it[0]
            val ourShape = it[2]

            val gameState = getGameState(SHAPE.getOppShape(oppShape), SHAPE.getOurShape(ourShape))

            totalScore += gameState.score + SHAPE.getOurShape(ourShape).score
        }
        return totalScore
    }

    fun getOurShapeForState(oppShape: SHAPE, endState: GAMESTATE): SHAPE {
        return when (endState) {
            GAMESTATE.LOSE -> when (oppShape) {
                SHAPE.ROCK -> SHAPE.SCISSORS
                SHAPE.PAPER -> SHAPE.ROCK
                SHAPE.SCISSORS -> SHAPE.PAPER
            }
            GAMESTATE.DRAW -> when (oppShape) {
                SHAPE.ROCK -> SHAPE.ROCK
                SHAPE.PAPER -> SHAPE.PAPER
                SHAPE.SCISSORS -> SHAPE.SCISSORS
            }
            GAMESTATE.WIN -> when (oppShape) {
                SHAPE.ROCK -> SHAPE.PAPER
                SHAPE.PAPER -> SHAPE.SCISSORS
                SHAPE.SCISSORS -> SHAPE.ROCK
            }
        }
    }

    fun part2(input: List<String>): Int {
        var totalScore = 0
        input.forEach {
            val oppShape = it[0]
            val gameEndStatusCode = it[2]

            val endState = endStateMap[gameEndStatusCode] ?: TODO()
            totalScore += endState.score
            val getOurShapeForState = getOurShapeForState(SHAPE.getOppShape(oppShape), endState)
            totalScore += getOurShapeForState.score
        }

        return totalScore
    }

    // test if implementation meets criteria from the description, like:
    /*val testInput = readInput("Day01_test")
    check(part1MostCalories(testInput) == 1)*/

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
