package com.example.timemngr.ui.stopwatch


import android.os.Bundle
import android.os.SystemClock
import android.transition.Explode
import android.transition.Fade
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.timemngr.R
import com.example.timemngr.databinding.FragmentStopwatchBinding
import com.google.android.material.snackbar.Snackbar


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
        val stopwatch:Chronometer = requireView().findViewById(R.id.chronometer2)


        var startStopwatch: Button = requireView().findViewById(R.id.startStopwatch)
        var stopStopwatch:Button = requireView().findViewById(R.id.stopStopwatch)

        var pauseOffset:Long = 0
        var running = false


        startStopwatch.setOnClickListener(){
            if(!running){

                //offset sluzi da zavara sistem tako da misli da je poceo prije x sekundi,
                //jer je tu sekundu u sistemu vec prosao
                stopwatch.base = SystemClock.elapsedRealtime() - pauseOffset
                Log.i("SYSTEM CLOCK START", SystemClock.elapsedRealtime().toString())
                Log.i("STOPWATCH BASE START", stopwatch.base.toString())
                Log.i("PAUSE OFFSET START", pauseOffset.toString())
                stopwatch.start()
                running = true
            }
        }

        stopStopwatch.setOnClickListener(){
            if(running){
                stopwatch.stop()

                pauseOffset = SystemClock.elapsedRealtime() - stopwatch.base
                Log.i("SYSTEM CLOCK STOP", SystemClock.elapsedRealtime().toString())
                Log.i("STOPWATCH BASE STOP", stopwatch.base.toString())
                Log.i("PAUSE OFFSET STOP", pauseOffset.toString())
                running = false
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}