package testKotlin

import org.junit.Test

class ClassAndInheritance {

    /**
     * Class instance examples
     */
    class Empty // brace could ignore, if a class have no instance

    class Person constructor(firstName: String) {
    }

    class Person2(firstName: String) {/**/ } // constructor could be ignore

    /**
     * Constructor
     */
    // use initializer block
    class InitOrderDemo(name: String) {
        val firstProperty = "First property: $name".also(::println)

        init {
            println("First initializer block that prints $name")
        }

        val secondProperty = "Second property: ${name.length}".also(::println)

        init {
            println("Second initializer block that prints ${name.length}")
        }
    }

    @Test
    fun testClass() {
        InitOrderDemo("hello")
    }

    // use property initializer
    class Customer(name: String) {
        val customerKey = name.toUpperCase()
    }

    // use clean initializer
    class Person3(val firstName: String, val lastName: String, val age: Int) {/**/ }

    annotation class Inject
    class Customer2 @Inject private constructor(name: String) {/**/ } // constructor is necessary here

    class Person4 {
        var children: MutableList<Person4> = mutableListOf()

        constructor(parent: Person4) { // second constructor
            parent.children.add(this)
        }
    }

    class Person5(val name: String) {
        var children: MutableList<Person5> = mutableListOf()

        constructor(name: String, parent: Person5) : this(name) {
            parent.children.add(this)
        }
    }

    @Test
    fun constructorTest() {
        class ConstructorsDemo {
            init {
                println("Init block") // run first
            }

            constructor(i: Int) {
                println("Constructor") // run second
            }
        }
        ConstructorsDemo(1)
    }

    // set default constructor private
    class DontCreateMe private constructor() {/**/ }

    /**
     *  Inheritance
     */
    class Example // inheritance from super class Any

    open class Base(p: Int) // open keyword let this class could be inheritance

    class Derived(p: Int) : Base(p)

//    class MyView : View {
//        constructor(ctx: Context) : super(ctx)
//        constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)
//    }

    // override method
    open class Shape {
        open val vertexCount: Int = 0
        open fun draw() {}
        fun fill() {}
    }

    class Circle() : Shape() {
        override fun draw() {}
    }

    class Rectangle : Shape() {
        final override fun draw() {} // final forbidden the next override
    }

    // override propertied
    class Triangle : Shape() {
        override val vertexCount: Int = 0
    }

    interface ShapeI {
        val vertexCount: Int
    }

    class Rectangle2(override val vertexCount: Int = 4) : ShapeI
    class Polygon : ShapeI {
        override var vertexCount: Int = 0 // use var override val, let it could be set to any number
    }

    /**
     *  the order of derived class initialization
     */
    @Test
    fun testOverrideSequence() {
        open class Base2(private val name: String) {
            init {
                println("Initializing Base")
            }

            open val size: Int = name.length.also { println("Initializing size in Base: $it") }
        }

        class Derived2(name: String, private val lastName: String) :
            Base2(name.capitalize().also { println("Argument for Base: $it") }) {
            init {
                println("Initializing Derived")
            }

            override val size: Int =
                (super.size + lastName.length).also { println("Initializing size in Derived: $it") }
        }
        println("Constructing Derived(\"Steven\", \"Chan\") ")
        val d = Derived2("Steven", "Chan")
    }

    /**
     * calling superclass implementation
     */
    open class Rectangle3 {
        open fun draw() {
            println("Drawing a rectangle")
        }

        val borderColor: String get() = "black"
    }

    class FilledRectangle : Rectangle3() {
        override fun draw() {
            super.draw()
            println("Filling the rectangle")
        }

        val fillColor: String get() = super.borderColor
    }

    /**
     * under a innerclass to visit outer class's super class
     */
    @Test
    fun testCallOuterClass() {
        open class Rectangle4 {
            open fun draw() {
                println("Drawing a Rectangle")
            }

            val borderColor: String get() = "black"
        }

        class FilledRectangle2 : Rectangle4() {
            override fun draw() {
                val filler = Filler()
                filler.drawAndFill()
            }

            inner class Filler {
                fun fill() {
                    println("Filling")
                }

                fun drawAndFill() {
                    super@FilledRectangle2.draw()
                    fill()
                    println("Drawn a filled rectangle with color ${super@FilledRectangle2.borderColor}")
                }
            }
        }

        var fr = FilledRectangle2()
        fr.draw()
    }

    // the usage of inheritance the same function from multiple class
    open class Rectangle5 {
        open fun draw() {}
    }

    interface Polygon2 {
        fun draw(){}
    }

    class Square() : Rectangle5(), Polygon2 {
        override fun draw() {
            super<Rectangle5>.draw()
            super<Polygon2>.draw()
        }
    }

    /**
     * Abstract
     */
    open class Polygon3 {
        open fun draw() {}
    }

    abstract class Rectangle6: Polygon3() {
        abstract override fun draw()
    }

}


