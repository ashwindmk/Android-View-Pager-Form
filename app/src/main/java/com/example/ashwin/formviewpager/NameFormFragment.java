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

public class NameFormFragment extends Fragment {

    private Context mContext;
    private View mView;
    private EditText mNameEditText, mEmailEditText, mUsernameEditText;
    private Button mPreviousButton, mNextButton;
    private Form mForm;

    public NameFormFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static NameFormFragment newInstance() {
        NameFormFragment fragment = new NameFormFragment();
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
        View view = inflater.inflate(R.layout.fragment_name_form, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        initViews();
    }

    private void initViews() {
        mNameEditText = (EditText) mView.findViewById(R.id.name_edit_text);
        mEmailEditText = (EditText) mView.findViewById(R.id.email_edit_text);
        mUsernameEditText = (EditText) mView.findViewById(R.id.username_edit_text);

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
                    mForm.setName(mNameEditText.getText().toString());
                    mForm.setEmail(mEmailEditText.getText().toString());
                    mForm.setUsername(mUsernameEditText.getText().toString());
                    ((MainActivity) getActivity()).saveForm(mForm);
                    ((MainActivity) getActivity()).onNextButtonClicked();
                } else {
                    Toast.makeText(getActivity(), "All fields are mandatory!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean valid() {
        if (mNameEditText.getText().toString() == null || (mNameEditText.getText().toString().trim()).equals("")) {
            return false;
        }

        if (mEmailEditText.getText().toString() == null || (mEmailEditText.getText().toString().trim()).equals("")) {
            return false;
        }

        if (mUsernameEditText.getText().toString() == null || (mUsernameEditText.getText().toString().trim()).equals("")) {
            return false;
        }

        return true;
    }
}
