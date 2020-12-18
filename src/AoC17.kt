import java.io.File
import kotlin.system.measureTimeMillis

class AoC17 {

    fun runProgram() {
        val lines = File("c:\\dev\\kjulekalender\\src\\files\\aoc17b.txt").readLines();
        var newList = lines
        var newArea = mutableListOf<List<String>>()
        newArea.add(newList)
        for (i in 0..5) {
            newArea = growLines(newArea)
        }
        println("Result: " + countActives(newArea))
    }

    fun growLines(area: List<List<String>>): MutableList<List<String>> {
        val newArea =  mutableListOf<List<String>>()
        for (i in -1..area.size) {
            var newList = mutableListOf<String>()
            for (j in -1..area[0].size) {
                var newString = ""
                for (k in -1..area[0][0].length) {
                    var isActive = isActive(area, i, j, k)
                    var neighbours = countNeighbours(area, i, j, k)
                    if ((isActive == 1 && neighbours == 2) || neighbours == 3)
                        newString = newString + "#"
                    else {
                        newString = newString + "."
                    }
                }
                newList.add(newString)
            }
            newArea.add(newList)
        }
        return newArea
    }

    fun isActive(area: List<List<String>>, x: Int, y: Int, z: Int): Int {
        if (x < 0 || x >= area.size || y < 0 || y >= area[0].size || z < 0 || z >= area[0].size || area[x][y][z] == '.')
            return 0
         else
             return 1
    }

    fun countNeighbours(area: List<List<String>>, x: Int, y: Int, z: Int): Int {
        var counter = 0;
        counter += isActive(area, x-1, y-1, z-1)
        counter += isActive(area, x-1, y-1, z)
        counter += isActive(area, x-1, y-1, z+1)
        counter += isActive(area, x-1, y, z-1)
        counter += isActive(area, x-1, y, z)
        counter += isActive(area, x-1, y, z+1)
        counter += isActive(area, x-1, y+1, z-1)
        counter += isActive(area, x-1, y+1, z)
        counter += isActive(area, x-1, y+1, z+1)
        counter += isActive(area, x, y-1, z-1)
        counter += isActive(area, x, y-1, z)
        counter += isActive(area, x, y-1, z+1)
        counter += isActive(area, x, y, z-1)
        counter += isActive(area, x, y, z+1)
        counter += isActive(area, x, y+1, z-1)
        counter += isActive(area, x, y+1, z)
        counter += isActive(area, x, y+1, z+1)
        counter += isActive(area, x+1, y-1, z-1)
        counter += isActive(area, x+1, y-1, z)
        counter += isActive(area, x+1, y-1, z+1)
        counter += isActive(area, x+1, y, z-1)
        counter += isActive(area, x+1, y, z)
        counter += isActive(area, x+1, y, z+1)
        counter += isActive(area, x+1, y+1, z-1)
        counter += isActive(area, x+1, y+1, z)
        counter += isActive(area, x+1, y+1, z+1)
        return counter
    }

    fun countActives(area: List<List<String>>): Int {
        var counter = 0
        for (i in 0..area.size-1)
            for (j in 0..area[i].size-1)
                for (k in 0..area[i][j].length-1) {
                    if (area[i][j][k] == '#') counter++
                }
        return counter
    }




}

fun main() {
    val aoC17 = AoC17();
    val timeInMillis = measureTimeMillis {
        aoC17.runProgram();
    }
    println("Timer: " + timeInMillis)
}
