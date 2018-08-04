package com.orobator.kotlin.intro.lesson21.solutions

import java.util.*

fun <T, C : MutableCollection<T>> Collection<T>.partitionTo(first: C, second: C, predicate: (T) -> Boolean): Pair<C, C> {
    for (element in this) {
        if (predicate(element)) {
            first.add(element)
        } else {
            second.add(element)
        }
    }
    return Pair(first, second)
}

fun partitionWordsAndLines() {
    val (words, lines) = listOf("a", "a b", "c", "d e").partitionTo(ArrayList<String>(), ArrayList()) { s -> !s.contains(" ") }
    println(words == listOf("a", "c"))
    println(lines == listOf("a b", "d e"))
}

fun partitionLettersAndOtherSymbols() {
    val (letters, other) = setOf('a', '%', 'r', '}').partitionTo(HashSet<Char>(), HashSet()) { c -> c in 'a'..'z' || c in 'A'..'Z' }
    println(letters == setOf('a', 'r'))
    println(other == setOf('%', '}'))
}