package com.example.rick_and_mortywiki.UI.Fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rick_and_mortywiki.Adapters.Adapter
import com.example.rick_and_mortywiki.Adapters.AdapterLocEpi
import com.example.rick_and_mortywiki.Interfaces.ClickImp
import com.example.rick_and_mortywiki.Model.DataClasses.Character
import com.example.rick_and_mortywiki.Model.DataClasses.Episode
import com.example.rick_and_mortywiki.Model.DataClasses.Location
import com.example.rick_and_mortywiki.R
import com.example.rick_and_mortywiki.UI.MainViewModel
import com.example.rick_and_mortywiki.Utils.Util
import com.example.rick_and_mortywiki.databinding.FragmentSearchBinding
import com.example.rick_and_mortywiki.databinding.MaketForDialogueLocationBinding
import com.example.rick_and_mortywiki.databinding.MaketForDialoguePersonBinding

class SearchFragment : Fragment(), ClickImp {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    lateinit var spinnerSelected: String
    var status: String = ""
    var gender: String = ""
    var type: String = ""
    var dimension: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        viewModel.liveDataFindCharacter.observe(viewLifecycleOwner, {
            showListCharacter(it.results, binding.recycleSearch)
        })
        viewModel.liveDataFindLocation.observe(viewLifecycleOwner, {
            showListLocation(it.results, null, binding.recycleSearch)
        })
        viewModel.liveDataFindEpisode.observe(viewLifecycleOwner, {
            showListLocation(null, it.results, binding.recycleSearch)
        })

        sendingResponse()

        val spinnerData = mutableListOf<String>()
        spinnerData.add("Episodes")
        spinnerData.add("Characters")
        spinnerData.add("Locations")

        val adapterForSpinner = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            spinnerData
        )

        adapterForSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerSearch.adapter = adapterForSpinner
        binding.spinnerSearch.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                spinnerSelected = parent.selectedItem.toString()
                if (spinnerSelected == "Characters") {
                    openDialogPersons()
                } else if (spinnerSelected == "Locations") {
                    openDialogueLocations()
                } else if (spinnerSelected == "Episodes") {
                    Toast.makeText(context, "Введите название эпизода", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(context, "Выберите один из вариантов", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun showListCharacter(list: List<Character>, recyclerView: RecyclerView) {
        recyclerView.layoutManager =
            LinearLayoutManager(parentFragment?.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = Adapter(requireContext(), list, this)
    }

    private fun showListLocation(
        listLoc: List<Location>? = null,
        listEpi: List<Episode>? = null,
        recyclerView: RecyclerView
    ) {
        recyclerView.layoutManager =
            LinearLayoutManager(parentFragment?.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = AdapterLocEpi(requireContext(), listLoc, listEpi, this)
    }


    private fun openDialogPersons() {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Введите параметры")
            .setMessage("Введите предпочитаемые параметры")

        val bindingPerson = MaketForDialoguePersonBinding.inflate(layoutInflater)
        dialog.setView(bindingPerson.root)
        //First Spinner
        //Data for spinner
        val spinnerDataStatus = mutableListOf<String>()
        spinnerDataStatus.add("Alive")
        spinnerDataStatus.add("Dead")
        spinnerDataStatus.add("Unknown")
        //Adapter for spinner
        val adapterForSpinnerStatus = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            spinnerDataStatus
        )
        adapterForSpinnerStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bindingPerson.spinnerStatus.adapter = adapterForSpinnerStatus

        //Second Spinner
        val spinnerDataGender = mutableListOf<String>()
        spinnerDataGender.apply {
            add("Male")
            add("Female")
            add("Unknown")
        }
        val adapterForSpinnerGender = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            spinnerDataGender
        )

        adapterForSpinnerGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bindingPerson.spinnerGender.adapter = adapterForSpinnerGender

        dialog.setPositiveButton("Введите параметры") { _, _ ->

            spinnerListenerStatus(bindingPerson)

            spinnerListenerGender(bindingPerson)

        }
        dialog.setNegativeButton("Отмена") { _, _ ->

        }
        dialog.show()
    }

    private fun spinnerListenerGender(bindingListener: MaketForDialoguePersonBinding) {
        bindingListener.spinnerStatus.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItemSt = parent.selectedItem.toString()
                if (selectedItemSt == "Alive") {
                    gender = "Alive"
                } else if (selectedItemSt == "Dead") {
                    gender = "Dead"
                } else if (selectedItemSt == "Unknown") {
                    gender = "Unknown"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(context, "Выберите один из вариантов", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun spinnerListenerStatus(bindingListener: MaketForDialoguePersonBinding) {

        bindingListener.spinnerStatus.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItemSt = parent.selectedItem.toString()
                if (selectedItemSt == "Alive") {
                    status = "alive"
                } else if (selectedItemSt == "Dead") {
                    status = "dead"
                } else if (selectedItemSt == "Unknown") {
                    status = "unknown"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(context, "Выберите один из вариантов", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun openDialogueLocations() {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Введите параметры")
            .setMessage("Введите предпочитаемые параметры")

        val bindingLocation = MaketForDialogueLocationBinding.inflate(layoutInflater)
        dialog.setView(bindingLocation.root)
        dialog.setPositiveButton("Ввести параметры") { _, _ ->
            type = bindingLocation.typeDialogue.text.toString()
            dimension = bindingLocation.dimensionDialogue.text.toString()
        }

        dialog.setNegativeButton("Отмена") { _, _ ->

        }
        dialog.show()
    }

    private fun sendingResponse() {

        binding.searchTextButton.setOnClickListener({
            if (binding.searchText.text.toString().isNotEmpty()) {
                if (spinnerSelected.equals("Episodes")) {
                    Log.i("MyLog", "It works")
                    viewModel.findEpisode(binding.searchText.text.toString())
                } else if (spinnerSelected.equals("Characters")) {
                    viewModel.findCharacter(binding.searchText.text.toString(), gender, status)
                } else if (spinnerSelected.equals("Locations")) {
                    viewModel.findLocations(binding.searchText.text.toString(), type, dimension)
                }
            }
        })
    }

    override fun onCardClickPerson(position: Int, list: List<Character>) {
        val bundle = Bundle()
        bundle.putSerializable(Util.KEY_PERSON, list.get(position))
        val fragment = DescriptionFragment()
        fragment.arguments = bundle

        Navigation.findNavController(binding.root)
            .navigate(R.id.action_searchFragment_to_descriptionFragment)
    }

    override fun onCardClickLocation(position: Int, list: List<Location>) {
        val bundle = Bundle()
        bundle.putSerializable(Util.KEY_LOCATION, list.get(position))
        val fragment = DescriptionFragment()
        fragment.arguments = bundle

        Navigation.findNavController(binding.root)
            .navigate(R.id.action_searchFragment_to_descriptionFragment)
    }

    override fun onCardClickEpisode(position: Int, list: List<Episode>) {
        val bundle = Bundle()
        bundle.putSerializable(Util.KEY_EPISODE, list.get(position))
        val fragment = DescriptionFragment()
        fragment.arguments = bundle

        Navigation.findNavController(binding.root)
            .navigate(R.id.action_searchFragment_to_descriptionFragment)
    }
}