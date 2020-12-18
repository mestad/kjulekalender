import java.io.File
import javax.print.attribute.IntegerSyntax
import kotlin.system.measureTimeMillis

class AoC16 {

    fun runProgram() {
        val lines = File("c:\\dev\\kjulekalender\\src\\files\\aoc16b.txt").readLines();
        var readRules = true
        var readMyTicket = false;
        var readOtherTickets = false
        var rules = mutableMapOf<String, List<IntRange>>()
        var tickets = mutableListOf<List<Int>>()
        var invalidValues = mutableListOf<Int>()
        for (line in lines) {
            println(line)
            if (readRules) {
                if (line.isBlank())
                    readRules = false
                else {
                    val keyValue = line.split(": ")
                    rules.put(keyValue[0], findRanges(keyValue[1]));
                }
            } else if (line.equals("your ticket:")) {
                readMyTicket = true
                continue
            } else if (readMyTicket) {
                //read my ticket
                readMyTicket = false;
            } else if (line.equals("nearby tickets:")) {
                readOtherTickets = true
                continue
            } else if (readOtherTickets) {
                tickets.add(readTicket(line))
            }
        }
        val flatRules = flattenRules(rules)
        for (ticket in tickets) {
            invalidValues.addAll(findInvalidValuesInTicket(ticket, flatRules))
        }
        println(invalidValues)

        var acc = 0
        for (invalidValue in invalidValues) {
            acc+= invalidValue
        }
        println("acc. " + acc)
    }

    fun flattenRules(rules: MutableMap<String, List<IntRange>>): List<IntRange> {
        val myList = mutableListOf<IntRange>()
        for (value in rules.values) {
            myList.addAll(value)
        }
        return myList
    }

    private fun findInvalidValuesInTicket(ticket: List<Int>, rules: List<IntRange>): Collection<Int> {
        val invalidList = mutableListOf<Int>()
        for (i in ticket) {
            var isOk = false
            for (rule in rules) {
                if (i in rule) {
                    isOk = true
                    break
                }
            }
            if (!isOk) {
                invalidList.add(i)
            }
        }
        return invalidList
    }

    fun readTicket(valueString: String): List<Int> {
        val values = valueString.split(",")
        val list = mutableListOf<Int>()
        for (value in values) {
            list.add(Integer.valueOf(value))
        }
        return list
    }


        fun findRanges(rangesString: String): List<IntRange> {
            val ranges = rangesString.split(" or ")
            val firstRange = ranges[0].split("-")
            val firstIR = IntRange(firstRange[0].toInt(), firstRange[1].toInt())
            val secondRange = ranges[1].split("-")
            val secondIR = IntRange(secondRange[0].toInt(), secondRange[1].toInt())
            return listOf(firstIR, secondIR)
        }

}

fun main() {
    val aoC16 = AoC16();
    val timeInMillis = measureTimeMillis {
        aoC16.runProgram();
    }
    println("Timer: " + timeInMillis)
}
