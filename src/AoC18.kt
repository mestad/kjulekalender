import java.io.File
import java.util.*
import kotlin.system.measureTimeMillis

class AoC18 {

//    2 * 3 + (4 * 5) becomes 26.
//    5 + (8 * 3 + 9 + 3 * 4 * 3) becomes 437.
//    5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4)) becomes 12240.
//    ((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2 becomes 13632.
// Too low : 1 3057 944 166

    fun runProgram() {
        var acc = 0.toLong()
        val lines = File("c:\\dev\\kjulekalender\\src\\files\\aoc18a.txt").readLines();
//        calculateLine2("2 * 3 + (4 * 5)")
//        calculateLine2("5 + (8 * 3 + 9 + 3 * 4 * 3)")
//        calculateLine2("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))")
//        calculateLine2("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2")
        for (line in lines) {
            acc += calculateLine2(line)
        }
        println("Result: " + acc)
    }

    fun calculateLine2(line: String): Long {
        val stack = Stack<Operation>()
        var lastNumber = 0.toLong()
        var lastOperation = Operation(0, '+')
        for (element in line) {
            if (element == '(') {
                stack.push(lastOperation)
                lastOperation = Operation(0, '+')
            } else if (element == ')') {
                lastOperation = stack.pop().operate(lastOperation.acc)
            } else if (element.isDigit()) {
                lastNumber = Integer.valueOf(element.toString()).toLong()
                if (lastOperation.operator == '+') {
                    lastOperation = lastOperation.operate(lastNumber)
                } else {
                    stack.push(lastOperation)
                    lastOperation = Operation(lastNumber, '+')
                }
//                lastOperation = lastOperation.operate(Integer.valueOf(element.toString()).toLong())
            } else if (element == '*') {
                lastOperation = Operation(lastOperation.acc, '*')
            } else if (element == '+') {
                stack.push(lastOperation)
                lastOperation
                lastOperation = Operation(lastOperation.acc, '+')
            } else {
                //do nothing
            }
            while (!stack.empty()) lastOperation = stack.pop().operate(lastOperation.acc)
        }
//        println("Result: " + lastOperation.acc)
        return lastOperation.acc
    }

    fun calculateLine(line: String): Long {
        val stack = Stack<Operation>()
        var lastOperation = Operation(0, '+')
        for (element in line) {
            if (element == '(') {
                stack.push(lastOperation)
                lastOperation = Operation(0, '+')
            } else if (element == ')') {
                lastOperation = stack.pop().operate(lastOperation.acc)
            } else if (element.isDigit()) {
                lastOperation = lastOperation.operate(Integer.valueOf(element.toString()).toLong())
            } else if (element == '*') {
                lastOperation = Operation(lastOperation.acc, '*')
            } else if (element == '+') {
                lastOperation = Operation(lastOperation.acc, '+')
            } else {
                //do nothing
            }

        }
//        println("Result: " + lastOperation.acc)
        return lastOperation.acc
    }

    data class Operation(val acc: Long, val operator: Char) {
        fun operate(value: Long): Operation =
               if (operator == '+') Operation(acc + value, '+')
               else Operation(acc * value, '+')
    }


}

fun main() {
    val aoC18 = AoC18();
    val timeInMillis = measureTimeMillis {
        aoC18.runProgram();
    }
    println("Timer: " + timeInMillis)
}
