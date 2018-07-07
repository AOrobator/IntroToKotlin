package com.orobator.kotlin.intro.lesson09.labs

// 1. When would you use an object declaration vs an object expression?


// 2. Is it possible to make an Android Application class an object declaration?
//    Why or why not?


// 3. List the initialization order for Declared, anon and TopLevel's companion object

object Declared {
    val id = 0
}

class TopLevel {
    val foo = ""

    private val anon = object {
        val x: Int = 42
    }

    fun bar() {
        println("Declared.id = ${Declared.id}, anon.x ${anon.x}")

    }

    companion object {
        val importantThing = "passport"
    }
}

fun main(args: Array<String>) {
    TopLevel().bar()
}