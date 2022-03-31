package com.example.encard02.ui.OnBoardFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.encard02.R;
import com.example.encard02.databinding.PagerBoardBinding;

public class OnBoardAdapter extends RecyclerView.Adapter<OnBoardAdapter.OnBoardViewHolder> {


    private final IClick listener;
    private PagerBoardBinding pagerBoardBinding;
    private static final String personalArea = "Личный Кабинет";
    private static final String letsGo = "Давай начнем!";
    
    private static final String wordsDes = "Создавайте слова на английском и кликайте " +
            "на карточку чтобы увидеть его перевод и картинку для ассоциации";
    private static final String personalAreaDes = "Следите за своими очками " +
            "и количеством очков опыта";
    private final int[] animateList = {R.raw.child, R.raw.online};
    private final static String[] titleList = {personalArea, letsGo};
    private final static String[] descriptionList = {wordsDes, personalAreaDes};

    public OnBoardAdapter(IClick listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public OnBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        pagerBoardBinding = PagerBoardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new OnBoardViewHolder(pagerBoardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull OnBoardViewHolder holder, int position) {
        holder.onBind(position);
        pagerBoardBinding.btnBoard.setOnClickListener(view -> listener.click());
    }

    @Override
    public int getItemCount() {
        return titleList.length;
    }

    public class OnBoardViewHolder extends RecyclerView.ViewHolder {
        PagerBoardBinding pagerBoardBinding;

        public OnBoardViewHolder(@NonNull PagerBoardBinding pagerBoardBinding) {
            super(pagerBoardBinding.getRoot());
            this.pagerBoardBinding = pagerBoardBinding;
        }

        public void onBind(int position) {

            pagerBoardBinding.tvCategoryBoard.setText(titleList[position]);
            pagerBoardBinding.tvDescriptionBoard.setText(descriptionList[position]);
            pagerBoardBinding.lottieAnimBoard.setAnimation(animateList[position]);

            if (position == animateList.length - 1) {
                pagerBoardBinding.btnBoard.setVisibility(View.VISIBLE);
                pagerBoardBinding.tvDescriptionBoard.setVisibility(View.GONE);
            } else {
                pagerBoardBinding.btnBoard.setVisibility(View.GONE);
            }
        }

        }
    }
    interface IClick {
        void click();
    }

