package com.andrewaprianto.mygithub1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andrewaprianto.mygithub1.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    private lateinit var binding: ActivityDetailBinding


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User

        title = getString(R.string.detail_user)

        binding.imgPhoto.setImageResource(user.photo)
        binding.txtName.text = "Hi, I am ${user.name}"
        binding.txtRepositories.text = "${user.repositories}, repositories"
        binding.txtFollowers.text = "${user.followers}, followers"
        binding.txtFollowing.text = "${user.following}, following"
        binding.txtAdditionalInfo.text = "${user.company} - ${user.location}"

    }
}