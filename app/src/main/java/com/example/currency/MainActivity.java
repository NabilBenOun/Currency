package com.example.currency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.currency.Adapter.CustomAdapter;
import com.example.currency.model.DataModel;
import com.example.currency.model.Utils;
import com.example.currency.model.currenciesDatail;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<DataModel> dataModels;
    ListView listView;
    ProgressDialog progressDoalog;
    private static CustomAdapter adapter;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.list_item);
        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();
        dataModels= new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        dataModels.clear();
        jsonParse();




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DataModel dataModel= dataModels.get(position);

                Snackbar.make(view, dataModel.getName()+"\n"+dataModel.getValue()+" API: "+dataModel.getCode(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
     //   getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
    private void jsonParse() {
        String url = "https://api.currencies.zone/v1/full/EGP/json?key=1861|9v06qGG_6*syeV35SwS8ksdRFF^c2MNV";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDoalog.dismiss();
                try {

                    JSONArray jsonArray = response.getJSONObject("result").getJSONArray("conversion");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject employee = jsonArray.getJSONObject(i);
                        String code = employee.getString("to");
                        double rate = employee.getDouble("rate");
                        String name ="!!";
                        String jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "currenciesWithDetail.json");
                        try{
                            JSONObject emp=(new JSONObject(jsonFileString)).getJSONObject(code);
                            String empname=emp.getString("name");
                            name=empname;

                        }catch (Exception e) {e.printStackTrace();}
                           if(name!="!!") {
                               int resId = getApplicationContext().getResources().getIdentifier(code.toLowerCase(), "drawable", getApplicationContext().getPackageName());
                               dataModels.add(new DataModel(resId, "" + name, "" + code, rate));
                           }


                    }
                    adapter= new CustomAdapter(dataModels,getApplicationContext());

                    listView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("hhh",e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();

                error.printStackTrace();
            }
        });

        requestQueue.add(request);
    }


}
