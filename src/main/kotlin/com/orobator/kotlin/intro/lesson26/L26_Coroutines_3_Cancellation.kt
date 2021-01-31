package com.orobator.kotlin.intro.lesson26

// Structured concurrency

// Compare to thread.join(), tracking all launched threads and having to manage them

// Instead use scopes. coroutine builders (examples) give your coroutines a scope to run in

// lambda with receiver type as parameter to coroutine builder

// mention GlobalScope

///////////////
// Benefits

// The scope is generally responsible for child coroutines, and their lifetime
// is attached to the lifetime of the scope.

// The scope can automatically cancel child coroutines if something goes wrong
// or if a user simply changes their mind and decides to revoke the operation.

// The scope automatically waits for completion of all the child coroutines.
// Therefore, if the scope corresponds to a coroutine, then the parent coroutine
// does not complete until all the coroutines launched in its scope are complete.


// Compare cancellation of GlobalScope with cancellation of other scopes

//////////
// Jobs

// coroutine builders usually return jobs (runBlocking is an exception)
// job.join()

// Cancellation,
// lab exploring cancellation
// Scoping (SupervisorScope)
