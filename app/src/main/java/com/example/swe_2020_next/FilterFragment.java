package com.example.swe_2020_next;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import controller.FilterController;
import model.Dater;

public class FilterFragment extends Fragment {
    Spinner genderSpinner;
    Button saveFilter;
    EditText maxDistance;
    EditText minAge;
    EditText maxAge;
    private Activity activity;
    FilterController filterController = new FilterController();

    public FilterFragment(Activity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_filter, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        genderSpinner = (Spinner) getView().findViewById(R.id.genderSpinner);

        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter
                .createFromResource(Objects.requireNonNull(getActivity()), R.array.gender_array,
                        android.R.layout.simple_spinner_item);
        genderAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        genderSpinner.setAdapter(genderAdapter);

        maxDistance = (EditText)getView().findViewById(R.id.editMaxRange);
        minAge = (EditText)getView().findViewById(R.id.editMinAge);
        maxAge = (EditText)getView().findViewById(R.id.editMaxAge);

        saveFilter = (Button) getView().findViewById(R.id.saveFilterButton);
        saveFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(minAge.getText().toString())<18){
                    Toast.makeText(getActivity(), "Mindestalter muss 18 oder höher sein!", Toast.LENGTH_LONG).show();
                }else if(Integer.parseInt(maxAge.getText().toString())<Integer.parseInt(minAge.getText().toString())) {
                    Toast.makeText(getActivity(), "Mindestalter muss niedriger als Höchstalter sein!", Toast.LENGTH_LONG).show();
                }else if(Integer.parseInt(maxDistance.getText().toString())<=0) {
                    Toast.makeText(getActivity(), "Distanz muss größer als 0 sein!", Toast.LENGTH_LONG).show();
                }else{
                    filterController.filterControl(Integer.parseInt(maxDistance.getText().toString()),Integer.parseInt(minAge.getText().toString()), Integer.parseInt(maxAge.getText().toString()), genderSpinner.getSelectedItem().toString());
                    Intent intent = new Intent(activity, MainActivity.class);
                    activity.startActivity(intent);
                    activity.finish();
                }

            }
        });
    }
}
