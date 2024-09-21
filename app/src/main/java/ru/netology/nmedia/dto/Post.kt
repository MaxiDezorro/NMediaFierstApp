package ru.netology.nmedia.dto

data class Post (
    val id:Long,
    val author: String,
    val content: String,
    val published: String,
    var likes:Int = 9999,
    var likeByMe: Boolean = false,

    var countShare: Int = 9999,

    var countViews:Int = 24
)