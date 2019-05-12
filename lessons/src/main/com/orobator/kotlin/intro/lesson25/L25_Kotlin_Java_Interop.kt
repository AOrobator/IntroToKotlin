package com.orobator.kotlin.intro.lesson25

import java.util.Calendar
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

// Calling Java code from Kotlin

// Kotlin is designed with Java Interoperability in mind.
// Existing Java code can be called from Kotlin in a natural way,
// and Kotlin code can be used from Java rather smoothly as well.
typealias JList = ArrayList<Int>

fun demo(source: List<Int>) {
    val list = ArrayList<Int>()
    // 'for'-loops work for Java collections:
    for (item in source) {
        list.add(item)
    }
    // Operator conventions work as well:
    for (i in 0 until source.size) {
        list[i] = source[i] // get and set are called
    }
}

//////////////////////
// Getters and Setters

// Methods that follow the Java conventions for getters and setters
// (no-argument methods with names starting with get and
// single-argument methods with names starting with set) are
// represented as properties in Kotlin.

// Boolean accessor methods (where the name of the getter starts with
// is and the name of the setter starts with set) are represented as
// properties which have the same name as the getter method.
fun calendarDemo() {
    val calendar = Calendar.getInstance()
    if (calendar.firstDayOfWeek == Calendar.SUNDAY) {  // call getFirstDayOfWeek()
        calendar.firstDayOfWeek = Calendar.MONDAY      // call setFirstDayOfWeek()
    }

    if (!calendar.isLenient) {    // call isLenient()
        calendar.isLenient = true // call setLenient()
    }
}

fun main() {
    val foo = FooBar()
    val bar: Unit = foo.getBar()
}

// Note that, if the Java class only has a setter, it will not be
// visible as a property in Kotlin, because Kotlin does not support
// set-only properties at this time.


/////////////////////////
// Methods returning void

// If a Java method returns void, it will return Unit when called from Kotlin.
// If, by any chance, someone uses that return value, it will be
// assigned at the call site by the Kotlin compiler, since the value
// itself is known in advance (being Unit).


////////////////////////////////////////////////////////////
// Escaping for Java identifiers that are keywords in Kotlin

// Some of the Kotlin keywords are valid identifiers in Java: in,
// object, is, etc.

// If a Java library uses a Kotlin keyword for a method,
// you can still call the method escaping it with the backtick (`) character:
fun mockitoDemo() {
    val mockedInterface = Mockito.mock()
    Mockito.`when`(mockedInterface).doSomething()
}

/////////////////////////////////
// Null-Safety and Platform Types

// Any reference in Java may be null, which makes Kotlin's
// requirements of strict null-safety impractical for objects coming
// from Java.

// Types of Java declarations are treated specially in Kotlin and
// called platform types.

// Null-checks are relaxed for such types, so that safety guarantees
// for them are the same as in Java.

fun platformTypeDemo() {
    val foo: String = FooBar().makeString()

    val list = ArrayList<String>() // non-null (constructor result)
    list.add("Item")
    val size = list.size // non-null (primitive int)
    val item = list[0] // platform type inferred (ordinary Java object)

    // Kotlin does not issue nullability errors at compile time,
    // but the call may fail at runtime, because of a null-pointer
    // exception or an assertion that Kotlin generates
    // to prevent nulls from propagating

    item.substring(1) // allowed, may throw an exception if item == null

    // Platform types are non-denotable, meaning that one can not
    // write them down explicitly in the language.
    // When a platform value is assigned to a Kotlin variable,
    // we can rely on type inference, or we can choose the
    // type that we expect:

    val nullable: String? = item // allowed, always works
    val notNull: String = item // allowed, may fail at runtime

    // Using a non-null type makes the compiler generate
    // non-null assertion
}

// This prevents Kotlin's non-null variables from holding nulls.
// Assertions are also emitted when we pass platform values to
// Kotlin functions expecting non-null values etc.

// Overall, the compiler does its best to prevent nulls from
// propagating far through the program (although sometimes this
// is impossible to eliminate entirely, because of generics).

//////////////////////////////////////////////////////////
// Platform types are a big source of bugs! Watch out!!!//
//////////////////////////////////////////////////////////

//////////////////////////////
// Notation for Platform Types

// Platform types cannot be mentioned explicitly in the program,
// so there's no syntax for them in the language.

// Nevertheless, the compiler and IDE need to display them
// sometimes (in error messages, parameter info etc),
// so we have a mnemonic notation for them:

// - T! means "T or T?",
// - (Mutable)Collection<T>! means "Java collection of T may be
//   mutable or not, may be nullable or not",
// - Array<(out) T>! means "Java array of T (or a subtype of T),
//   nullable or not"

//////////////////////////
// Nullability Annotations

// Best way to defend against platform types
// If things are annotated with their nullability,
// Kotlin will use that to assign the correct type. (null vs non-null)

// Whole bunch of annotations recognized:
// JetBrains (@Nullable and @NotNull from the org.jetbrains.annotations package)
// Android (com.android.annotations and android.support.annotations)
// JSR-305 (javax.annotation)
// And more! see https://kotlinlang.org/docs/reference/java-interop.html#nullability-annotations


///////////////////////////////////////////
// SAM Conversions (Single Abstract Method)

// Kotlin function literals can be automatically converted into
// implementations of Java interfaces with a single non-default method

// You can use this for creating instances of SAM interfaces:
fun samDemo() {
    val runnable = Runnable { println("This runs in a runnable") }

    val executor = ThreadPoolExecutor(1, 1,1, TimeUnit.SECONDS, null)
    // Java signature: void execute(Runnable command)
    executor.execute { println("This runs in a thread pool") }
}

class KotlinClass {
    var foo: String = ""
}

// Note that SAM conversions only work for interfaces,
// not for abstract classes,
// even if those also have just a single abstract method.