package ru.netology.nmedia.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                content.text = post.content
                published.text = post.published

                numberShare.text = showHowMany(post.countShare)

                numberViews.text = showHowMany(post.countViews)

                numberLikes.text = showHowMany(post.likes)

                likes.setImageResource(
                    if (post.likeByMe) {
                        R.drawable.ic_like_red_24
                    } else {
                        R.drawable.ic_like_24
                    }
                )
                if (post.likeByMe)
                    numberLikes.text = showHowMany(post.likes)


            }
        }

        binding.likes.setOnClickListener {
            viewModel.like()
        }
        binding.share.setOnClickListener {
            viewModel.share()
        }

    }


    fun showHowMany(number: Int): String {

        val thousand = "K"
        val million = "M"
        val value: Double = number.toDouble() / 1000
        val result = if (value.toString()[2] != '0') value.toString()[0] + "." + value.toString()[2]
        else value.toString()[0].toString()
        if (number <= 999) return number.toString()
        if (value >= 1 && value < 2) return result + thousand
        if (value >= 2 && value < 3) return result + thousand
        if (value >= 3 && value < 4) return result + thousand
        if (value >= 4 && value < 5) return result + thousand
        if (value >= 5 && value < 6) return result + thousand
        if (value >= 6 && value < 7) return result + thousand
        if (value >= 7 && value < 8) return result + thousand
        if (value >= 8 && value < 9) return result + thousand
        if (value >= 9 && value < 10) return result + thousand

        val value1: Double = number.toDouble() / 10000
        val result1 = value.toString()[0] + "" + value.toString()[1]
        if (value1 >= 1 && value1 < 2) return result1 + thousand
        if (value1 >= 2 && value1 < 3) return result1 + thousand
        if (value1 >= 3 && value1 < 4) return result1 + thousand
        if (value1 >= 4 && value1 < 5) return result1 + thousand
        if (value1 >= 5 && value1 < 6) return result1 + thousand
        if (value1 >= 6 && value1 < 7) return result1 + thousand
        if (value1 >= 7 && value1 < 8) return result1 + thousand
        if (value1 >= 8 && value1 < 9) return result1 + thousand
        if (value1 >= 9 && value1 < 10) return result1 + thousand

        val value2: Double = number.toDouble() / 100000
        val result2 = value1.toString()[0] + "" + value1.toString()[1] + value.toString()[2]
        if (value2 >= 1 && value2 < 2) return result2 + thousand
        if (value2 >= 2 && value2 < 3) return result2 + thousand
        if (value2 >= 3 && value2 < 4) return result2 + thousand
        if (value2 >= 4 && value2 < 5) return result2 + thousand
        if (value2 >= 5 && value2 < 6) return result2 + thousand
        if (value2 >= 6 && value2 < 7) return result2 + thousand
        if (value2 >= 7 && value2 < 8) return result2 + thousand
        if (value2 >= 8 && value2 < 9) return result2 + thousand
        if (value2 >= 9 && value2 < 10) return result2 + thousand


        val value3: Double = number.toDouble() / 1000000
        val result3 = if (value3.toString()[2] != '0') {
            value3.toString()[0] + "." + value3.toString()[2]
        } else {
            value3.toString()[0].toString()
        }
        if (value3 >= 1 && value3 < 2) return result3 + million
        if (value3 >= 2 && value3 < 3) return result3 + million
        if (value3 >= 3 && value3 < 4) return result3 + million
        if (value3 >= 4 && value3 < 5) return result3 + million
        if (value3 >= 5 && value3 < 6) return result3 + million
        if (value3 >= 6 && value3 < 7) return result3 + million
        if (value3 >= 7 && value3 < 8) return result3 + million
        if (value3 >= 8 && value3 < 9) return result3 + million
        if (value3 >= 9 && value3 < 10) return result3 + million

        val value4: Double = number.toDouble() / 10000000
        val result4 = value3.toString()[0] + "" + value3.toString()[1]
        if (value4 >= 1 && value4 < 2) return result4 + million
        if (value4 >= 2 && value4 < 3) return result4 + million
        if (value4 >= 3 && value4 < 4) return result4 + million
        if (value4 >= 4 && value4 < 5) return result4 + million
        if (value4 >= 5 && value4 < 6) return result4 + million
        if (value4 >= 6 && value4 < 7) return result4 + million
        if (value4 >= 7 && value4 < 8) return result4 + million
        if (value4 >= 8 && value4 < 9) return result4 + million
        if (value4 >= 9 && value4 < 10) return result4 + million

        val value5: Double = number.toDouble() / 100000000
        val result5 = value4.toString()[0] + "" + value4.toString()[1] + value.toString()[2]
        if (value5 >= 1 && value5 < 2) return result5 + million
        if (value5 >= 2 && value5 < 3) return result5 + million
        if (value5 >= 3 && value5 < 4) return result5 + million
        if (value5 >= 4 && value5 < 5) return result5 + million
        if (value5 >= 5 && value5 < 6) return result5 + million
        if (value5 >= 6 && value5 < 7) return result5 + million
        if (value5 >= 7 && value5 < 8) return result5 + million
        if (value5 >= 8 && value5 < 9) return result5 + million
        if (value5 >= 9 && value5 < 10) return result5 + million
        return number.toString()

    }

}