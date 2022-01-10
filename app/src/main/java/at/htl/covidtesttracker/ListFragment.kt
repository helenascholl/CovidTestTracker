package at.htl.covidtesttracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import at.htl.covidtesttracker.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val args: ListFragmentArgs by navArgs()
    private var items: MutableList<Test> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)

        items.add(args.result)

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1, items
        )
        binding.list.adapter = adapter

        return binding.root
    }
}