package com.orobator.kotlin.intro.lesson16.solutions

import com.orobator.kotlin.intro.lesson13.example.typed.id.Song
import com.orobator.kotlin.intro.lesson13.example.typed.id.part1.SongId

// Refactor bindNowPlayingListItem() to use Kotlin standard library functions
object songRepo {
    fun getSongById(id: SongId): Song? = null
}

interface NowPlayingListItem {
    fun loadArtwork(artworkLocation: String)
    fun setTitle(title: String)
    fun setArtist(artist: String)
    fun loadDefaultAlbumArt()
}

fun bindNowPlayingListItem(listItem: NowPlayingListItem) {
    songRepo.getSongById(SongId(5))?.let { song ->
        listItem.apply {
            setTitle(song.title)
            setArtist(song.artist)

            if (song.hasArtwork) {
                loadArtwork(song.artworkLocation)
            } else {
                loadDefaultAlbumArt()
            }
        }
    }
}