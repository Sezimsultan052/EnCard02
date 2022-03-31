package com.example.encard02.ui.WordsFragment;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.PixabayResponse;
import com.example.encard02.R;
import com.example.encard02.base.BaseFragment;
import com.example.encard02.common.Resource;
import com.example.encard02.data.model.WordEntity;
import com.example.encard02.databinding.FragmentWordsBinding;
import com.example.encard02.ui.AddWordsFragment.AddWordsFragment;
import com.example.encard02.ui.AddWordsFragment.ISendKeyword;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WordsFragment extends BaseFragment<FragmentWordsBinding> implements ISendKeyword {

    private WordAdapter adapter;
    private WordViewModel wordViewModel;
    private AddWordsFragment addWordsFragment;
    private String category;

    @Override
    protected FragmentWordsBinding bind() {
        return FragmentWordsBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTag();
        initClickers();
    }

    private void initTag() {
    category = WordsFragmentArgs.fromBundle(getArguments()).getCategory();
    }


    @Override
    protected void setupUI() {
        wordViewModel = new ViewModelProvider(requireActivity()).get(WordViewModel.class);
        adapter = new WordAdapter();
        binding.rvImage.setAdapter(adapter);
    }

    @Override
    protected void setupObservers() {
        wordViewModel.liveData.observe(getViewLifecycleOwner(), new Observer<Resource<PixabayResponse>>() {
            @Override
            public void onChanged(Resource<PixabayResponse> pixabayResponseResource) {
                switch (pixabayResponseResource.status) {
                    case SUCCESS:
                        wordViewModel.getWords(category).observe(getViewLifecycleOwner(), wordEntities -> {
                            adapter.setImageList(wordEntities);
                        });
                        break;
                    case LOADING:
                        Toast.makeText(requireContext(), "Load", Toast.LENGTH_SHORT).show();
                        break;
                    case ERROR:
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });

    }

    private void initClickers() {
        binding.btnAddWord.setOnClickListener(v -> {
            addWordsFragment = new AddWordsFragment(this);
            addWordsFragment.show(requireActivity().getSupportFragmentManager(), category);
        });
    }


    @Override
    public void sendKeyword(String text, String category) {
        Log.e("sendWord", text + "");
        wordViewModel.loadImage(text, category);
    }
}