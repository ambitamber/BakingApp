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

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeAdapterVieweHolder>{

    private List<Recipe> recipeList;
    private final Context context;
    private final RecipeAdapterOnClickHandler onClickHandler;

    public RecipeAdapter(List<Recipe> recipeList, Context context, RecipeAdapterOnClickHandler onClickHandler) {
        this.recipeList = recipeList;
        this.context = context;
        this.onClickHandler = onClickHandler;
    }


    public interface RecipeAdapterOnClickHandler {
        void onClick(Recipe recipe);
    }

    public class RecipeAdapterVieweHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title,serviings;
        ImageView imageView;

        public RecipeAdapterVieweHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.TV_title);
            serviings = itemView.findViewById(R.id.TV_servings);
            imageView = itemView.findViewById(R.id.IV_pic);
            itemView.setOnClickListener(this);
        }

        void bind(int index){
            Recipe recipe = recipeList.get(index);
            title = itemView.findViewById(R.id.TV_title);
            serviings = itemView.findViewById(R.id.TV_servings);
            title.setText(recipe.getTitle());
            serviings.setText(recipe.getServings());
            switch (recipe.getTitle()){
                case "Yellow Cake":
                    imageView.setImageResource(R.drawable.yellowcake);
                    break;
                case "Brownies":
                    imageView.setImageResource(R.drawable.brownies);
                    break;
                case "Nutella Pie":
                    imageView.setImageResource(R.drawable.nutellapie);
                    break;
                case "Cheesecake":
                    imageView.setImageResource(R.drawable.cheesecake);
                    break;
            }
        }

        @Override
        public void onClick(View v) {
            int adapterposition = getAdapterPosition();
            Recipe recipe = recipeList.get(adapterposition);
            onClickHandler.onClick(recipe);
        }
    }

    @NonNull
    @Override
    public RecipeAdapterVieweHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutForItem = R.layout.list;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutForItem,parent,false);
        return new RecipeAdapterVieweHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapterVieweHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if (recipeList == null){
            return 0;
        }
        return recipeList.size();
    }

    public void setRecipeList(List<Recipe> recipes){
        recipeList = recipes;
        notifyDataSetChanged();
    }
}
