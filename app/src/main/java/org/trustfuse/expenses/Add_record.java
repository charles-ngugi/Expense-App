package org.trustfuse.expenses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Add_record extends AppCompatActivity {
    DatePickerDialog pickerDialog;
    int year;
    EditText showdate;
    String mydatepicked;
    Button submit;
    EditText description, amount;
    String date;
    String describe;
    String amnt;
    String[] spinnerTitles = { "Clothing", "Food", "Gaming", "Shopping", "Transport", "Others" };
    int [] spinnerImages = { R.drawable.clothing,
            R.drawable.food,
            R.drawable.gaming,
            R.drawable.shopping,
            R.drawable.travel,
            R.drawable.others };
    FirebaseFirestore db;
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
        description = findViewById(R.id.description);
        amount = findViewById(R.id.amount);
        submit = findViewById(R.id.submit);
        date = showdate.getText().toString();
        db = FirebaseFirestore.getInstance();
//        describe = description.getText().toString();
//        amnt = amount.getText().toString();


        // select date
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
        //spinner/dropdown
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(Add_record.this, "You Selected:  "+spinnerTitles[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        CustomAdapter customAdapter=new CustomAdapter(getApplicationContext(),spinnerTitles,spinnerImages);
        spinner.setAdapter(customAdapter);
        //backbutton
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"BACK CLICKED",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        //save data
        //submit button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                describe = description.getText().toString();
                amnt = amount.getText().toString();
                String datee = showdate.getText().toString();

                DocumentReference documentReference = db.collection("Expenses").document("Charles");
                Map<String,Object> expenditure = new HashMap<>();
                expenditure.put("category","cat" );
                expenditure.put("description",describe);
                expenditure.put("amount",amnt);
                expenditure.put("date",date);
                documentReference.set(expenditure).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(),"SUCCESS",Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Add_record.this, "UNSUCCESSFUL", Toast.LENGTH_LONG).show();
//                LottieAlertDialog alertDialog= new LottieAlertDialog.Builder(Good_owner_post.this, DialogTypes.TYPE_ERROR)
//                        .setTitle("OOPS!!!")
//                        .setDescription("Upload Unsuccessful")
//                        .build();
//                alertDialog.setCancelable(true);
//                alertDialog.show();
                    }
                });
            }
        });
    }

}