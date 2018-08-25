package com.orobator.kotlin.intro.lesson23

import com.orobator.kotlin.intro.lesson05.Foo
import com.orobator.kotlin.intro.lesson22.solutions.profile

// Inline functions

// Using higher-order functions imposes certain runtime penalties:
// each function is an object, and it captures a closure, i.e. those
// variables that are accessed in the body of the function.

// Memory allocations (both for function objects and classes) and
// virtual calls introduce runtime overhead.

// But it appears that in many cases this kind of overhead can be
// eliminated by inlining the lambda expressions.

// The functions shown below are good examples of this situation.
// I.e., the profile() function could be easily inlined at call-sites.
// Consider the following case:

fun profileDemo() {
    val elapsedTime = profile {
        var loopCount = 0
        for (i in 1..100_000_00) {
            (1..100).map { it * it }
            loopCount += 2
        }
        println(loopCount)
    }

    println("Took $elapsedTime ms")
}

// Instead of creating a function object for the parameter and
// generating a call, the compiler could emit the following code:
fun idealProfileDemo() {
    val startTime = System.currentTimeMillis()
    for (i in 1..100_000_00) {
        (1..100).map { it * it }
                .filter { it < 25 }
                .forEach { print(null) }
    }
    val endTime = System.currentTimeMillis()
    val elapsedTime = endTime - startTime
    println("Took $elapsedTime ms")
}

// To make the compiler do this, we need to mark the profile()
// function with the inline modifier:

inline fun inlinedProfile(block: () -> Unit): Long {
    val startTime = System.currentTimeMillis()
    block()
    val endTime = System.currentTimeMillis()
    return endTime - startTime
}

// Call site ends up identical
fun inlineProfileDemo() {
    val elapsedTime = inlinedProfile {
        for (i in 1..100_000_00) {
            (1..100).map { it * it }
        }
    }

    println("Took $elapsedTime ms")
}

// The inline modifier affects both the function itself and the
// lambdas passed to it: all of those will be inlined into the
// call site.

// Inlining may cause the generated code to grow; however, if we do it
// in a reasonable way (i.e. avoiding inlining large functions),
// it will pay off in performance, especially at "megamorphic"
// call-sites inside loops.

// Consider the following code
fun terriblePerformance() {
    val time = profile {
        for (i in 1..1_000_000) {
            val time = profile { print("") }
        }
    }
    println("Not inlined profile took: $time ms")
}

fun betterPerformance() {
    val time = inlinedProfile {
        for (i in 1..1_000_000) {
            val time = inlinedProfile { print("") }
        }
    }
    println("Inlined profile took: $time ms")
}

// For each iteration, an function object is created to be passed into
// profile.

// Inlining would prevent this object creation.

fun main(args: Array<String>) {
    terriblePerformance()
    betterPerformance()
}

///////////
// noinline

// In case you want only some of the lambdas passed to an inline
// function to be inlined, you can mark some of your function
// parameters with the noinline modifier:

inline fun foo(inlined: () -> Unit, noinline notInlined: () -> Unit): Unit = TODO()

// Inlinable lambdas can only be called inside the inline functions or
// passed as inlinable arguments, but noinline ones can be manipulated
// in any way we like: stored in fields, passed around etc.


//////////////
// crossinline

// If the function type you're using isn't directly called in the body,
// but invoked in another execution context use crossinline.

// This disables the non-local control flow feature inside the
// provided lambda, and enables the code to compile.

inline fun exampleFun(crossinline body: () -> Unit) {
    Runnable {
        body()
    }.run()
}


////////////////////
// Non-local returns

// In Kotlin, we can only use a normal, unqualified return to exit a
// named function or an anonymous function.

// This means that to exit a lambda, we have to use a label, and a
// bare return is forbidden inside a lambda, because a lambda can not
// make the enclosing function return:
fun ordinaryFunction(block: () -> Unit) {
    println("hi!")
}

fun foo1() {
    ordinaryFunction {
        //      return // ERROR: can not make `foo` return here
    }
}

// But if the function the lambda is passed to is inlined, the return
// can be inlined as well, so it is allowed:
inline fun inlined(block: () -> Unit) {
    println("hi!")
}

fun foo2() {
    inlined {
        return // OK: the lambda is inlined
    }
}

// Such returns (located in a lambda, but exiting the enclosing
// function) are called non-local returns.

// We are used to this sort of construct in loops, which inline
// functions often enclose:
fun hasZeros(ints: List<Int>): Boolean {
    ints.forEach {
        if (it == 0) return true // returns from hasZeros
    }
    return false
}

// also works for map(), filter(), other stdlib functions

////////////////////
// Inline Properties

// The inline modifier can be used on accessors of properties that
// don't have a backing field.

// You can annotate individual property accessors:

val inlineFoo: Foo
    inline get() = Foo()

val regularFoo: Foo
    get() = Foo()

// The whole property can be marked inline as well
inline var inlineAny: Any
    get() = Any()
    set(value) {
        println("Set $value")
    }

fun demoInlineProperties() {
    println(inlineFoo)
    println(inlineFoo)
    println(regularFoo)

    val any = inlineAny
    inlineAny = "Hello!"
}