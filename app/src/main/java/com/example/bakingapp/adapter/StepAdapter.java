package com.example.bakingapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.R;
import com.example.bakingapp.model.Steps;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepAdapterViewHolder> {

    private final List<Steps> stepsList;
    private final StepAdapterOnClickHandler onClickHandler;

    public StepAdapter(List<Steps> stepsList, StepAdapterOnClickHandler onClickHandler) {
        this.stepsList = stepsList;
        this.onClickHandler = onClickHandler;
    }

    public interface StepAdapterOnClickHandler {
        void onClick(int index);
    }

    public class StepAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.TV_Step_Id)
        TextView step_id;
        @BindView(R.id.TV_shortDescription)
        TextView shortDescription;

        public StepAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {
            int adapterposition = getAdapterPosition();
            onClickHandler.onClick(adapterposition);
        }
    }

    @NonNull
    @Override
    public StepAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutForItem = R.layout.list_step;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutForItem, parent, false);
        return new StepAdapterViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StepAdapterViewHolder holder, int position) {
        holder.shortDescription.setText(stepsList.get(position).getShortDescription());
        holder.step_id.setText(stepsList.get(position).getId() + ".");
    }

    @Override
    public int getItemCount() {
        if (stepsList == null) {
            return 0;
        }
        return stepsList.size();
    }
}
