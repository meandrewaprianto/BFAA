package com.andrewaprianto.mylistview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.andrewaprianto.mylistview.databinding.ItemHeroBinding

class HeroAdapter constructor(private val context: Context): BaseAdapter() {
    var heroes = arrayListOf<Hero>()
    override fun getCount(): Int = heroes.size

    override fun getItem(i: Int): Any = heroes[i]

    override fun getItemId(i: Int): Long = i.toLong()

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup): View {
        var itemView = view
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_hero, viewGroup, false)
        }

        val viewHolder =  ViewHolder(itemView as View)

        val hero = getItem(position) as Hero
        viewHolder.bind(hero)
        return itemView
    }

    class ViewHolder constructor(view: View){
        private val binding = ItemHeroBinding.bind(view)
        fun bind(hero: Hero){
            binding.txtName.text = hero.name
            binding.txtDescription.text = hero.description
            binding.imgPhoto.setImageResource(hero.photo)
        }
    }
}