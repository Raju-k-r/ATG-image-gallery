package com.krr.atgimagegallery.resource.network

import com.google.gson.annotations.SerializedName

data class Response(
        @SerializedName("photos")
        val photos : Photos
)

data class Photos(
        val page : Int,
        val pages : Int,
        val photo : List<Photo>
)

data class Photo(
        val id : String,
        @SerializedName("url_s")
        val urls : String
)