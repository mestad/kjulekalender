import java.io.File

class AoC4 {
    //232 too low
    fun validatePassports():Int {
        val lines: List<String> = File("c:\\dev\\kjulekalender\\src\\files\\aoc4b.txt").readLines()
        var passport = ""
        var counter = 0;
        var invalidcounter = 0
        var totC = 0;
        for (line in lines) {
            if (line.isBlank()) {
                if (validatePassport(passport.trim())) {
                    counter++
                } else {
                    invalidcounter++
                }
                passport = ""
                totC++
            } else {
                passport = passport + " " + line
            }
        }
        totC++
        if (validatePassport(passport.trim())) {
            counter++
        } else {
            invalidcounter++
        }
        println("Totc: " + totC)
        println("Invalid: " + invalidcounter)
        println("Counter: " + counter)
        return counter
    }

    /**
     * 1-3 a: abcde
    1-3 b: cdefg
    2-9 c: ccccccccc
     */

    fun validatePassport(passport: String): Boolean  {
        val required = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
        val optional = listOf("cid")
        val elements = passport.split(" ")

        for (req in required) {
            val exists = elements.stream()
                    .map { el -> getFirstPart(el) }
                    .filter { req == it }
                    .findAny()
                    .orElse("not")
            if (exists == "not") {
                return false;
            }
        }
        for (element in elements) {
            if (!validateElement(element)) return false
        }
        return true
    }

    /**
     * byr (Birth Year) - four digits; at least 1920 and at most 2002.
    iyr (Issue Year) - four digits; at least 2010 and at most 2020.
    eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
    hgt (Height) - a number followed by either cm or in:
    If cm, the number must be at least 150 and at most 193.
    If in, the number must be at least 59 and at most 76.
    hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
    ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
    pid (Passport ID) - a nine-digit number, including leading zeroes.
    cid (Country ID) - ignored, missing or not.
     */
    fun validateElement(element: String): Boolean {
        val map = element.split(":")
        when (map.get(0)) {
            "byr" -> return validateByr(map.get(1))
            "iyr" -> return validateIyr(map.get(1))
            "eyr" -> return validateEyr(map.get(1))
            "hgt" -> return validateHgt(map.get(1))
            "hcl" -> return validateHcl(map.get(1))
            "ecl" -> return validateEcl(map.get(1))
            "pid" -> return validatePid(map.get(1))
            else -> { // Note the block
                print("x is neither 1 nor 2")
            }
        }
        return true;
    }

    fun validateByr(value: String): Boolean {
        val year = value.toInt()
        return year >= 1920 && year <= 2002
    }

    fun validateIyr(value: String): Boolean {
        val year = value.toInt()
        return year >= 2010 && year <= 2020
    }

    fun validateEyr(value: String): Boolean {
        val year = value.toInt()
        return year >= 2020 && year <= 2030
    }

    fun validateHgt(value: String): Boolean {
        if (value.endsWith("cm")) {
            val cm = value.substring(0, value.length - 2).toInt()
            return cm >= 150 && cm <= 193
        }
        if (value.endsWith("in")) {
            val inches = value.substring(0, value.length - 2).toInt()
            return inches >= 59 && inches <= 76
        }
        return false
    }

    fun validateHcl(value: String): Boolean {
        if (value.length != 7) return false
        return Regex("\\A#[a-z,0-9]{6}").toPattern().matcher(value).matches()
    }

    fun validateEcl(value: String): Boolean {
        val colors = listOf("amb","blu","brn","gry","grn","hzl","oth")
        return colors.contains(value)
    }

    fun validatePid(value: String): Boolean {
        if (value.length != 9) return false
        return Regex("[0-9]{9}").toPattern().matcher(value).matches()
    }

    fun getFirstPart(element: String): String {
        val split = element.split(":")
        return split[0]
    }

}


fun main() {
    val aoC4 = AoC4()
//    println(aoC4.validateHcl("#123ffa"))
//    println(aoC4.validateHcl("#123ffa33"))
//    println(aoC4.validateHcl(":#123ffa33"))
//    println(aoC4.validateEcl("gry"))
//    println(aoC4.validateEcl("gryn"))
//    println(aoC4.validatePid("0123456789"))
//    println(aoC4.validatePid("123456789"))
    println(aoC4.validatePassports())
}
