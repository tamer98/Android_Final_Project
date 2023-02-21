package com.example.ATCheck_app;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private ArrayList<Student> mStudentList;

    public StudentAdapter(ArrayList<Student> studentList) {
        mStudentList = studentList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        StudentViewHolder viewHolder = new StudentViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Student currentItem = mStudentList.get(position);

        holder.mTextViewFirstName.setText(currentItem.getFirstName());
        holder.mTextViewLastName.setText(currentItem.getLastName());
        holder.mTextViewPhoneNumber.setText(currentItem.getPhoneNumber());
        holder.mSeekBarInvolvement.setProgress(currentItem.getInvolvement());
        holder.mSeekBarBehavior.setProgress(currentItem.getBehavior());
        holder.mSeekBarSocial.setProgress(currentItem.getSocial());
        holder.mTextViewSocial.setText(currentItem.getSocial()+"");
        holder.mTextViewBehavior.setText(currentItem.getBehavior()+"");
        holder.mTextViewInvolvement.setText(currentItem.getInvolvement()+"");
        holder.mButtonRemoveStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStudentList.remove(mStudentList.get(position));
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

// Update the students array in Firestore
                Map<String, Object> updates = new HashMap<>();
                updates.put("students", mStudentList);

                db.collection("Users").document(userId)
                        .update(updates)
                        .addOnSuccessListener(aVoid -> {
                            // Successfully updated the students array
                            notifyDataSetChanged();
                        })
                        .addOnFailureListener(e -> {
                            // Failed to update the students array
                        });
            }
        });

        holder.mSeekBarInvolvement.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mStudentList.get(position).setInvolvement(seekBar.getProgress());
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

// Update the students array in Firestore
                Map<String, Object> updates = new HashMap<>();
                updates.put("students", mStudentList);

                db.collection("Users").document(userId)
                        .update(updates)
                        .addOnSuccessListener(aVoid -> {
                            // Successfully updated the students array
                            notifyDataSetChanged();
                        })
                        .addOnFailureListener(e -> {
                            // Failed to update the students array
                        });

            }
        });
        holder.mSeekBarBehavior.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mStudentList.get(position).setBehavior(seekBar.getProgress());
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

// Update the students array in Firestore
                Map<String, Object> updates = new HashMap<>();
                updates.put("students", mStudentList);

                db.collection("Users").document(userId)
                        .update(updates)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Successfully updated the students array
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Failed to update the students array
                            }
                        });
            }
        });
        holder.mSeekBarSocial.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mStudentList.get(position).setSocial(seekBar.getProgress());
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

// Update the students array in Firestore
                Map<String, Object> updates = new HashMap<>();
                updates.put("students", mStudentList);

                db.collection("Users").document(userId)
                        .update(updates)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Successfully updated the students array
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Failed to update the students array
                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStudentList.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewFirstName,mTextViewLastName, mTextViewPhoneNumber,mTextViewBirthdate,
                mTextViewSocial,mTextViewBehavior,mTextViewInvolvement ;
        public SeekBar mSeekBarInvolvement, mSeekBarBehavior,mSeekBarSocial;
        public Button mButtonRemoveStudent;

        public StudentViewHolder(View itemView) {
            super(itemView);
            mTextViewFirstName = itemView.findViewById(R.id.text_view_first_name);
            mTextViewLastName = itemView.findViewById(R.id.text_view_last_name);
            mTextViewPhoneNumber = itemView.findViewById(R.id.text_view_phone);
            mTextViewBirthdate = itemView.findViewById(R.id.text_view_birthdate);
            mSeekBarInvolvement = itemView.findViewById(R.id.seek_bar_involvement);
            mSeekBarBehavior = itemView.findViewById(R.id.seek_bar_behavior);
            mSeekBarSocial = itemView.findViewById(R.id.seek_bar_social);
            mTextViewInvolvement = itemView.findViewById(R.id.involvement_value);
            mTextViewBehavior = itemView.findViewById(R.id.behavior_value);
            mTextViewSocial = itemView.findViewById(R.id.social_value);
            mButtonRemoveStudent = itemView.findViewById(R.id.remove_student_button);
        }
    }
}
