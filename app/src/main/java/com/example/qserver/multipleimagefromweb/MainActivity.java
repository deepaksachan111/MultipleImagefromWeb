package com.example.qserver.multipleimagefromweb;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView listView;
    ArrayList<String> imageURL = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

       /*  String[] imageURLArray = new String[]{
                "http://farm8.staticflickr.com/7315/9046944633_881f24c4fa_s.jpg",
                "http://farm4.staticflickr.com/3777/9049174610_bf51be8a07_s.jpg",
                "http://farm8.staticflickr.com/7324/9046946887_d96a28376c_s.jpg",
                "http://farm3.staticflickr.com/2828/9046946983_923887b17d_s.jpg",
                "http://farm4.staticflickr.com/3810/9046947167_3a51fffa0b_s.jpg",
                "http://farm4.staticflickr.com/3773/9049175264_b0ea30fa75_s.jpg",
                "http://farm4.staticflickr.com/3781/9046945893_f27db35c7e_s.jpg",
                "http://farm6.staticflickr.com/5344/9049177018_4621cb63db_s.jpg",
                "http://farm8.staticflickr.com/7307/9046947621_67e0394f7b_s.jpg",
                "http://farm6.staticflickr.com/5457/9046948185_3be564ac10_s.jpg",
                "http://farm4.staticflickr.com/3752/9046946459_a41fbfe614_s.jpg",
                "http://farm8.staticflickr.com/7403/9046946715_85f13b91e5_s.jpg",
                "http://farm8.staticflickr.com/7315/9046944633_881f24c4fa_s.jpg",
                "http://farm4.staticflickr.com/3777/9049174610_bf51be8a07_s.jpg",
                "http://farm8.staticflickr.com/7324/9046946887_d96a28376c_s.jpg",
                "http://farm3.staticflickr.com/2828/9046946983_923887b17d_s.jpg",
                "http://farm4.staticflickr.com/3810/9046947167_3a51fffa0b_s.jpg",
                "http://farm4.staticflickr.com/3773/9049175264_b0ea30fa75_s.jpg",
                "http://farm4.staticflickr.com/3781/9046945893_f27db35c7e_s.jpg",
                "http://farm6.staticflickr.com/5344/9049177018_4621cb63db_s.jpg",
                "http://farm8.staticflickr.com/7307/9046947621_67e0394f7b_s.jpg",
                "http://farm6.staticflickr.com/5457/9046948185_3be564ac10_s.jpg",
                "http://farm4.staticflickr.com/3752/9046946459_a41fbfe614_s.jpg",
                "http://farm8.staticflickr.com/7403/9046946715_85f13b91e5_s.jpg"};*/



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      listView = (ListView)this.findViewById(R.id.listViewss);

        String url = "http://wptrafficanalyzer.in/p/demo1/first.php/countries/";

        new GetdataServer().execute(url);
      //  ImageAdapter imageAdapter = new ImageAdapter(this, R.layout.imageitem, imageURLArray);
      //  listView.setAdapter(imageAdapter);
    }


    private class  GetdataServer extends AsyncTask<String,Void,String>{

   String response ;
        @Override
        protected String doInBackground(String... params) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httppost = new HttpGet(params[0]);

            try {
                HttpResponse httpResponse = httpClient.execute(httppost);
                HttpEntity httpEntity = httpResponse.getEntity();
                response = EntityUtils.toString(httpEntity);
                Log.v("Source Response ", response);



            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(" Exception is caught here ......." + e.toString());
            }
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            downloadUrl(s);
        }
    }
    private void downloadUrl(String strUrl)  {

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(strUrl);
            JSONArray jsonArray = jsonObject.getJSONArray("countries");

            for( int j= 0; j<jsonArray.length(); j++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(j);
                String countryname = jsonObject1.getString("countryname");
                Log.v("EmpName", countryname);
                String flag = jsonObject1.getString("flag");

                imageURL.add(flag);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
          ImageAdapter imageAdapter = new ImageAdapter(this, R.layout.imageitem, imageURL);
          listView.setAdapter(imageAdapter);

    }
}
