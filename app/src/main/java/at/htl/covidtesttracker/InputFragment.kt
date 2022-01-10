package at.htl.covidtesttracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import at.htl.covidtesttracker.databinding.FragmentInputBinding

class InputFragment : Fragment() {

    private lateinit var binding: FragmentInputBinding
    private var locations = arrayOf("Linz-Stadtplatz",
        "Leonding-Meixnerkreuzung", "Parkplatz-Haidcenter")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInputBinding.inflate(inflater, container, false)

        val resultAdapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_spinner_item, TestResult.values())
        resultAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.editResult.adapter = resultAdapter

        val locationAdapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_spinner_item, locations)
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.editLocation.adapter = locationAdapter

        return binding.root
    }

}