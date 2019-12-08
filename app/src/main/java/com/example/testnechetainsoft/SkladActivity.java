package com.example.testnechetainsoft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.testnechetainsoft.adapter.RecyclerAdapterShop;
import com.example.testnechetainsoft.databinding.ActivitySkladBinding;
import com.example.testnechetainsoft.db.SQLiteConnector;
import com.example.testnechetainsoft.model.ModelShop;
import com.example.testnechetainsoft.singleton.Shops;

import java.util.ArrayList;
import java.util.List;

public class SkladActivity extends AppCompatActivity {

    private RecyclerAdapterShop mAdapter;
    private RecyclerView.LayoutManager mLayoutManagers;
    private List<ModelShop> modelShops;
    private SQLiteConnector sqLiteConnector;
    private SQLiteDatabase sqLiteDatabase;

    private ActivitySkladBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sklad);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sklad);
        modelShops = new ArrayList<>();
        sqLiteConnector = new SQLiteConnector(getApplicationContext());
        sqLiteDatabase = sqLiteConnector.getWritableDatabase();

        init();
        getDBData();

        binding.fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AddShop.class);
            startActivity(intent);

            Shops.getInstance().setFLAG_ADD(2);
        });

        mAdapter.setMyInterface(new RecyclerAdapterShop.MyInterface() {
            @Override
            public void btnChange(int positions, String name, String descriptions, String image) {
                Intent intent = new Intent(getApplicationContext(), AddShop.class);
                startActivity(intent);

                Shops.getInstance().setFLAG_ADD(2);
                Shops.getInstance().setFLAG_CHANGE(2);
                Shops.getInstance().setName(name);
                Shops.getInstance().setDescriptions(descriptions);
                Shops.getInstance().setImage(image);
            }

            @Override
            public void btnDel(int positions, String name) {
                sqLiteDatabase = sqLiteConnector.getWritableDatabase();
                sqLiteConnector.deletinItemSklad(name);
                modelShops.remove(positions);
                mAdapter.notifyDataSetChanged();
            }
        });

        mAdapter.notifyDataSetChanged();
    }

    private void init() {
        mLayoutManagers = new LinearLayoutManager(getApplicationContext());
        binding.rwShop.setLayoutManager(mLayoutManagers);
        mAdapter = new RecyclerAdapterShop(getApplicationContext(), modelShops, 2);
        binding.rwShop.setAdapter(mAdapter);
    }

    private void getDBData() {
        Cursor cursor = sqLiteConnector.getReadableDatabase().query("Sklad"
                , new String[]{"nameShop", "descriptions", "image"}
                , null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                ModelShop model = new ModelShop();
                model.setNameShop(cursor.getString(0));
                model.setDecsriptions(cursor.getString(1));
                model.setImage(cursor.getString(2));

                modelShops.add(model);
                mAdapter.notifyDataSetChanged();
            } while (cursor.moveToNext());
        }
    }
}

