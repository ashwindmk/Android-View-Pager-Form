package com.example.ashwin.formviewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by ashwin on 25/5/17.
 */

public class AddressFormFragment extends Fragment {

    private Context mContext;
    private View mView;
    private EditText mHouseNumberEditText, mStreetEditText, mCityEditText, mCountryEditText, mZipEditText;
    private Button mPreviousButton, mNextButton;
    private Form mForm;

    public AddressFormFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static AddressFormFragment newInstance() {
        AddressFormFragment fragment = new AddressFormFragment();
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
        View view = inflater.inflate(R.layout.fragment_address_form, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        initViews();
    }

    private void initViews() {
        mHouseNumberEditText = (EditText) mView.findViewById(R.id.house_number_edit_text);
        mStreetEditText = (EditText) mView.findViewById(R.id.street_edit_text);
        mCityEditText = (EditText) mView.findViewById(R.id.city_edit_text);
        mCountryEditText = (EditText) mView.findViewById(R.id.country_edit_text);
        mZipEditText = (EditText) mView.findViewById(R.id.zip_edit_text);

        mPreviousButton = (Button) mView.findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).onPreviousButtonClicked();
            }
        });

        mNextButton = (Button) mView.findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valid()) {
                    mForm = ((MainActivity) getActivity()).getForm();
                    mForm.setHouseNumber(Integer.parseInt(mHouseNumberEditText.getText().toString().trim()));
                    mForm.setStreet(mStreetEditText.getText().toString());
                    mForm.setCity(mCityEditText.getText().toString());
                    mForm.setCountry(mCountryEditText.getText().toString());
                    mForm.setZipCode(Integer.parseInt(mZipEditText.getText().toString().trim()));
                    ((MainActivity) getActivity()).saveForm(mForm);
                    ((MainActivity) getActivity()).onNextButtonClicked();
                } else {
                    Toast.makeText(getActivity(), "All fields are mandatory!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean valid() {
        if (mHouseNumberEditText.getText().toString() == null || (mHouseNumberEditText.getText().toString().trim()).equals("")) {
            return false;
        }

        if (mStreetEditText.getText().toString() == null || (mStreetEditText.getText().toString().trim()).equals("")) {
            return false;
        }

        if (mCityEditText.getText().toString() == null || (mCityEditText.getText().toString().trim()).equals("")) {
            return false;
        }

        if (mCountryEditText.getText().toString() == null || (mCountryEditText.getText().toString().trim()).equals("")) {
            return false;
        }

        if (mZipEditText.getText().toString() == null || (mZipEditText.getText().toString().trim()).equals("")) {
            return false;
        }

        return true;
    }
}
