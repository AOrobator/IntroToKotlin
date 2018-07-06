package com.orobator.kotlin.intro.lesson4

fun main(args: Array<String>) {
    // Basic syntax needed to create a class.
    // Empty is a fully valid Kotlin class!
    class Empty

    // Constructors are created with the constructor keyword
    class Human constructor(name: String)

    // Constructor keyword can be dropped if it has no modifiers (public/private/etc)
    // Primary constructor contains no code
    class Customer(name: String) {
        // Initialization code goes in init block
        init {
            println("Hello, $name")
        }

        // Constructor args can be accessed by both init blocks and properties
        val customerKey: String = name.toUpperCase()

        // Secondary constructors are specified with constructor keyword
        // Must call the primary constructor via this keyword
        // this keyword works the same as it does in Java
        constructor(name: String, address: String) : this(name) {
            println("Customer submitted address")
        }
    }

    // Initializer blocks are executed in the same order
    // as they appear in the class body.
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

    // No "new" keyword needed to create an object, unlike Java
    val initOrderDemo = InitOrderDemo("Hello")

    // To declare and initialize properties in the constructor, Kotlin has a
    // concise syntax: just use val or var.
    class Smartphone(val manufacturer: String, var hasCase: Boolean)

    // Properties can also be declared in the class body
    class Headphones {
        // Same within functions: val -> read-only, var -> read/write
        val manufacturer: String = "Shure"
        var inEar = false
    }
}
