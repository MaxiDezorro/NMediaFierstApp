package ru.netology.nmedia.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemoryImpl

private val empty = Post(
    id = 0L,
    author = "",
    content = "",
    published = "",
    likeByMe = false
)

class PostViewModel : ViewModel() {

    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    val edited = MutableLiveData(empty)

    fun removeById(id: Long) = repository.removeById(id)
    fun like(id: Long) = repository.like(id)
    fun share(id: Long) = repository.share(id)
    fun applyChangesAndSave(newText: String) {
        edited.value?.let {
            val text = newText.trim()
            if (newText != it.content) {
                repository.save(it.copy(content = text))
            }
        }
        edited.value = empty
    }
    fun edit(post: Post) {
        edited.value = post
    }
}