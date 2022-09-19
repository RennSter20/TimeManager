package com.example.timemngr.ui.stopwatch


import android.os.Bundle
import android.transition.Explode
import android.transition.Fade
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.timemngr.R
import com.example.timemngr.databinding.FragmentStopwatchBinding


class StopwatchFragment : Fragment() {

    private var _binding: FragmentStopwatchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = Fade()
        exitTransition = Explode()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(StopwatchViewModel::class.java)

        _binding = FragmentStopwatchBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val stopwatch = requireView().findViewById(R.id.chronometer2) as Chronometer
        var startStopwatch: Button = requireView().findViewById(R.id.startStopwatch)
        var stopStopwatch:Button = requireView().findViewById(R.id.stopStopwatch)

        var currentTime:String

        startStopwatch.setOnClickListener(){
            stopwatch.start()
        }

        stopStopwatch.setOnClickListener(){
            stopwatch.stop()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}