import java.io.File

class AoC3 {
    fun slope(incX: Int, incY: Int):Int {
        return getArrayOfStringsFromFile("c:\\dev\\kjulekalender\\src\\files\\aoc3b.txt", incX, incY)
//        println(validatePassword2("1-3 a: abcde"))
    }

    /**
     * 1-3 a: abcde
    1-3 b: cdefg
    2-9 c: ccccccccc
     */

    fun getArrayOfStringsFromFile(fileName: String, incX: Int, incY: Int): Int  {
        val lines: List<String> = File(fileName).readLines()
        var trees = 0;
        var y = 0;
        var counter = 0;
        for (line in lines) {
            if (counter % incX == 0) {
                if (line.get(y) == '#') {
                    trees++

                }
                y = (y + incY) % line.length
            }
            counter++
        }
        println(trees)
        return trees
    }

}

/**
 * Right 1, down 1.
Right 3, down 1. (This is the slope you already checked.)
Right 5, down 1.
Right 7, down 1.
Right 1, down 2.
 */

fun main() {
    val aoC3 = AoC3()
    var product: Long = 1;
    product = product.times(aoC3.slope(1,1))
    product = product.times(aoC3.slope(1,3))
    product = product.times(aoC3.slope(1,5))
    product = product.times(aoC3.slope(1,7))
    product = product.times(aoC3.slope(2,1))
    println(product);
}
