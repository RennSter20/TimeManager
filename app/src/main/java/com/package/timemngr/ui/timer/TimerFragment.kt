package com.`package`.timemngr.ui.timer

import android.graphics.Color
import android.os.Bundle
import android.transition.Explode
import android.transition.Fade
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.timemngr.R
import com.example.timemngr.databinding.FragmentTimerBinding

class TimerFragment : Fragment() {

    private var _binding: FragmentTimerBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(TimerViewModel::class.java)

        _binding = FragmentTimerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = Fade()
        exitTransition = Explode()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var layoutOther:ConstraintLayout = requireView().findViewById(R.id.layout)

        var buttonZero = requireView().findViewById<Button>(R.id.button0)
        var buttonOne = requireView().findViewById<Button>(R.id.button)
        var buttonTwo = requireView().findViewById<Button>(R.id.button2)
        var buttonThree = requireView().findViewById<Button>(R.id.button3)
        var buttonFour = requireView().findViewById<Button>(R.id.button4)
        var buttonFive = requireView().findViewById<Button>(R.id.button5)
        var buttonSix = requireView().findViewById<Button>(R.id.button6)
        var buttonSeven = requireView().findViewById<Button>(R.id.button7)
        var buttonEight = requireView().findViewById<Button>(R.id.button8)
        var buttonNine = requireView().findViewById<Button>(R.id.button9)
        var buttonDelete = requireView().findViewById<Button>(R.id.buttonDelete)

        var textHourDigits:TextView = requireView().findViewById(R.id.textHourDigits)
        var textHourLetter:TextView = requireView().findViewById(R.id.textHourLetter)

        var textMinuteDigits:TextView = requireView().findViewById(R.id.textHourDigits)
        var textMinuteLetter:TextView = requireView().findViewById(R.id.textMinuteLetter)

        var textSecondsDigits:TextView = requireView().findViewById(R.id.textSecondsDigits)
        var textSecondLetter:TextView = requireView().findViewById(R.id.textSecondsLetter)



        var selectedDigits:TextView? = null

        fun setSelectedText(text:TextView){
            textHourDigits.setTextColor(Color.CYAN)
            selectedDigits = text
        }



        textHourDigits.setOnClickListener(){
            setSelectedText(textHourDigits)

        }

        textMinuteDigits.setOnClickListener(){
            setSelectedText(textMinuteDigits)
        }

        textSecondsDigits.setOnClickListener(){
            setSelectedText(textSecondsDigits)
        }

        buttonZero.setOnClickListener(){

        }

        buttonOne.setOnClickListener(){

        }

        buttonTwo.setOnClickListener(){

        }

        buttonThree.setOnClickListener(){

        }

        buttonFour.setOnClickListener(){

        }

        buttonFive.setOnClickListener(){

        }

        buttonSix.setOnClickListener(){

        }

        buttonSeven.setOnClickListener(){

        }

        buttonEight.setOnClickListener(){

        }

        buttonNine.setOnClickListener(){

        }

        buttonDelete.setOnClickListener(){

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}