import java.io.File
import java.math.BigInteger
import java.util.*
import kotlin.system.measureTimeMillis

class AoC13 {

//    The earliest timestamp that matches the list 17,x,13,19 is 3417.
//    67,7,59,61 first occurs at timestamp 754018.
//    67,x,7,59,61 first occurs at timestamp 779210.
//    67,7,x,59,61 first occurs at timestamp 1261476.
//    1789,37,47,1889 first occurs at timestamp 1202161486.
//    7,13,x,x,59,x,31,19 gir 1068781

//

    fun runProgram4() {
        val line = "23,x,x,x,x,x,x,x,x,x,x,x,x,41,x,x,x,x,x,x,x,x,x,449,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,13,19,x,x,x,x,x,x,x,x,x,29,x,991,x,x,x,x,x,37,x,x,x,x,x,x,x,x,x,x,17"
        val buses = findBuses(line)
        var accBus = Bus(BigInteger.ONE, BigInteger.ZERO)
        for (i in 0..buses.size-1) {
            accBus = mergeBuses(accBus, buses.get(i))
            println(accBus)
        }
        println(accBus.value - accBus.offset)
    }

    fun mergeBuses(a:Bus, b: Bus): Bus {
        var x = a.offset
        var y = b.offset
        var inc = BigInteger.ZERO
        while (x != y) {
            if (x < y) {
                if (((y - x) % a.value).compareTo(BigInteger.ZERO) == 0)
                    inc = (y-x)
                else inc = ((((y - x) / a.value) * a.value) + a.value)
                x = x + inc
            }
            else {
                if (((x - y) % b.value).compareTo(BigInteger.ZERO) == 0)
                    inc = (x - y)
                else inc = ((((x - y) / b.value) * b.value) + b.value)
                y = y + inc
            }
        }
        return Bus(a.value * b.value, x)
    }

    fun findCommonForTwoBuses(a: Bus, b: Bus, start: BigInteger): BigInteger {
        var x = start - a.offset
        var y = start - b.offset
            while (x != y) {
                if (x < y)
                    x = x + a.value
                else
                    y = y + b.value
            }
        return x
    }

    fun runProgram2() {
//        val buses = "7,13,x,x,59,x,31,19".split(",")
        val buses = "23,x,x,x,x,x,x,x,x,x,x,x,x,41,x,x,x,x,x,x,x,x,x,449,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,13,19,x,x,x,x,x,x,x,x,x,29,x,991,x,x,x,x,x,37,x,x,x,x,x,x,x,x,x,x,17".split(",")
//        var result = 1068781.toBigInteger()
        var oldResult = BigInteger.valueOf(8);
        var newResult = BigInteger.valueOf(100000000000000);
//        println(findTimeForNextBus(result, 19.toBigInteger(), 7.toBigInteger(), 7.toBigInteger(), BigInteger.ZERO))
//        println(findTimeForNextBus(result, 7.toBigInteger(), 0.toBigInteger(), 13.toBigInteger(), BigInteger.ONE))
//        println(findTimeForNextBus(result, 13.toBigInteger(), 1.toBigInteger(), 59.toBigInteger(), 4.toBigInteger()))
//        println(findTimeForNextBus(result, 59.toBigInteger(), 4.toBigInteger(), 31.toBigInteger(), 6.toBigInteger()))
//        println(findTimeForNextBus(result, 31.toBigInteger(), 6.toBigInteger(), 19.toBigInteger(), 7.toBigInteger()))
        var lastBus = BigInteger.ONE
        var lastOffset = 0.toBigInteger()
        var isChanged = true
        while (isChanged) {
            isChanged = false
            for (i in 0..buses.size-1) {
                oldResult = newResult
                if (!buses[i].equals("x")) {
                    println("Bus: " + buses[i] + " result: " + oldResult)
                    newResult = findTimeForNextBus(oldResult, lastBus, lastOffset, buses[i].toBigInteger(), i.toBigInteger())
                    lastBus = buses[i].toBigInteger()
                    lastOffset = i.toBigInteger()
                    if (!isChanged) isChanged = !(oldResult == newResult)
                }
            }
        }
        println(newResult)

    }

    fun program3() {
        val timeInMillis = measureTimeMillis {

//val line = "7,13,x,x,59,x,31,19"
        val line = "23,x,x,x,x,x,x,x,x,x,x,x,x,41,x,x,x,x,x,x,x,x,x,449,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,13,19,x,x,x,x,x,x,x,x,x,29,x,991,x,x,x,x,x,37,x,x,x,x,x,x,x,x,x,x,17"
        val buses = findBuses(line)
        val max = findBiggestBus(buses)
        val max2 = findSecondBiggestBus(buses, max)
            val startValue = findCommonForTwoBuses(max, max2, 0.toBigInteger())
        var isValid = false;
//        var index = BigInteger.valueOf(101000000000)
//        var result = BigInteger.valueOf(100000000000000);
            var index = BigInteger.valueOf(0)
            var result = startValue

        while (!isValid) {
            isValid = validateLine(buses, result)
            if (index % 1000.toBigInteger() == BigInteger.ZERO) println("index: " + index + " result: " + result)
            if (isValid) break
            result = result + (max.value * max2.value)
            index++
        }
        println(result)
        }
        println("Tid: " + timeInMillis + " in seconds: " + timeInMillis/1000 + " in minutes: " + timeInMillis/60000)
    }

    fun findBuses(line: String): List<Bus> {
        val buses = line.split(",")
        var list = mutableListOf<Bus>()
        for (i in 0..buses.size-1) {
            if (!buses[i].equals("x")) list.add(Bus(buses[i].toBigInteger(), i.toBigInteger()))
        }
        return list
    }

    fun findBiggestBus(buses: List<Bus>): Bus {
        var max = Bus(BigInteger.valueOf(0), BigInteger.valueOf(0))
        for (bus in buses) {
            if (bus.value > max.value) {
                max = bus
            }
        }
        return max
    }
fun findSecondBiggestBus(buses: List<Bus>, max: Bus): Bus {
        var temp = Bus(BigInteger.valueOf(0), BigInteger.valueOf(0))
        for (bus in buses) {
            if (bus.value > temp.value && bus.value < max.value) {
                temp = bus
            }
        }
        return temp
    }

    data class Bus(val value: BigInteger, val offset: BigInteger) {
        fun result(index: BigInteger): BigInteger = index * value - offset
    }


    fun validateLine(buses: List<Bus>, value: BigInteger): Boolean {
        for (bus in buses) {
                if (!validateBus(value, bus.value, bus.offset)) return false;
        }
        return true
    }

    fun validateBus(result: BigInteger, bus: BigInteger, offset: BigInteger): Boolean =
            (result.add(offset).mod(bus).compareTo(BigInteger.ZERO) == 0)

    fun findTimeForNextBus(bestFit: BigInteger, lastBus: BigInteger, lastOffset: BigInteger, bus: BigInteger, offset: BigInteger): BigInteger {
        var index = bestFit.add(offset).div(bus)
//        var currentBestFit = bestFit
        while (true) {
            if (bus.multiply(index) > 1068750.toBigInteger() && bus.multiply(index) < 1068790.toBigInteger()) {
                println("Passerer " + 1068781)
            }
            if (!(bus.multiply(index).subtract(offset).compareTo(bestFit) < 0)
                    && (bus.multiply(index).subtract(offset).add(lastOffset).mod(lastBus).compareTo(BigInteger.ZERO) == 0)) break;
            index++
        }
        return (bus * index) - offset
    }

    fun runProgram1() {
        val lines = File("c:\\dev\\kjulekalender\\src\\files\\aoc13b.txt").readLines();
        val myTime = Integer.valueOf(lines.get(0))
        val buses = lines.get(1).split(",")
        var bestOffset = Integer.MAX_VALUE
        var bestBus = 0
        var counter = 0
        for (bus in buses) {
            if (!bus.equals("x")) {
                val busTime = Integer.valueOf(bus)
                val offset = busTime - (myTime % busTime)
                if (offset < bestOffset) {
                    bestOffset = offset
                    bestBus = busTime
                }
                println("Bus " + bus + " offset: " + offset + " result: " + busTime * bestOffset)

                counter++
            }
        }
        println("result: " + bestBus * bestOffset)
        println(counter)
    }


}

fun main() {
    val aoC13 = AoC13();
//    aoC13.runProgram1();
//    aoC13.runProgram2();
//    aoC13.program3();
    aoC13.runProgram4()
}
