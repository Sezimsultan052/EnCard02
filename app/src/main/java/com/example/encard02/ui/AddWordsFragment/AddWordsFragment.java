package com.example.encard02.ui.AddWordsFragment;

import android.os.Bundle;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.encard02.R;
import com.example.encard02.base.BaseBottomSheetDialogFragment;
import com.example.encard02.databinding.FragmentAddWordsBinding;
import com.example.encard02.ui.WordsFragment.WordViewModel;

import java.util.Timer;
import java.util.TimerTask;

public class AddWordsFragment extends BaseBottomSheetDialogFragment<FragmentAddWordsBinding> {

    private ISendKeyword iSendKeyword;

    private Handler handler;
    private String text;
    private final long DELAY = 2000; // Milliseconds

    public AddWordsFragment(ISendKeyword iSendKeyword) {
        this.iSendKeyword = iSendKeyword;
    }

    @Override
    protected FragmentAddWordsBinding bind() {
        return FragmentAddWordsBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupUI() {

    }

    @Override
    protected void setupObservers() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initClickers();
    }

    private void initClickers() {
        binding.etWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = binding.etWord.getText().length();
                handler = new Handler();
                handler.postDelayed(() -> {
                    text = binding.etWord.getText().toString();
                    if (length == text.length()) {
                        iSendKeyword.sendKeyword(text, getTag());
                        controller.navigate(R.id.wordsFragment);
                        dismiss();
                    }
                }, DELAY);


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}