import java.io.File
import kotlin.system.measureTimeMillis

class AoC16b {
    fun length(): Int = 20

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
        val validTickets = mutableListOf<List<Int>>()
        for (ticket in tickets) {
            val invalid = findInvalidValuesInTicket(ticket, flatRules)
            if (invalid.isEmpty()) validTickets.add(ticket)
        }
        val ruleMap = findFields(rules, validTickets)
        allocateFields(ruleMap)

    }


    fun findFields(rules: MutableMap<String, List<IntRange>>, tickets: List<List<Int>>): MutableMap<String, MutableList<Int>> {
        val length = tickets[0].size
        val candidates = mutableMapOf<String, MutableList<Int>>()
        for (key in rules.keys) {
            for (i in 0..length - 1) {
                var candidateList = candidates.get(key)
                if (findValidCandidates(rules.get(key), tickets, i)) {
                    if (candidateList == null) {
                        candidateList = mutableListOf<Int>()
                    }
                    candidateList.add(i)
                    candidates.put(key, candidateList)
                }
            }
        }
        return candidates
    }

    fun allocateFields(rules: MutableMap<String, MutableList<Int>>) {
        val result = mutableMapOf<String, Int>()
        val allocatedFields = mutableSetOf<Int>()
        while (allocatedFields.size < length()) {
            for (key in rules.keys) {
                val list = rules.get(key)?.toList()
                if (list == null) continue
                if (list.size == 0) {
                    continue
                } else if (list.size == 1) {
                    result.put(key, list[0])
                    allocatedFields.add(list[0])
                    rules.put(key, emptyList<Int>().toMutableList())
                } else {
                    val newSet = mutableListOf<Int>()
                    for (i in list) {
                        if (!(i in allocatedFields))
                            newSet.add(i)
                    }
                    if (newSet.size == 1) {
                        result.put(key, newSet[0])
                        allocatedFields.add(newSet[0])
                        rules.put(key, emptyList<Int>().toMutableList())
                    } else {
                        rules.put(key, newSet)
                    }
                }
            }
        }
        println(result)
        val myTicket = "89,139,79,151,97,67,71,53,59,149,127,131,103,109,137,73,101,83,61,107".split(",")
        var acc = 1.toLong()
        for (key in result.keys) {
            if (key.startsWith("departure")) {
                acc = acc * Integer.valueOf(myTicket[result.get(key)!!])
            }
        }
        //Too low: 2019600
        println("Acc: " + acc)
    }

    fun findValidCandidates(intRanges: List<IntRange>?, tickets: List<List<Int>>, index: Int): Boolean {
        if (intRanges == null) return false
        for (ticket in tickets) {
            if (!(ticket[index] in intRanges[0] || ticket[index] in intRanges[1])) {
                return false
            }
        }
        return return true
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
    val aoC16 = AoC16b();
    val timeInMillis = measureTimeMillis {
        aoC16.runProgram();
    }
    println("Timer: " + timeInMillis)
}
