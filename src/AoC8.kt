import java.io.File
import java.lang.RuntimeException

class AoC8 {

    fun runManyPrograms() {
        val lines: List<String> = File("c:\\dev\\kjulekalender\\src\\files\\aoc8b.txt").readLines()
        val list = lines.map { parseLine(it) }
        val indexList = findJmpsAndNoops(list)

        for (i in indexList) {
            if (runProgram(list, i)) {
                println("Ferdig! " + i)
                break;
            }
        }


    }

    fun runProgram(list: List<Operation>, index: Int):Boolean {
        var myList = list.toMutableList()
        myList[index] = myList[index].switchOp()

        var usedLines = mutableListOf<Int>()
        var result = Result(0,0)
        while (!usedLines.contains(result.index) && !(result.index < 0 || result.index >= myList.size)) {
            val nextResult = myList.get(result.index).nextStep(result)
            usedLines.add(result.index)
            result = nextResult
        }
        println(result)
        return result.index == myList.size
    }

    fun parseLine(line: String): Operation  {
        val parts = line.split(" ")
        return Operation(parts[0], Integer.valueOf(parts[1]));
    }

    fun findJmpsAndNoops(operations: List<Operation>): MutableList<Int> {
        var myList = mutableListOf<Int>()
        val opList = listOf("nop", "jmp")
        for (i in 0..operations.size-1) {
            if (opList.contains(operations.get(i).op)) myList.add(i)
        }
        return myList
    }


    data class Operation(val op: String, val steps: Int) {
        fun nextStep(result: Result): Result {
            if (op == "nop") return Result(result.acc, result.index + 1)
            if (op == "acc") return Result(result.acc + steps, result.index + 1)
            if (op == "jmp") return Result(result.acc, result.index + steps)
            else throw RuntimeException("Unknown operation: " + op)
        }

        fun switchOp(): Operation {
            if (op == "nop") return Operation("jmp", steps)
            if (op == "jmp") return Operation("nop", steps)
            println("!!! feil op" + op)
            return Operation(op, steps)
        }
    }

    data class Result(val acc: Int, val index: Int)
}

//nop +0
//acc +1
//jmp +4
//acc +3
//jmp -3
//acc -99
//acc +1
//jmp -4
//acc +6

fun main() {
    val aoC8 = AoC8();
    aoC8.runManyPrograms();
}
