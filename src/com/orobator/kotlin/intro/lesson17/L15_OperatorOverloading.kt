package com.orobator.kotlin.intro.lesson17

// Operator Overloading

// Kotlin allows us to provide alternate implementations for operators such as
// "+", "==", "in", etc.

data class Point(val x: Int, val y: Int)

// Operator overloads can either be a member function,
// or an extension function.

// Must use operator keyword, can only declare a predefined set of functions
operator fun Point.unaryMinus() = Point(-x, -y)

fun main(args: Array<String>) {
    val point = Point(10, 20)
    println(-point)  // prints "(-10, -20)"
}

// Binary Operator
data class Counter(val dayIndex: Int) {
    operator fun plus(increment: Int): Counter {
        return Counter(dayIndex + increment)
    }
}

fun counterPlusDemo() {
    // Because we defined the operator, Kotlin knows how to handle this
    val incremented: Counter = Counter(5) + 8
}

// Class that only allows odd index accessing
class OddIndexedList<T>(private val list: List<T>) {
    operator fun get(i: Int): T {
        if (i % 2 == 0) throw IllegalArgumentException("No even indices")

        return list[i]
    }
}

fun oddListDemo() {
    val oddList = OddIndexedList(listOf(1, 2, 3, 4, 5, 6, 7))
    // Just defined indexed access
    println(oddList[3])
}

// Only you thing you can't overload is the identity check ===
