package com.spinny.assignment.view.customViews;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.spinny.assignment.R;

import java.util.ArrayList;
import java.util.List;

public class CSpinner extends ConstraintLayout implements AdapterView.OnItemSelectedListener {

    private int resId;
    private Boolean enable;
    private OnItemClick onItemClick;
    private Spinner spinner;
    private ImageView imageView;
    private ArrayAdapter<String> arrayAdapter;
    private List<String> list;

    public CSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomSpinner,
                0, 0);

        String label = "";
        try {
            label = a.getString(R.styleable.CustomSpinner_spinnerLabel);
            enable = a.getBoolean(R.styleable.CustomSpinner_spinnerEnable, true);

        } finally {
            setEnable(enable);
            setLabel(label);

            invalidate();
            requestLayout();
            a.recycle();
            list = new ArrayList<>();
            arrayAdapter = new CArrayAdapter<>(context, R.layout.custom_adapter_layout, R.id.customTextView, list);
            spinner.setAdapter(arrayAdapter);
            spinner.setOnItemSelectedListener(this);
        }

    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setListItems(Activity activity, @NonNull List<String> items) {

        this.list = new ArrayList<>();
        this.list.add("Select any one");
        this.list.addAll(items);
        arrayAdapter = new CArrayAdapter<>(activity, R.layout.custom_adapter_layout, R.id.customTextView, list);
        spinner.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
        setEnable(list.size() > 1);

    }

    public void setLabel(String label) {
        this.spinner.setPrompt(label);
    }


    public void setImageVisibility(int status) {
        this.imageView.setVisibility(status);
    }

    public void setEnable(Boolean enable) {
        spinner.setEnabled(enable);
    }


    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_spinner, this, true);
        spinner = view.findViewById(R.id.spinner);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (onItemClick != null && position != 0)
            onItemClick.onClicked(list.get(position), position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void setSelectedItem(String item) {
        spinner.setSelection(list.indexOf(item));
    }

    public interface OnItemClick {
        void onClicked(String itemSelected, int position);
    }
}
