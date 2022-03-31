package com.example.encard02.ui.OnBoardFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.encard02.R;
import com.example.encard02.base.BaseFragment;
import com.example.encard02.databinding.FragmentOnBoardBinding;
import com.example.encard02.ui.Prefs;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class OnBoardFragment extends BaseFragment<FragmentOnBoardBinding> implements IClick {

    private OnBoardAdapter adapter;
    @Inject
    public Prefs prefs;
    

    @Override
    protected FragmentOnBoardBinding bind() {
        return FragmentOnBoardBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewPager();
    }

    private void initViewPager() {
        adapter = new OnBoardAdapter(this);
        binding.boarPager.setAdapter(adapter);
        binding.wormDotsIndicator.setViewPager2(binding.boarPager);
    }


    @Override
    protected void setupUI() {

    }

    @Override
    protected void setupObservers() {

    }

    @Override
    public void click() {
        controller.navigateUp();
        prefs.saveBoardState();

    }
}