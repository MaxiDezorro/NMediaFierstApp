package ru.netology.nmedia.dto

data class Post (
    val id:Long,
    val author: String,
    val content: String,
    val published: String,
    val likes:Int = 9999,
    val likeByMe: Boolean = false,

    val countShare: Int = 999,

    val countViews:Int = 2468
)