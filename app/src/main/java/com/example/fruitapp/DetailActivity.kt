package com.example.fruitapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.fruitapp.databinding.ActivityDetailBinding
import com.example.fruitapp.model.Fruit

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val DATA_FOOD = "data_food"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<Fruit>(DATA_FOOD)

        if (data != null) {
            binding.imgFood.setImageResource(data.photo)
            binding.textTitle.text = data.title
            binding.textDescription.text = data.description
            binding.textTeknikdasar.text = data.teknik_dasar
        }

        supportActionBar?.title = data?.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Mengatur onClickListener untuk tombol "Share"
        val shareButton: Button = findViewById(R.id.shareButton)

        shareButton.setOnClickListener {
            val data = intent.getParcelableExtra<Fruit>(DATA_FOOD)

            if (data != null) {
                shareDetail(data.title)
            } else {
                showToast("Data buah tidak valid")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun shareDetail(nameFruit: String) {
        val intent = when (nameFruit) {
            "Boxing" -> {
                // Intent untuk menampilkan video-video boxing
                Intent(this, BoxingActivity::class.java)
            }

            "KickxBoxing" -> {
                // Intent untuk menampilkan video-video kickboxing
                Intent(this, KickActivity::class.java)
            }

            "Muaythai" -> {
                // Intent untuk menampilkan video-video kickboxing
                Intent(this, MuaytaiActivity::class.java)
            }

            else -> {
                // Intent untuk menampilkan pesan "Video Tidak ditemukan"
                val errorIntent = Intent(this, BoxingActivity::class.java)
                errorIntent.putExtra("errorMessage", "Video Tidak ditemukan")
                errorIntent
            }
        }
        // Mengirimkan jenis buah (fruitName) ke VideoActivity
        intent.putExtra("fruitName", nameFruit)

        startActivity(intent)
    }
}