package com.orobator.kotlin.intro.lesson13.typed.id.example.part1

import com.orobator.kotlin.intro.lesson13.typed.id.example.Playlist
import com.orobator.kotlin.intro.lesson13.typed.id.example.Song

// Sealed classes can also be used to prevent strange bugs
// Do you see the potential bug with this interface?

interface PlaylistRepository {
    fun addSongToPlaylist(songId: Long, playlistId: Long)
}

// When storing ID's in the database, they're stored as longs in totally
// different tables, so that makes sense. But to the compiler, a songId has the
// same type as a playlistId. Logically this doesn't make sense, and you'd be
// able to get away with passing in a playlistId as a songId and vice versa.
// This could lead to very unexpected behavior or runtime exceptions.

val playlist: Playlist = Playlist(4)
val song: Song = Song(824)
val playlistRepo = object : PlaylistRepository {
    override fun addSongToPlaylist(songId: Long, playlistId: Long) = Unit
}

fun showcasePotentialBug() {
    // This is a bug, but the compiler allows it!
    playlistRepo.addSongToPlaylist(songId = playlist.id, playlistId = song.id)
}

// Here's where a stronger type system comes into play
data class SongId(val id: Long)

data class PlaylistId(val id: Long)

interface PlaylistRepository2 {
    fun addSongToPlaylist(songId: SongId, playlistId: PlaylistId)
}

val playlistRepo2 = object : PlaylistRepository2 {
    override fun addSongToPlaylist(songId: SongId, playlistId: PlaylistId) = Unit
}

fun showcasePotentialBugSquashed() {
    // Compiler won't allow this because it's strongly typed!
//    playlistRepo2.addSongToPlaylist(songId = PlaylistId(4), playlistId = SongId(940))
}

// We have compile time safety, but we still need to generate these types.
// To prevent modifying the model, we'll create extension properties.
val Song.typedId: SongId get() = SongId(this.id)
val Playlist.typedId: PlaylistId get() = PlaylistId(this.id)

// Extension properties allow us to extend/add a property to a class.
// Extensions can only access the public API of a class
// Will be covered more in-depth later

fun showImprovedCallSite() {
    playlistRepo2.addSongToPlaylist(song.typedId, playlist.typedId)
}
