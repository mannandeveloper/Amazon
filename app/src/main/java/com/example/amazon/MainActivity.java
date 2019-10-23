package com.example.amazon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AmazonAdapter.OnItemClickListener
{
    public static final String EXTRA_URL = "image";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_PRICE = "price";

    private RecyclerView mRecyclerView;
    private AmazonAdapter mAdapter;
    private ArrayList<Content> mContentArrayList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));

        mContentArrayList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);

        parseJSON();
    }

    private void parseJSON()
    {
        String JSON_URL = "https://api.myjson.com/bins/o5tis";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            JSONArray jsonArray = response.getJSONArray("Flip Kart");

                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String images = jsonObject.getString("image");
                                String names = jsonObject.getString("name");
                                int prices = jsonObject.getInt("price");

                                mContentArrayList.add(new Content(images,names, prices));
                            }

                            mAdapter = new AmazonAdapter(MainActivity.this,mContentArrayList);
                            mRecyclerView.setAdapter(mAdapter);
                            mAdapter.setOnItemClickListener(MainActivity.this);
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        error.printStackTrace();
                    }
                });

        mRequestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onItemClick(int position)
    {
        Intent intent = new Intent(this, DetailsActivity.class);
        Content clickedItem = mContentArrayList.get(position);

        intent.putExtra(EXTRA_URL,clickedItem.getImages());
        intent.putExtra(EXTRA_NAME,clickedItem.getNames());
        intent.putExtra(EXTRA_PRICE,clickedItem.getPrices());

        startActivity(intent);
    }
}
