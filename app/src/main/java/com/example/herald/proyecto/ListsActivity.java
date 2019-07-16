package com.example.herald.proyecto;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ListsActivity extends AppCompatActivity {

    private ArrayList<String> list_data;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
        list_data = new ArrayList<>();
        loadData();
    }

    private void loadData() {
        ListView list = findViewById(R.id.list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_data);
        list.setAdapter(adapter);
        AsyncHttpClient client  = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        list_data.clear();
        client.get(Utils.LIST_SERVICE, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                for(int i = 0; i < response.length(); i++){
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        list_data.add(obj.getString("name"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
            }
        });
    }
}