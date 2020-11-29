package com.example.a7classestask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicators;
    private MaterialButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutOnboardingIndicators = findViewById(R.id.layoutOI);
        button = findViewById(R.id.Action);
        setupOnboardingItems();

        final ViewPager2 onboardingViewPager = findViewById(R.id.viewpager);
        onboardingViewPager.setAdapter(onboardingAdapter);

        setupOnboardingIndicators();
        setCurrentOnboardingIndicator(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onboardingViewPager.getCurrentItem()+1 < onboardingAdapter.getItemCount())
                {
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem()+1);
                }
                else
                {
                    startActivity(new Intent(getApplicationContext(), OtpSend.class));
                    finish();
                }
            }
        });
    }

    private void setupOnboardingItems(){
        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem first = new OnboardingItem();
        first.setTitle("Test Your Confidence");
        first.setDesc("Take a 5-minute Confidence based Assessment for the exam that you are preparing to crack.");
        first.setImage(R.drawable.imgf);

        OnboardingItem second = new OnboardingItem();
        second.setTitle("Predict Your Performance");
        second.setDesc("Predict Your performance and get detailed analysis based on the 5-minute Test.");
        second.setImage(R.drawable.imgs);

        OnboardingItem third = new OnboardingItem();
        third.setTitle("Plan Your Preparation");
        third.setDesc("Get personalised plan based on the deatailed analytics for excelling in the exam.");
        third.setImage(R.drawable.imgt);

        onboardingItems.add(first);
        onboardingItems.add(second);
        onboardingItems.add(third);

        onboardingAdapter = new OnboardingAdapter(onboardingItems);

    }

    private void setupOnboardingIndicators(){
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
                layoutParams.setMargins(8,0,8,0);
                for(int i=0;i<indicators.length;i++) {
                    indicators[i] = new ImageView(getApplicationContext());
                    indicators[i].setImageDrawable(ContextCompat.getDrawable(
                            getApplicationContext(),
                            R.drawable.onboarding_indicator_inactive
                    ));
                    indicators[i].setLayoutParams(layoutParams);
                    layoutOnboardingIndicators.addView(indicators[i]);
                }
    }

    private void setCurrentOnboardingIndicator(int index)
    {
        int childCount = layoutOnboardingIndicators.getChildCount();
        for(int i =0;i<childCount; i++)
        {
            ImageView imageView = (ImageView)layoutOnboardingIndicators.getChildAt(i);
            if(i==index)
            {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_active));
            }
            else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_inactive));
            }
        }
        if(index == onboardingAdapter.getItemCount()-1)
        {
            button.setText("Lets Go!");
        }
        else
        {
            button.setText("Next");
        }
    }
}