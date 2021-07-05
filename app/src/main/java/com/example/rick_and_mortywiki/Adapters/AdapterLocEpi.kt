package com.example.rick_and_mortywiki.Adapters

import android.content.Context
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.rick_and_mortywiki.Interfaces.ClickImp
import com.example.rick_and_mortywiki.Model.DataClasses.Character
import com.example.rick_and_mortywiki.Model.DataClasses.Episode
import com.example.rick_and_mortywiki.Model.DataClasses.Location
import com.example.rick_and_mortywiki.UI.Fragments.DescriptionFragment
import com.example.rick_and_mortywiki.databinding.MaketLocationsBinding
import com.example.rick_and_mortywiki.databinding.MaketPersonsBinding

class AdapterLocEpi(
    private val context: Context,
    private val listLocation: List<Location>? = null,
    private val listEpisode: List<Episode>? = null,
    private val listener: ClickImp
) : RecyclerView.Adapter<AdapterLocEpi.Holder>() {
    inner class Holder(val binding: MaketLocationsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            if (listLocation != null) {
                binding.cardLocEpi.setOnClickListener {
                    listener.onCardClickLocation(adapterPosition, listLocation)
                }
            }
            if (listEpisode != null) {
                binding.cardLocEpi.setOnClickListener {
                    listener.onCardClickEpisode(adapterPosition, listEpisode)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterLocEpi.Holder {
        val binding = MaketLocationsBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: AdapterLocEpi.Holder, position: Int) {
        if (listLocation != null) {
            val location = listLocation!![position]
            holder.binding.apply {
                nameLocation.text = location.name
            }
        } else {
            val episode = listEpisode!![position]
            holder.binding.nameLocation.text = episode.name
        }

    }

    override fun getItemCount(): Int {
        if (listLocation != null) {
            return listLocation.size
        } else {
            return listEpisode!!.size
        }
    }
}