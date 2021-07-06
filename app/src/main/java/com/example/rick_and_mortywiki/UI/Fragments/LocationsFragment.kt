package com.example.rick_and_mortywiki.UI.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rick_and_mortywiki.Adapters.Adapter
import com.example.rick_and_mortywiki.Adapters.AdapterLocEpi
import com.example.rick_and_mortywiki.Interfaces.ClickImp
import com.example.rick_and_mortywiki.Model.DataClasses.Character
import com.example.rick_and_mortywiki.Model.DataClasses.Episode
import com.example.rick_and_mortywiki.Model.DataClasses.Location
import com.example.rick_and_mortywiki.R
import com.example.rick_and_mortywiki.UI.MainViewModel
import com.example.rick_and_mortywiki.Utils.Util
import com.example.rick_and_mortywiki.databinding.FragmentLocationsBinding
import com.example.rick_and_mortywiki.databinding.FragmentPersonsBinding

class LocationsFragment : Fragment(), ClickImp {
    private var _binding: FragmentLocationsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private var page = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLocationsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.liveDataLocations.observe(viewLifecycleOwner, {
            showList(it.results)
        })

        binding.newListLoc.setOnClickListener({
            viewModel.getLocations(page)
            page++
            if (page==7){
                page=1
            }
        })
    }

    private fun showList(list: List<Location>) {
        binding.recycleLocations.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false
            )
           binding.recycleLocations.adapter = context?.let { AdapterLocEpi(it, list, null,this) }
    }

    override fun onCardClickPerson(position: Int, list: List<Character>) {

    }

    override fun onCardClickLocation(position: Int, list: List<Location>) {
        val bundle = Bundle()
        bundle.putSerializable(Util.KEY_LOCATION, list.get(position))
        val fragment = DescriptionFragment()
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            addToBackStack(null)
            commit()
        }
    }

    override fun onCardClickEpisode(position: Int, list: List<Episode>) {}
}