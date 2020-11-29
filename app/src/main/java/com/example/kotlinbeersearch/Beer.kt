package com.example.kotlinbeersearch

class Beer(val name: String, val tagline:String, val description:String, val imageurl:String) {
    private var favorite: Boolean = false

    fun toggleFavorite(){
        favorite = !favorite
    }
}

