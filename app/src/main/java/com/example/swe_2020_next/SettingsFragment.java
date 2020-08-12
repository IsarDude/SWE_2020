package com.example.swe_2020_next;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class SettingsFragment extends Fragment {
    Spinner languageSpinner;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        languageSpinner = (Spinner) getView().findViewById(R.id.languageSpinner);

        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter
                .createFromResource(Objects.requireNonNull(getActivity()), R.array.language_array,
                        android.R.layout.simple_spinner_item);
        genderAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        languageSpinner.setAdapter(genderAdapter);
    }

}
