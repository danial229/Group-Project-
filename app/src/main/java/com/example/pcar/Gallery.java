package com.example.pcar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

import java.io.IOException;

public class Gallery extends AppCompatActivity {

    private TextView mVibrant, mDarkVibrant, mLightVibrant, mMuted, mDarkMuted, mLightMuted;
    Button btnGallery;
    ImageView imgView12;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);



        mVibrant = findViewById(R.id.TextView11);
        mDarkVibrant = findViewById(R.id.TextView22);
        mLightVibrant = findViewById(R.id.TextView33);
        mMuted = findViewById(R.id.TextView44);
        mDarkMuted = findViewById(R.id.TextView55);
        mLightMuted = findViewById(R.id.TextView66);
        imgView12 = findViewById(R.id.imgView12);


        btnGallery = findViewById(R.id.btnGallery);
        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
                imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                if (bitmap != null){
                    Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                        @Override
                        public void onGenerated(@Nullable Palette palette) {
                            setViewSwatch(mVibrant, palette.getVibrantSwatch(), "Vibrant");
                            setViewSwatch(mDarkVibrant, palette.getDarkVibrantSwatch(), "Dark Vibrant");
                            setViewSwatch(mLightVibrant, palette.getLightVibrantSwatch(), "Light Vibrant");
                            setViewSwatch(mMuted, palette.getMutedSwatch(), "Muted");
                            setViewSwatch(mDarkMuted, palette.getDarkMutedSwatch(), "Dark Muted");
                            setViewSwatch(mLightMuted, palette.getLightMutedSwatch(), "Light Muted");
                        }
                    });
                }

                //set the image ine the iv_photo view
                ImageView iv_photo;
                iv_photo = findViewById(R.id.imgView12);
                iv_photo.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }


            }



        }


    private void setViewSwatch(TextView view, Palette.Swatch swatch, final String title) {
        if (swatch != null) {
            view.setBackgroundColor(swatch.getRgb());
            view.setText(title);
            view.setTextColor(swatch.getTitleTextColor());
        }

    }
}
