package com.example.myapplicationwebcontent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

//====================================================================================================
//код для загрузки web страниц с нета

public class MainActivity extends AppCompatActivity {

    private String google = "https://www.google.ru/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task = new DownloadTask();
        try {
           String result =  task.execute(google).get();
           Log.i("URL", result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class DownloadTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
           StringBuilder result = new StringBuilder();
            URL url = null;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = bufferedReader.readLine();
                while (line != null){
                    result.append(line);
                    line = bufferedReader.readLine();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null){
                    urlConnection.disconnect();
                }
            }
            return result.toString();
        }
    }
}
//=================================================================================================
//код для получения картинок с интернета

//public class MainActivity extends AppCompatActivity {
//
//    private ImageView imageView;
//    private String url = "https://avatars.mds.yandex.net/get-pdb/2821631/820e19d2-7a17-41a9-8ae4-f7b221cca8d5/s1200";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        imageView = findViewById(R.id.imageView);
//    }
//
//
//    public void onClickDownloadImage(View view) {
//        DownloadImageTask task = new DownloadImageTask();
//        Bitmap bitmap = null;
//        try {
//            bitmap = task.execute(url).get();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        imageView.setImageBitmap(bitmap);
//    }
//
//    private static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
//        @Override
//        protected Bitmap doInBackground(String... strings) {
//            URL url = null;
//            HttpURLConnection urlConnection = null;
//            try {
//                url = new URL(strings[0]);
//                urlConnection = (HttpURLConnection) url.openConnection();
//                InputStream inputStream = urlConnection.getInputStream();
//                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                return bitmap;
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if (urlConnection != null) {
//                    urlConnection.disconnect();
//                }
//            }
//            return null;
//        }
//    }
//}
//====================================================================================================
//код для работы со строками, нужен для дальнейших разработок

//        String nameString = "Arnold, Britney, Abama, Trump, Vasya";
//        String[] names = nameString.split(", ");
//        for (String name: names){
//            Log.i("MyName", name);
//        }
//        String geometry = "Геометрия";
//        String meter = geometry.substring(3, 7);
//        Log.i("MyName", meter);
//    }
//        String river = "MississipiMississipi";
//        Pattern pattern = Pattern.compile("Mi(.*?)pi");
//        Matcher matcher = pattern.matcher(river);
//        while (matcher.find()) {
//            Log.i("MaName", matcher.group(1));
//        }