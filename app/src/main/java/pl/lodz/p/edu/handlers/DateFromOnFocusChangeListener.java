package pl.lodz.p.edu.handlers;


import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import pl.lodz.p.edu.activities.AddPackingListActivity;

public class DateFromOnFocusChangeListener implements View.OnFocusChangeListener {

    private static final String DATE_FORMAT_STR = "dd-MM-yyyy";

    private AddPackingListActivity parent;

    public DateFromOnFocusChangeListener(AddPackingListActivity parent) {
        this.parent = parent;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            showDatePickerDialog(v);
        }
    }

    private void showDatePickerDialog(final View v) {
        final Calendar currCal = Calendar.getInstance();

        final DatePickerDialog datePickerDialog = new DatePickerDialog(parent, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STR);
                final Calendar cal = Calendar.getInstance();
                cal.set(year, month, dayOfMonth);
                ((TextInputEditText) v).setText(dateFormat.format(cal.getTime()));

                parent.getCreationParameters().getRangeDate()[0] = cal.getTime();
            }
        }, currCal.get(Calendar.YEAR), currCal.get(Calendar.MONTH), currCal.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}
