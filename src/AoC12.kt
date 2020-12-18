import java.io.File
import java.util.*

class AoC12 {

    fun runProgram2() {
        val lines = File("c:\\dev\\kjulekalender\\src\\files\\aoc12b.txt").readLines();
        var ship = Position(0,0,'E')
        for (line in lines) {
            ship = ship.nextPosition(line)
            println("Line: " + line + " ship: " + ship)
        }
        println("Result: " + (Math.abs(ship.x) + Math.abs(ship.y)))
    }

    data class Position(val x:Int, val y:Int, val direction: Char) {
        fun nextPosition(command: String): Position {
            val action = command.substring(0, 1)
            val value = Integer.valueOf(command.substring(1, command.length))
            when (action) {
                "N" -> return Position(x, y + value, direction)
                "S" -> return Position(x, y - value, direction)
                "W" -> return Position(x - value, y, direction)
                "E" -> return Position(x + value, y, direction)
                "R" -> return Position(x, y, nextDirection(action, value))
                "L" -> return Position(x, y, nextDirection(action, value))
                "F" -> return forward(value)
                else -> {
                    println("!!!! ERRor")
                    return this
                }
            }
        }

        fun nextDirection(turn: String, degrees: Int): Char {
            val directionsRight = "NESW";
            val directionsLeft = "NWSE";
            if (turn.equals("L")) {
                val currentDirection = directionsLeft.indexOf(direction)
                return directionsLeft.get((currentDirection + (degrees / 90)) % 4)
            } else {
                val currentDirection = directionsRight.indexOf(direction)
                return directionsRight.get((currentDirection + (degrees / 90)) % 4)
            }
        }

        fun forward(value: Int): Position =
                when (direction) {
                    'N' -> Position (x, y + value, direction)
                    'S' -> Position (x, y - value, direction)
                    'W' -> Position (x - value, y, direction)
                    'E' -> Position (x + value, y, direction)
                    else -> {
                        println("!!! Error 2")
                        this
                    }
                }


    }

    data class Command(val command: String) {

    }
}

fun main() {
    val aoC12 = AoC12();
    aoC12.runProgram2();
}
