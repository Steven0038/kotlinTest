package testKotlin

import org.junit.Test
import java.math.BigDecimal
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.set

class CustomUsage {
    /**
     *  DTOs (POJOs/POCOs)
     */
    data class Customer(val name: String, val email: String)

    @Test
    fun testDtoMethods() {
        var steven = Customer("Steven", "cat@gmail.com")
        var steven2 = Customer("Steven", "cat@gmail.com")
        var sasa = Customer("Sasa", "dog@gmail.com")
        println(steven.equals(sasa))
        println(steven.equals(steven2))
    }

    /**
     *  Default function argument
     */
    fun foo(a: Int = 0, b: String = "default arg") { /*....*/
    }

    /**
     *  filter
     */
    @Test
    fun testFilter() {
        var nums = listOf(0, 2, 3, 4)
        println(nums.filter { x -> x > 0 })

        println(nums.filter { it > 2 })
    }

    /**
     *  Check element in Collection
     */
    @Test
    fun testIn() {
        var emailList = listOf("cat@gmail.com", "Dog@gmail.com")
        when {
            "cat@gmail.com" in emailList -> println("when yes")
            "cat@gmail.com" !in emailList -> println("when no")
        }

        if ("cat@gmail.com" in emailList) {
            println("if yes")
        } else {
            println("if no")
        }
    }

    /**
     *  print in line variable
     */
    @Test
    fun testInline() {
        val name = "Steven"
        print("Name $name")
    }

    /**
     *  Class judgement
     */
    @Test
    fun testClassJudgement() {
        class Foo() {}
        class Bar() {}

        var x = Foo()

        when (x) {
            is Foo -> println("x is Foo")
//            is Bar -> println("x is Bar")
            else -> println("WTF?")
        }
    }

    /**
     * Iterate Map
     */
    @Test
    fun testMap() {
        var aMap = mapOf(0 to "Steven", 1 to "Sasa")
        for ((k, v) in aMap) {
            println("Key is $k, Value is $v")
        }
    }

    /**
     *  Range
     */
    @Test
    fun testRange() {
        for (i in 1..10) { // include 10
            print("$i ")
        }

        for (i in 1 until 10) { // not include 10
            print("$i")
        }

        for (x in 2..10 step 2) {
            print("$x ")
        }

        for (x in 10 downTo 1) {
            print("$x")
        }

        val x = 2
        if (x in 1..10) {
            print("yes, $x in range 1 to 10")
        }
    }

    /**
     *  Map and List
     */
    @Test
    fun testMapAndList() {
        // only readable
        val aMap = mapOf("a" to 1, "b" to 2)
        val aList = listOf("a", "b", "c")

        // mutable
        var mMap = mutableMapOf("a" to 1, "b" to 2)
        // visit Map
        mMap["a"] = 3

        println(mMap["a"])
    }

    /**
     *  lazy property ???
     */
    @Test
    fun testLazy() {
//        val p: String by lazy {
//
//        }
    }

    /**
     *  extension function
     */
    @Test
    fun testExtendFun() {
        fun String.spaceToCamelCase() {

        }
        "Convert this to Camel Case".spaceToCamelCase()
    }

    /**
     *  Create Singleton
     */
    object Resource {
        val name = "Steven"
    }

    /**
     *  if not null
     */
    @Test
    fun testNotNull() {
        class File(var name: String, var size: Int) {
        }

        val file = File(" a file", 10)
//        val file = File("a file", null)
//        print(file?.size ?: "empty")

        print(file?.size ?: throw IllegalStateException("File Size is missing!"))
    }

    /**
     *  take first from possible empty collection
     */
    @Test
    fun testTakeFirst() {
//        val emails = listOf("a", "b", "c")
        val emails = listOf<String>()
        println(emails.firstOrNull())
    }

    /**
     *  if not null -> execute code
     */
    @Test
    fun testNotNullLet() {
        var value = "124"
        value?.let {
            value = "123"
            print(value)
        }
    }

    /**
     *  mapping could be null
     */
    @Test
    fun testMappingNull() {
        fun transformValue(it: String): Int? {
            return it.toIntOrNull()
        }

        val value = ""
        val mapped = value?.let { transformValue(it) ?: 0 }
        print(mapped)
    }

    /**
     *  When return
     */
    @Test
    fun testWhenReturn() {
        fun transform(color: String): Int {
            return when (color) {
                "red" -> 0
                "green" -> 1
                "blue" -> 2
                else -> throw IllegalArgumentException("Invalid color param value")
            }
        }

        print(transform("green"))
    }

    /**
     *  try catch expression
     */
    @Test
    fun testTryCatch() {
        fun count(): Int {
            var c = 0
            for (i in 1..10) {
                c++
            }
            return c
        }

        val takeAaaTry = try {
            count()
        } catch (e: ArithmeticException) {
            throw IllegalStateException(e)
        }

        print(takeAaaTry)
    }

    /**
     *  if expression
     */
    @Test
    fun testIfExp() {
        val param = 1
        fun foo(param: Int) {
            val result = if (param == 1) {
                "one"
            } else if (param == 2) {
                "two"
            } else {
                "three"
            }
            print(result)
        }
        foo(param)
    }

    /**
     *  Array Builder
     */
    @Test
    fun testRetUnitBuilder() {
        val amo = arrayOfMinusOnes(10) // amo: {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1}
    }

    private fun arrayOfMinusOnes(size: Int): IntArray {
        return IntArray(size).apply { fill(-1) }
    }

    /**
     *  Single Expression function
     */
    fun theAnswer() = 42
    fun theAnswer2(): Int {
        return 42
    }

    @Test
    fun testSingleExpression() {
        var color = "red"
        fun transform(color: String): Int = when (color) {
            "red" -> 0
            "green" -> 1
            "yellow" -> 2
            else -> throw java.lang.IllegalArgumentException("Invalid color param value")
        }
        print(transform(color))
    }

    /**
     *  call multiple function in a class (WITH)
     */
    @Test
    fun testCallMultipleFunc() {
        class Turtle {
            fun penDown() {}
            fun penUp() {}
            fun turn(d: Double) {}
            fun forward(d: Double) {}
        }

        val myTurtle = Turtle()
        with(myTurtle) {
            penDown()
            for (i in 1..4) {
                forward(100.0)
                turn(90.0)
            }
        }
    }

    /**
     *  apply
     */
    class Rectangle {
        var length = 0
        var breadth = 0
        var color = ""
    }

    val myRectangle = Rectangle().apply {
        length = 4
        breadth = 5
        color = "green"
    }

    @Test
    fun testApply() {
        print("Rectangle length is ${myRectangle.length}, breadth is ${myRectangle.breadth} color is ${myRectangle.color}")
    }

    /**
     *  try with resources
     */
    @Test
    fun testResources() {
        val stream = Files.newInputStream(Paths.get("/some/file.txt"))
        stream.buffered().reader().use { reader ->
            println(reader.readText())
        }
    }

    /**
     *  Generic shortcut ???
     */
//    public final class Gson {
//        public <T> T fromJson(JsonElement json, Class<T> classOfT) throw JsonSyntaxException {
//
//        }
//    }
//    inline fun <reified T: Any> Gson.fromJson(json: JsonElement): T = this.fromJson(json, T::class.java)

    /**
     *  variable exchanger ???
     */
//    var a = 1
//    var b = 2
//    a = b.also {b = a}

    /**
     *  TODO
     */
    fun calcTaxes(): BigDecimal = TODO("Waiting for feedback from accounting")

    /**
     * unchangeable constant
     */
    class Consts{
        companion object {
            const val MAX_COUNT = 8
            const val USER_NAME_FILED = "UserName"
        }
    }


}