package com.`package`.timemngr.ui.timer

import android.os.Bundle
import android.transition.Explode
import android.transition.Fade
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.timemngr.R
import com.example.timemngr.databinding.FragmentTimerBinding
import org.w3c.dom.Text

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

        var textHourOne:TextView = requireView().findViewById(R.id.textHourOne)
        var textHourTwo:TextView = requireView().findViewById(R.id.textHourTwo)
        var textHourLetter:TextView = requireView().findViewById(R.id.textHourLetter)

        var textMinuteOne:TextView = requireView().findViewById(R.id.textMinuteOne)
        var textMinuteTwo:TextView = requireView().findViewById(R.id.textMinuteTwo)
        var textMinuteLetter:TextView = requireView().findViewById(R.id.textMinuteLetter)

        var textSecondOne:TextView = requireView().findViewById(R.id.textSecondsOne)
        var textSecondTwo:TextView = requireView().findViewById(R.id.textSecondsTwo)
        var textSecondLetter:TextView = requireView().findViewById(R.id.textSecondsLetter)


        val listOfText = arrayOf(textHourOne, textHourTwo, textMinuteOne, textMinuteTwo, textSecondOne, textSecondTwo)

        var currentPosition = 5

        fun decrease(){
            if(currentPosition > 0){
                currentPosition--
            }
        }

        fun increase(){
            if(currentPosition < 5){
                currentPosition++
            }
        }

        buttonZero.setOnClickListener(){
                if(currentPosition < 5){
                    listOfText[currentPosition].setText("0")
                    decrease()
                }
        }

        buttonOne.setOnClickListener(){
            listOfText[currentPosition].setText("1")
            decrease()
        }

        buttonTwo.setOnClickListener(){
            listOfText[currentPosition].setText("2")
            decrease()
        }

        buttonThree.setOnClickListener(){
            listOfText[currentPosition].setText("3")
            decrease()
        }

        buttonFour.setOnClickListener(){
            listOfText[currentPosition].setText("4")
            decrease()
        }

        buttonFive.setOnClickListener(){
            listOfText[currentPosition].setText("5")
            decrease()
        }

        buttonSix.setOnClickListener(){
            listOfText[currentPosition].setText("6")
            decrease()
        }

        buttonSeven.setOnClickListener(){
            listOfText[currentPosition].setText("7")
            decrease()
        }

        buttonEight.setOnClickListener(){
            listOfText[currentPosition].setText("8")
            decrease()
        }

        buttonNine.setOnClickListener(){
            listOfText[currentPosition].setText("9")
            decrease()
        }

        buttonDelete.setOnClickListener(){



        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}