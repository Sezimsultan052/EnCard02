package com.example.encard02.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.example.encard02.R;
import com.example.encard02.databinding.ActivityMainBinding;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController controller;
    private NavHostFragment navHostFragment;

    @Inject
    public Prefs prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initNav();
    }

    private void initNav() {
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host);
        controller = navHostFragment.getNavController();
        if (!prefs.isBoardShow()) {
            controller.navigate(R.id.onBoardFragment);
        }
        controller.addOnDestinationChangedListener((controller, destination, arguments) -> {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(R.id.wordsFragment);

            if (list.contains(destination.getId())) {
                binding.navView.setVisibility(View.VISIBLE);
            } else {
                binding.navView.setVisibility(View.GONE);
            }
        });
    }

}

