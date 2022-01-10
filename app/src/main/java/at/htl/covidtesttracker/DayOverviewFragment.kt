package at.htl.covidtesttracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import at.htl.covidtesttracker.databinding.FragmentDayOverviewBinding
import java.time.LocalDate

class DayOverviewFragment : Fragment() {

    private lateinit var binding: FragmentDayOverviewBinding
    private val args: DayOverviewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDayOverviewBinding.inflate(inflater, container, false)

        val testsToday = args.tests.filter { LocalDate.from(it.date).isEqual(LocalDate.now()) }
        val testsTodayPos = testsToday.filter { it.result == TestResult.POSITIVE }.size

        binding.totalTests.text = "Total Tests: ${testsToday.size}\n" +
                "Positive: $testsTodayPos\n" +
                "Negative: ${testsToday.size - testsTodayPos}"

        binding.progressBar.max = testsToday.size
        binding.progressBar.progress = testsTodayPos

        val testsByLocation = HashMap<String, Int>()
        testsToday.forEach {
            if (!testsByLocation.containsKey(it.location)) {
                testsByLocation[it.location] = 0
            }

            testsByLocation[it.location] = testsByLocation[it.location]!! + 1
        }

        binding.locationTests.text =
            testsByLocation.keys.joinToString("\n") { "$it: ${testsByLocation[it]} people tested" }

        return binding.root
    }

}