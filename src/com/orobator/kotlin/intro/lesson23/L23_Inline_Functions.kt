package com.orobator.kotlin.intro.lesson23

// Inline functions

// Using higher-order functions imposes certain runtime penalties:
// each function is an object, and it captures a closure, i.e. those
// variables that are accessed in the body of the function.

// Memory allocations (both for function objects and classes) and
// virtual calls introduce runtime overhead.

// But it appears that in many cases this kind of overhead can be
// eliminated by inlining the lambda expressions.

// The functions shown below are good examples of this situation.
// I.e., the lock() function could be easily inlined at call-sites.
// Consider the following case:

// lock(l) { foo() }

// Instead of creating a function object for the parameter and
// generating a call, the compiler could emit the following code:


