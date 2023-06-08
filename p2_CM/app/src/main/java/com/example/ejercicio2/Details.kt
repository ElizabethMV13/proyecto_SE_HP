package com.example.ejercicio2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.ejercicio2.databinding.ActivityDetailsBinding
import com.example.ejercicio2.databinding.ActivityMainBinding
import com.example.ejercicio2.model.TargetPersonaje
import com.example.ejercicio2.model.staff.StaffApiService
import com.example.ejercicio2.model.student.StudentApiService
import com.example.ejercicio2.utils.Constants
import com.example.ejercicio2.utils.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import kotlinx.coroutines.*
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.coroutineContext

class Details : AppCompatActivity() {

    private  lateinit var binding: ActivityDetailsBinding

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)





          val btn1: Button = findViewById(R.id.buttonBack)
          btn1.setOnClickListener{
             val intent1: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent1)
          }




          val fragment = intent.getIntExtra("fragment", 0)
          val id = intent.getStringExtra("id")

          Log.d("MainActivity", "Valor de fragment: $fragment   valor del id /api/character/${id}")



        val call = RetrofitService.getRetrofit().create(StudentApiService::class.java).getTargerE("$id")

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<TargetPersonaje>{

                override fun onResponse(
                    call: Call<TargetPersonaje>,
                    response: Response<TargetPersonaje>
                ) {

                    if (response.isSuccessful) {
                        val targetPersonaje = response.body()
                        targetPersonaje?.get(0)?.let { item ->

                            binding.conexionElement.visibility = View.GONE

                            binding.text1.setTextColor(ContextCompat.getColor(this@Details, R.color.text1))
                            binding.text2.setTextColor(ContextCompat.getColor(this@Details, R.color.text1))
                            binding.text3.setTextColor(ContextCompat.getColor(this@Details, R.color.text1))
                            binding.text4.setTextColor(ContextCompat.getColor(this@Details, R.color.text1))
                            binding.text5.setTextColor(ContextCompat.getColor(this@Details, R.color.text1))
                            binding.text6.setTextColor(ContextCompat.getColor(this@Details, R.color.text1))
                            binding.text7.setTextColor(ContextCompat.getColor(this@Details, R.color.text1))
                            binding.text8.setTextColor(ContextCompat.getColor(this@Details, R.color.text1))
                            binding.text9.setTextColor(ContextCompat.getColor(this@Details, R.color.text1))


                            Log.d("MainActivity", "------------------Valor de id: ${item.name}")
                            if (item.image == "") { binding.itemImg.setImageResource(R.drawable.sunfoto)
                            } else { Glide.with(this@Details).load(item.image).into(binding.itemImg) }

                            if (item.name == "") { binding.itemName.text  = this@Details.getString(R.string.sinestudiante)
                            } else { binding.itemName.text = item.name }

                            if (item.actor == "") { binding.itemActor.text  = this@Details.getString(R.string.sinactor)
                            } else { binding.itemActor.text = item.actor }

                            if (item.species == "") { binding.itemEspecie.text  = this@Details.getString(R.string.sinespecie)
                            } else { binding.itemEspecie.text = item.species }

                            if (item.gender == "") { binding.itemGenero.text  = this@Details.getString(R.string.singenero)
                            } else { binding.itemGenero.text = item.gender }

                            if (item.house == "") { binding.itemHouse.text  = this@Details.getString(R.string.sinhouse)
                            } else { binding.itemHouse.text = item.house }

                            if (item.patronus == "") { binding.itemPatronus.text  = this@Details.getString(R.string.sinpatronus)
                            } else { binding.itemPatronus.text = item.patronus }

                            if (item.wand.wood == "") { binding.itemMadera.text  = this@Details.getString(R.string.sin)
                            } else { binding.itemMadera.text = item.wand.wood }

                            if (item.wand.core == "") { binding.itemNucleo.text  = this@Details.getString(R.string.sin)
                            } else { binding.itemNucleo.text = item.wand.core }

                            if (item.wand.length.toString() == "") { binding.itemLongitud.text  = this@Details.getString(R.string.sin)
                            } else { binding.itemLongitud.text = item.wand.length.toString() }

                            val datoHouse = item.house
                            //card_personaje

                            when (datoHouse) {
                                "Slytherin" -> {
                                    binding.cardPersonaje.setCardBackgroundColor(ContextCompat.getColor(this@Details,R.color.card_s))
                                    binding.buttonBack.setBackgroundColor(ContextCompat.getColor(this@Details, R.color.btn_s))
                                    binding.itemNucleo.setTextColor(ContextCompat.getColor(this@Details, R.color.text_s))
                                    binding.itemName.setTextColor(ContextCompat.getColor(this@Details,R.color.text_s))
                                    binding.itemLongitud.setTextColor(ContextCompat.getColor(this@Details,R.color.text_s))
                                    binding.itemMadera.setTextColor(ContextCompat.getColor(this@Details,R.color.text_s))
                                    binding.itemActor.setTextColor(ContextCompat.getColor(this@Details,R.color.text_s))
                                    binding.itemEspecie.setTextColor(ContextCompat.getColor(this@Details,R.color.text_s))
                                    binding.itemHouse.setTextColor(ContextCompat.getColor(this@Details,R.color.text_s))
                                    binding.itemGenero.setTextColor(ContextCompat.getColor(this@Details,R.color.text_s))
                                    binding.itemPatronus.setTextColor(ContextCompat.getColor(this@Details,R.color.text_s))
                                }
                                "Gryffindor" -> {
                                    binding.cardPersonaje.setCardBackgroundColor(ContextCompat.getColor(this@Details, R.color.card_g))
                                    binding.buttonBack.setBackgroundColor(ContextCompat.getColor(this@Details, R.color.btn_g))
                                    binding.itemNucleo.setTextColor(ContextCompat.getColor(this@Details, R.color.text_g))
                                    binding.itemName.setTextColor(ContextCompat.getColor(this@Details,R.color.text_g))
                                    binding.itemLongitud.setTextColor(ContextCompat.getColor(this@Details,R.color.text_g))
                                    binding.itemMadera.setTextColor(ContextCompat.getColor(this@Details,R.color.text_g))
                                    binding.itemActor.setTextColor(ContextCompat.getColor(this@Details,R.color.text_g))
                                    binding.itemEspecie.setTextColor(ContextCompat.getColor(this@Details,R.color.text_g))
                                    binding.itemHouse.setTextColor(ContextCompat.getColor(this@Details,R.color.text_g))
                                    binding.itemGenero.setTextColor(ContextCompat.getColor(this@Details,R.color.text_g))
                                    binding.itemPatronus.setTextColor(ContextCompat.getColor(this@Details,R.color.text_g))
                                }
                                "Hufflepuff" -> {
                                    binding.cardPersonaje.setCardBackgroundColor(ContextCompat.getColor(this@Details,R.color.card_h))
                                    binding.buttonBack.setBackgroundColor(ContextCompat.getColor(this@Details, R.color.btn_h))
                                    binding.itemNucleo.setTextColor(ContextCompat.getColor(this@Details, R.color.text_h))
                                    binding.itemName.setTextColor(ContextCompat.getColor(this@Details,R.color.text_h))
                                    binding.itemLongitud.setTextColor(ContextCompat.getColor(this@Details,R.color.text_h))
                                    binding.itemMadera.setTextColor(ContextCompat.getColor(this@Details,R.color.text_h))
                                    binding.itemActor.setTextColor(ContextCompat.getColor(this@Details,R.color.text_h))
                                    binding.itemEspecie.setTextColor(ContextCompat.getColor(this@Details,R.color.text_h))
                                    binding.itemHouse.setTextColor(ContextCompat.getColor(this@Details,R.color.text_h))
                                    binding.itemGenero.setTextColor(ContextCompat.getColor(this@Details,R.color.text_h))
                                    binding.itemPatronus.setTextColor(ContextCompat.getColor(this@Details,R.color.text_h))
                                }
                                "Ravenclaw" -> {
                                    binding.cardPersonaje.setCardBackgroundColor(ContextCompat.getColor(this@Details, R.color.card_r))
                                    binding.buttonBack.setBackgroundColor(ContextCompat.getColor(this@Details, R.color.btn_r))
                                    binding.itemNucleo.setTextColor(ContextCompat.getColor(this@Details, R.color.text_r))
                                    binding.itemName.setTextColor(ContextCompat.getColor(this@Details,R.color.text_r))
                                    binding.itemLongitud.setTextColor(ContextCompat.getColor(this@Details,R.color.text_r))
                                    binding.itemMadera.setTextColor(ContextCompat.getColor(this@Details,R.color.text_r))
                                    binding.itemActor.setTextColor(ContextCompat.getColor(this@Details,R.color.text_r))
                                    binding.itemEspecie.setTextColor(ContextCompat.getColor(this@Details,R.color.text_r))
                                    binding.itemHouse.setTextColor(ContextCompat.getColor(this@Details,R.color.text_r))
                                    binding.itemGenero.setTextColor(ContextCompat.getColor(this@Details,R.color.text_r))
                                    binding.itemPatronus.setTextColor(ContextCompat.getColor(this@Details,R.color.text_r))
                                }
                                else -> {
                                    binding.cardPersonaje.setCardBackgroundColor(ContextCompat.getColor(this@Details, R.color.card))
                                    binding.buttonBack.setBackgroundColor(ContextCompat.getColor(this@Details, R.color.btn_s))
                                    binding.itemNucleo.setTextColor(ContextCompat.getColor(this@Details,R.color.text_s))
                                    binding.itemName.setTextColor(ContextCompat.getColor(this@Details,R.color.text_s))
                                    binding.itemLongitud.setTextColor(ContextCompat.getColor(this@Details,R.color.text_s))
                                    binding.itemMadera.setTextColor(ContextCompat.getColor(this@Details,R.color.text_s))
                                    binding.itemActor.setTextColor(ContextCompat.getColor(this@Details,R.color.text_s))
                                    binding.itemEspecie.setTextColor(ContextCompat.getColor(this@Details,R.color.text_s))
                                    binding.itemHouse.setTextColor(ContextCompat.getColor(this@Details,R.color.text_s))
                                    binding.itemGenero.setTextColor(ContextCompat.getColor(this@Details,R.color.text_s))
                                    binding.itemPatronus.setTextColor(ContextCompat.getColor(this@Details,R.color.text_s))
                                }

                            }
                        }

                    }
                    else {
                        Log.d("MainActivity", "else $id")
                        val builder = AlertDialog.Builder(this@Details)
                        builder.setTitle(R.string.titleError2)
                            .setMessage(R.string.error2)
                            .setPositiveButton(R.string.leyenda1) { dialog, _ ->
                                dialog.dismiss()
                            }

                        val dialog = builder.create()
                        dialog.show()
                    }
                }

                override fun onFailure(call: Call<TargetPersonaje>, t: Throwable) {
                    val builder = AlertDialog.Builder(this@Details)
                    builder.setTitle(R.string.titleError1)
                        .setMessage(R.string.error1)
                        .setPositiveButton(R.string.leyenda1) { dialog, _ ->
                            dialog.dismiss()
                        }

                    val dialog = builder.create()
                    dialog.show()

                    Log.d("falla", "onFailure")
                }
            })
        }




    }
}