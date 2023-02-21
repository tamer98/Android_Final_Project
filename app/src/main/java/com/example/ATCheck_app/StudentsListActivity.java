package com.example.ATCheck_app;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentsListActivity extends AppCompatActivity {


    private RecyclerView recyclerView;

    private Button mButtonClearFeedback;
    private StudentAdapter studentAdapter;
    private ArrayList<Student> studentList;

    private FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentList = new ArrayList<>();
        studentAdapter = new StudentAdapter(studentList);
        recyclerView.setAdapter(studentAdapter);
        mButtonClearFeedback = findViewById(R.id.clear_feedback);

        ImageView backButton = findViewById(R.id.back_button_student_list);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentsListActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference userRef = db.collection("Users").document(userId);


        db.collection("Users").document(userId).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        User user = documentSnapshot.toObject(User.class);
                        if (user != null) {
                            List<Student> students = user.getStudents();
                            // Do something with the list of students
                            studentList.addAll(user.getStudents());
                            studentAdapter.notifyDataSetChanged();
                        }

                    }
                });

        mButtonClearFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(Student s : studentList){
                    s.setSocial(0);
                    s.setBehavior(0);
                    s.setInvolvement(0);
                }
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

// Update the students array in Firestore
                Map<String, Object> updates = new HashMap<>();
                updates.put("students", studentList);

                db.collection("Users").document(userId)
                        .update(updates)
                        .addOnSuccessListener(aVoid -> {
                            // Successfully updated the students array
                            studentAdapter.notifyDataSetChanged();
                        })
                        .addOnFailureListener(e -> {
                            // Failed to update the students array
                        });

            }
        });


    }
}
