package com.example.timemngr.ui.Alarm

import android.app.AlarmManager
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.timemngr.R
import com.example.timemngr.databinding.FragmentHomeBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*
import kotlin.collections.ArrayList

class AlarmFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(AlarmViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pickerTime = MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H).setHour(12).setMinute(10).build()
        var selectedTimeText:TextView = requireView().findViewById(R.id.pickedTimeText)
        var selectedDaysText:TextView = requireView().findViewById(R.id.selectedDaysText)

        var selectTimeButton:Button? = requireView().findViewById(R.id.selectTimeButton)


        var checkBoxMonday = requireView().findViewById<CheckBox>(R.id.checkBoxMonday)
        var checkBoxTuesday = requireView().findViewById<CheckBox>(R.id.checkBoxTuesday)
        var checkBoxWednesday = requireView().findViewById<CheckBox>(R.id.checkBoxWednesday)
        var checkBoxThursday = requireView().findViewById<CheckBox>(R.id.checkBoxThursday)
        var checkBoxFriday = requireView().findViewById<CheckBox>(R.id.checkBoxFriday)
        var checkBoxSaturday = requireView().findViewById<CheckBox>(R.id.checkBoxSaturday)
        var checkBoxSunday = requireView().findViewById<CheckBox>(R.id.checkBoxSunday)

        selectTimeButton?.setOnClickListener(){
            pickerTime.show(parentFragmentManager, "alarmShow")
        }


        pickerTime.addOnPositiveButtonClickListener {
            selectedTimeText.text = pickerTime.hour.toString() + " : " + pickerTime.minute.toString()
            var startAlarmApp:Intent = Intent(AlarmClock.ACTION_SET_ALARM).putExtra(AlarmClock.EXTRA_DAYS, Calendar.MONDAY).putExtra(AlarmClock.EXTRA_HOUR, pickerTime.hour).putExtra(AlarmClock.EXTRA_MINUTES, pickerTime.minute)
            startActivity(startAlarmApp)
        }

        var firstCheck:Boolean = true

//target, replacement
        fun checkIsAlreadySelected(day:String){
            var newString:String = ""

            if(!selectedDaysText.text.contains(day)){

                if(day.equals("Sun")){
                    selectedDaysText.append(day)
                }else{
                    selectedDaysText.append(day + ", ")
                }

            }else{
                if(day.equals("Sun")){
                    selectedDaysText.text = selectedDaysText.text.toString().replace("Sun", "")

                }else{
                    selectedDaysText.text = selectedDaysText.text.toString().replace(day + ", ", "")
                    Log.i("TAG", newString)
                }
            }
        }
        fun checkFirstCheck(day:String){
            if(firstCheck){
                firstCheck = false
                selectedDaysText.text = ""
            }
            checkIsAlreadySelected(day)
        }

        var listOfSelectedDays = mutableListOf<String>()


        checkBoxMonday.setOnCheckedChangeListener { buttonView, isChecked -> listOfSelectedDays.add("Monday")
        //checkFirstCheck("Mon")
        }

        checkBoxTuesday.setOnCheckedChangeListener { buttonView, isChecked -> listOfSelectedDays.add("Tuesday")
        //checkFirstCheck("Tue")
        }

        checkBoxWednesday.setOnCheckedChangeListener { buttonView, isChecked -> listOfSelectedDays.add("Wednesday")
        //checkFirstCheck("Wed")
        }

        checkBoxThursday.setOnCheckedChangeListener { buttonView, isChecked -> listOfSelectedDays.add("Thursday")
        //checkFirstCheck("Thu")
        }

        checkBoxFriday.setOnCheckedChangeListener { buttonView, isChecked -> listOfSelectedDays.add("Friday")
        //checkFirstCheck("Fri")
        }

        checkBoxSaturday.setOnCheckedChangeListener { buttonView, isChecked -> listOfSelectedDays.add("Saturday")
        //checkFirstCheck("Sat")
        }

        checkBoxSunday.setOnCheckedChangeListener { buttonView, isChecked -> listOfSelectedDays.add("Sunday")
        //checkFirstCheck("Sun")
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}