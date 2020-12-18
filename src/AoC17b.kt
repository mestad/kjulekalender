import java.io.File
import kotlin.system.measureTimeMillis

class AoC17b {

    fun runProgram() {
        val lines = File("c:\\dev\\kjulekalender\\src\\files\\aoc17b.txt").readLines();
        var newList = lines
        var newArea = mutableListOf<List<String>>()
        var newCube = mutableListOf<List<List<String>>>()
        newArea.add(newList)
        newCube.add(newArea)
        for (i in 0..5) {
            newCube = growLines(newCube)
        }
        println("Result: " + countActives(newCube))
    }

    fun growLines(cube: List<List<List<String>>>): MutableList<List<List<String>>> {
        val newCube =  mutableListOf<List<List<String>>>()
        for (n in -1..cube.size) {
            var newArea = mutableListOf<List<String>>()
            for (i in -1..cube[0].size) {
                var newList = mutableListOf<String>()
                for (j in -1..cube[0][0].size) {
                    var newString = ""
                    for (k in -1..cube[0][0][0].length) {
                        var isActive = isActive(cube, n, i, j, k)
                        var neighbours = countNeighbours(cube, n, i, j, k)
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
            newCube.add(newArea)
        }
        return newCube
    }

    fun isActive(cube: List<List<List<String>>>, n: Int, x: Int, y: Int, z: Int): Int {
        if (n < 0 || n >= cube.size
                || x < 0 || x >= cube[0].size
                || y < 0 || y >= cube[0][0].size
                || z < 0 || z >= cube[0][0][0].length
                || cube[n][x][y][z] == '.')
            return 0
         else
             return 1
    }

    fun countNeighbours(cube: List<List<List<String>>>, n: Int, x: Int, y: Int, z: Int): Int {
        var counter = 0;
        for (a in -1..1) {
            for (i in -1..1) {
                for (j in -1..1) {
                    for (k in -1..1) {
                        if (a == 0 && i == 0 && j == 0 && k == 0) counter = counter
                        else counter += isActive(cube, n+a, x+i, y+j, z+k)
                    }
                }
            }
        }
        return counter
    }

    fun countActives(area: List<List<List<String>>>): Int {
        var counter = 0
        for (i in 0..area.size-1)
            for (j in 0..area[i].size-1)
            for (k in 0..area[i][j].size-1)
                for (n in 0..area[i][j][k].length-1) {
                    if (area[i][j][k][n] == '#') counter++
                }
        return counter
    }




}

fun main() {
    val aoC17b = AoC17b();
    val timeInMillis = measureTimeMillis {
        aoC17b.runProgram();
    }
    println("Timer: " + timeInMillis)
}
