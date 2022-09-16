package com.example.timemngr.ui.Alarm

import android.os.Bundle
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


        var selectTimeButton:Button? = requireView().findViewById(R.id.selectTimeButton)

        //var checkboxDayOfWeek = mutableListOf(R.id.checkBoxMonday, R.id.checkBoxTuesday, R.id.checkBoxWednesday, R.id.checkBoxThursday, R.id.checkBoxFriday, R.id.checkBoxSaturday, R.id.checkBoxSunday)
        var checkBoxMonday = R.id.checkBoxMonday
        var checkBoxTuesday = R.id.checkBoxTuesday
        var checkBoxWednesday = R.id.checkBoxWednesday
        var checkBoxThursday = R.id.checkBoxThursday
        var checkBoxFriday = R.id.checkBoxFriday
        var checkBoxSaturday = R.id.checkBoxSaturday
        var checkBoxSunday = R.id.checkBoxSunday

        selectTimeButton?.setOnClickListener(){
            pickerTime.show(parentFragmentManager, "alarmShow")
        }

        pickerTime.addOnPositiveButtonClickListener {
            selectedTimeText.text = pickerTime.hour.toString() + " : " + pickerTime.minute.toString()
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}