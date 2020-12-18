import java.io.File

class AoC7 {

    var result: MutableSet<String> = mutableSetOf()

    fun countBags() {
        val lines: List<String> = File("c:\\dev\\kjulekalender\\src\\files\\aoc7b.txt").readLines()
        var map = mutableMapOf<String, Bag>()
        for (line in lines) {
            if (!line.isBlank()) {
                val bag = parseLine(line)
                map.put(bag.color, bag)
            }
        }
        val result = map.get("shinygold")?.let { recursive(map, it) }
        println(result)
    }

    fun recursive(map: MutableMap<String, Bag>, bag: Bag): Int {
        if (bag.inside.isEmpty()) return bag.count
        var counter = 0;
        bag.inside.forEach {key ->
            println(bag.color + " " + key.color + " " + key.count)
            counter += key.count * recursive(map, map.get(key.color)!!)
        }
        return counter + 1
    }


    fun recursive1(map: Map<String, MutableList<String>>, bags: MutableList<String>) {
        if (bags.isEmpty()) return
        for (bag in bags) {
            result.add(bag)
            val recList = map.get(bag)
            if (recList != null) recursive1(map, recList)
        }

    }

    fun parseLine(line: String): Bag {
        val parts = line.split(" contain ")
        val firstColor = parts[0].split(" ")
//        println(line)
        val bagColor = firstColor[0] + firstColor[1]

        if (parts[1] == "no other bags.") {
            return Bag(bagColor, 1, emptyList())
        } else {
            val containedBags = parts[1].split(",")
            var list = mutableListOf<Bag>()
            for (bag in containedBags) {
                val words = bag.trim().split(" ")
                val count = Integer.valueOf(words[0])
                list.add(Bag(words[1] + words[2], count, emptyList()))
            }
            return Bag(bagColor, 1, list)
        }
    }

//    fun findBags() {
//        val lines: List<String> = File("c:\\dev\\kjulekalender\\src\\files\\aoc7a.txt").readLines()
//        var map = mutableMapOf<String, MutableList<String>>()
//        for (line in lines) {
//            if (!line.isBlank()) {
//                val bag = parseLine(line)
//                for (containedBag in bag.inside) {
//                    var p = map.get(containedBag)
//                    if (p != null) {
//                        p.add(bag.color)
//                        map.put(containedBag, p)
//                    } else {
//                        map.put(containedBag, mutableListOf(bag.color))
//                    }
//                }
//            }
//        }
//        var key = "shinygold"
//        map.get(key)?.let { recursive1(map, it) }
//        println("Shinygold: " + result.size)
//    }
}

/**
 * light red bags contain 1 bright white bag, 2 muted yellow bags.
dark orange bags contain 3 bright white bags, 4 muted yellow bags.
bright white bags contain 1 shiny gold bag.
muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
dark olive bags contain 3 faded blue bags, 4 dotted black bags.
vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
faded blue bags contain no other bags.
dotted black bags contain no other bags.
 */

data class Bag(val color: String, val count: Int, val inside: List<Bag>)

fun main() {
    val aoC7 = AoC7()
//    pri/ntln(aoC7.parseLine("light red bags contain 1 bright white bag, 2 muted yellow bags."))
    aoC7.countBags()
}


