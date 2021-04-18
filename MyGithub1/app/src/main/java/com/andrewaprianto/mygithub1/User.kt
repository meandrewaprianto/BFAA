package com.andrewaprianto.mygithub1

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var photo: Int,
    var username: String,
    var followers: String,
    var following: String,
    var repositories: String,
    var company: String,
    var location: String,
    var name: String
): Parcelable