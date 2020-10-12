package com.orobator.kotlin.intro.lesson18

import com.orobator.kotlin.intro.lesson13.example.typed.id.Song

// Operator Overloading

// Kotlin allows us to provide alternate implementations for operators such as
// "+", "==", "in", etc.

data class Point(val x: Int, val y: Int)

// Operator overloads can either be a member function,
// or an extension function.

// Must use operator keyword, can only declare a predefined set of functions
// Full List: https://kotlinlang.org/docs/reference/operator-overloading.html
operator fun Point.unaryMinus() = Point(-x, -y)

fun main() {
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

class Mario(val size: Int) {

    operator fun inc(): Mario {
        return Mario(size + 1)
    }
}

fun demo() {
    var mario = Mario(5)
    mario++
    println(mario.size)
}

class SongQueue {
    private val list: List<Song> = listOf()

    operator fun get(n: Int) : Song {
        return list[n]
    }
}

fun demo2() {
    val queue = SongQueue()

    val upNextSong = queue[1]
}

// Real world Android Example
// val prefs: SharedPreferences = ...
// val myData = prefs[MY_KEY]
// prefs[MY_KEY] = 2
/*
* inline operator fun <reified T> SharedPreferences.get(key: String): T? {
        return when (T::class) {
            String::class -> getString(key, null) as T?
            Int::class -> getInt(key, 0) as T?
            Boolean::class -> getBoolean(key, false) as T?
            else -> null
        }
    }

    inline operator fun <reified T> SharedPreferences.set(key: String, value: T?) {
        when (T::class) {
            String::class -> edit { putString(key, value as String?) }
            Int::class -> edit { putInt(key, (value as Int?) ?: 0) }
            Boolean::class -> edit { putBoolean(key, (value as Boolean?) ?: false) }
        }
    }
*/