package com.orobator.kotlin.intro.lesson23

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
    for (i in 1..100_000) {
        val time = profile { print(null) }
        print(time)
    }
}

// For each iteration, an function object is created to be passed into
// profile.

// Inlining would prevent this object creation.