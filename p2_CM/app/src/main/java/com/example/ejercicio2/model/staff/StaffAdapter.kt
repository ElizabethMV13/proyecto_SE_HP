package com.example.ejercicio2.model.staff

import android.content.Context
import android.util.Log
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

class StaffAdapter (var context : Context, private val listStaff:List<Staff>, val listener: ClickListener): RecyclerView.Adapter<StaffAdapter.ViewHolder>() {

    var onItemOnClick: ((Staff) ->Unit)? = null

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
            itemView.setOnClickListener { v: View->
                val position: Int = absoluteAdapterPosition

                val staff = listStaff[position]

                listener.onClick(staff.id)

            }
        }


    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view= LayoutInflater.from(parent.context).inflate(R.layout.item_personaje, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = listStaff.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val staff = listStaff[position]

        if (staff.image == "") { holder.img.setImageResource(R.drawable.sunfoto)
        } else { Glide.with(context).load(staff.image).into(holder.img) }

        if (staff.name == "") { holder.nombre.text = context.getString(R.string.sinestudiante)
        } else { holder.nombre.text = staff.name }

        if (staff.actor == "") { holder.actor.text = context.getString(R.string.sinactor)
        } else { holder.actor.text = staff.actor }

        val datoHouse = staff.house

        when (datoHouse) {
            "Slytherin" -> {
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.card_s))
                holder.actor.setTextColor(ContextCompat.getColor(context,R.color.text_s))
                holder.nombre.setTextColor(ContextCompat.getColor(context,R.color.text_s))
                holder.house.setTextColor(ContextCompat.getColor(context,R.color.text_s))
                holder.house.text = staff.house
            }
            "Gryffindor" -> {
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.card_g))
                holder.actor.setTextColor(ContextCompat.getColor(context,R.color.text_g))
                holder.nombre.setTextColor(ContextCompat.getColor(context,R.color.text_g))
                holder.house.setTextColor(ContextCompat.getColor(context,R.color.text_g))
                holder.house.text = staff.house
            }
            "Hufflepuff" -> {
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.card_h))
                holder.actor.setTextColor(ContextCompat.getColor(context,R.color.text_h))
                holder.nombre.setTextColor(ContextCompat.getColor(context,R.color.text_h))
                holder.house.setTextColor(ContextCompat.getColor(context,R.color.text_h))
                holder.house.text = staff.house
            }
            "Ravenclaw" -> {
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.card_r))
                holder.actor.setTextColor(ContextCompat.getColor(context,R.color.text_r))
                holder.nombre.setTextColor(ContextCompat.getColor(context,R.color.text_r))
                holder.house.setTextColor(ContextCompat.getColor(context,R.color.text_r))
                holder.house.text = staff.house
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