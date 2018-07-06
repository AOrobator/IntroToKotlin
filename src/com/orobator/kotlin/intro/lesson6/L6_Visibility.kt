package com.orobator.kotlin.intro.lesson6

import com.orobator.kotlin.intro.lesson5.Foo

class VisibilityDemo {
    // Everything in Kotlin is public by default!
    var foo: Foo = Foo()

    // The public modifier, just for completeness
    val bar = 42
}

// Top-level visibilities

// Top-level function is only visible in this file
private fun doSomethingCool() = Unit

// Top-level property with a private setter
var globalInt: Int = 0
    private set // Notice how we don't have to define the rest of the setter

// Internal means only visible in this module
// Module - a set of Kotlin files compiled together
// - An IntelliJ Idea module
// - A gradle source set
// - etc.
internal fun doSomethingElse() = Unit

// Class-level visibilities

// open keyword allows classes, functions, and properties to be extended
// Everything in Kotlin is final by default!
open class Outer {
    private val a = 1 // Only visible inside this class
    protected open val b = 2 // private + visible to subclasses
    internal val c = 3 // Visible to clients inside this module
    val d = 4  // public by default

    // outer class does not see private members of its inner classes in Kotlin.
    protected class Nested {
        val e: Int = 5
        private val f: Int = 3 // Not visible to Outer
    }
}

class Subclass : Outer() {
    // a is not visible
    // b, c and d are visible
    // Nested and e are visible

    override val b = 5   // 'b' is protected
}

class Unrelated(o: Outer) {
    // o.a, o.b are not visible
    // o.c and o.d are visible (same module)
    // Outer.Nested is not visible, and Nested::e is not visible either
}

// Constructor visibility
class C private constructor(a: Int)
// By default all constructors are public
