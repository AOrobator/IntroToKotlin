package com.orobator.kotlin.intro.lesson26.solutions

// Modify NumberFactViewModel so that it doesn't use a SupervisorJob. What is
// the new behavior of the running coroutines and the scopes they are in?

// Now that NumberFactViewModel isn't using a SupervisorJob, when
// getRandomMathFact fails, getRandomNumberFact fails as well. This is because
// they share the same scope, and an unhandled exception stops execution for
// that scope.

// What are some benefits of structured concurrency?

// - Easier to keep track of all coroutines

// - Can easily wait for all coroutines to finish or cancel them

// - Makes it easier to reason about the lifecycle of coroutines.