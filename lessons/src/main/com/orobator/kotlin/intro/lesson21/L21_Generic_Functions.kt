package com.orobator.kotlin.intro.lesson21

// Generic Functions

// Functions can have type parameters as well
// Type parameters are placed before the name of the function.

fun <T> singletonList(item: T): List<T> = listOf(item)

fun <T> T.basicToString(): String {
    return "This is an extension function"
}

// To call a generic function, specify the type arguments at the
// call site AFTER the name of the function, just like Java

val foo = singletonList<Int>(1)

// Type arguments can be omitted if they can be inferred from context

val bar = singletonList(0)

//////////////////////
// Generic Constraints

// The set of all possible types that can be substituted for a given type
// parameter may be restricted by generic constraints.

// Upper Bounds
// Corresponds to Java's extends keyword
fun <T : Comparable<T>> sort(list: List<T>) = Unit

// Only a subtype of Comparable may be substituted for T, for example:

fun demo() {
    sort(listOf(1, 2, 3)) // OK. Int is a subtype of Comparable<Int>
    // Error: HashMap<Int, String> isn't a subtype of
    // Comparable<HashMap<Int, String>>
    // sort(listOf(HashMap<Int, String>()))
}

// Default upper bound (if none specified) is Any?
// Only one upper bound can be specified inside angle brackets
// More than one upper bound requires a where-clause
fun <T> copyWhenGreater(list: List<T>, threshold: T): List<String>
        where T : CharSequence,
              T : Comparable<T> {
    return list.filter { it > threshold }.map { it.toString() }
}

///////////////
// Type Erasure

// The type safety checks that Kotlin performs for generic declaration usages
// are only done at compile time. At runtime, instances of generic types don't
// hold any information about their actual type arguments.

// The type information is said to be erased.

// Ex: instances of Foo<Bar> and Foo<Baz?> are erased to just Foo<*>

// This means there's no general way to check if an instance belongs to a
// generic type with certain type arguments at runtime.

fun <T> demoGeneric(items: List<T>) {
// Error cannot check for instance of erased type: List<int
//  if (items is List<Int>) {
//      print(items)
//  }

    // Unchecked cast can't be checked at runtime
    // Only non generic part (List) can be checked
    (items as List<String>).forEach { print(it.length) }

    // Can do a safe cast that returns null if casting failed
    (items as? List<String>)?.forEach { print(it.length) }
}