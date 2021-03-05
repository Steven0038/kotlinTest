package testKotlin

import org.junit.Test

class ReturnAndBreak {

    /**
     *  return
     */
    @Test
    fun testReturn() {
        class Person(name: String, age: Int) {
            val name = name
            val age = age
        }

        val person = Person("Steven", 10)
        val s = person.name ?: return

        println("This person have name $s")

        fun foo() {
            listOf(1, 2, 3, 4, 5).forEach {
                if (it == 3) return // return to foo
                println(it)
            }
            println("this point is unreachable") // not printed
        }
        foo()
        println("===foo ended===")

        fun foo2() {
            listOf(1, 2, 3, 4, 5).forEach lit@{
                if (it == 3) return@lit // partial return to lambda caller， forEach
                println(it)
            }
            println("done with explicit label")
        }
        foo2()
        println("===foo2 ended===")

        fun foo3() {
            listOf(1, 2, 3, 4, 5).forEach {
                if (it == 3) return@forEach // same as foo2
                println(it)
            }
            println(" done with implicit label")
        }
        foo3()
        println("===end of foo3===")

        fun foo4() {
            listOf(1, 2, 3, 4, 5).forEach(fun(value: Int) {
                if (value == 3) return  // 局部返回到匿名函数的调用者，即 forEach 循环
                println(value)
            })
            println(" done with anonymous function")
        }
        foo4()
        println("===end of foo4===")

        fun foo5() {
            run loop@{
                listOf(1, 2, 3, 4, 5).forEach {
                    if (it == 3) return@loop // return to loop
                    println(it)
                }
            }
            println("done with nested loop") // will be printed
        }
        foo5()
        print("===foo5 ended===")
    }
}


