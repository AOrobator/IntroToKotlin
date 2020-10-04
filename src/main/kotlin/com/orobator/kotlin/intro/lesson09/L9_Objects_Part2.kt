package com.orobator.kotlin.intro.lesson09

// Object Expressions
fun main() {
    val editText = EditText()
    var changeCount = 0

    // Instead of anonymous classes, Kotlin has object expressions
    editText.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(text: String) = Unit

        override fun beforeTextChanged(text: String) = Unit

        override fun onTextChanged(text: String) {
            // Code in object expressions can access variables from the
            // enclosing scope. Unlike Java, this isn't restricted to final
            // variables.
            changeCount++
        }
    })

    // We can also declare an object with no super type
    val adHoc = object {
        var x: Int = 0
        var y: Int = 0
    }

    // Object expressions are executed/initialized where they are declared
    print(adHoc.x + adHoc.y)
}

// Returning Object Expressions

// If you return an object expression as a public function, the return type
// will be Any. Therefore, none of the custom properties or methods will be
// accessible.

// If you return an object expression as a private function, you'll be able to
// use the custom properties.
class C {
    // Private function, so the return type is the anonymous object type
    private fun foo() = object {
        val x: String = "x"
    }

    // Public function, so the return type is Any
    fun publicFoo() = object {
        val x: String = "x"
    }

    fun bar() {
        val x1 = foo().x        // Works
//        val x2 = publicFoo().x  // ERROR: Unresolved reference 'x'
    }
}