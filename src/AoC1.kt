import java.io.File

fun main() {
    val list: ArrayList<Int> = ArrayList();
    getArrayOfStringsFromFile("c:\\dev\\kjulekalender\\src\\files\\aoc1a.txt", list)
    for (i in 0..list.size-1) {
        println(list[i])
        for (j in i+1..list.size-1) {

            if (list[i] + list[j] == 2020) {
                println("Svar: " + list[i] + " + " + list[j] + " = " + (list[i] * list[j]))
            }
        }
    }
}

fun getArrayOfStringsFromFile(fileName: String, list: ArrayList<Int>)
        = File(fileName).forEachLine {
    list.add(Integer.valueOf(it)) }
