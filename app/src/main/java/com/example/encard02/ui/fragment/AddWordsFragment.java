package com.example.encard02.ui.fragment;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.PixabayResponse;
import com.example.encard02.App;
import com.example.encard02.R;
import com.example.encard02.base.BaseBottomSheetDialogFragment;
import com.example.encard02.databinding.FragmentAddWordsBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddWordsFragment extends BaseBottomSheetDialogFragment<FragmentAddWordsBinding> {

private ISendKeyword iSendKeyword;

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
        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!binding.etWord.getText().toString().isEmpty()){
                        iSendKeyword.sendKeyword(binding.etWord.getText().toString());
                        controller.navigate(R.id.wordsFragment);
                        dismiss();
                    }
                }
        });
    }



}