package com.example.ATCheck_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {


    private Button addStudent;
    private Button studentList;
    private Button summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //set the elements from the view.

        addStudent = findViewById(R.id.add_students);
        studentList = findViewById(R.id.students_list);
        summary = findViewById(R.id.summary);

        addStudent.setOnClickListener(view -> {
            Intent intent=new Intent(HomeActivity.this, AddStudentsActivity.class);
            startActivity(intent);

        });
        studentList.setOnClickListener(view -> {
            Intent intent=new Intent(HomeActivity.this, StudentsListActivity.class);
            startActivity(intent);

        });
        summary.setOnClickListener(view -> {
            Intent intent=new Intent(HomeActivity.this, SummaryActivity.class);
            startActivity(intent);

        });
    }
}