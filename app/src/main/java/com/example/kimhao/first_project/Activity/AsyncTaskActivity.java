package com.example.kimhao.first_project.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kimhao.first_project.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by KimHao on 04/04/2017.
 */

public class AsyncTaskActivity extends AppCompatActivity {
    String url = "https://i.ytimg.com/vi/TqHwZl033Z8/maxresdefault.jpg";
    //String url = "https://wallpaperscraft.com/image/coffee_crockery_cafe_114567_3840x2160.jpg";
    Button btnDownload;
    ImageView imgDownload;
    TextView tvStatus;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);

        btnDownload = (Button) findViewById(R.id.btnDownload);
        imgDownload = (ImageView) findViewById(R.id.imgDownload);
        tvStatus = (TextView) findViewById(R.id.tvStatus);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvStatus.setText("Downloading....");
                DownloadTask downloadTask = new DownloadTask();
                downloadTask.execute(url);
            }
        });
    }
    private class DownloadTask extends AsyncTask<String, Integer, String> {
        File input_file;
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(AsyncTaskActivity.this);
            progressDialog.setTitle("Downloading in Progress...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMax(100);
            progressDialog.setProgress(0);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String path = params[0];
            Log.e("aaa", "onProgressUpdate: "+path);
            int file_length;
            try {

                URL url = new URL(path);
                URLConnection urlConnection = url.openConnection();
                urlConnection.connect();
                file_length = urlConnection.getContentLength();
                File new_folder = new File("/sdcard/Pictures");
                if (!new_folder.exists()){
                    new_folder.mkdir();
                }
                input_file = new File(new_folder,"download_image.jpg");
                InputStream inputStream = new BufferedInputStream(url.openStream(),8192);
                byte[] data = new byte[1024];
                int total = 0;
                int count;
                OutputStream outputStream = new FileOutputStream(input_file);
                while ((count=inputStream.read(data)) != -1){
                    total += count;
                    outputStream.write(data,0,count);
                    int progress = total *1000/file_length;
                    publishProgress(progress);
                }
                inputStream.close();
                outputStream.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Download Complete...";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.hide();
            tvStatus.setText("Complete!!");
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            String path = "/sdcard/Pictures/download_image.jpg";
            imgDownload.setImageDrawable(Drawable.createFromPath(path));
            Log.e("llllll", "onPostExecute: " );
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(input_file)));
        }
    }
}