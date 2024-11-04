package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.activity.showHowManyIntToString
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post

interface onInteractionListener {
    fun onLike(post: Post)
    fun onShare(post: Post)
    fun onRemove(post: Post)
    fun onEdit(post: Post)


}

//class PostAdapter(
//    private val onLikeListener: OnLikeListener,
//    private val onShareListener: OnShareListener
//) : RecyclerView.Adapter<PostViewHolder>() {
//
//    var list = emptyList<Post>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
//        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return PostViewHolder(binding, onLikeListener, onShareListener)
//    }
//
//    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
//        val post = list[position]
//        holder.bind(post)
//    }
//
//    override fun getItemCount(): Int = list.size
//}

class PostAdapter(
    private val onInteractionListener: onInteractionListener
) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

}


class PostViewHolder(
    val binding: CardPostBinding,
    val onInteractionListener: onInteractionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            content.text = post.content
            published.text = post.published

//            numberLikes.text = showHowManyIntToString(post.likes)
//            numberShare.text = showHowManyIntToString(post.countShare)
            numberViews.text = showHowManyIntToString(post.countViews)

//            likes.setImageResource(  // likes теперь не imageButton
//                if (post.likeByMe) {
//                    R.drawable.ic_like_red_24
//                } else {
//                    R.drawable.ic_like_24
//                }
//            )
            likes.isChecked = post.likeByMe
            likes.text = showHowManyIntToString(post.likes)
            share.text = showHowManyIntToString(post.countShare)

            likes.setOnClickListener { onInteractionListener.onLike(post) }
            share.setOnClickListener { onInteractionListener.onShare(post) }
            menu.setOnClickListener { // устанавливаем обработчик на кнопку menu
                PopupMenu(
                    it.context,
                    it
                ).apply { // попап - всплывающее меню  2 параметра(контекст, и вью рядом с которым отображать всплывающее меню)
                    inflate(R.menu.menu_post) // через метод inflate загружаем разметку нашего меню
                    setOnMenuItemClickListener { item -> // установливаем один обработчик нажатия на все пункты меню
                        when (item.itemId) { // обрабатываем по идентификатору к нужный пункт меню
                            R.id.remove -> {
                                onInteractionListener.onRemove(post)
                                true
                            }

                            R.id.edit -> {
                                onInteractionListener.onEdit(post)
                                true
                            }

                            else -> false

                        }
                    }
                }.show() // не забывать вызывать метод показ меню
            }

        }
    }

}

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem == newItem

}



