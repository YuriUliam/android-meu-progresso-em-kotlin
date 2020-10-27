package com.example.meuprogressoemkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun handleSubmit(component: View) {
        et_name.error = null
        et_age.error = null

        val name = et_name.text.toString()
        val age = et_age.text.toString().toIntOrNull()

        if (name.isBlank()) {
            et_name.error = "Este campo é obrigatório."
            return
        }

        if (age == null) {
            et_age.error = "Digite uma idade válida!"
            return
        }

        if (age < 16) {
            et_age.error = "Você ainda não pode votar."
            return
        }

        val detailsScreen = Intent(this, VoteScreen::class.java)

        startActivity(detailsScreen)
    }
}