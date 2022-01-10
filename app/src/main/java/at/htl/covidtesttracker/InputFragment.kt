package at.htl.covidtesttracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import at.htl.covidtesttracker.databinding.FragmentInputBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class InputFragment : Fragment() {

    private lateinit var binding: FragmentInputBinding
    private var locations = arrayOf(
        "Linz-Stadtplatz", "Leonding-Meixnerkreuzung", "Parkplatz-Haidcenter"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInputBinding.inflate(inflater, container, false)

        val resultAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item, TestResult.values()
        )
        resultAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.editResult.adapter = resultAdapter

        val locationAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item, locations
        )
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.editLocation.adapter = locationAdapter

        binding.save.setOnClickListener { view ->
            val result = Test(
                binding.editId.text.toString(),
                LocalDateTime.parse(
                    "${binding.editDate.text}T${binding.editTime.text}",
                    DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm")
                ),
                TestResult.valueOf(binding.editResult.selectedItem.toString()),
                binding.editLocation.selectedItem.toString()
            )
            val action = InputFragmentDirections.actionInputFragmentToListFragment(result)
            view
                .findNavController()
                .navigate(action)
        }

        return binding.root
    }

}