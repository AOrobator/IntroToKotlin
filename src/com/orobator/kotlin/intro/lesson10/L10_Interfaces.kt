package com.orobator.kotlin.intro.lesson10

// Interfaces

// Use the interface keyword to define an interface
interface Foo {
    // Can declare functions as abstract
    fun doFoo()

    // Can provide functions with implementations
    fun doBar() = println("Bar has been done")

    // Can also declare abstract properties
    val prop: Int

    // But interfaces can't contain state, so you can't access backing fields
    val propertyWithImplementation: Int get() = 4
}

interface Bar
interface Baz

open class Base

// A class can implement one or more interfaces
// Best practice to put each interface on its own line, after calling
// constructor of super class
class Derived(override val prop: Int) :
        Base(), /* super class precedes interface(s) */
        Foo,
        Bar,
        Baz {
    override fun doFoo() = Unit
}

// Interface Inheritance

// Interfaces can inherit from other interfaces.

// These interfaces can then provide implementations for previously declared
// functions or properties.

// Implementing classes don't have to re-implement what was done in an
// interface.

interface Named {
    val name: String
}

interface Person : Named {
    val firstName: String
    val lastName: String

    override val name: String get() = "$firstName $lastName"
}

class Employee(
        // implementing 'name' is not required
        override val firstName: String,
        override val lastName: String,
        val position: String
) : Person

interface A {
    fun foo() {
        print("A")
    }
}

interface B {
    fun foo() {
        print("B")
    }
}

// If you have multiple interfaces that provide the same function name, use
// `super<InterfaceName>` to distinguish between interface implementations.
class C : A, B {
    override fun foo() {
        super<A>.foo()
        super<B>.foo()
    }
}