package com.andrewaprianto.mygithub1

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.andrewaprianto.mygithub1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private lateinit var dataUsername: Array<String>
    private lateinit var dataFollowing: Array<String>
    private lateinit var dataFollowers: Array<String>

    private lateinit var dataRepositories: Array<String>
    private lateinit var dataCompany: Array<String>
    private lateinit var dataLocation: Array<String>
    private lateinit var dataName: Array<String>

    private lateinit var dataPhoto: TypedArray
    private var users = arrayListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter(this)
        binding.lvUsers.adapter = adapter

        prepare()
        addItem()

        title = getString(R.string.github_users)

        binding.lvUsers.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val toDetailActivity = Intent(this@MainActivity, DetailActivity::class.java)
            val user = users[position]
            toDetailActivity.putExtra(DetailActivity.EXTRA_USER, user)
            startActivity(toDetailActivity)
        }
    }

    private fun prepare() {
        dataUsername = resources.getStringArray(R.array.username)
        dataFollowing = resources.getStringArray(R.array.following)
        dataFollowers = resources.getStringArray(R.array.followers)
        dataPhoto = resources.obtainTypedArray(R.array.avatar)
        dataRepositories = resources.getStringArray(R.array.repository)
        dataCompany = resources.getStringArray(R.array.company)
        dataLocation = resources.getStringArray(R.array.location)
        dataName = resources.getStringArray(R.array.name)
    }

    private fun addItem() {
        for (position in dataUsername.indices) {
            val user = User(
                dataPhoto.getResourceId(position, -1),
                dataUsername[position],
                dataFollowers[position],
                dataFollowing[position],
                dataRepositories[position],
                dataCompany[position],
                dataLocation[position],
                dataName[position]
            )
            users.add(user)
        }
        adapter.users = users
    }
}