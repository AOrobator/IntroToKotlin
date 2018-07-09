package com.orobator.kotlin.intro.lesson13

// Sealed classes are used for representing restricted class hierarchies.
// A value can have a type from a limited set, similar to enums.
// Major difference is that with enums, you can only have a single instance of
// each type. Sealed classes allow you to have multiple instances of a type
// that can contain state.

// Direct children of sealed class must be in the same file.

// Toy example
sealed class LinkedList

data class Node(val value: Int, val nextNode: LinkedList) : LinkedList()
object Tail : LinkedList()

fun contains(x: Int, list: LinkedList): Boolean = when (list) {
// Compiler forces you to check for both types
    is Node -> if (x == list.value) true else contains(x, list.nextNode)
    Tail -> false
}
