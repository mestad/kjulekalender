import java.io.File
import java.math.BigInteger
import java.util.*
import kotlin.system.measureTimeMillis

class AoC14 {

    fun runProgram2() {
        val lines = File("c:\\dev\\kjulekalender\\src\\files\\aoc14b.txt").readLines();
        val values = mutableMapOf <Long, Int>()
        var currentMask = Mask("xx")
        for (line in lines) {
            if (line.startsWith("mask")) {
                val value = line.split(" = ")[1]
                currentMask = Mask(value)
            } else if (line.startsWith("mem")) {
                val mem = parseMemory(line)
                val memList = finnAdresser(mutableListOf(""), currentMask.applyValueToAddress(mem.address))
                for (binary in memList) {
                    values.put(binary.toLong(2), mem.value)
                }
            }
        }
        var acc = 0.toLong();
        for (value in values.values) {
            acc += value
        }
        println(acc)
    }

    fun runProgram() {
        val lines = File("c:\\dev\\kjulekalender\\src\\files\\aoc14b.txt").readLines();
        var values = mutableMapOf <Int, Long>()
        var currentMask = Mask("xx")
        for (line in lines) {
            if (line.startsWith("mask")) {
                val value = line.split(" = ")[1]
                println("Antall X'er: " + antallXer(value))
                currentMask = Mask(value)
            } else if (line.startsWith("mem")) {
                val mem = parseMemory(line)
                values.put(mem.address, currentMask.applyValue(mem.value))
            }
        }
        var acc = 0.toLong();
        for (value in values.values) {
            acc += value
        }
        println(acc)
    }

    fun antallXer(line: String): Int =
        if (line.indexOf('X') < 0)
            0
        else
            1 + antallXer(line.substring(line.indexOf('X') + 1))

    fun parseMemory(line: String): Mem {
        val address = line.substring(line.indexOf("[") + 1, line.indexOf("]"))
        val value = line.split(" = ")[1]
        return Mem(Integer.valueOf(address), Integer.valueOf(value))
    }


    fun finnAdresser(accList: MutableList<String>, rest: String):List<String> {
        if (rest.indexOf('X') < 0) {
            for (i in 0..accList.size - 1) {
                accList[i] += rest
            }
        } else {
            var accList2 = accList.toMutableList()
            for (i in 0..accList.size - 1) {
                accList[i] = accList[i] + rest.substring(0, rest.indexOf('X')) + '0'
                accList2[i] = accList2[i] + rest.substring(0, rest.indexOf('X')) + '1'
            }
            accList.addAll(accList2)
            return finnAdresser(accList, rest.substring(rest.indexOf('X') + 1))
        }
        return accList
    }


    data class Mem(val address: Int, val value: Int) {
    }

    data class Mask(val mask: String) {
        fun applyValue(newValue: Int): Long {
            val newValueString = Integer.toBinaryString(newValue).padStart(mask.length, '0')
            var result = ""
            for (i in 0..mask.length - 1) {
                when (mask[i]) {
                    'X' -> result += newValueString[i]
                    '0' -> result += "0"
                    '1' -> result += "1"
                }
            }
            return result.toLong(2)
        }

            fun applyValueToAddress(newValue: Int): String {
                val newValueString = Integer.toBinaryString(newValue).padStart(mask.length, '0')
                var result = ""
                for (i in 0..mask.length-1) {
                    when (mask[i]) {
                        'X' -> result += "X"
                        '0' -> result += newValueString[i]
                        '1' -> result += "1"
                    }
                }
                return result
            }

    }

}

fun main() {
    val aoC14 = AoC14();
//    aoC14.runProgram();
//    val finnAdresser = aoC14.finnAdresser(mutableListOf(""), "00XX11")
//    println(finnAdresser)
    aoC14.runProgram2()
}
