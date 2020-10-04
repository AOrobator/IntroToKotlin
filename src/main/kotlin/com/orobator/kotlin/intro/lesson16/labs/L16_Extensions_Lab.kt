package com.orobator.kotlin.intro.lesson16.labs

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
    val currentSong: Song? = songRepo.getSongById(SongId(5))

    if (currentSong != null) {
        listItem.setTitle(currentSong.title)
        listItem.setArtist(currentSong.artist)

        if (currentSong.hasArtwork) {
            listItem.loadArtwork(currentSong.artworkLocation)
        } else {
            listItem.loadDefaultAlbumArt()
        }
    }
}