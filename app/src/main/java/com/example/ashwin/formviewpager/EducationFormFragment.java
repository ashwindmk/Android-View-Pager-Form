package com.example.ashwin.formviewpager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ashwin on 25/5/17.
 */

public class EducationFormFragment extends Fragment {

    private Context mContext;
    private View mView;
    private Spinner mEducationSpinner;
    private Button mPreviousButton, mNextButton;
    private String mEducation = "Select Education";
    private Form mForm;

    public EducationFormFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static EducationFormFragment newInstance() {
        EducationFormFragment fragment = new EducationFormFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_education_form, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        initViews();
    }

    private void initViews() {
        mEducationSpinner = (Spinner) mView.findViewById(R.id.education_spinner);
        final ArrayList<String> spinnerArray = new ArrayList<>();
        spinnerArray.add("Select Education");
        spinnerArray.add("Phd");
        spinnerArray.add("MS");
        spinnerArray.add("ME");
        spinnerArray.add("MSc");
        spinnerArray.add("B-Tech");
        spinnerArray.add("BE");
        spinnerArray.add("BSc");
        spinnerArray.add("BCom");
        spinnerArray.add("BA");
        spinnerArray.add("HSC");
        spinnerArray.add("SSC");
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        mEducationSpinner.setAdapter(spinnerArrayAdapter);
        mEducationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mEducation = spinnerArray.get(position);
                Log.d("debuglogging", "EducationFormFragment : initViews : onItemSelected : " + mEducation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mEducation = spinnerArray.get(0);
            }
        });

        mPreviousButton = (Button) mView.findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((MainActivity) getActivity()).getCurrentPage() > 0) {
                    ((MainActivity) getActivity()).onPreviousButtonClicked();
                } else {
                    getActivity().onBackPressed();
                }
            }
        });

        mNextButton = (Button) mView.findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valid()) {
                    mForm = ((MainActivity) getActivity()).getForm();
                    mForm.setEducation(mEducation);
                    ((MainActivity) getActivity()).saveForm(mForm);
                    Intent intent = new Intent((MainActivity) getActivity(), ConfirmActivity.class);
                    intent.putExtra("Form", mForm);
                    getActivity().startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "All fields are mandatory!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean valid() {
        if (mEducation.equalsIgnoreCase("select education") || mEducation.equalsIgnoreCase("")) {
            return false;
        }

        return true;
    }
}
