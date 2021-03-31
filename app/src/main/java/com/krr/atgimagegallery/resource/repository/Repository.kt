package com.krr.atgimagegallery.resource.repository

import com.krr.atgimagegallery.resource.network.NetWorkResource
import com.krr.atgimagegallery.resource.network.Photo

class Repository {

    suspend fun getAllImages() : List<Photo>{
        return NetWorkResource._api.getAllImages().photos.photo
    }
}