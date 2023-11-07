package com.example.fruitapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fruitapp.adapter.ListFriutAdapter
import com.example.fruitapp.model.Fruit

class MainActivity : AppCompatActivity() {
    private lateinit var rvFoods: RecyclerView
    private val list = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rvFoods = findViewById(R.id.rv_fruit)
        rvFoods.setHasFixedSize(true)

        val bt_quis = findViewById<Button>(R.id.bt_quis)

        bt_quis.setOnClickListener {
            val intent = Intent(this, QuisActivity::class.java)
            startActivity(intent)
        }

        list.addAll(getListFoods())
        showRecyclerList()
    }

    private fun getListFoods(): ArrayList<Fruit> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataTeknik = resources.getStringArray(R.array.teknik_dasar)
        val listFruit = ArrayList<Fruit>()
        for (i in dataName.indices) {
            val fruit = Fruit(dataName[i], dataDescription[i], dataPhoto.getResourceId(i,   -1), dataTeknik[i])
            listFruit.add(fruit)
        }
        return listFruit
    }

    private fun showRecyclerList() {
        rvFoods.layoutManager = LinearLayoutManager(this)
        val listFriutAdapter = ListFriutAdapter(list)
        rvFoods.adapter = listFriutAdapter
        listFriutAdapter.setOnItemClickCallback(object : ListFriutAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Fruit) {
                val intent = Intent(applicationContext, DetailActivity::class.java)
                intent.putExtra(DetailActivity.DATA_FOOD, data)
                startActivity(intent)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}