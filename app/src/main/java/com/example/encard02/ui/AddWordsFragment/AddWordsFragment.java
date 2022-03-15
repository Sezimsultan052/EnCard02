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
private WordViewModel viewModel;

    private Handler handler;
    private Timer timer = new Timer();
    private final long DELAY = 2000; // Milliseconds

    public AddWordsFragment(ISendKeyword iSendKeyword) {
        this.iSendKeyword = iSendKeyword;
    }

    @Override
    protected FragmentAddWordsBinding bind() {
        return FragmentAddWordsBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initClickers();
    }

    private void initClickers() {

        //-------->OPEN FRAGMENT WITH TEXTWATCHER<---------

        binding.etWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = binding.etWord.getText().length();
                handler = new Handler();
                handler.postDelayed(() -> {
                    if (length == binding.etWord.getText().length()) {
                        iSendKeyword.sendKeyword(binding.etWord.getText().toString());
                        controller.navigate(R.id.wordsFragment);
                    }
                }, DELAY);


            }

            @Override
            public void afterTextChanged(Editable s) {
                //this code is not working -> CORRECT ERROR
//                int wordLength = binding.etWord.getText().length();
//                timer.cancel();
//                timer = new Timer();
//                timer.schedule(
//                        new TimerTask() {
//                            @Override
//                            public void run() {
//                                if (wordLength ==  binding.etWord.getText().length()) {
//                                    iSendKeyword.sendKeyword(binding.etWord.getText().toString());
//                                    controller.navigate(R.id.wordsFragment);
//                                }
//
//                            }
//                        },
//                        DELAY
//                );
            }
        });

//            ----> open fragment with OnClickListener <------
//        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    if(!binding.etWord.getText().toString().isEmpty()){
////                        iSendKeyword.sendKeyword(binding.etWord.getText().toString());
////                        controller.navigate(R.id.wordsFragment);
////                        dismiss();
////                    }
//                    String word = binding.etWord.getText().toString();
//                    viewModel.getHitLiveData(word).observe(getViewLifecycleOwner(), images -> {
//
//                    });
//                }
//        });
    }



}