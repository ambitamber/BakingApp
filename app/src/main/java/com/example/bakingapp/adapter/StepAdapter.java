package com.example.bakingapp.adapter;

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

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepAdapterViewHolder>{

    private final Context context;
    private final List<Steps> stepsList;
    private final StepAdapterOnClickHandler onClickHandler;

    public StepAdapter(Context context, List<Steps> stepsList, StepAdapterOnClickHandler onClickHandler) {
        this.context = context;
        this.stepsList = stepsList;
        this.onClickHandler = onClickHandler;
    }

    public interface StepAdapterOnClickHandler {
        void onClick(Steps steps);
    }

    public class StepAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView shortDescription,description;

        public StepAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            shortDescription = itemView.findViewById(R.id.TV_shortDescription);
            description = itemView.findViewById(R.id.TV_description);
            itemView.setOnClickListener(this);
        }

        void bind(int index){
            Steps steps = stepsList.get(index);
            shortDescription = itemView.findViewById(R.id.TV_shortDescription);
            description = itemView.findViewById(R.id.TV_description);
            shortDescription.setText(steps.getShortDescription());
            description.setText(steps.getDescription());
        }

        @Override
        public void onClick(View v) {
            int adapterposition = getAdapterPosition();
            Steps steps = stepsList.get(adapterposition);
            onClickHandler.onClick(steps);
        }
    }

    @NonNull
    @Override
    public StepAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutForItem = R.layout.steplist;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutForItem,parent,false);
        return new StepAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepAdapterViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if (stepsList == null){
            return 0;
        }
        return stepsList.size();
    }
}
