package com.orobator.kotlin.intro.lesson17.labs

import com.orobator.kotlin.intro.lesson17.Point

// Write an operator overload for + so that you can add two Points. You can
// add two Points by adding the X's and the Y's together respectively.

// Given the following class, overload the in operator to detect whether a
// point is in a box. Being on the edge counts.

// The positive y axis points up, the positive x axis points right
data class Box(val topLeft: Point, val bottomRight: Point)

fun main(args: Array<String>) {
//    val result = Point(1,1) + Point(3,3)
//    println("result should be Point(x=4, y=4); is actually $result")

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
}