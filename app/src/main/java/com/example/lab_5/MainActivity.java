package com.example.lab_5;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView currencyList;
    ArrayList<String> listItems=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(Constants.MAIN_ACTIVITY_TAG, "menthod onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new DataLoader().execute();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(Constants.MAIN_ACTIVITY_TAG, "method run called");
                findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                displayList();
            }
        }, 1000);

    }
    public void onBtnClickLoadData(View view) {
        Log.d(Constants.MAIN_ACTIVITY_TAG, "method onBtnClickLoadData called");
        findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
        new DataLoader().execute();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(Constants.MAIN_ACTIVITY_TAG, "method run called");
                findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                displayList();
            }
        }, 1000);
    }
    public void displayList(){
        Log.d(Constants.MAIN_ACTIVITY_TAG, "method displayList called ");
        listItems=Parser.getList();
        currencyList = (ListView) findViewById(R.id.lvCurrency);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        currencyList.setAdapter(arrayAdapter);
    }
}