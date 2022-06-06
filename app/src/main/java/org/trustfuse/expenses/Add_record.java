package org.trustfuse.expenses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Objects;

public class Add_record extends AppCompatActivity {
    DatePickerDialog pickerDialog;
    EditText showdate;
    int year;
    String mydatepicked;
    String[] spinnerTitles = { "Clothing", "Food", "Gaming", "Shopping", "Transport", "Others" };
    int [] spinnerImages = { R.drawable.clothing,
            R.drawable.food,
            R.drawable.gaming,
            R.drawable.shopping,
            R.drawable.travel,
            R.drawable.others };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        Toolbar toolbar = findViewById(R.id.toolbarrecord);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Expense/Income");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        showdate = findViewById(R.id.date);
        Spinner spinner = findViewById(R.id.spinner);
        ImageView imageView = findViewById(R.id.image_view_spinner);
        TextView textView = findViewById(R.id.text_view_spinner);



        showdate.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                year = cldr.get(Calendar.YEAR);
                pickerDialog = new DatePickerDialog(this,
                        (vee, year1, monthOfYear, dayOfMonth)
                                -> {
                            showdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1);

                                mydatepicked = showdate.getText().toString();
                        }, year, month, day);
                pickerDialog.show();

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Add_record.this, "You Select Position: "+i+" "+spinnerImages[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        CustomAdapter customAdapter=new CustomAdapter(getApplicationContext(),spinnerTitles,spinnerImages);
        spinner.setAdapter(customAdapter);

    }

}