package com.example.meuprogressoemkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_vote_screen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VoteScreen : AppCompatActivity() {
    var elect: Elect? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vote_screen)
    }

    fun handleConfirmVote(component: View) {
        Toast.makeText(applicationContext, "Seu voto foi confirmado para ${elect?.name}!", Toast.LENGTH_LONG)

        val mainActivityScreen = Intent(this, MainActivity::class.java)

        startActivity(mainActivityScreen)
    }

    fun handleVerifyVote(component: View) {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://5f96597e11ab98001603a8ba.mockapi.io/api/v0/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val requests = retrofit.create(ApiElectsRequest::class.java)

        val callElect = requests.getElect(1)

        callElect.enqueue(object: Callback<Elect> {
            override fun onFailure(call: Call<Elect>, t: Throwable) {
                Toast.makeText(applicationContext, "Ocorreu um erro: $t", Toast.LENGTH_SHORT)
            }

            override fun onResponse(call: Call<Elect>, response: Response<Elect>) {
                elect = response.body()

                tv_response.text = """
                    o seu número é o ${elect?.id}
                    O candidato é o ${elect?.name}.
                    Seu partido é o ${elect?.entourage}
                    
                    Deseja confirmar o seu voto?
                """.trimIndent()

                tv_response.visibility = View.VISIBLE
                bt_confirm_vote.visibility = View.VISIBLE
            }
        })

    }

//    fun consumeApi() {
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://5f96597e11ab98001603a8ba.mockapi.io/api/v0/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val requests = retrofit.create(ApiElectsRequest::class.java)
//
//        val callElects = requests.getElects()
//
//        callElects.enqueue(object: Callback<List<Elect>> {
//            override fun onFailure(call: Call<List<Elect>>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onResponse(call: Call<List<Elect>>, response: Response<List<Elect>>) {
//            }
//        })
//    }
}