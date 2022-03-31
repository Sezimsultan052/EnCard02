package com.example.encard02.ui.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.example.encard02.databinding.FragmentDialogBinding;

import org.jetbrains.annotations.NotNull;

public class Dialog extends DialogFragment{
    private FragmentDialogBinding binding;
    private String title;
    private String imgUrl;

    public Dialog(String title, String imgUrl) {
        this.title = title;
        this.imgUrl = imgUrl;
    }

    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDialogBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.tvTitleDialog.setText(title);
        Glide.with(binding.getRoot()).load(imgUrl).centerCrop().into(binding.imgDialog);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                dismiss();

            }
        });
    }
}
