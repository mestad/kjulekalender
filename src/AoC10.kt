import java.io.File
import java.util.*

class AoC10 {

    fun runProgram2() {
        println(UUID.randomUUID().toString())
        var diff = mutableListOf<Int>()
        val myList = File("c:\\dev\\kjulekalender\\src\\files\\aoc10b.txt")
                .readLines().map { Integer.valueOf(it) }.sorted();
        println(myList)
        var lastCharger = 0
        for (charger in myList) {
            diff.add(charger - lastCharger)
            lastCharger = charger
        }
        diff.add(3)
        var map = mutableMapOf<Int, Int>()
        var counter = 0;
        var acc = 1.toDouble();
        for (tall in diff) {
            when (tall) {
                1 -> counter++
                3 -> {
                    acc = acc * getValueForCounter(counter)
                    counter = 0
                }
                else -> { // Note the block
                    print("!!! er feil")
                }
            }
        }
        println(map)
        println("diff: " + diff)
        println("Acc: " + acc)
    }

    fun getValueForCounter(counter: Int): Int =
            when (counter) {
                0, 1 -> 1
                2 -> 2
                3 -> 4
                4 -> 7
                else -> {
                    print("Feil! " + counter)
                    0;
                }
            }
}


fun main() {
    val aoC10 = AoC10();
    aoC10.runProgram2();
}
