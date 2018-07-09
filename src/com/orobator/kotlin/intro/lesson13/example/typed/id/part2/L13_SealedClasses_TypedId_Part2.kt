package com.orobator.kotlin.intro.lesson13.example.typed.id.part2

import com.orobator.kotlin.intro.lesson13.example.typed.id.Song

// Where do the sealed classes come in?
// It’s possible for a Song to have an invalid ID if it hasn’t been added to
// the database yet. Previously I was using an ID of -1 to represent this
// state, but we can do much better with sealed classes.

// Here’s the updated version of SongId where the type system takes into
// account that a SongId can be invalid:

sealed class SongId
data class ValidSongId(val id: Long) : SongId()
object InvalidSongId : SongId() // Singleton because there's only one

val Song.typedId: SongId
    get() = if (this.id == -1L) InvalidSongId else ValidSongId(this.id)

// The key benefit of using sealed classes comes into play when you use them in
// a when expression. If it’s possible to verify that the statement covers all
// cases, you don’t need to add an else clause to the statement. However, this
// works only if you use when as an expression (using the result) and not as a
// statement.

// This allows us to write the following code in SongRepositoryImpl:
interface SongRepository {
    fun getSongById(songId: SongId): Song?
}

class SongRepositoryImpl : SongRepository {
    override fun getSongById(songId: SongId): Song? = when (songId) {
    // Able to access id because of smartcast
        is ValidSongId -> Song(songId.id)
        InvalidSongId -> null
    // No else statement needed, but compiler will complain if new SongId
    // subtype is created.
    }
}

// It’s worth mentioning the runtime downsides of doing this, which is the cost
// of the allocation of these wrappers and the constant indirection that you
// must unwrap. If we did this for everything there would be a significant
// penalty. If we did this for nothing, though, the chances of bugs is
// extremely high (see: javascript, python, etc.). Where this makes sense to
// use is different for every app.

// But, since the examples are in Kotlin, that leads me to something
// forthcoming in Kotlin 1.3 which is inline classes. Basically all the type
// safety of wrapper classes with all the overhead of typealias (aka none).
// There’s no documentation on them yet, you have to sleuth around in the
// compiler and its tests to take a look at them, but they basically enable
// creating these wrapper classes which only exist in the type system and then
// which are erased at compile time. Any methods or properties defined on these
// classes become static methods taking the wrapped type as the first parameter.
//   - Jake Wharton
// https://proandroiddev.com/types-as-tests-how-to-fail-the-build-when-theres-a-logical-error-e7000e2f62b8
