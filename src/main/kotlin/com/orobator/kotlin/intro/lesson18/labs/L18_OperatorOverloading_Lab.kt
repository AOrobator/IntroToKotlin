package com.orobator.kotlin.intro.lesson18.labs

import com.orobator.kotlin.intro.lesson18.Point

// Write an operator overload for + so that you can add two Points. You can
// add two Points by adding the X's and the Y's together respectively.

// Given the following class, overload the in operator to detect whether a
// point is in a box. Being on the edge counts.

// The positive y axis points up, the positive x axis points right
data class Box(val topLeft: Point, val bottomRight: Point)

// Write a sealed class IndexedLinkedList that has two possible subtypes Node
// and Tail.
// Node should have two properties, value: String and next: IndexedLinkedList
// Tail should be declared as an object.

// Overload the get operator function so you can index into the list like you
// would a regular list. Throw IndexOutOfBoundsExceptions when appropriate.

fun main() {
//    val result = Point(1, 1) + Point(3, 3)
//    println("result should be Point(x=4, y=4); is actually $result\n")

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

//    println("Point(-1, -1) !in boundingBox should be true and it's ${Point(-1, -1) !in boundingBox}")
//    println("Point(1, 1) in boundingBox should be true and it's ${Point(1, 1) in boundingBox}")
//    println("Point(20, 20) in boundingBox should be false and it's ${Point(20, 20) in boundingBox}\n")
//
//    val myList = IndexedLinkedList.Node(value = "Hello", next = IndexedLinkedList.Node(value = "World", next = IndexedLinkedList.Tail))
//
//    println("myList[0] should be 'Hello', is ${myList[0]}")
//    println("myList[1] should be 'World', is ${myList[1]}")
//
//    try {
//        myList[2]
//        error("Should never get here")
//    } catch (e: IndexOutOfBoundsException) {
//        println("Caught expected exception")
//    }
}