package com.example.bakingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.R;
import com.example.bakingapp.model.Ingredients;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientAdapterViewHolder>{

    private final List<Ingredients> ingredientsList;
    private IngredientAdapterOnClickHandler onClickHandler;

    public IngredientAdapter(List<Ingredients> ingredientsList,IngredientAdapterOnClickHandler onClickHandler) {
        this.ingredientsList = ingredientsList;
        this.onClickHandler = onClickHandler;
    }

    @NonNull
    @Override
    public IngredientAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_ingredient,parent,false);
        return new IngredientAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapterViewHolder holder, int position) {
        holder.quantity.setText(ingredientsList.get(position).getQuantity());
        holder.measure.setText(ingredientsList.get(position).getMeasure());
        holder.ingredient.setText(ingredientsList.get(position).getIngredient());
    }

    @Override
    public int getItemCount() {
       if (ingredientsList == null){
           return 0;
       }
       return ingredientsList.size();
    }

    public interface IngredientAdapterOnClickHandler{
        void onClick(int index);
    }

    public class IngredientAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.TV_quantity)
        TextView quantity;
        @BindView(R.id.TV_measure)
        TextView measure;
        @BindView(R.id.TV_ingredient)
        TextView ingredient;

        public IngredientAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onClick(View v) {
            int adapterposition = getAdapterPosition();
            onClickHandler.onClick(adapterposition);
        }
    }
}
