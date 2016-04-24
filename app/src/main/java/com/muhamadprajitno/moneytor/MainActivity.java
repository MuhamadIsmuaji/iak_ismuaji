package com.muhamadprajitno.moneytor;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //For date picker
    private EditText fromDateEtxt;
    private EditText toDateEtxt;

    protected EditText input_income;
    private EditText output_income;

    protected int jumlah_uang = 0;

    private TextView jumlah_uang_text;
    private TextView waktu;
    private  TextView keterangan;

    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;

    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        findViewsById();
        setDateTimeField();
    }

    private void findViewsById() {
        fromDateEtxt = (EditText) findViewById(R.id.date_from);
        fromDateEtxt.setInputType(InputType.TYPE_NULL);
        fromDateEtxt.requestFocus();

        toDateEtxt = (EditText) findViewById(R.id.date_until);
        toDateEtxt.setInputType(InputType.TYPE_NULL);

        input_income = (EditText) findViewById(R.id.input_income);
        output_income = (EditText) findViewById(R.id.input_outcome);
        jumlah_uang_text = (TextView) findViewById(R.id.jumlah_uang_text);
        waktu = (TextView) findViewById(R.id.waktu);
        keterangan = (TextView) findViewById(R.id.keterangan);
    }

    private void setDateTimeField() {
        fromDateEtxt.setOnClickListener(this);
        toDateEtxt.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                toDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    // Handle when user submit income button
    public void submitIncome(View view) {
        jumlah_uang = jumlah_uang + Integer.parseInt(input_income.getText().toString());
        jumlah_uang_text.setText(String.valueOf(jumlah_uang));

        waktu.setText("From : " + fromDateEtxt.getText() + " Until : " + toDateEtxt.getText());

        input_income.setText("");
        fromDateEtxt.setText("");
        toDateEtxt.setText("");
    }

    // Handle when user submit outcome button
    public void submitOutcome(View view) {

        if ( jumlah_uang < Integer.parseInt(output_income.getText().toString())) {
            keterangan.setText("Maaf Uang Anda Tidak Cukup");
            output_income.setText("");
        } else {
            jumlah_uang = jumlah_uang - Integer.parseInt(output_income.getText().toString());
            jumlah_uang_text.setText(String.valueOf(jumlah_uang));

            output_income.setText("");
            keterangan.setText("Pastikan Outcome Kurang Dari Sama Dengan Income");
        }
    }

    @Override
    public void onClick(View view) {
        if(view == fromDateEtxt) {
            fromDatePickerDialog.show();
        } else if(view == toDateEtxt) {
            toDatePickerDialog.show();
        }
    }
}
