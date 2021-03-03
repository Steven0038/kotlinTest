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

    fun printProduct(arg1: String, arg2: String){
        val x = parseInt(arg1)
        val y = parseInt(arg2)

        // check null
        if (x != null && y != null) {
            println(x * y)
        } else {
            println("'$arg1' or '$arg2' is not a number")
        }
    }

    @Test
    fun testParseIntMain(){
        printProduct("1", "3")
        printProduct("", "3")
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

    @Test
    fun convertTest() {
        fun printLength(obj: Any) {
            println("'$obj' string length is ${getStringLength(obj) ?: "... err, not a string"} ")
        }
        printLength("Incomprehensibilities")
        printLength(1000)
        printLength(listOf(Any()))
    }



}