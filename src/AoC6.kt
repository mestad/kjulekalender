import java.io.File

class AoC6 {
    fun validatePassports(): Int {
        val lines: List<String> = File("c:\\dev\\kjulekalender\\src\\files\\aoc6b.txt").readLines()
        var declaraction2 = ""
        var counter = 0;
        for (line in lines) {
            if (line.isBlank()) {
                println(findCommonYes(declaraction2.trim()))
                counter += findCommonYes(declaraction2.trim())
                declaraction2 = ""
            } else {
                declaraction2 += "+" + line.trim()
            }
        }
        println("Counter: " + counter)
        return counter
    }

    fun findCommonYes(declaration: String): Int {
        var first = declaration.toCharArray().toMutableSet()
        first.remove('+');
        var second = mutableSetOf<Char>()
        val elements = declaration.substring(1).split("+")
        if (elements.size == 1) return first.size
        for (element in elements) {
            for (c in first) {
                if (element.contains(c)) {
                    second.add(c)
                }
            }
            first = second
            second = mutableSetOf()
        }
        return first.size

    }

    /**
     * 1-3 a: abcde
    1-3 b: cdefg
    2-9 c: ccccccccc
     */

    fun parseDeclaration(declaration: String): Int = declaration.toCharArray().toSet().size;
}

fun main() {
    val aoC6 = AoC6()
    println(aoC6.validatePassports())
}
