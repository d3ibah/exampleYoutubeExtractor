package com.example.videoview;

import android.os.Bundle;

import com.example.videoview.db.DataBase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ThirdActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VideoListAdapter adapter;
    private DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        dataBase = App.getInstance()
                      .getDatabase();

        recyclerView = findViewById(R.id.recycler_view);

        adapter = new VideoListAdapter(dataBase.getVideosDao()
                                               .getVideo());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
