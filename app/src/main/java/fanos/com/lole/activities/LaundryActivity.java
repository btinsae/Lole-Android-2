package fanos.com.lole.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import fanos.com.lole.R;

public class LaundryActivity extends AppCompatActivity /*implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener*/ {
    @BindView(R.id.time_picker)
    EditText timePicker;
    @BindView(R.id.date_picker)
    EditText datePicker;
    Calendar calendar;
    private int day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(LaundryActivity.this, R.style.MyDialogTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker picker, int i, int i1) {
                        timePicker.setText(i + ":" + i1);
                    }
                }, 1, 0, false);
                timePickerDialog.show();
            }
        });
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(LaundryActivity.this, R.style.MyDialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker picker, int year, int month, int day) {
                      String monthName= calendar.getDisplayName(Calendar.MONTH,Calendar.LONG, Locale.getDefault())  ;
                        datePicker.setText(monthName+" "+day+", "+year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
//
//    @Override
//    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//
//    }
//
//    @Override
//    public void onTimeSet(TimePicker timePicker, int i, int i1) {
//
//    }

    private void showTimePicker() {

    }
}
