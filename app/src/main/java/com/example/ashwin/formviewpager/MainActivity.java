package com.example.ashwin.formviewpager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static TabLayout mFormTabs;
    public static NonSwipeableViewPager mFormsViewPager;
    public static FormsViewPagerAdapter mFormsViewPagerAdapter;
    public static Form mForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initForm();
    }

    private void initViews() {
        mFormTabs = (TabLayout) findViewById(R.id.formTabs);
        mFormsViewPagerAdapter = new FormsViewPagerAdapter(getSupportFragmentManager());
        mFormsViewPager = (NonSwipeableViewPager) findViewById(R.id.formsViewPager);
        mFormsViewPagerAdapter.addFragment(NameFormFragment.newInstance(), "Name");
        mFormsViewPagerAdapter.addFragment(AddressFormFragment.newInstance(), "Address");
        mFormsViewPagerAdapter.addFragment(EducationFormFragment.newInstance(), "Education");
        mFormsViewPager.setAdapter(mFormsViewPagerAdapter);
        mFormTabs.setupWithViewPager(mFormsViewPager);

        // Disable tab click
        TabLayoutUtils.enableTabs( mFormTabs, false );
    }

    private void initForm() {
        mForm = new Form();
    }

    public static Form getForm() {
        return mForm;
    }

    public static void saveForm(Form form) {
        mForm = form;
    }

    public static int getTotalPages() {
        return mFormsViewPagerAdapter.getCount();
    }

    public static int getCurrentPage() {
        return mFormsViewPager.getCurrentItem();
    }

    public static void onPreviousButtonClicked() {
        int currentPage = mFormsViewPager.getCurrentItem();
        Log.d("debuglogging", "MainActivity : onPreviousButtonClicked from tab " + currentPage);
        mFormsViewPager.setCurrentItem(currentPage - 1, true);
    }

    public static void onNextButtonClicked() {
        int currentPage = mFormsViewPager.getCurrentItem();
        Log.d("debuglogging", "MainActivity : onNextButtonClicked from tab " + currentPage);
        mFormsViewPager.setCurrentItem(currentPage + 1, true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
