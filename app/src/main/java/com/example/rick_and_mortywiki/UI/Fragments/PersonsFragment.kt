package com.example.rick_and_mortywiki.UI.Fragments

import android.app.Person
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rick_and_mortywiki.Adapters.Adapter
import com.example.rick_and_mortywiki.Interfaces.ClickImp
import com.example.rick_and_mortywiki.Model.DataClasses.Character
import com.example.rick_and_mortywiki.Model.DataClasses.Episode
import com.example.rick_and_mortywiki.R
import com.example.rick_and_mortywiki.UI.MainViewModel
import com.example.rick_and_mortywiki.Utils.Util
import com.example.rick_and_mortywiki.databinding.FragmentPersonsBinding

class PersonsFragment : Fragment(), ClickImp {
    private var _binding: FragmentPersonsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.liveDatacharacters.observe(viewLifecycleOwner, {
            showList(it.results)
        })
    }

    private fun showList(list: List<Character>) {
        binding.recyclePersons.layoutManager = GridLayoutManager(
            context, 1, GridLayoutManager.VERTICAL,
            false
        )
        binding.recyclePersons.adapter = context?.let { Adapter(it, list, this) }
    }

    override fun onCardClickPerson(position: Int, list: List<Character>) {
        val bundle = Bundle()
        bundle.putSerializable(Util.KEY_PERSON, list.get(position))
        val fragment = DescriptionFragment()
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            addToBackStack(null)
            commit()
        }
    }

    override fun onCardClickLocation(position: Int, list: List<com.example.rick_and_mortywiki.Model.DataClasses.Location>) {}

    override fun onCardClickEpisode(position: Int, list: List<Episode>) {}
}
