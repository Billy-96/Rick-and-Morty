package com.example.rick_and_mortywiki.UI.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.rick_and_mortywiki.Model.DataClasses.Character
import com.example.rick_and_mortywiki.Model.DataClasses.Episode
import com.example.rick_and_mortywiki.Model.DataClasses.Location
import com.example.rick_and_mortywiki.UI.MainViewModel
import com.example.rick_and_mortywiki.Utils.Util
import com.example.rick_and_mortywiki.databinding.FragmentDescriptionBinding
import java.lang.NullPointerException

class DescriptionFragment : Fragment() {
    private var _binding: FragmentDescriptionBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    var listEpisodes = mutableListOf<Episode>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDescriptionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val bundle = this.arguments
        try {
            val person = bundle?.getSerializable(Util.KEY_PERSON) as Character


            binding.constraintPerson.visibility = View.VISIBLE
            binding.apply {
                context?.let { Glide.with(it).load(person.image).into(imageDesc) }
                namePersonDesc.text = person.name
                statusDesc.text = person.status
                speciesDesc.text = person.species
                genderDesc.text = person.gender
                for (i in person.episode) {
                    if (i.length > 41) {
                        var string =
                            i.toCharArray().get(40).toString() + i.toCharArray().get(41).toString()
                        viewModel.getOneEpisode(string)
                    } else {
                        viewModel.getOneEpisode(i.toCharArray().get(40).toString())
                    }
                }
                viewModel.liveDataOneEpisode.observe(viewLifecycleOwner,{
                    listEpisodes.add(it)
                    Log.i("Myf",listEpisodes.toString())
                })

            }
        } catch (e: NullPointerException) {
            try {
                val location = bundle?.getSerializable(Util.KEY_LOCATION) as Location
                binding.apply {
                    nameLocationDesc.text = location.name
                    typeDescLoc.text = location.type
                    dimensionDesc.text = location.dimension
                }
                TODO("residents")
            } catch (e: NullPointerException) {
                try {
                    val episode = bundle?.getSerializable(Util.KEY_EPISODE) as Episode
                    binding.apply {
                        nameEpisodeDesc.text = episode.name
                        airdateDesc.text = episode.date
                        createdDesc.text = episode.created
                    }

                } catch (e: NullPointerException) {
                }
            }
        }
    }
}