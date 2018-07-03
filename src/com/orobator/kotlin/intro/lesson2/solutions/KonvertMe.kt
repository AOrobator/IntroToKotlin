package com.orobator.kotlin.intro.lesson2.solutions

class KonvertMe {
    @JvmOverloads
    fun doStuff(foo: Int, bar: String = "BAR", baz: Double = 42.0) {
        println("foo: $foo, bar: $bar, baz: $baz")
    }
}