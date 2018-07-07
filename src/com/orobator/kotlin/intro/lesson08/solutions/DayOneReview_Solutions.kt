package  com.orobator.kotlin.intro.lesson08.solutions

import java.util.*

// 1. What's the difference between a val and a var?

// A val is a read-only property, while a var is a read/write property. I
// specifically didn't use the word immutable because a val can actually change!
val changingVal: Int
    get() = Random().nextInt()

// List.size is a real world example of a val that changes over invocations
// A possible solution is to make vals that change over invocations a function
// instead of a property.
fun changeingVal(): Int = Random().nextInt()


// 2. What are some of the major differences between Java and Kotlin so far?

// There are many but: Nullability, properties vs fields, string interpolation
// and parameter name and type order are just a few.


// 3. What's a default parameter? Why would you use one?

// A function or constructor parameter that you can optionally specify upon
// invocation. If you choose not to specify it, the value in the default
// parameter will be used. You can use it as a way of doing function
// overloading.


// 4. Name a difference between the Java switch statement and the Kotlin when statement

// Kotlin when statements have no break statements. They also don't fall
// through to the next stage. If you want to have things map to the same logic,
// you have to be explicit about that. When statements can also be expressions.


// 5. What's the difference between a Java field and a Kotlin property?

// A Kotlin property has overrideable accessor methods (and sometimes a backing
// field) instead of a field. You can't declare a field in Kotlin!