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
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.timemngr.R
import com.example.timemngr.databinding.FragmentTimerBinding
import com.google.android.material.snackbar.Snackbar

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

        var textMinuteDigits:TextView = requireView().findViewById(R.id.textMinuteDigits)
        var textMinuteLetter:TextView = requireView().findViewById(R.id.textMinuteLetter)

        var textSecondsDigits:TextView = requireView().findViewById(R.id.textSecondsDigits)
        var textSecondLetter:TextView = requireView().findViewById(R.id.textSecondsLetter)
        
        var listOfDigits = listOf<TextView>(textHourDigits, textMinuteDigits, textSecondsDigits)
        var listOfLetters = listOf<TextView>(textHourLetter, textMinuteLetter, textSecondLetter)


        var selectedDigits: TextView? = null
        var selectedLetters:TextView? = null
        var areDigitsSelected:Boolean = false
        var defaultColor = Color.parseColor("#808080")

        var leftDigit:Boolean = false
        var rightDigit:Boolean = false

        var leftDigitNumber:Int? = null
        var rightDigitNumber:Int? = null

        fun addingDigit(digit:Int){
            if(selectedDigits != null){
                if(!rightDigit){
                    selectedDigits?.setText("0" + digit)
                    rightDigit = true
                    rightDigitNumber = digit
                }else{
                    selectedDigits!!.setText(digit.toString() + rightDigitNumber.toString())
                    leftDigit = true
                }

                if(selectedDigits!!.text.toString().toInt() >= 60){
                    selectedDigits?.setText("00")

                    when(selectedDigits){
                        //TO DO if value is greater than 60
                    }
                }
            }
        }

        fun deselectDigits(){
            selectedDigits?.setTextColor(defaultColor)
            selectedLetters?.setTextColor(defaultColor)

            selectedDigits = null
            areDigitsSelected = false

            leftDigit = false
            rightDigit = false
        }

        fun selectDigits(textIndex:Int){
            if(selectedDigits != null){
                deselectDigits()
            }
            listOfDigits[textIndex].setTextColor(Color.CYAN)
            listOfLetters[textIndex].setTextColor(Color.CYAN)

            selectedDigits = listOfDigits[textIndex]
            selectedLetters = listOfLetters[textIndex]
            areDigitsSelected = true
        }
        


        layoutOther.setOnClickListener(){
            if(areDigitsSelected){
                deselectDigits()
            }
        }

        textHourDigits.setOnClickListener(){
            selectDigits(0)

        }

        textMinuteDigits.setOnClickListener(){
            selectDigits(1)
        }

        textSecondsDigits.setOnClickListener(){
            selectDigits(2)
        }

        buttonZero.setOnClickListener(){
            addingDigit(0)
        }

        buttonOne.setOnClickListener(){
            addingDigit(1)
        }

        buttonTwo.setOnClickListener(){
            addingDigit(2)
        }

        buttonThree.setOnClickListener(){
            addingDigit(3)
        }

        buttonFour.setOnClickListener(){
            addingDigit(4)
        }

        buttonFive.setOnClickListener(){
            addingDigit(5)
        }

        buttonSix.setOnClickListener(){
            addingDigit(6)
        }

        buttonSeven.setOnClickListener(){
            addingDigit(7)
        }

        buttonEight.setOnClickListener(){
            addingDigit(8)
        }

        buttonNine.setOnClickListener(){
            addingDigit(9)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}