import java.io.File
import java.util.*

class AoC11 {

    fun runProgram2() {
        val lines = File("c:\\dev\\kjulekalender\\src\\files\\aoc11b.txt").readLines();
        println(lines)
        var result = 0;
        var isChanged = true;
        var oldSeatPlan = lines.toMutableList()
        var newSeatPlan = mutableListOf<String>()

        while (isChanged) {
            isChanged = false;
            for (i in 0..oldSeatPlan.size - 1) {
                var newLine = ""
                for (j in 0..oldSeatPlan.get(i).length - 1) {
                    newLine += getValueForPosition2(oldSeatPlan, i,j)
                }
                newSeatPlan.add(newLine)
                if (!newLine.equals(oldSeatPlan.get(i))) isChanged = true
            }
            oldSeatPlan = newSeatPlan
            newSeatPlan = mutableListOf()
            println(oldSeatPlan)
        }
        println(oldSeatPlan)
        var counter = 0
        for (i in 0..oldSeatPlan.size - 1) {
            for (j in 0..oldSeatPlan.get(i).length - 1) {
                if (oldSeatPlan.get(i).get(j) == '#') counter++
            }
        }
        println("Result: " + counter)
    }


    fun getValueForPosition2(seatPlan: MutableList<String>, i: Int, j: Int): Char {
        if (seatPlan.get(i).get(j) == '.') return '.'
        var counter = 0
        if (safeCheck2(seatPlan, i-1, j-1, -1, -1)) counter++
        if (safeCheck2(seatPlan, i-1, j, -1, 0)) counter++
        if (safeCheck2(seatPlan, i-1, j+1, -1, 1)) counter++
        if (safeCheck2(seatPlan, i, j-1, 0, -1)) counter++
        if (safeCheck2(seatPlan, i, j+1, 0, 1)) counter++
        if (safeCheck2(seatPlan, i+1, j-1, 1, -1)) counter++
        if (safeCheck2(seatPlan, i+1, j, 1, 0)) counter++
        if (safeCheck2(seatPlan, i+1, j+1, 1, 1)) counter++
        if (counter == 0) return '#'
        else if (counter >= 5) return 'L' else return seatPlan.get(i).get(j)
    }

    fun safeCheck2(seatPlan: MutableList<String>, i: Int, j: Int, dirI: Int, dirJ: Int): Boolean =
            if (i < 0 || j < 0 || i >= seatPlan.size || j >= seatPlan.get(i).length) false
            else if (seatPlan.get(i).get(j) == '#') true
            else if (seatPlan.get(i).get(j) == 'L') false
            else safeCheck2(seatPlan, i + dirI, j + dirJ, dirI, dirJ)


        fun getValueForPosition(seatPlan: MutableList<String>, i: Int, j: Int): Char {
        if (seatPlan.get(i).get(j) == '.') return '.'
        var counter = 0
        if (safeCheck(seatPlan, i-1, j-1)) counter++
        if (safeCheck(seatPlan, i-1, j)) counter++
        if (safeCheck(seatPlan, i-1, j+1)) counter++
        if (safeCheck(seatPlan, i, j-1)) counter++
        if (safeCheck(seatPlan, i, j+1)) counter++
        if (safeCheck(seatPlan, i+1, j-1)) counter++
        if (safeCheck(seatPlan, i+1, j)) counter++
        if (safeCheck(seatPlan, i+1, j+1)) counter++
        if (counter == 0) return '#'
        else if (counter >= 4) return 'L' else return seatPlan.get(i).get(j)
    }

    fun safeCheck(seatPlan: MutableList<String>, i: Int, j: Int): Boolean =
            if (i < 0 || j < 0 || i >= seatPlan.size || j >= seatPlan.get(i).length) false
            else seatPlan.get(i).get(j) == '#'


}

fun main() {
    val aoC11 = AoC11();
    aoC11.runProgram2();
}
