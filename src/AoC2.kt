import java.io.File

class AoC2 {
    fun main() {
        getArrayOfStringsFromFile("c:\\dev\\kjulekalender\\src\\files\\aoc2a.txt")
//        println(validatePassword2("1-3 a: abcde"))
    }

    /**
     * 1-3 a: abcde
    1-3 b: cdefg
    2-9 c: ccccccccc
     */

    fun getArrayOfStringsFromFile(fileName: String): Int  {
        val lines: List<String> = File(fileName).readLines()
        var counter = 0;
        var linecounter = 0;
        lines.forEach{
            if (validatePassword2(it)) {
                println("OK: " + it)
                counter++
            } else {
                println("Feil: " + it)
            }

            linecounter++
        }
        println(" " + counter + " - " + linecounter)
        return counter
    }


    fun validatePassword1(protocol: String): Boolean  {
        val parts = protocol.split("-", " ", ": ")
        var counter = 0
        for (i in 0..parts[3].length-1) {
            if (parts[3].get(i) == parts[2].get(0)) {
//                println(parts[3].get(i))
                counter++
            }
        }
        return (counter >= Integer.valueOf(parts[0]) && counter <= Integer.valueOf(parts[1]))
    }

    fun validatePassword2(protocol: String): Boolean  {
        val parts = protocol.split("-", " ", ": ")
        val firstToken = parts[3].get(Integer.valueOf(parts[0]) - 1) == parts[2].get(0);
        val secondToken = parts[3].get(Integer.valueOf(parts[1]) - 1) == parts[2].get(0);
        return firstToken != secondToken
    }

}

fun main() {
    val aoC2 = AoC2()
    aoC2.main()
}
