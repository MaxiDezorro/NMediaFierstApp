package ru.netology.nmedia.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemoryImpl

private val empty = Post( // data-обьект поста для залоннения, пустой пост
    id = 0L,
    author = "",
    content = "",
    published = "",
    likeByMe = false
)

class PostViewModel : ViewModel() {

    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    val edited = MutableLiveData(empty) // хранит текущий пост который добавляем или редактируем

    fun removeById(id: Long) = repository.removeById(id) // удалить
    fun like(id: Long) = repository.like(id) // лайкнуть
    fun share(id: Long) = repository.share(id) // поделиться
    fun applyChangesAndSave(newText: String) { // метод изменения и сохранения
        edited.value?.let { // берем значение из edited
            val text = newText.trim() // trim() уберает пробелы с начала и конца строки
            if (newText != it.content) {
                repository.save(it.copy(content = text)) /* заменяем у текушего поста контент на новый текст
                и отправляем в репозиторий  на сохранение */
             }
        }
        edited.value = empty // записываем стандартное значение пустого поста (сбрасываем добавление  )
    }
    fun edit(post: Post) {
        edited.value = post
    }
}