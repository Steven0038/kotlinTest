package testKotlin

import org.junit.Test
import java.lang.Integer.parseInt

/**
 *  sample code refer from https://www.kotlincn.net/docs/reference/basic-syntax.html
 */
class BasicCompute {

    @Test
    fun main() {
        println("Hello, Test starts")
        println(sum(1, 2))
        println(sum2(1, 2))

        printSum(3, 4)
    }

    /**
     *  Function
     */
    private fun sum(a: Int, b: Int): Int {
        return a + b
    }

    // function auto referer
    private fun sum2(a: Int, b: Int) = a + b

    // Unit could be ignored
    private fun printSum(a: Int, b: Int): Unit {
        println("sum of $a and $b is ${a + b}")
    }

    /**
     *  Variable
     */
    fun variables() {
        // only readable
        val a: Int = 1
        val b = 2
//    c = 3
//    val c: Int   // compile error

        // could be re-assign
        var x = 5
        x += 1

        val PI = 3.14
        var y = 0

        fun incrementX() {
            y += 1
        }
    }

    /**
     *  Annotation
     */

    // on line annotation

    /* multi-line
            annotation
     */

    /*
     /* annotation with annotation*/
     */

    /**
     * String Template
     */
    @Test
    fun stringTemplate() {
        var a = 1
        val s1 = "a is $a"
        println(s1)
        a = 2
        val s2 = "${s1.replace("is", "was")}, but now is $a"
        print(s2)
    }


    /**
     *  Condition
     */
    @Test
    fun maxMain() {
        println("max of 0 and 42 is ${maxOf(0, 42)}")

        println("max of 10 and 33 ${maxOf2(10, 33)}")
    }

    private fun maxOf(a: Int, b: Int): Int {
        return if (a > b) {
            a
        } else {
            b
        }
    }

    fun maxOf2(a: Int, b: Int) = if (a > b) a else b

    /**
     *  empty value and null handle
     */
    fun parsInt(str: String): Int? { // ? means argument could be null
//        return Integer.valueOf(str)
//        return parseInt(str)
        return str.toIntOrNull()
    }

    fun printProduct(arg1: String, arg2: String) {
        val x = parsInt(arg1)
        val y = parsInt(arg2)

        // check null
        if (x != null && y != null) {
            println(x * y)
        } else {
            println("'$arg1' or '$arg2' is not a number")
        }
    }

    @Test
    fun testParseIntMain() {
        printProduct("", "3")
        printProduct("1", "3")
    }

    /**
     *  Object detection and Auto-conversion
     */
    fun getStringLength(obj: Any): Int? {
        // obj auto-convert to string
        if (obj is String) {
            return obj.length
        }

        // still Any type
        return null
    }

    fun getStringLength2(obj: Any): Int? {
        if (obj is String && obj.length > 0) {
            return obj.length
        }
        return null
    }

    @Test
    fun convertTest() {
        fun printLength(obj: Any) {
            println("'$obj' string length is ${getStringLength(obj) ?: "... err, not a string"} ")
        }
        printLength("Incomprehensibilities")
        printLength(1000)
        printLength(listOf(Any()))
    }

    /**
     * Iteration
     */
    @Test
    fun testFor() {
        val items = listOf("apple", "banana", "kiwifruit")
        for (item in items) {
            println(item)
        }

        val cars = listOf("Porch", "Toyota", "Lexus")
        for (index in cars.indices) {
            println("car at $index is ${cars[index]}")
        }
    }

    @Test
    fun testWhile() {
        val animals = listOf("cat", "dog", "pig")
        var index = 0

        while (index < animals.size) {
            println("animal at $index is ${animals[index]}")
            index++
        }
    }

    /**
     *  When
     */
    @Test
    fun testWhen() {
        fun describe(obj: Any): String = // here as a function refer
            when (obj) {
                1 -> "one"
                "Hello" -> "greeting"
                is Long -> "is long obj."
                !is String -> "Not a String"
                else -> "Unknow"
            }

        println(describe(1))
        println(describe("Hello"))
        println(describe(1000L))
        println(describe(2))
        println(describe("Other"))
    }

    /**
     * Range
     */
    @Test
    fun testRange() {
        val x = 10
        val y = 9
        if (y in 1..y + 1) {
            print("fit in range")
        }
    }

    @Test
    fun testRange2() {
        val list = listOf("a", "b", "c")

        if (-1 !in 0..list.lastIndex) {
            println("-1 is out of range")
        }
        if (list.size !in list.indices) {
            println("list size is ${list.size}, not in list.indices range ${list.indices}, too")
        }
    }

    @Test
    fun testRangeIterate() {
        for (x in 1..2) {
            println(x)
        }
    }

    /**
     * Collections
     */
    @Test
    fun testList() {
//        testListIterate()
//        testContains()
        testLambda()
    }

    private fun testListIterate() {
        val items = listOf("apple", "banana", "kiwifruit")
        for (item in items) {
            println(item)
        }
    }

    private fun testContains() {
        val items = setOf("apple", "banana", "kiwifruit")
        when {
            "orange" in items -> println("orange is juicy!")
            "apple" in items -> println("apple is sweet too!")
        }
    }

    private fun testLambda() {
        val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
        fruits
            .filter { it.startsWith("a") }
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach { println(it) }
    }

    /**
     *  Class and Instance
     */
    abstract class Shape(private val sides: List<Double>) {
        val perimeter: Double get() = sides.sum()
        abstract fun calculateArea(): Double
    }

    interface RectangleProperties {
        val isSquare: Boolean
    }

    class Rectangle(
        var height: Double,
        var length: Double
    ) : Shape(listOf(height, length, height, length)), RectangleProperties {
        override val isSquare: Boolean get() = length == height
        override fun calculateArea(): Double = height * length
    }

    class Triangle(
        var sideA: Double,
        var sideB: Double,
        var sideC: Double
    ) : Shape(listOf(sideA, sideB, sideC)) {
        override fun calculateArea(): Double {
            val s = perimeter / 2
            return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC))
        }
    }

    @Test
    fun testShapeClass () {
        var rectangle = Rectangle(5.0, 2.0)
        var triangle = Triangle(3.0, 4.0, 5.0)
        println("Area of rectangle is ${rectangle.calculateArea()}, it's parameter is ${rectangle.perimeter}")
        println("Area of triangle is ${triangle.calculateArea()}, it's parameter is ${triangle.perimeter}")
    }

    /**
     * Standard Libary
     */
    fun String.go(): String = "$this let it go"

    fun text() = "Elsa: "
        .let { it.go() }
        .let { it.go() }
        .takeIf { it.length == 8 }
        ?.run { print(this) }
        ?: run { print("no") }


    fun play() {

        val t = """
    <div>
        <h1>${"".let { it.go() } ?: ""}</h1>
    </div>
    """

    }

    val foo = if (true) {
        "a"
    } else {
        "b"
    }


}