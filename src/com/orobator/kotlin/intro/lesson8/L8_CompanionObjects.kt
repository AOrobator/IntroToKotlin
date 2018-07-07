package com.orobator.kotlin.intro.lesson8

import com.orobator.kotlin.intro.lesson7.Activity
import com.orobator.kotlin.intro.lesson7.Context

// Kotlin doesn't have static methods or fields
// Instead it has a companion object
class MyActivity : Activity() {
    val someProperty: Int = 0

    // Goes at the bottom of the class by convention.
    companion object {
        // Everything here may look static, but they're all instance members of objects

        // Constants are in capital snake case.
        val ACTION_FOO = "com.example.foo"

        // Use const word
        const val ACTION_BAR = "com.example.bar"

        fun getIntent(context: Context, x: Int, y: Int): Intent = Intent(context, ACTION_BAR)
    }
}
