package com.example.kotlinbeersearch

object Model {
    data class Result(val name: Name, val tagline: Tagline, val description: Description, val imageurl: Imageurl)
    data class Name(val name: String)
    data class Tagline(val tagline: String)
    data class Description(val description:String)
    data class Imageurl(val imageurl:String)
}