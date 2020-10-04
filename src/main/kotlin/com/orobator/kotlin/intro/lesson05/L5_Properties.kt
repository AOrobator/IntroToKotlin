package com.orobator.kotlin.intro.lesson05

// You can't declare a field in Kotlin!
// When declaring a Kotlin property, setters and getters (accessors) are
// automatically generated for Java inter-op.
class Student {
    var grade: Char = 'A' // getFinalGrade() and setFinalGrade(Char) auto-gen'd
}

// You can define them yourself.
// But it only makes sense to define them if you change the behavior of the
// accessor methods.
class Student1 {
    var grade: Char = 'A'
}

// Field keyword relates to Kotlin's backing field.
// Backing fields are generated if at least one default accessor is used OR
// if a custom accessor references it through the field identifier

// Example of when to use custom setter. (changing visibility or restricting values)
class Student2 {
    var grade: Char = 'A'
        private set(newGrade) { // Can give custom name to setter param
            if (newGrade in 'A'..'F' && newGrade != 'E') {
                field = newGrade
            }
        }
}

// Example of custom getter (computed property)
class Student3 {
    var testGrade = 93
    var hwGrade = 87

    var finalGrade: Char = 'A'
        get() {
            val weightedGrade = (testGrade * .8 + hwGrade * .2).toInt()
            return when (weightedGrade) {
                in 90..100 -> 'A'
                in 80..89 -> 'B'
                in 70..79 -> 'C'
                in 60..69 -> 'D'
                else -> 'F'
            }
        }
}

// Making var's with custom get() is wasteful.
// Field and accessor method still get generated, but never used.
fun main() {
    val student = Student3()

    student.testGrade = 89 // Can access a property similarly to a field.
    student.hwGrade = 89

    println("Student's finalGrade = ${student.finalGrade}")

    student.finalGrade = 'A'

    println("Student's finalGrade after assigning = ${student.finalGrade}")
}

// This can be cleaned up using a val
class Student4 {
    var testGrade = 93
    var hwGrade = 87

    val finalGrade: Char // No backing field generated
        get() {
            val weightedGrade = (testGrade * .8 + hwGrade * .2).toInt()
            return when (weightedGrade) {
                in 90..100 -> 'A'
                in 80..89 -> 'B'
                in 70..79 -> 'C'
                in 60..69 -> 'D'
                else -> 'F'
            }
        }
}

// finalGrade looks a lot like a function...

// Use a property over a function when
// - It describes the object
// - It is computationally trivial
// - Returns the same value over invocations if object state hasn't changed
// - Doesn't throw
// Use a function over a property when
// - It mutates the object
// - Computing the value has observable side-effects (not caching)
// - Is computationally expensive


// Possible pitfall - don't forget get()
class Student5 {
    var testGrade = 93
    var hwGrade = 87


    // Because this val is assigned without a getter,
    // it will be computed at construction time and won't change.
    val finalGradeNumber = (testGrade * .8 + hwGrade * .2).toInt()
    val finalGrade: Char = when (finalGradeNumber) {
        in 90..100 -> 'A'
        in 80..89 -> 'B'
        in 70..79 -> 'C'
        in 60..69 -> 'D'
        else -> 'F'
    }
}

// Backing property
class Foo {
    private var _table: Map<String, Int>? = null
    val table: Map<String, Int>
        get() {
            if (_table == null) {
                _table = HashMap() // Type parameters are inferred
            }
            return _table ?: throw AssertionError("Set to null by another thread")
        }
}

// This is just the same as in Java since access to private properties with
// default getters and setters is optimized so that no function call overhead
// is introduced.


// Lateinit
// Used for when a non-null var can't be defined at instantiation
// Useful for Dependency Injection and testing

annotation class Inject
class Dagger {
    fun inject(any: Any) = Unit
}

class MyActivity {
    @Inject
    lateinit var injectedDependency: Foo

    fun onCreate() {
        Dagger().inject(this)

        // You can check for initialization using a reference to that property (uses reflection!)
        if (::injectedDependency.isInitialized) {
            println("Is initialized")
        }
    }
}