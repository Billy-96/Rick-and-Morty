package com.example.rick_and_mortywiki.UI.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rick_and_mortywiki.Adapters.AdapterLocEpi
import com.example.rick_and_mortywiki.Interfaces.ClickImp
import com.example.rick_and_mortywiki.Model.DataClasses.Character
import com.example.rick_and_mortywiki.Model.DataClasses.Episode
import com.example.rick_and_mortywiki.Model.DataClasses.Location
import com.example.rick_and_mortywiki.R
import com.example.rick_and_mortywiki.UI.MainViewModel
import com.example.rick_and_mortywiki.Utils.Util
import com.example.rick_and_mortywiki.databinding.FragmentEpisodesBinding
import com.example.rick_and_mortywiki.databinding.FragmentLocationsBinding

class EpisodesFragment : Fragment(),ClickImp {
    private var _binding: FragmentEpisodesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private var page = 2
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEpisodesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.livedataEpisodes.observe(viewLifecycleOwner, {
            showList(it.results)
        })
        binding.newListEpi.setOnClickListener({
            viewModel.getEpisodes(page)
            page++
            if (page==4){
                page=1
            }
        })
    }

    private fun showList(list: List<Episode>) {
        binding.
            recycleEpisodes.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false
            )
            binding.recycleEpisodes.adapter = context?.let {
                AdapterLocEpi(it, null, list,this)
            }

    }
    override fun onCardClickPerson(position: Int, list: List<Character>) {}

    override fun onCardClickLocation(position: Int, list: List<Location>) {}

    override fun onCardClickEpisode(position: Int, list: List<Episode>) {
        val bundle = Bundle()
        bundle.putSerializable(Util.KEY_EPISODE, list.get(position))
        val fragment = DescriptionFragment()
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            addToBackStack(null)
            commit()
        }
    }
}