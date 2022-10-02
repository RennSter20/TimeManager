package com.`package`.timemngr.ui.timer

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.transition.Explode
import android.transition.Fade
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.timemngr.R
import com.example.timemngr.databinding.FragmentTimerBinding
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.snackbar.Snackbar
import java.lang.String.format
import java.text.DateFormat

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


        var progressIndicator:CircularProgressIndicator = requireView().findViewById(R.id.progressBar)
        progressIndicator.isVisible = false


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
        var defaultColor = textHourDigits.currentTextColor

        var leftDigit:Boolean = false
        var rightDigit:Boolean = false

        var leftDigitNumber:Int? = null
        var rightDigitNumber:Int? = null

        fun deselectDigits(){
            selectedDigits?.setTextColor(defaultColor)
            selectedLetters?.setTextColor(defaultColor)

            selectedDigits = null
            areDigitsSelected = false

            leftDigit = false
            rightDigit = false
            leftDigitNumber = null
            rightDigitNumber = null
        }

        fun addingDigit(digit:Int){
            if(selectedDigits != null){
                if(!rightDigit){
                    selectedDigits?.setText("0" + digit)
                    rightDigit = true
                    rightDigitNumber = digit
                }else{
                    selectedDigits!!.setText(digit.toString() + rightDigitNumber.toString())
                    leftDigit = true

                    if(selectedDigits != null)
                    if(selectedDigits?.text.toString().toInt() >= 60 && selectedDigits != null){
                        if(selectedDigits == listOfDigits[1] || selectedDigits == listOfDigits[2]){
                            selectedDigits?.setText("00")
                            Toast.makeText(requireContext(), "Maximum value is 59!",Toast.LENGTH_SHORT).show()
                        }
                    }
                    deselectDigits()
                }


            }
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


        var startButton = requireView().findViewById<Button>(R.id.startButton)
        var buttons = listOf(buttonZero, buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine,buttonDelete,startButton)

        var chronometer:Chronometer = requireView().findViewById(R.id.timerText)
        chronometer.isVisible = false
        //chronometer.setText(DateFormat.format("kk:mm:ss", vrijeme))

        startButton.setOnClickListener(){
            var animationFadeOut: Animation? = AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_out)
            var animationFadeIn:Animation? = AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in)

            for(button in buttons){
                button.startAnimation(animationFadeOut)
                button.isVisible = false
            }

            for(text in listOfDigits){
                text.startAnimation(animationFadeOut)
                text.isVisible = false
            }

            for(text in listOfLetters){
                text.startAnimation(animationFadeOut)
                text.isVisible = false
            }

            progressIndicator.isVisible = true
            chronometer.isVisible = true
            chronometer.startAnimation(animationFadeIn)
            progressIndicator.startAnimation(animationFadeIn)
            progressIndicator.indicatorDirection = CircularProgressIndicator.INDICATOR_DIRECTION_COUNTERCLOCKWISE


            var hours = listOfDigits[0].text.toString().toInt() * 3600
            var minutes = listOfDigits[1].text.toString().toInt() * 60
            var seconds = listOfDigits[2].text.toString().toInt()
            var sum = hours + minutes + seconds

            progressIndicator.max = sum
            progressIndicator.progress = sum

            //TO DO implement countdown
        }




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}