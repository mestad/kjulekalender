import java.io.File

class AoC5 {
    fun findMySpace() {
        val myList = initialize()
        val lines: List<String> = File("c:\\dev\\kjulekalender\\src\\files\\aoc5.txt").readLines()
        for (line in lines) {
            myList.remove(findPlace(line))
        }
        println("Liste " + myList)
    }

    fun initialize(): MutableList<Int> {
        val max = 127 * 8 + 7
        var myList = mutableListOf<Int>()
        for (i in 1..max) {
            myList.add(i)
        }
        return myList
    }

    fun findHighestNumber(): Int {
        val lines: List<String> = File("c:\\dev\\kjulekalender\\src\\files\\aoc5.txt").readLines()
        var max = 0
        var tot = 0
        for (line in lines) {
            tot++
            val place = findPlace(line)
            if (max < place) max = place
        }
        println("Antall boarding passes: " + tot)
        return max
    }

    fun findPlace(value: String): Int {
        val row = findRow(value.substring(0, 7), Partition(0, 127), 'B')
        val column = findRow(value.substring(7, 10), Partition(0, 7), 'R')
        return row * 8 + column;
    }

    fun findRow(value: String, firstPartition: Partition, highChar: Char): Int {
        var lastPartition = firstPartition
        for (c in value) {
            lastPartition = if (c == highChar) lastPartition.split(true) else lastPartition.split(false)
        }
//        println(lastPartition)
        return lastPartition.start
    }



    data class Partition(val start: Int, val end: Int) {
        fun split(up: Boolean): Partition {
            val newStart = if (up) (start + ((end - start) / 2) + 1) else start
            val newEnd = if (up) end else (end - ((end - start) / 2) - 1)
            return Partition(newStart, newEnd)
        }
    }
}
//
//So, decoding FBFBBFFRLR reveals that it is the seat at row 44, column 5.
//
//Every seat also has a unique seat ID: multiply the row by 8, then add the column. In this example, the seat has ID 44 * 8 + 5 = 357.
//
//Here are some other boarding passes:
//
//BFFFBBFRRR: row 70, column 7, seat ID 567.
//FFFBBBFRRR: row 14, column 7, seat ID 119.
//BBFFBBFRLL: row 102, column 4, seat ID 820.

fun main() {
    val aoC5 = AoC5()
//    println(aoC5.findPlace("FBFBBFFRLR"))
//    println(aoC5.findPlace("BFFFBBFRRR"))
//    println(aoC5.findPlace("FFFBBBFRRR"))
//    println(aoC5.findPlace("BBFFBBFRLL"))
//    println(aoC5.findHighestNumber())
    aoC5.findMySpace()
}
