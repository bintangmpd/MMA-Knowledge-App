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
                shareDetail(data.title, data.description)
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

    private fun shareDetail(nameFruit: String, informations: String) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        val detailText = "Informasi detail buah:\nNama: $nameFruit\n$informations"
        shareIntent.putExtra(Intent.EXTRA_TEXT, detailText)
        startActivity(Intent.createChooser(shareIntent, "Bagikan informasi melalui:"))
    }
}