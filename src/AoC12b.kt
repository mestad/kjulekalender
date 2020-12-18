import javafx.geometry.Pos
import java.io.File
import java.util.*

class AoC12b {

    fun runProgram2() {
        val lines = File("c:\\dev\\kjulekalender\\src\\files\\aoc12b.txt").readLines();
        var ship = Position(0,0,)
        var waypoint = Position(10,1)
        for (line in lines) {
            if (line.substring(0,1).equals("F")) {
                ship = ship.nextPosition(line, waypoint)
            } else {
                waypoint = waypoint.nextPosition(line, ship)
            }
            println("Line: " + line + " ship: " + ship + " waypoint: " + waypoint)
        }
        println("Result: " + (Math.abs(ship.x) + Math.abs(ship.y)))
    }

    data class Position(val x:Int, val y:Int) {
        fun nextPosition(command: String, otherObject: Position): Position {
            val action = command.substring(0, 1)
            val value = Integer.valueOf(command.substring(1, command.length))
            when (action) {
                //Waypoint
                "N" -> return Position(x, y + value)
                "S" -> return Position(x, y - value)
                "W" -> return Position(x - value, y)
                "E" -> return Position(x + value, y)
                "R" -> return rotate(action, value)
                "L" -> return rotate(action, value)
                "F" -> return distanceToWaypoint(value, otherObject)
                else -> {
                    println("!!!! ERRor")
                    return this
                }
            }
        }

        fun distanceToWaypoint(value: Int, waypoint: Position): Position =
                Position(x + waypoint.x * value, y + waypoint.y * value)

        fun rotate(direction: String, value: Int): Position {
            var turns = (value / 90) % 4
            if (direction.equals("L")) turns = 4 - turns
            var oldX = x
            var oldY = y
            var newX = x
            var newY = y
            for (i in 1..turns) {
                newX = oldY
                newY = - oldX
                oldX = newX
                oldY = newY
            }
            return Position(oldX, oldY)
        }
    }

    data class Command(val command: String) {

    }
}

fun main() {
    val aoC12b = AoC12b();
    aoC12b.runProgram2();
}
