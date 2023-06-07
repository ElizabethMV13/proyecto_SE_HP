package com.example.ejercicio2.model.student

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ejercicio2.R
import com.example.ejercicio2.model.TargetPersonaje
import com.example.ejercicio2.model.staff.Staff

class StudentAdapter(var context: Context, private val listStudent:List<Student>, val listener: ClickListener): RecyclerView.Adapter<StudentAdapter.ViewHolder>() {


    var onItemOnClick: ((Student) ->Unit)? = null

    interface ClickListener{
        fun onClick(position: String)
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nombre = itemView.findViewById<TextView>(R.id.item_name)
        val actor = itemView.findViewById<TextView>(R.id.item_actor)
        val house = itemView.findViewById<TextView>(R.id.item_house)
        val img = itemView.findViewById<ImageView>(R.id.item_img)
        val cardView =  itemView.findViewById<CardView>(R.id.card_personaje)

        init {
            itemView.setOnClickListener { v: View ->
                val position: Int = absoluteAdapterPosition
                val estudiante = listStudent[position]

                listener.onClick(estudiante.id)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view= LayoutInflater.from(parent.context).inflate(R.layout.item_personaje, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = listStudent.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val estudiante = listStudent[position]

        if (estudiante.image == "") { holder.img.setImageResource(R.drawable.sunfoto)
        } else { Glide.with(context).load(estudiante.image).into(holder.img) }

        if (estudiante.name == "") { holder.nombre.text = context.getString(R.string.sinestudiante)
        } else { holder.nombre.text = estudiante.name }


        if (estudiante.actor == "") { holder.actor.text = context.getString(R.string.sinactor)
        } else { holder.actor.text = estudiante.actor }

        val datoHouse = estudiante.house

        when (datoHouse) {
            "Slytherin" -> {
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.card_s))
                holder.actor.setTextColor(ContextCompat.getColor(context,R.color.text_s))
                holder.nombre.setTextColor(ContextCompat.getColor(context,R.color.text_s))
                holder.house.setTextColor(ContextCompat.getColor(context,R.color.text_s))
                holder.house.text = estudiante.house
            }
            "Gryffindor" -> {
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.card_g))
                holder.actor.setTextColor(ContextCompat.getColor(context,R.color.text_g))
                holder.nombre.setTextColor(ContextCompat.getColor(context,R.color.text_g))
                holder.house.setTextColor(ContextCompat.getColor(context,R.color.text_g))
                holder.house.text = estudiante.house
            }
            "Hufflepuff" -> {
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.card_h))
                holder.actor.setTextColor(ContextCompat.getColor(context,R.color.text_h))
                holder.nombre.setTextColor(ContextCompat.getColor(context,R.color.text_h))
                holder.house.setTextColor(ContextCompat.getColor(context,R.color.text_h))
                holder.house.text = estudiante.house
            }
            "Ravenclaw" -> {
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.card_r))
                holder.actor.setTextColor(ContextCompat.getColor(context,R.color.text_r))
                holder.nombre.setTextColor(ContextCompat.getColor(context,R.color.text_r))
                holder.house.setTextColor(ContextCompat.getColor(context,R.color.text_r))
                holder.house.text = estudiante.house
            }
            else -> {
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.card))
                holder.actor.setTextColor(ContextCompat.getColor(context,R.color.text_s))
                holder.nombre.setTextColor(ContextCompat.getColor(context,R.color.text_s))
                holder.house.setTextColor(ContextCompat.getColor(context,R.color.text_s))
                holder.house.text = context.getString(R.string.sinhouse)
            }

        }


    }
}