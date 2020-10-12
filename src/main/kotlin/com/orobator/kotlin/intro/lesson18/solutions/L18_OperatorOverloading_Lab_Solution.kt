package com.orobator.kotlin.intro.lesson18.solutions

import com.orobator.kotlin.intro.lesson18.Point
import com.orobator.kotlin.intro.lesson18.solutions.IndexedLinkedList.Node
import com.orobator.kotlin.intro.lesson18.solutions.IndexedLinkedList.Tail

// Write an operator overload for + so that you can add two Points. You can
// add two Points by adding the X's and the Y's together respectively.
operator fun Point.plus(p: Point): Point = Point(this.x + p.x, this.y + p.y)

// Given the following class, overload the in operator to detect whether a
// point is in a box. Being on the edge counts.

// The positive y axis points up, the positive x axis points right
data class Box(val topLeft: Point, val bottomRight: Point)

operator fun Box.contains(p: Point): Boolean {
    val inXRange = p.x in topLeft.x..bottomRight.x
    val inYRange = p.y in bottomRight.y..topLeft.y
    return inXRange and inYRange
}

// Write a sealed class IndexedLinkedList that has two possible subtypes Node
// and Tail.
// Node should have two properties, value: String and next: IndexedLinkedList
// Tail should be declared as an object.

// Overload the get operator function so you can index into the list like you
// would a regular list. Throw IndexOutOfBoundsExceptions when appropriate.
sealed class IndexedLinkedList {
    data class Node(val value: String, val next: IndexedLinkedList) : IndexedLinkedList()
    object Tail : IndexedLinkedList()

    operator fun get(index: Int): String {
        if (index < 0) throw IndexOutOfBoundsException()

        return when (this) {
            Tail -> throw IndexOutOfBoundsException()
            is Node -> if (index == 0) {
                this.value
            } else {
                next[index - 1]
            }
        }
    }
}

fun main() {
    val result = Point(1, 1) + Point(3, 3)
    println("result should be Point(x=4, y=4); is actually $result\n")

    val boundingBox: Box = Box(
            topLeft = Point(
                    x = 0,
                    y = 10
            ),
            bottomRight = Point(
                    x = 10,
                    y = 0
            )
    )

    println("Point(-1, -1) !in boundingBox should be true and it's ${Point(-1, -1) !in boundingBox}")
    println("Point(1, 1) in boundingBox should be true and it's ${Point(1, 1) in boundingBox}")
    println("Point(20, 20) in boundingBox should be false and it's ${Point(20, 20) in boundingBox}\n")

    val myList = Node(value = "Hello", next = Node(value = "World", next = Tail))

    println("myList[0] should be 'Hello', is ${myList[0]}")
    println("myList[1] should be 'World', is ${myList[1]}")

    try {
        myList[2]
        println("Should never get here")
    } catch (e: IndexOutOfBoundsException) {
        println("Caught expected exception")
    }
}