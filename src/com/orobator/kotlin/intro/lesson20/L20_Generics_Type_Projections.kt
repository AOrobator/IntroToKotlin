package com.orobator.kotlin.intro.lesson20

// Generics - Type Projections

// Use-site variance
// It's convenient to declare a type parameter T as out to avoid
// subtyping issues at the use site, but some classes can't be
// restricted to only return T's. A good example is Array

const val array = """
class Array<T>(val size: Int) {
    fun get(index: Int): T { ... }
    fun set(index: Int, value: T) { ... }
}
"""

// Because this class produces AND consumes T's, it can't be marked
// with in or out. Neither covariant nor contravariant

// This imposes restrictions. Consider the following function:

/**
 * Copies elements in src and puts them in dst
 */
fun copy(src: Array<Any>, dst: Array<Any>) {
    assert(src.size == dst.size)
    for (i in src.indices) {
        dst[i] = src[i]
    }
}

fun main(args: Array<String>) {
    val ints: Array<Int> = arrayOf(1, 2, 3)
    val any = Array<Any>(3) { "" }
//  copy(ints, any) // Error: expects (Array<Any>, Array<Any>)
}

// Array<T> is invariant in T
// Means Array<Int> is NOT a subtype of Array<Any>

// copy() *might* be doing bad things, i.e. it might attempt to write a
// String to src, and if we actually passed an array of Int there,
// a ClassCastException would have been thrown later.

// We can prohibit copy from writing to src by using type projection:

fun typeSafeCopy(src: Array<out Any>, dst: Array<Any>) {
    assert(src.size == dst.size)
    for (i in src.indices) {
        dst[i] = src[i]
//      src[i] = "" // Not allowed
    }
}

// We said that src is a restricted (projected) Array.
// Now we can only call methods that return type T.
// get() in this case.

// This is use-site variance and corresponds to Java's Array<? extends Object>

// You can project a type with *in* as well:
const val inTypeProjection = """
    fun fill(dest: Array<in String>, value: String) { ... }
"""

// Array<in String> corresponds to Java's Array<? super String>, i.e. you can
// pass an array of CharSequence or an array of Object to the fill() function.