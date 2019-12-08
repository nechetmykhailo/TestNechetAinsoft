package com.example.testnechetainsoft.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testnechetainsoft.MainActivity;
import com.example.testnechetainsoft.ProductActivity;
import com.example.testnechetainsoft.R;
import com.example.testnechetainsoft.SkladActivity;
import com.example.testnechetainsoft.databinding.ItemShopBinding;
import com.example.testnechetainsoft.model.ModelShop;

import java.util.List;

public class RecyclerAdapterShop extends RecyclerView.Adapter<RecyclerAdapterShop.CardViewHolder> {
    private Context context;
    private List<ModelShop> modelShops;
    private MyInterface myInterface;
    private int count;

    public RecyclerAdapterShop(Context context, List<ModelShop> modelShops, int count) {
        this.context = context;
        this.modelShops = modelShops;
        this.count = count;
    }

    public void setMyInterface(MyInterface myInterface) {
        this.myInterface = myInterface;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new CardViewHolder(inflater
                .inflate(R.layout.item_shop, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.binding.tvNameShop.setText(modelShops.get(position).getNameShop());
        holder.binding.btnChange.setOnClickListener(v ->
                myInterface.btnChange(
                        position,
                        modelShops.get(position).getNameShop(),
                        modelShops.get(position).getDecsriptions(),
                        modelShops.get(position).getImage()));
        holder.binding.btnDel.setOnClickListener(v -> myInterface.btnDel(position, modelShops.get(position).getNameShop()));

        if (count == 1){
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, SkladActivity.class);
                context.startActivity(intent);
            });
        }else if (count == 2){
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, ProductActivity.class);
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return modelShops.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        private ItemShopBinding binding;

        public CardViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    public interface MyInterface {
        void btnChange(int positions, String name, String descriptions, String image);
        void btnDel(int positions, String name);
    }
}
