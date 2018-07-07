package com.orobator.kotlin.intro.lesson9.solutions

// 1. When would you use an object declaration vs an object expression?

// Object expressions are generally used in replace of anonymous classes while
// object declarations are for creating singletons. In addition, only object
// expressions can be declared locally (inside a function).

// 2. Is it possible to make an Android Application class an object declaration?
//    Why or why not?

// It is not possible because even though the Application class is a singleton
// in nature, Kotlin object declarations have private constructors. The
// Application class needs to have a public constructor so the Android system
// can instantiate it.

// 3. List the initialization order for Declared, anon and TopLevel's companion object

// 1. TopLevel's companion object gets loaded when the class is loaded
// 2. anon is created when an instance of TopLevel is created
// 3. Declared gets initialized lazily so in the call of bar() where it's first
//    referenced
object Declared {
    init {
        println("Init Declared")
    }

    val id = 0
}

class TopLevel {
    val foo = ""

    private val anon = object {
        init {
            println("Init anon")
        }

        val x: Int = 42
    }

    fun bar() {
        println("Declared.id = ${Declared.id}, anon.x ${anon.x}")
    }

    companion object {
        init {
            println("Init companion")
        }

        val importantThing = "passport"
    }
}

fun main(args: Array<String>) {
    TopLevel().bar()
}