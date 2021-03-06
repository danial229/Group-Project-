package com.example.pcar;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.palette.graphics.Palette;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Camera extends AppCompatActivity {

    private TextView mVibrant, mDarkVibrant, mLightVibrant, mMuted, mDarkMuted, mLightMuted;

    String currentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 1;
    //Button btnTake;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);

        mVibrant = findViewById(R.id.TextView1);
        mDarkVibrant = findViewById(R.id.TextView2);
        mLightVibrant = findViewById(R.id.TextView3);
        mMuted = findViewById(R.id.TextView4);
        mDarkMuted = findViewById(R.id.TextView5);
        mLightMuted = findViewById(R.id.TextView6);

       // btnTake = findViewById(R.id.btnOc);
       /* btnTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        dispatchTakePictureIntent();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.pcar.provider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    @Override
    protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {

            //get the bitmap from the file name
            Bitmap takenImage = BitmapFactory.decodeFile(currentPhotoPath);

            if (takenImage != null){
                Palette.from(takenImage).generate(new Palette.PaletteAsyncListener() {
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
            iv_photo = findViewById(R.id.imgView1);
            iv_photo.setImageBitmap(takenImage);

            //show the file name in tv_message
            TextView tv_message;
            tv_message = findViewById(R.id.tv_message);
            tv_message.setText(currentPhotoPath);
        }
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void setViewSwatch(TextView view, Palette.Swatch swatch, final String title){
        if (swatch != null){
            view.setBackgroundColor(swatch.getRgb());
            view.setText(title);
            view.setTextColor(swatch.getTitleTextColor());
        }
    }
}
