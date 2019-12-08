package com.example.testnechetainsoft;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.testnechetainsoft.databinding.ActivityAddShopBinding;
import com.example.testnechetainsoft.db.SQLiteConnector;
import com.example.testnechetainsoft.singleton.Shops;
import com.squareup.picasso.Picasso;

public class AddShop extends AppCompatActivity {
    private ActivityAddShopBinding binding;
    private SQLiteConnector sqLiteConnector;
    private SQLiteDatabase sqLiteDatabase;

    private String imagePath;

    private String FLAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_shop);
        sqLiteConnector = new SQLiteConnector(getApplicationContext());
        sqLiteDatabase = sqLiteConnector.getWritableDatabase();

        if (Shops.getInstance().getFLAG_ADD() == 1){
            if (Shops.getInstance().getFLAG_CHANGE() == 1) {
                change();

                binding.btnSave.setOnClickListener(v -> {
                    String name = binding.etName.getText().toString();
                    String decriptions = binding.etDescription.getText().toString();

                    sqLiteConnector.changeNameShop(name, Shops.getInstance().getName(), decriptions, imagePath);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                });
            } else {
                binding.btnSave.setOnClickListener(v -> {
                    String name = binding.etName.getText().toString();
                    String decriptions = binding.etDescription.getText().toString();

                    sqLiteConnector.insertNameShop(name, decriptions, imagePath);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                });
            }
        }else if(Shops.getInstance().getFLAG_ADD() == 2){
            if (Shops.getInstance().getFLAG_CHANGE() == 2) {
               change();

                binding.btnSave.setOnClickListener(v -> {
                    String name = binding.etName.getText().toString();
                    String decriptions = binding.etDescription.getText().toString();

                    sqLiteConnector.changeNameSklad(name, Shops.getInstance().getName(), decriptions, imagePath);
                    Intent intent = new Intent(getApplicationContext(), SkladActivity.class);
                    startActivity(intent);
                });
            } else {
                binding.btnSave.setOnClickListener(v -> {
                    String name = binding.etName.getText().toString();
                    String decriptions = binding.etDescription.getText().toString();

                    sqLiteConnector.insertNameSklad(name, decriptions, imagePath);
                    Intent intent = new Intent(getApplicationContext(), SkladActivity.class);
                    startActivity(intent);
                });
            }
        }else if (Shops.getInstance().getFLAG_ADD() == 3){
            if (Shops.getInstance().getFLAG_CHANGE() == 3) {
                change();

                binding.btnSave.setOnClickListener(v -> {
                    String name = binding.etName.getText().toString();
                    String decriptions = binding.etDescription.getText().toString();

                    sqLiteConnector.changeNameProduct(name, Shops.getInstance().getName(), decriptions, imagePath);
                    Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                    startActivity(intent);
                });
            } else {
                binding.btnSave.setOnClickListener(v -> {
                    String name = binding.etName.getText().toString();
                    String decriptions = binding.etDescription.getText().toString();

                    sqLiteConnector.insertNameProduct(name, decriptions, imagePath);
                    Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                    startActivity(intent);
                });
            }
        }

        binding.btnAddPhoto.setOnClickListener(v -> {
            Intent photoPickerIntent = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            );
            photoPickerIntent.setType("image/*");
            photoPickerIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            photoPickerIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(photoPickerIntent, 31);
        });


    }

    private void change() {
        Picasso.get()
                .load(Shops.getInstance().getImage())
                .resize(600, 0)
                .centerCrop()
                .into(binding.imageView);

        binding.btnSave.setText("Change");

        binding.etName.setText(Shops.getInstance().getName());
        binding.etDescription.setText(Shops.getInstance().getDescriptions());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 31) {
                Uri uri = data.getData();
                Picasso.get()
                        .load(uri)
                        .resize(600, 0)
                        .centerCrop()
                        .into(binding.imageView);

                imagePath = String.valueOf(uri);
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        Shops.reset();
    }
}
