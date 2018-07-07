package com.orobator.kotlin.intro.lesson07

// All Kotlin classes have a common super class kotlin.Any
class Example : Any() // Explicit declaration for super class

// Any is NOT java.lang.Object, although it is analogous
// - Only defines equals(), hashCode(), and toString()
// When consuming Java APIs with Object, it'll be converted to Any

// We must declare things as open if we want to extend them
// Opposite of Java's final keyword
// By default, all classes in Kotlin are final, which corresponds to Effective
// Java Item 19: Design and document for inheritance or else prohibit it.
open class ParentClass(i: Int)

// To extend a class, use a colon, then call the parent class's primary constructor
class ChildClass(i: Int) : ParentClass(i)

// If the class doesn't have a primary constructor, each secondary constructor
// must initialize the base type

class MyView : View {
    // Different secondary constructors can call different parent
    constructor(ctx: Context) : super(ctx)

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)
}

// The above is an example from the docs, but there's a much cleaner way to
// make a custom view

class MyCustomView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    init {
        inflate(context, R.layout.custom_view, this)
    }
}

// @JvmOverloads in combination with the default parameters help you create
// all the constructors

// Overrides
open class Parent {
    // Properties also need to be declared open in order to override
    open val someVal: Int = 2
    open val otherVal: String = "otherVal"
    open val lastVal: String = "lastVal"

    open fun foo() = Unit
    fun bar() = Unit
}

class Child : Parent() {
    // type needs to be compatible
    override val someVal: Int = 3 // Can declare with an initializer

    override val otherVal: String // Can also declare with a getter
        get() = "overridden val"

    // Possible to override val and make it a var
    // This works because a val declares a getter method,
    // while the overriding class declares a setter method
    var _backingProperty = ""
    override var lastVal: String
        get() = _backingProperty
        set(value) {
            _backingProperty = value
        }

    // Override keyword is required, compiler complains otherwise
    override fun foo() = println("overriding foo")
    // override fun bar() = Unit // Impossible because bar() is final
}

// Be Careful! Derived class initialization order
// When constructing a derived class, the base class is initialized first.

// By the time the base class's constructor executes, properties declared or
// overridden in the base types haven't been initialized.

// Using any of these properties in the initialization logic could lead to
// incorrect behaviors or runtime exceptions.

// Designing a base class, you should therefore avoid using open members in
// the constructors, property initializers, and init blocks.
open class Base(val name: String) {
    init {
        println("Initializing Base")
    }

    open val size: Int =
            name.length.also { println("Initializing size in Base: $it") }
}

class Derived(
        name: String,
        val lastName: String
) : Base(name.capitalize().also { println("Argument for Base: $it") }) {
    init {
        println("Initializing Derived")
    }

    override val size: Int = (super.size + lastName.length).also { println("Initializing size in Derived: $it") }
}

fun main(args: Array<String>) {
    println("""Constructing Derived("hello", "world")""")
    val d = Derived("hello", "world")
}

// Calling the super class
open class Activity {
    open val title: String get() = "Title"

    open fun onResume() = Unit
}

class MyActivity : Activity() {
    // Similar to java, you use the super keyword to call up to the parent
    // class's implementation
    override val title: String
        get() = super.title + " MyActivity"

}


// Abstract classes in Kotlin work very similarly to Java
abstract class AbstractActivity : Activity() {
    // Can't create instances
    // abstract members don't need implementations

    abstract val x: Int

    abstract fun doSomething()

    // Can override other methods and make them abstract
    abstract override fun onResume()
}