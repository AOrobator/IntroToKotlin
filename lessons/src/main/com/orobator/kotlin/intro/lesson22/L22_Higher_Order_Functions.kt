package com.orobator.kotlin.intro.lesson22

import com.orobator.kotlin.intro.lesson07.Context

// Higher-Order Functions

// In Kotlin, functions are first class citizens. This means they can be
// stored in variables and data structures, can be passed as in as parameters
// to functions, and can be returned from functions.

// Basically you can operate with functions in any way that's possible for
// other non-function values.

// To facilitate this, Kotlin has a family of function types to represent
// functions and provides special language constructs like lambda expressions.

// What is a higher-order function?

// A function that takes functions as parameters or returns a function.

// Where have we seen higher-order functions already?

// map(), filter(), apply(), fold() etc.

// Fold takes an initial accumulator value and a combining function and builds
// its return value by consecutively combining current accumulator value with
// each collection element, replacing the accumulator:

inline fun sum(x: Int, y: Int) = x + y

//fun <T, R> Collection<T>.fold(
//        initial: R,
//        combine: (acc: R, nextElement: T) -> R
//): R {
//    var accumulator: R = initial
//    for (element: T in this) {
//        // Passed in function is called here
//        accumulator = combine(accumulator, element)
//    }
//    return accumulator
//}

// The parameter combine has a function type (R, T) -> R, so it accepts a
// function that takes two arguments of types R and T and returns a value
// of type R

// To call fold, we need to pass it an instance of the function type as an
// argument, and lambda expressions are widely used for this purpose at
// higher-order function call sites:

//fun main() {
//    val items = listOf(1, 2, 3, 4, 5)
//
//    // Lambdas are code blocks enclosed in curly braces.
//    items.fold(0) {
//        // When a lambda has parameters, they go first, followed by '->'
//        acc: Int, i: Int ->
//        print("acc = $acc, i = $i, ")
//        val result = acc + i
//        println("result = $result")
//        // The last expression in a lambda is considered the return value:
//        result
//    }
//
//    // When the last parameter of a function is a function type, we can
//    // put the lambda expression outside of the parentheses.
//
//    // Parameter types in a lambda are optional if they can be inferred:
//    val joinedToString: String = items.fold("Elements:") { acc, i -> "$acc $i" }
//
//    // Function references can also be used for higher-order function calls:
//    val product = items.fold(1, Int::times)
//
//    println("joinedToString = $joinedToString")
//    println("product = $product")
//}

/////////////////
// Function types

// Kotlin uses a family of function types like (Int) -> String for
// declarations that deal with functions: val onClick: () -> Unit = ...

// These types have a special notation that corresponds to the
// signatures of the functions, i.e. their parameters and return values:

// All function types have a parenthesized parameter types list and a
// return type: (A, B) -> C denotes a type that represents functions
// taking two arguments of types A and B and returning a value of type C.

// The parameter types list may be empty, as in () -> A.

typealias StringProducer = () -> String

val foo: StringProducer = { "hello" }
val bar = foo().length


// A.(B) -> C
// (A, B) -> C
val higherOrder: StringProducer.(Int) -> Int = { f -> 2 * f }

//fun main() {
//    println(10 == foo.higherOrder(5))
//}

// The Unit return type cannot be omitted.

// Function types can optionally have an additional receiver type,
// which is specified before a dot in the notation:

// The type A.(B) -> C represents functions that can be called on a
// receiver object of A with a parameter of B and return a value of C.

// When you're executing a function with a receiver type,
// `this` refers to the receiver object.

/**
 * Helper method to pass in usages of the shortcut api without having
 * to do an explicit version check every time.
 */
inline fun shortcutAction(
        context: Context,
        action: ShortcutManager.() -> Unit
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
        val shortcutManager =
                context.getSystemService(ShortcutManager::class.java)
        shortcutManager.action()
        action(shortcutManager)
    }
}

fun demoFunctionWithReceiverType() {
    val context = Context()
    shortcutAction(context) {
        // Can call functions of receiver type, ShortcutManager
        // `this` refers to the ShortcutManager
        this.requestPinShortcut(ShortcutInfo(id = "my_shortcut"))
    }
}

// The function type notation can optionally include names for the
// function parameters: (x: Int, y: Int) -> Point.
// These names can be used for documenting the meaning of the parameters.

// To specify that a function type is nullable, use parentheses:
// ((Int, Int) -> Int)?

// Function types can be combined using parentheses:
// (Int) -> ((Int) -> Unit)

// The arrow notation is right-associative,
lateinit var baz: (Int) -> (Int) -> Unit //is equivalent to the previous example,
// but not to ((Int) -> (Int)) -> Unit.

// You can also give a function type an alternative name by using a type alias:
typealias ClickHandler = (Button, ClickEvent) -> Unit

////////////////////////////////
// Instantiating a function type

val lambdaExpression = { x: Int, y: Int -> x + y }
// There are several ways to obtain an instance of a function type:
val anonFunction = fun(x: Int, y: Int): Int = x + y

val result = anonFunction(3, 4)
val result2 = lambdaExpression(3, 4)

// Using a code block within a function literal, in one of the forms:
// - a lambda expression: { a, b -> a + b },
// - an anonymous function: fun(s: String): Int { return s.toIntOrNull() ?: 0 }

fun isOdd(n: Int): Boolean = n % 2 == 1

fun demoMethodRef() {
    val listOfInts = listOf(1, 2, 3).filter { x -> x % 2 == 1 }
}

fun badLambda(f: () -> Unit, n: Int) {}

fun demoBadCall() {
    badLambda({}, 5)
}

fun goodLambda(n: Int, f: () -> Unit) {}

fun demoGoodCall() {
    goodLambda(5, { print("") })

    goodLambda(5) { print("") }
}

// Using a callable reference to an existing declaration:
// - a top-level, local, member, or extension function: ::isOdd, String::toInt,
// - a top-level, member, or extension property: List<Int>::size,
// - a constructor: ::Regex,
// - a member of a particular instance: foo::toString

// Using instances of a custom class that implements a function type
// as an interface:

class IntTransformer : (Int) -> Int {
    override operator fun invoke(x: Int): Int = 5

    // Can still have other methods
    fun doSomething() {

    }
}

val intFunction = IntTransformer()

val fooBar: Int = intFunction.invoke(3)

class Baz {
    fun foo() = "Native"
}

fun Baz.foo() = "Extension"

//fun main() {
//    val baz = Baz()
//
//    print(baz.foo())
//}

// The compiler can infer the function types for variables if there is
// enough information:

fun Int.plus(l: Long): Int = this + l.toInt()
val a = { i: Int -> i + 1 } // The inferred type is (Int) -> Int

////////////////////////////////////
// Invoking a function type instance

// A value of a function type can be invoked by using its
// invoke(...) operator: f.invoke(x) or just f(x).

// If the value has a receiver type, the receiver object should be
// passed as the first argument.

// Another way to invoke a value of a function type with receiver is
// to prepend it with the receiver object, as if the value were an
// extension function: 1.foo(2)

// A.(B) -> C
// (A, B) -> C

fun main1(args: Array<String>) {
    val stringPlus: (String, String) -> String = String::plus
    val intPlus: Int.(Int) -> Int = Int::plus

    println(stringPlus.invoke("<-", "->"))
    println(stringPlus("Hello, ", "world!"))

    println(intPlus.invoke(1, 1))
    println(intPlus(1, 2))
    println(2.intPlus(3)) // extension-like call
}

/////////////////////////////////////////////
// Returning a value from a lambda expression

// We can explicitly return a value from the lambda using the
// qualified return syntax.

// Otherwise, the value of the last expression is implicitly returned.

fun notInline(f: () -> Unit) = f()


// Therefore, the two following snippets are equivalent:
fun returnDemo(): () -> Unit {
    val ints = listOf(1, 2, 3)

    ints.filter {
        val shouldFilter = it > 0
        shouldFilter // last line interpreted as return value
    }

//    notInline {
//        return
//    }

    val mutableList = mutableListOf<Int>()
    for (i in ints) {
        val check = i > 0
        return {}
    }


//    fun(x: Int): Int {
//        return {}
//    }


    ints.filter {
        val shouldFilter = it > 0
        return {}
    }
//    }

//    return fun() = Unit
    return {}
}

//////////////////////////////////
// Underscore for unused variables

// If the lambda parameter is unused, you can place an underscore
// instead of its name:
fun underscoreDemo() {
    val map = mapOf(1 to 2, 2 to 3, 3 to 4)
    map.forEach { _, value -> println("$value!") }
}

// Same pattern used when destructuring a data class

//////////////////////
// Anonymous functions

// One thing missing from the lambda expression syntax is the ability
// to specify the return type of the function.

// Unnecessary in most cases due to type inference.

// However, if you do need to specify it explicitly,
// you can use an alternative syntax: an anonymous function.

val anon = fun(x: Int, y: Int): Int = x + y

// An anonymous function looks very much like a regular function
// declaration, except that its name is omitted.

// Its body can be either an expression (as shown above) or a block:
val anon1 = fun(x: Int, y: Int): Int {
    return x + y
}

// The parameters and the return type are specified in the same way as
// for regular functions, except that the parameter types can be
// omitted if they can be inferred from context:
fun typeOmissionDemo() {
    val ints = listOf(1, 2, 3)
    ints.filter(fun(item) = item > 0)
}

///////////
// Closures

// A lambda expression or anonymous function can access its closure,
// i.e. the variables declared in the outer scope.

// Unlike Java, the variables captured in the closure can be modified:
fun main() {
    val ints = listOf(0)
    var sum = 0
    ints.filter { it > 0 }
            .forEach { sum += it }
    println("Before notinline Sum = $sum")

    notInline { sum++ }

    println("After notinline Sum = $sum")
}