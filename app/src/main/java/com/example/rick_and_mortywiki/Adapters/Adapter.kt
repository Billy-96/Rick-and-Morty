package com.example.rick_and_mortywiki.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rick_and_mortywiki.Interfaces.ClickImp
import com.example.rick_and_mortywiki.Model.DataClasses.Character
import com.example.rick_and_mortywiki.R
import com.example.rick_and_mortywiki.databinding.MaketLocationsBinding
import com.example.rick_and_mortywiki.databinding.MaketPersonsBinding

class Adapter(
    private val context:Context,
    private val list:List<Character>,
    val listener:ClickImp
) : RecyclerView.Adapter<Adapter.HolderPerson>(){
    inner class HolderPerson(val bindingPerson:MaketPersonsBinding) :
        RecyclerView.ViewHolder(bindingPerson.root){
            init {
                bindingPerson.maketPerson.setOnClickListener {
                    listener.onCardClickPerson(adapterPosition,list)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderPerson {
        val bindingPerson = MaketPersonsBinding.inflate(LayoutInflater.from(parent.context))
        val holder = HolderPerson(bindingPerson)
        return holder
    }

    override fun onBindViewHolder(holder: HolderPerson, position: Int) {
        val person = list[position]
        holder.bindingPerson.apply {
            namePerson.text = person.name
            statusPerson.text = person.status
            speciesPerson.text = person.species
            locationPerson.text = person.location.name
        }
        context?.let {
            Glide.with(it).load(person.image).into(holder.bindingPerson.imageView)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}