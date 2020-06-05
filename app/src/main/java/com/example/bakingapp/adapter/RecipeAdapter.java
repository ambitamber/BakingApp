package com.example.bakingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.R;
import com.example.bakingapp.model.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeAdapterVieweHolder> {

    private List<Recipe> recipeList;
    private final RecipeAdapterOnClickHandler onClickHandler;

    public RecipeAdapter(List<Recipe> recipeList, RecipeAdapterOnClickHandler onClickHandler) {
        this.recipeList = recipeList;
        this.onClickHandler = onClickHandler;
    }

    public interface RecipeAdapterOnClickHandler {
        void onClick(int index);
    }

    public class RecipeAdapterVieweHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.TV_title)
        TextView title;
        @BindView(R.id.TV_servings)
        TextView serviings;
        @BindView(R.id.TV_steps)
        TextView steps;
        @BindView(R.id.IV_pic)
        ImageView imageView;

        public RecipeAdapterVieweHolder(@NonNull View itemView) {
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
    public RecipeAdapterVieweHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutForItem = R.layout.list;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutForItem, parent, false);
        return new RecipeAdapterVieweHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapterVieweHolder holder, int position) {
        holder.title.setText(recipeList.get(position).getName());
        holder.serviings.setText(recipeList.get(position).getServings());
        String stepsLenght = String.valueOf(recipeList.get(position).getSteps().size());
        holder.steps.setText(stepsLenght);
        switch (recipeList.get(position).getName()){
            case "Nutella Pie":
                holder.imageView.setImageResource(R.drawable.nutellapie);
                break;
            case "Brownies":
                holder.imageView.setImageResource(R.drawable.brownies);
                break;
            case "Yellow Cake":
                holder.imageView.setImageResource(R.drawable.yellowcake);
                break;
            case "Cheesecake":
                holder.imageView.setImageResource(R.drawable.cheesecake);
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (recipeList == null) {
            return 0;
        }
        return recipeList.size();
    }
}
