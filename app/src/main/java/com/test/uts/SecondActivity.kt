package com.test.uts

import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_second.*
import java.text.SimpleDateFormat
import java.util.*

class SecondActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener, OnItemSelectedListener {

    private var item: MyModel? = null

    private val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.US)

    private val currDate = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        item = intent.getParcelableExtra(KEY)

        Glide.with(this).load(item?.gambarMobil).into(iv_main)
        tv_title.text = item?.namaMobil
        tv_subtitle.text = item?.jenisMobil
        tv_desc.text = item?.deskripsiMobil
        tv_price.text = "Price : \$${item?.hargaHarian}/day"
        tv_additional.text =
            "Seat: ${item?.jumlahTempatDuduk} Bag: ${item?.jumlahBagasi}"

        tv_date.text = dateFormat.format(currDate.time)

        spinner_main.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            arrayOf("One day", "Two day", "Three day", "Four day", "Five day")
        )

        spinner_main.onItemSelectedListener = this

        button_rent.setOnClickListener {
            val alarmManager =
                getSystemService(Context.ALARM_SERVICE) as AlarmManager

            val pendingIntent = PendingIntent.getBroadcast(
                this,
                101,
                Intent(this, AlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            alarmManager.setExact(
                AlarmManager.RTC,
                currDate.timeInMillis,
                pendingIntent
            )

            Toast.makeText(this, "Successful Rent the car", Toast.LENGTH_SHORT).show()
            finish()
        }

        ll_date.setOnClickListener {
            val day = currDate.get(Calendar.DAY_OF_MONTH)
            val month = currDate.get(Calendar.MONTH)
            val year = currDate.get(Calendar.YEAR)
            val datePickerDialog =
                DatePickerDialog(this@SecondActivity, this@SecondActivity, year, month, day)
            datePickerDialog.datePicker.minDate = Calendar.getInstance().timeInMillis
            datePickerDialog.show()
        }
    }

    companion object {
        const val KEY = "key"
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        currDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        currDate.set(Calendar.MONTH, month)
        currDate.set(Calendar.YEAR, year)

        tv_date.text = dateFormat.format(currDate.time)

        val hour = currDate.get(Calendar.HOUR_OF_DAY)
        val minute = currDate.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(this, this, hour, minute, true)

        timePickerDialog.show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        currDate.set(Calendar.HOUR_OF_DAY, hourOfDay)
        currDate.set(Calendar.MINUTE, minute)

        tv_date.text = dateFormat.format(currDate.time)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        try {
            tv_payment.text = "payment : \$${(item?.hargaHarian ?: 0) * (position + 1)}"
        } catch (e: Exception) {
            Log.d("spinner", e.message.toString())
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}