package com.orobator.kotlin.intro.lesson13.example.typed.id

// Shared models for the typed ID example
class Song(val id: Long) {
    val title: String = ""
    val artist: String = ""
    var hasArtwork: Boolean = true
    val artworkLocation: String = ""
}

class Playlist(val id: Long)