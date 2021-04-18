package com.andrewaprianto.mygithub1

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.andrewaprianto.mygithub1.databinding.ItemUserBinding

class UserAdapter(private val context: Context) : BaseAdapter() {
    var users = arrayListOf<User>()

    override fun getCount(): Int = users.size

    override fun getItem(i: Int): Any = users[i]

    override fun getItemId(i: Int): Long = i.toLong()

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        var itemView = view
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_user, viewGroup, false)
        }
        val viewHolder = ViewHolder(itemView as View)

        val user = getItem(position) as User

        viewHolder.bind(user)
        return itemView
    }

    class ViewHolder(view: View) {
        private val binding = ItemUserBinding.bind(view)
        @SuppressLint("SetTextI18n")
        fun bind(user: User) {
            binding.txtUsername.text = user.username
            binding.txtFollowers.text = "${user.followers} Followers"
            binding.txtFollowing.text = "${user.following} Following"
            binding.imgPhoto.setImageResource(user.photo)
        }
    }
}