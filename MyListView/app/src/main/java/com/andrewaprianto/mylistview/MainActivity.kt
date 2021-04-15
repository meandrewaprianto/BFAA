package com.andrewaprianto.mylistview

import android.content.res.TypedArray
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.andrewaprianto.mylistview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //    private val dataName = arrayOf("Cut Nyak Dien", "Ki Hajar Dewantara", "Moh Yamin", "Pattimura", "R.A. Kartini", "Sukarno")
    private lateinit var adapter: HeroAdapter
    private lateinit var dataName: Array<String>
    private lateinit var dataDescription: Array<String>
    private lateinit var dataPhoto: TypedArray
    private var heroes = arrayListOf<Hero>()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, dataName)
        adapter = HeroAdapter(this)
        binding.lvList.adapter = adapter

        prepare()
        addItem()

        binding.lvList.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            Toast.makeText(this@MainActivity, heroes[position].name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun prepare() {
        dataName = resources.getStringArray(R.array.data_name)
        dataDescription = resources.getStringArray(R.array.data_description)
        dataPhoto = resources.obtainTypedArray(R.array.data_photo)
    }

    private fun addItem() {
        for (position in dataName.indices) {
            val hero = Hero(
                dataPhoto.getResourceId(position, -1),
                dataName[position],
                dataDescription[position]
            )
            heroes.add(hero)
        }
        adapter.heroes = heroes
    }
}