package com.orobator.kotlin.intro.lesson16.solutions

val String?.startsWithCapital: Boolean
    get() {
        if (this == null || this.isEmpty()) {
            return false
        }

        return this[0].isUpperCase()
    }

val String?.isEmailAddress: Boolean
    get() {
        val shortestEmail = "a@b.c"
        return if (this == null || this.length < shortestEmail.length) {
            false
        } else this.contains("@")
    }

fun String?.countChar(c: Char): Int {
    return this?.filter { it == c }?.length ?: 0
}
