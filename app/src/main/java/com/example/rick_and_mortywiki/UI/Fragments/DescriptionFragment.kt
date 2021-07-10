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
import androidx.navigation.fragment.navArgs
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
    var listOfIds = mutableListOf<String>()
    val argument : DescriptionFragmentArgs by navArgs()

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
        try {
            val  person = argument.character
            binding.constraintPerson.visibility = View.VISIBLE
            binding.constraintLocation.visibility = View.GONE
            binding.constraintEpisode.visibility = View.GONE
            binding.apply {
                context?.let { Glide.with(it).load(person!!.image).into(imageDesc) }
                namePersonDesc.text = person!!.name
                statusDesc.text = person.status
                speciesDesc.text = person.species
                genderDesc.text = person.gender
                for (i in person.episode) {
                    if (i.length > 41) {
                        var string =
                            i.toCharArray().get(40).toString() + i.toCharArray().get(41).toString()
                        listOfIds.add(string)
                    } else {
                        listOfIds.add(i.toCharArray().get(40).toString())
                    }
                }
                viewModel.getCharacters()
                viewModel.liveDataOneEpisode.observe(viewLifecycleOwner,{
                    listEpisodes.add(it)
                    Log.i("Myf",listEpisodes.toString())
                })

            }
        } catch (e: NullPointerException) {
            try {
                val location = argument.location
                binding.constraintLocation.visibility = View.VISIBLE
                binding.constraintPerson.visibility = View.GONE
                binding.constraintEpisode.visibility = View.GONE
                binding.apply {
                    nameLocationDesc.text = location!!.name
                    typeDescLoc.text = location.type
                    dimensionDesc.text = location.dimension
                }
            } catch (e: NullPointerException) {
                try {
                    val episode = argument.episode
                    binding.constraintEpisode.visibility = View.VISIBLE
                    binding.constraintPerson.visibility = View.GONE
                    binding.constraintLocation.visibility = View.GONE
                    binding.apply {
                        nameEpisodeDesc.text = episode!!.name
                        airdateDesc.text = episode.date
                        createdDesc.text = episode.created
                    }

                } catch (e: NullPointerException) {
                }
            }
        }
    }
}