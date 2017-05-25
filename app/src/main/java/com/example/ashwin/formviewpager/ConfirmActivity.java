package com.example.ashwin.formviewpager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmActivity extends AppCompatActivity {

    private Form mForm;
    private TextView mFormContentsTextView;
    private Button mDoneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        mForm = getIntent().getParcelableExtra("Form");

        initViews();

        initFormContents();
    }

    private void initViews() {
        mFormContentsTextView = (TextView) findViewById(R.id.form_contents_text_view);

        mDoneButton = (Button) findViewById(R.id.done_button);
        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ConfirmActivity.this, "Thank You :)", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initFormContents() {
        String formContents = "";
        if (mForm != null) {
            formContents = "Name : " + mForm.getName() + "\n\n"
                    + "Email : " + mForm.getEmail() + "\n\n"
                    + "Country : " + mForm.getCountry() + "\n\n"
                    + "Education : " + mForm.getEducation();
        } else {
            formContents = "Null";
        }
        mFormContentsTextView.setText(formContents);
    }
}
