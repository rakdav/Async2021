package com.example.async;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private Button download;
    private EditText editLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        download=findViewById(R.id.download);
        editLink=findViewById(R.id.edit_link);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link=editLink.getText().toString();
                new DownloadImage(findViewById(R.id.imageview)).execute("");
            }
        });
    }

    private class DownloadImage extends AsyncTask<String,Void, Bitmap>
    {
        ImageView image;

        public DownloadImage(ImageView image) {
            this.image = image;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String url=strings[0];
            Bitmap icon=null;
            try {
                InputStream in=new URL(url).openStream();
                icon= BitmapFactory.decodeStream(in);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return icon;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            image.setImageBitmap(bitmap);
        }
    }
}