package com.example.ATCheck_app;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddStudentsActivity extends AppCompatActivity {

    private  User userProfile;
    private EditText studentFirstName;
    private EditText studentLastname;
    private EditText studentBirthDate;
    private EditText studentPhoneNumber;

    private Button addStudentBtn;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_students);

        studentFirstName =findViewById(R.id.get_student_firstname);
        studentLastname =findViewById(R.id.get_student_lastname);
        studentBirthDate =findViewById(R.id.get_student_birthdate);
        studentPhoneNumber =findViewById(R.id.get_student_phone_number);
        addStudentBtn = findViewById(R.id.add_student);

        ImageView backButton = findViewById(R.id.back_button_add_students);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddStudentsActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        addStudentBtn.setOnClickListener(v -> {
            // Create a new student object
            Student student = new Student(
                    studentFirstName.getText().toString().trim(),
                    studentLastname.getText().toString().trim(),
                    studentBirthDate.getText().toString().trim(),
                    studentPhoneNumber.getText().toString().trim());

            // Get the current user object from Firestore
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference userRef = db.collection("Users").document(currentUser.getUid());

            // Retrieve the "fieldName" field from the document
            // Create an array of Student objects to add to the current user's students array
            ArrayList<Student> newStudents = new ArrayList<>();
            newStudents.add(student);

// Update the current user's students array in Firestore using arrayUnion
            userRef.update("students", FieldValue.arrayUnion(newStudents.toArray(new Student[0])))
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "Students added successfully");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e(TAG, "Error adding students", e);
                        }
                    });

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            // Clear the text views
            studentFirstName.setText("");
            studentLastname.setText("");
            studentBirthDate.setText("");
            studentPhoneNumber.setText("");

            // Hide the keyboard
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        });
    }
}