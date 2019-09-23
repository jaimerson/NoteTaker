package com.example.notetaker

import kotlinx.serialization.Serializable

@Serializable
data class Note(val title: String?, var body: String?) : java.io.Serializable