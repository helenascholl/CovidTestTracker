package at.htl.covidtesttracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import at.htl.covidtesttracker.databinding.FragmentListBinding
import java.time.format.DateTimeFormatter

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val args: ListFragmentArgs by navArgs()
    private var items: MutableList<Test> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)

        args.result?.let {
            items.add(it)
        }

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1, items.map(this::toListString)
        )
        binding.list.adapter = adapter

        binding.addTest.setOnClickListener { view ->
            view
                .findNavController()
                .navigate(R.id.action_listFragment_to_inputFragment)
        }

        return binding.root
    }

    private fun toListString(test: Test): String {
        val id = if (test.id.length >= 8) {
            "${test.id.substring(0, 2)}...${test.id.substring(test.id.length - 4)}"
        } else {
            test.id
        }

        var date = test.date.format(DateTimeFormatter.ISO_DATE_TIME)
        date = date.substring(0, date.length - 3)

        val result = when (test.result) {
            TestResult.POSITIV -> "pos"
            TestResult.NEGATIV -> "neg"
        }

        val location = if (test.location.length >= 22) {
            "${test.location.substring(0, 10)}...${test.location.substring(test.location.length - 10)}"
        } else {
            test.location
        }

        return "$id;$date;$result;$location"
    }

}