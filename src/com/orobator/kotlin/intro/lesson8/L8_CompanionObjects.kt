package com.orobator.kotlin.intro.lesson8

import com.orobator.kotlin.intro.lesson7.Activity
import com.orobator.kotlin.intro.lesson7.Context

// Kotlin doesn't have static methods or fields
// Instead it has a companion object
class MyActivity : Activity() {
    val someProperty: Int = 0

    // Goes at the bottom of the class by convention. It's initialized when
    // the corresponding class is loaded (resolved), matching the semantics of
    // a Java static initializer.
    companion object {
        // Constants are in capital snake case.
        val ACTION_FOO = "com.example.foo"

        // Use const word to declare a compile time constant
        // Compile time constants can be used in annotations
        private const val ACTION_BAR = "com.example.bar"

        fun getIntent(context: Context, x: Int, y: Int): Intent = Intent(context, ACTION_BAR)
    }

}

// Using members of a companion object is similar to calling Java static
// functions or accessing static properties.
val intent = MyActivity.getIntent(Context(), x = 1, y = 0)

// Companion object is named Companion by default.
val activityCompanion = MyActivity.Companion

// Can rename Companion object if you like
class Authenticator {
    companion object Factory {
        fun create(): Authenticator = Authenticator()
    }
}

val authCompanion = Authenticator.Factory

class GoodJavaInterop {
    companion object {
        @JvmField
        val JVM_FIELD_VAL = 0

        const val CONST_VAL = 1

        @JvmStatic
        fun jvmStaticFunction() = Unit
    }
}


/** See how it looks in Java: [L8_CompanionObjects_Part2] */