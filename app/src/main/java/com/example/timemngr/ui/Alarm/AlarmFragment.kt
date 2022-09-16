package com.example.timemngr.ui.Alarm

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.timemngr.R
import com.example.timemngr.databinding.FragmentHomeBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

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

        val picker =
            MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(12)
                .setMinute(10)
                .build()

        var button:Button? = requireView().findViewById(R.id.containedButton)

        button?.setOnClickListener(){
            picker.show(parentFragmentManager, "alarmShow")
        }

        picker?.addOnPositiveButtonClickListener {
            Log.i("PICKER", "${picker?.hour}, ${picker?.minute}")
            var alarmGo: Intent = Intent(AlarmClock.ACTION_SET_ALARM)
            alarmGo.putExtra(AlarmClock.EXTRA_HOUR, picker?.hour)
            alarmGo.putExtra(AlarmClock.EXTRA_MINUTES, picker?.minute)
            startActivity(alarmGo)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}