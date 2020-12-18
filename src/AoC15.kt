import java.time.LocalDateTime
import java.util.*
import kotlin.system.measureTimeMillis

class AoC15 {

    //5 er ikke riktig
    fun runProgram() {
//        val max = 9
//        val max = 2019
        val max = 30000000 - 1
        var list = mutableListOf(0,3,6)
//        var list = mutableListOf(7,12,1,0,16,2)
//        for (i in 5..max-1) {
        for (i in 2..max-1) {
            if (i % 1000 == 0) println(i)
            val lastIndexOf = list.subList(0, i).lastIndexOf(list[i])
            if (lastIndexOf < 0)
                list.add(0)
            else
                list.add(i - lastIndexOf)
        }
//        println(list)
        println(list.get(list.size - 1))
    }

fun runProgram2() {
    val timeInMillis = measureTimeMillis {
        val max = 30000000 - 1
        var next = 2
        var map = mutableMapOf(7 to 0, 12 to 1, 1 to 2, 0 to 3, 16 to 4, 2 to 5)
        var counter = map.keys.size - 1
        while (counter < max) {
            val lastIndexOf = map.get(next)
            map.put(next, counter)
            if (lastIndexOf == null) {
                next = 0
            } else {
                next = counter - lastIndexOf
            }
            counter++
        }
        println(next)
    }
    println("tid: " + timeInMillis + " in s: " + timeInMillis / 1000)
}

}

fun main() {
    val aoC15 = AoC15();
    aoC15.runProgram2();
}
