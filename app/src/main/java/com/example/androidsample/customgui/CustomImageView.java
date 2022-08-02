package com.example.androidsample.customgui;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class CustomImageView extends AppCompatImageView {
    public CustomImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean performClick() {
        super.performClick();
        return true;

    }
}
