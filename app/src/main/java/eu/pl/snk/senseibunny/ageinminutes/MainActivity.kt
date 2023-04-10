package eu.pl.snk.senseibunny.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var inMinutes: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById<Button>(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.Selected)
        inMinutes = findViewById(R.id.Minutes)

        btnDatePicker.setOnClickListener { view ->
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this, "Year: $selectedYear", Toast.LENGTH_LONG).show()

                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"

                tvSelectedDate?.text = (selectedDate)

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val date = sdf.parse(selectedDate)

                date?.let{ // do this code only if date is not null
                    val selectedDateInMinutes = date.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let{
                        val currentDateInMinutes = currentDate.time / 60000

                        val diffInMinutes = currentDateInMinutes - selectedDateInMinutes
                        println(currentDateInMinutes)
                        println(selectedDateInMinutes)

                        inMinutes?.text = diffInMinutes.toString()
                    }
                }

            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate= System.currentTimeMillis()-86400000 // setting max date to yesterday
        dpd.show()
    }
}