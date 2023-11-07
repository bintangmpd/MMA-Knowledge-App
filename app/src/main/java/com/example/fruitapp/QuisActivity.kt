package com.example.fruitapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AlertDialog

@Suppress("NAME_SHADOWING")
class QuisActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var questionTextView: TextView
    private lateinit var answerEditText: EditText
    private lateinit var submitButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quis)

        videoView = findViewById(R.id.videoView)
        questionTextView = findViewById(R.id.questionTextView)
        answerEditText = findViewById(R.id.answerEditText)
        submitButton = findViewById(R.id.submitButton)

        data class QuizQuestion(val videoResource: Int, val correctAnswer: String)

        val quizQuestions = listOf(
            QuizQuestion(R.raw.boxing_hook, "Hook"),
            QuizQuestion(R.raw.boxing_jab, "jab"),
            QuizQuestion(R.raw.boxing_strike, "strike"),
            QuizQuestion(R.raw.boxing_uppercut, "uppercut"),
            QuizQuestion(R.raw.kickboxing_jabstrike_knee, "jab strike knee"),
            QuizQuestion(R.raw.kickboxing_jabstrike_lowkick, "jab strike low kick"),
            QuizQuestion(R.raw.kickboxing_jabstrike_pushkick, "jab strike push kick"),
            QuizQuestion(R.raw.muaythai_elbow, "elbow"),
            QuizQuestion(R.raw.muaythai_highelbow, "high elbow"),
            QuizQuestion(R.raw.muaythai_spinningelbow, "spinning elbow"),
        )

        var currentQuestionIndex = 0 // Indeks pertanyaan saat ini
        var skor = 0

// Tampilkan video pertanyaan
        val videoPath = "android.resource://" + packageName + "/" + quizQuestions[currentQuestionIndex].videoResource
        videoView.setVideoPath(videoPath)
        videoView.start()

        submitButton.setOnClickListener {
            val userAnswer = answerEditText.text.toString()
            if (userAnswer == ""){
                val toast = Toast.makeText(this, "Isi jawaban terlebih dahulu!", Toast.LENGTH_SHORT)
                toast.show()
            }else{
                if (userAnswer.equals(quizQuestions[currentQuestionIndex].correctAnswer, ignoreCase = true)) {
                    skor++
                    // Membuat dan menampilkan dialog peringatan
                    val alertDialogBuilder = AlertDialog.Builder(this)
                    alertDialogBuilder.setTitle("Jawaban Benar")
                    alertDialogBuilder.setMessage("Selamat! Jawaban Anda benar.")
                    alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss() // Menutup dialog saat tombol OK diklik
                        currentQuestionIndex++

                        if (currentQuestionIndex < quizQuestions.size) {
                            // Tampilkan pertanyaan berikutnya
                            val nextVideoPath = "android.resource://" + packageName + "/" + quizQuestions[currentQuestionIndex].videoResource
                            videoView.setVideoPath(nextVideoPath)
                            videoView.start()
                            answerEditText.text.clear() // Bersihkan EditText
                        } else {
                            val alertDialogBuilder = AlertDialog.Builder(this)
                            alertDialogBuilder.setTitle("Hasil Akhir")
                            alertDialogBuilder.setMessage("Jawaban anda benar : \b$skor soal dari 10 Soal")
                            alertDialogBuilder.setPositiveButton("OK") { _, _ ->
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                            }
                            val alertDialog = alertDialogBuilder.create()
                            alertDialog.show()
                        }
                    }
                    val alertDialog = alertDialogBuilder.create()
                    alertDialog.show()

                } else {
                    // Membuat dan menampilkan dialog peringatan jika jawaban salah
                    val alertDialogBuilder = AlertDialog.Builder(this)
                    alertDialogBuilder.setTitle("Jawaban Salah")
                    val correctAnswer = quizQuestions[currentQuestionIndex].correctAnswer
                    alertDialogBuilder.setMessage("Jawaban yang benar adalah: \b$correctAnswer")
                    alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss() // Menutup dialog saat tombol OK diklik

                        currentQuestionIndex++

                        if (currentQuestionIndex < quizQuestions.size) {
                            // Tampilkan pertanyaan berikutnya
                            val nextVideoPath = "android.resource://" + packageName + "/" + quizQuestions[currentQuestionIndex].videoResource
                            videoView.setVideoPath(nextVideoPath)
                            videoView.start()
                            answerEditText.text.clear() // Bersihkan EditText
                        } else {
                            val alertDialogBuilder = AlertDialog.Builder(this)
                            alertDialogBuilder.setTitle("Hasil Akhir")
                            alertDialogBuilder.setMessage("Jawaban anda benar : \b$skor soal dari 10 Soal")
                            alertDialogBuilder.setPositiveButton("OK") { _, _ ->
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                            }
                            val alertDialog = alertDialogBuilder.create()
                            alertDialog.show()
                        }
                    }
                    val alertDialog = alertDialogBuilder.create()
                    alertDialog.show()


                }
            }

        }

    }
}