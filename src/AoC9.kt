import java.io.File

class AoC9 {


    fun runProgram() {
        val preamble = 25;
        val lines: List<String> = File("c:\\dev\\kjulekalender\\src\\files\\aoc9b.txt").readLines()
        var i = 0;
        var myValue = 0;
        var list = mutableListOf<Int>()
        for (line in lines) {
            val value = Integer.valueOf(line.trim())
            if (i < preamble) list.add(value)
            else {
                if (isValid(value, list.subList(i-preamble, list.size))) list.add(value)
                else {
                    println("Invalid! " + value)
                    myValue = value;
                    break;
                }
            }
            i++
        }
        println(findContiguousSet(list, myValue))
    }

    fun findContiguousSet(values: List<Int>, goal: Int): Int {
        var currentList = mutableListOf<Int>()
        for (i in 0..values.size - 1) {
            for (j in i..values.size - 1) {
                currentList.add(values.get(j))
                val currentSetValue = calculateSetValue(currentList)
                if (currentSetValue == goal) return (calculateRangeValue(currentList))
                else if (currentSetValue > goal) {
                    currentList = mutableListOf()
                    break;
                }
            }
        }
        println("To the end")
        return calculateSetValue(currentList)
    }

    fun calculateSetValue(list: MutableList<Int>): Int {
        var sum = 0;
        for (i in list) {
            sum += i;
        }
        return sum
    }

    fun calculateRangeValue(list: MutableList<Int>): Int {
        var smallest = Integer.MAX_VALUE
        var biggest = 0
        for (value in list) {
            if (value < smallest) smallest = value
            if (value > biggest) biggest = value
        }
        return smallest + biggest
    }

    fun isValid(value: Int, list: MutableList<Int>): Boolean {
        for (i in 0..list.size-2) {
            for (j in i..list.size-1) {
                if (list.get(i) + list.get(j) == value) return true;
            }
        }
        return false;
    }

}

fun main() {
    val aoC9 = AoC9();
    aoC9.runProgram();
}
