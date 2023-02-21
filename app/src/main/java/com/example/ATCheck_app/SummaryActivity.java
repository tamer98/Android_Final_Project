package com.example.ATCheck_app;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class SummaryActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    private TextView mostInvolvementName;
    private TextView mostInvolvementValue;
    private TextView mostSocialName;
    private TextView mostSocialValue;
    private TextView mostBehaviorName;
    private TextView mostBehaviorValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        // Initialize database reference
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Initialize views
        mostInvolvementName = findViewById(R.id.most_involvement_name);
        mostInvolvementValue = findViewById(R.id.most_involvement_value);
        mostSocialName = findViewById(R.id.most_Social_name);
        mostSocialValue = findViewById(R.id.most_social_value);
        mostBehaviorName = findViewById(R.id.most_behavior_name);
        mostBehaviorValue = findViewById(R.id.most_behavior_value);

        // Display most involvement student
        displayMostInvolvementStudent();

        // Display most social student
        displayMostSocialStudent();

        // Display most behavior student
        displayMostBehaviorStudent();

        ImageView backButton = findViewById(R.id.back_button_summary);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SummaryActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void displayMostBehaviorStudent() {
        // Find the student with the highest social score
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
                            Student mostBehaviorStudent = null;
                            int highestBehavior = 0;

                            for (Student student : students) {
                                if (student.getBehavior() > highestBehavior) {
                                    highestBehavior = student.getBehavior();
                                    mostBehaviorStudent = student;
                                }
                            }
                            if (mostBehaviorStudent != null) {
                                mostBehaviorName.setText(mostBehaviorStudent.getFirstName() + " " + mostBehaviorStudent.getLastName());
                                mostBehaviorValue.setText(String.valueOf(mostBehaviorStudent.getBehavior()));
                            }
                        }

                    }
                });
    }

    private void displayMostSocialStudent() {
        // Find the student with the highest social score
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
                            Student mostSocialStudent = null;
                            int highestSocial = 0;

                            for (Student student : students) {
                                if (student.getSocial() > highestSocial) {
                                    highestSocial = student.getSocial();
                                    mostSocialStudent = student;
                                }
                            }
                            if (mostSocialStudent != null) {
                                mostSocialName.setText(mostSocialStudent.getFirstName() + " " + mostSocialStudent.getLastName());
                                mostSocialValue.setText(String.valueOf(mostSocialStudent.getSocial()));
                            }
                        }

                    }
                });
    }

    private void displayMostInvolvementStudent() {
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
                            Student mostInvolvementStudent = null;
                            int highestInvolvement = 0;

                            for (Student student : students) {
                                if (student.getInvolvement() > highestInvolvement) {
                                    highestInvolvement = student.getInvolvement();
                                    mostInvolvementStudent = student;
                                }
                            }
                            if (mostInvolvementStudent != null) {
                                mostInvolvementName.setText(mostInvolvementStudent.getFirstName() + " " + mostInvolvementStudent.getLastName());
                                mostInvolvementValue.setText(String.valueOf(mostInvolvementStudent.getInvolvement()));
                            }
                        }

                    }
                });

    }
}
//    Student mostInvolvementStudent = null;
//    int highestInvolvement = 0;
//
//                for (Student student : studentList) {
//                        if (student.getBehavior() > highestInvolvement) {
//                        highestInvolvement = student.getBehavior();
//                        mostInvolvementStudent = student;
//                        }
//                        }
//                        if (mostInvolvementStudent != null) {
//                        mostInvolvementName.setText(mostInvolvementStudent.getFirstName() + " " + mostInvolvementStudent.getLastName());
//                        mostInvolvementValue.setText(String.valueOf(mostInvolvementStudent.getSocial()));
//                        }