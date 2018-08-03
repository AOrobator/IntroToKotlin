package com.orobator.kotlin.intro.lesson16

import com.orobator.kotlin.intro.lesson12.solutions.Steak

// Extensions

// Kotlin has a feature called extensions which allow you to add functionality
// to a class, even if you're not inheriting from that class

// To declare an extension function, we need to prefix its name with a
// receiver type, i.e. the type being extended. The following adds a swap
// function to MutableList<Int>:

fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' corresponds to the list
    this[index1] = this[index2]
    this[index2] = tmp
}

// The this keyword inside an extension function corresponds to the receiver
// object (the one that is passed before the dot). Now, we can call such a
// function on any MutableList<Int>:

fun extensionDemo() {
    val l = mutableListOf(1, 2, 3)
    l.swap(0, 2) // 'this' inside 'swap()' will hold the value of 'l'
}

// This example is on the Int class
fun Int.square(): Int = this * this

fun intExtensionDemo() {
    val foo = 6.square()
}

// Google is developing Android-KTX -> a library full of extensions to make
// the Android api easier to work with. Currently in alpha.

// https://developer.android.com/kotlin/ktx

// Regular Android
fun regularAndroid() {
    sharedPreferences
            .edit()
            .putBoolean("Key", true)
            .apply()
}

// Android KTX
fun androidKtx() {
    sharedPreferences.edit { putBoolean("Key", true) }
}

// Can also have extension properties

val Int.plusTen get() = this + 10

val hundred = 90.plusTen


// Extensions are resolved statically.

// Extensions do not actually modify classes they extend. By defining an
// extension, you do not insert new members into a class, but merely make new
// functions callable with the dot-notation on variables of this type.

// The extension function being called is determined by the type of the
// expression on which the function is invoked, not by the type of the result
// of evaluating that expression at runtime.

open class C

class D : C()

fun C.foo() = "c"

fun D.foo() = "d"

fun printFoo(c: C) {
    println(c.foo())
}

fun main(args: Array<String>) {
    // Prints C, because only static type matters, not runtime type
    printFoo(D())
}

// If a class has a member function, and an extension function is defined
// which has the same receiver type, the same name and is applicable to given
// arguments, the member always wins.

class Example {
    fun doSomething() = println("Member")
}

fun Example.doSomething() = println("Extension")

fun overrideDemo() {
    Example().doSomething() // Always prints "Member"
}

// Totally cool to override the same function name but use different parameters
fun Example.doSomething(i: Int) = println("Extension")

fun overrideDemo2() {
    Example().doSomething(4) // prints "Extension"
}

// Nullable Receivers

// Note that extensions can be defined with a nullable receiver type. Such
// extensions can be called on an object variable even if its value is null,
// and can check for this == null inside the body.

fun Any?.printOut() {
    if (this == null) {
        println("null")
    } else {
        println(toString())
    }
}


// Using extension functions

// Usually you declare a series of extensions as top-level functions, then
// import those functions for use throughout your codebase

// Declaring Extensions as Members

// Inside a class, you can declare extensions for another class. Inside such
// an extension, there are multiple implicit receivers - objects members of
// which can be accessed without a qualifier. The instance of the class in
// which the extension is declared is called dispatch receiver, and the
// instance of the receiver type of the extension method is called extension
// receiver.

// In case of a name conflict between the members of the dispatch receiver and
// the extension receiver, the extension receiver takes precedence.

// Use qualified this to reach outer class

class A {
    fun D.bar() {
        toString()         // calls D.toString()
        this@A.toString()  // calls A.toString()
    }
}

// Regular visibility rules still apply
// Can't access private members of a class unless you're in the same file the
// class is declared in.

// Extension functions help you avoid a Utils class with a bunch of static
// methods

// Kotlin Stdlib comes with several very helpful extensions

// fun <T> T.apply(f: T.() -> Unit): T { f(); return this }

// Is an extension function on all types

// Takes a lambda with a receiver, f. Inside the lambda, this refers to T

// Can call functions and interact with T without repeated references to T

// Then it returns T

// without apply()
fun cookRareSteak0(): Steak {
    val nyStrip = Steak()
    nyStrip.cook()
    return nyStrip
}

// With apply()
fun cookRareSteak() = Steak().apply { cook() }