package com.`package`.timemngr.ui.Alarm

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.text.TextUtils.replace
import android.transition.Explode
import android.transition.Fade
import android.transition.Slide
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.timemngr.R
import com.example.timemngr.databinding.FragmentAlarmBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class AlarmFragment : Fragment() {

    private var _binding: FragmentAlarmBinding? = null

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
        val homeViewModel =
            ViewModelProvider(this).get(AlarmViewModel::class.java)

        _binding = FragmentAlarmBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pickerTime =
            MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H).setHour(12)
                .setMinute(10).build()
        var selectedTimeText: TextView = requireView().findViewById(R.id.pickedTimeText)
        var selectedDaysText: TextView = requireView().findViewById(R.id.selectedDaysText)

        var selectTimeButton: Button? = requireView().findViewById(R.id.selectTimeButton)


        selectTimeButton?.setOnClickListener() {
            pickerTime.show(parentFragmentManager, "alarmShow")
        }


        var startAlarmApp:Intent? = null

        pickerTime.addOnPositiveButtonClickListener {
            selectedTimeText.text =
                pickerTime.hour.toString() + " : " + pickerTime.minute.toString()
            startAlarmApp=
                Intent(AlarmClock.ACTION_SET_ALARM).putExtra(AlarmClock.EXTRA_HOUR, pickerTime.hour)
                    .putExtra(AlarmClock.EXTRA_MINUTES, pickerTime.minute)


        }

        var selectDaysDialogButton: Button = requireView().findViewById(R.id.selectDaysButton)

        val alarmDays = ArrayList<Int>()
        selectDaysDialogButton.setOnClickListener() {
            val multiItems = arrayOf(
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday",
                "Sunday"
            )
            val checkedItems = booleanArrayOf(false, false, false, false, false, false, false)
            val calendarDays = arrayOf(Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY, Calendar.SATURDAY, Calendar.SUNDAY)

            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.selectDays))
                .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                    // Respond to neutral button press
                }
                .setPositiveButton(resources.getString(R.string.ok)) { dialog, which ->

                    selectedDaysText.text = ""

                    var index:Int = 0

                    var noOfTrue:Int = 0

                    for(item in checkedItems){
                        if(item){
                            noOfTrue++
                        }
                    }

                    if(noOfTrue > 0){
                        for(item in multiItems){

                            if(checkedItems.elementAt(index)) {
                                var dayShort:String = item.removeRange(3, item.length)
                                selectedDaysText.append(dayShort)
                                if (noOfTrue > 1) {
                                    selectedDaysText.append(", ")
                                }
                                noOfTrue--
                            }
                            index++

                        }
                    }else{
                        selectedDaysText.text == ""
                        selectedDaysText.append("Not selected")
                    }

                    var dayIndex:Int = 0
                    for(bool in checkedItems){
                        if(bool){
                            alarmDays.add(calendarDays.elementAt(dayIndex))
                        }
                        dayIndex++
                    }


                }
                //Multi-choice items (initialized with checked items)
                .setMultiChoiceItems(multiItems, checkedItems) { dialog, which, checked ->
                    // Respond to item chosen
                }
                .show()


        }

        var addAlarmButton:Button = requireView().findViewById(R.id.launchAlarmButton)

        addAlarmButton.setOnClickListener(){
            if(selectedTimeText.text.equals("Not selected")){
                Snackbar.make(requireView(), "Please select wanted time for alarm!", Snackbar.LENGTH_SHORT).show()
            }else if(selectedDaysText.text.equals("Not selected")){
                Snackbar.make(requireView(), "Please select wanted days for alarm to go off!", Snackbar.LENGTH_SHORT).show()
            }else{
                startAlarmApp?.putExtra(AlarmClock.EXTRA_DAYS, alarmDays)
                startActivity(startAlarmApp!!)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}