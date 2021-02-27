package com.orobator.kotlin.intro.lesson26

//////////////////////////
// Structured concurrency

// Cancelling the parent cancels the children -> throws
// CancellationException to kids


// Parent Scope waits for children to complete

// Structured concurrency makes it harder to leak coroutines