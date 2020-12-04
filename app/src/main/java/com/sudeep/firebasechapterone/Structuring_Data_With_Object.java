package com.sudeep.firebasechapterone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Structuring_Data_With_Object extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText phone;
    private Button  Add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_structuring__data__with__object);

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phomne);
        Add=findViewById(R.id.doneBtn);


        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("Donor/Person");

        String UserId= databaseReference.push().getKey();

        User user=new User("Sudeep","sudeepdahiya152@gmail.com",999990);
        String newmail="shashankKumarDahiay@gmail.com";


        databaseReference.child(UserId).setValue(user);
        databaseReference.child(UserId).child("email").setValue(newmail);




        databaseReference.child(UserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                User user=snapshot.getValue(User.class);
                Toast.makeText(Structuring_Data_With_Object.this, ""+"name: "+user.getName()+ user.getEmail() +user.getPhone(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}