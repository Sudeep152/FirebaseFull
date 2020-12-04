package com.sudeep.firebasechapterone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class FirebaseWritingData_Using_Hashmap extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private  EditText phone;
   private Button saveBtn;
   private Button ReadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_writing_data__using__hashmap);

        name=findViewById(R.id.edt1);
        email=findViewById(R.id.edt2);
        phone=findViewById(R.id.edt3);
        saveBtn=findViewById(R.id.saveBtn);
        ReadBtn=findViewById(R.id.ReadBtn);





        HashMap<String,Object>map=new HashMap<>();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!EmptyText(name)){

                }else if(!EmptyText(email)){

                }else if(!EmptyText(phone)){

                }




                else {

                    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference=firebaseDatabase.getReference().child("Donor").push();


                    map.put("name", name.getText().toString());


                    map.put("email", email.getText().toString());
                    map.put("phone", phone.getText().toString());

                    name.setText("");
                    phone.setText("");
                    email.setText("");

                    databaseReference.setValue(map);
                }
            }
        });

        ReadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                DatabaseReference databaseReference= (DatabaseReference) firebaseDatabase.getReference().child("Donor").child("-MNgx4BAjWlGje0hNhPP")
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String msg= "name:"+snapshot.child("name").getValue(String.class)+
                                        "phone:"+snapshot.child("phone").getValue(String.class)
                                        +"email:"+snapshot.child("email").getValue(String.class);

                                Toast.makeText(FirebaseWritingData_Using_Hashmap.this, ""+msg, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


            }
        });


    }
    public boolean EmptyText(EditText editText){
        if(editText.getText().toString().equals("")){
            Toast.makeText(this, ""+editText.getHint().toString(), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}