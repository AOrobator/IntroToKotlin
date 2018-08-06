package com.orobator.kotlin.intro.lesson25

import java.util.*
import java.util.Calendar

// Calling Java code from Kotlin

// Kotlin is designed with Java Interoperability in mind.
// Existing Java code can be called from Kotlin in a natural way,
// and Kotlin code can be used from Java rather smoothly as well.


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