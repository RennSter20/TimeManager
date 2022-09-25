package com.`package`.timemngr.ui.stopwatch


import android.graphics.Color
import android.graphics.Color.GREEN
import android.os.Bundle
import android.os.SystemClock
import android.transition.Explode
import android.transition.Fade
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Chronometer
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.timemngr.R
import com.example.timemngr.databinding.FragmentStopwatchBinding
import com.google.android.material.progressindicator.CircularProgressIndicator


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


        var circularProgress = requireView().findViewById<CircularProgressIndicator>(R.id.progressBar)
        circularProgress.visibility = View.INVISIBLE

        var passiveCircularProgress = requireView().findViewById<CircularProgressIndicator>(R.id.passiveProgressBar)

        passiveCircularProgress.visibility = View.VISIBLE

        var startStopwatch: Button = requireView().findViewById(R.id.startStopwatch)

        var stopStopwatch:Button = requireView().findViewById(R.id.stopStopwatch)
        stopStopwatch.isClickable = false
        stopStopwatch.isVisible = false

        var resetStopwatch:Button = requireView().findViewById(R.id.resetStopwatch)
        resetStopwatch.isClickable = false
        resetStopwatch.isVisible = false

        var pauseOffset:Long = 0
        var running = false
        var showButtons = true

        var animationFadeIn: Animation? = AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in)
        var animationFadeOut: Animation? = AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_out)

        fun manageElements(arg:String){
            if(arg.equals("START")){
                stopStopwatch.isClickable = true
                resetStopwatch.isClickable = true
                startStopwatch.isClickable = false

                if(showButtons){
                    stopStopwatch.startAnimation(animationFadeIn)
                    stopStopwatch.isVisible = true

                    resetStopwatch.startAnimation(animationFadeIn)
                    resetStopwatch.isVisible = true
                }
            }else if(arg.equals("STOP")){

                stopStopwatch.isClickable = false
                resetStopwatch.isClickable = true
                startStopwatch.isClickable = true


            }else if(arg.equals("RESET")){
                stopStopwatch.isClickable = false
                resetStopwatch.isClickable = false
                startStopwatch.isClickable = true

                stopStopwatch.startAnimation(animationFadeOut)
                stopStopwatch.isVisible = false

                resetStopwatch.startAnimation(animationFadeOut)
                resetStopwatch.isVisible = false
            }


        }

        startStopwatch.setOnClickListener(){
            if(!running){
                circularProgress.visibility = View.VISIBLE

                circularProgress.trackColor = GREEN
                passiveCircularProgress.visibility = View.INVISIBLE

                manageElements("START")

                //offset sluzi da zavara sistem tako da misli da je poceo prije x sekundi,
                //jer je tu sekundu u sistemu vec prosao
                stopwatch.base = SystemClock.elapsedRealtime() - pauseOffset
                stopwatch.start()
                running = true
                showButtons = true

            }
        }

        stopStopwatch.setOnClickListener(){
            if(running){
                stopwatch.stop()

                pauseOffset = SystemClock.elapsedRealtime() - stopwatch.base
                running = false
                showButtons = false

                circularProgress.visibility = View.INVISIBLE
                passiveCircularProgress.visibility = View.VISIBLE
                passiveCircularProgress.trackColor = Color.RED
                passiveCircularProgress.startAnimation(animationFadeIn)

                manageElements("STOP")
            }
        }

        resetStopwatch.setOnClickListener(){
            stopwatch.stop()

            stopwatch.base = SystemClock.elapsedRealtime()
            pauseOffset = 0
            running = false
            showButtons = true

            circularProgress.visibility = View.INVISIBLE
            passiveCircularProgress.visibility = View.VISIBLE
            passiveCircularProgress.trackColor = Color.parseColor("#9C27B0")

            manageElements("RESET")
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}