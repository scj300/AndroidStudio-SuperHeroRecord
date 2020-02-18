package com.example.superherorecord;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 1000;

    public static final String NAME = "name";
    public static final String LAST_NAME = "last_name";
    public static final String HERO_NAME = "hero_name";
    public static final String AGE = "age";
    public static final String ADDRESS = "address";
    public static final String CITY = "city";

    public ImageView heroImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText nameEdit = findViewById(R.id.name_edit);
        final EditText lastNameEdit = findViewById(R.id.lastName_edit);
        final EditText heroNameEdit = findViewById(R.id.nameHero_edit);
        final EditText ageEdit = findViewById(R.id.age_edit);
        final EditText addressEdit = findViewById(R.id.address_edit);
        final EditText cityEdit = findViewById(R.id.city_edit);


        Button registerButton = findViewById(R.id.register_button);

        registerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                // Call other activities
                Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);

                detailIntent.putExtra(NAME, nameEdit.getText().toString());
                detailIntent.putExtra(LAST_NAME, lastNameEdit.getText().toString());
                detailIntent.putExtra(HERO_NAME, heroNameEdit.getText().toString());
                detailIntent.putExtra(AGE, ageEdit.getText().toString());
                detailIntent.putExtra(ADDRESS, addressEdit.getText().toString());
                detailIntent.putExtra(CITY, cityEdit.getText().toString());

                startActivity(detailIntent);
            }
        }); // end registerButton clickListener()


        heroImage = findViewById(R.id.hero_image);

        heroImage.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                openCamera();
            }

        }); // end hero clickListener()

    } // end onCreate()

    private void openCamera(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
    } // end openCamera()

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_REQUEST_CODE){
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");

            heroImage.setImageBitmap(bitmap);
        }

    }


} // end MainActivity class
