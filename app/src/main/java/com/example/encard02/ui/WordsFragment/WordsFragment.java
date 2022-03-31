package com.example.encard02.ui.WordsFragment;

import android.os.Bundle;

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
import com.example.encard02.databinding.FragmentWordsBinding;
import com.example.encard02.ui.AddWordsFragment.AddWordsFragment;
import com.example.encard02.ui.AddWordsFragment.ISendKeyword;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WordsFragment extends BaseFragment<FragmentWordsBinding> implements ISendKeyword {

    private WordAdapter adapter;
    private WordViewModel wordViewModel;
    private AddWordsFragment addWordsFragment;

    private String newWord;
    private int newPage = 1;



    @Override
    protected FragmentWordsBinding bind() {
        return FragmentWordsBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initClickers();
        initRefresh();

    }

    private void initRefresh() {
        binding.swipeRefresh.setOnRefreshListener(() -> {
            if (getArguments() != null) {
                newWord = getArguments().getString("word");
                wordViewModel.getMutableLiveData(newWord, newPage += 1);
            }
            binding.swipeRefresh.setRefreshing(false);
            binding.swipeRefresh.setColorSchemeResources(R.color.black,
                    R.color.white, R.color.red);
        });
    }

//    private void getBundleValue() {
//        if (getArguments() != null) {
//            newWord = getArguments().getString("word");
//            wordViewModel.getMutableLiveData(newWord, newPage += 1);
//        }
//    }

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
                        adapter.setImageList(pixabayResponseResource.data.getHits());
                        break;
                    case LOADING:
                        Toast.makeText(requireActivity(), "Loading", Toast.LENGTH_SHORT).show();
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
            addWordsFragment.show(requireActivity().getSupportFragmentManager(), "");
        });
    }


    @Override
    public void sendKeyword(String text, int page) {
        wordViewModel.getMutableLiveData(text, page);
    }
}