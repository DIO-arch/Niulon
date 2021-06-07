package com.example.fixedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserInfo extends AppCompatActivity {
    Intent i = getIntent();
    long _id = i.getExtras().getInt("_id");
    Dal dal = new Dal(this);
    TextView infoname, infopass, infouser;
    Button save, delete, reset;
    Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        infoname = findViewById(R.id.InfoName);
        infopass = findViewById(R.id.InfoPassword);
        infouser = findViewById(R.id.InfoUser);

        save = findViewById(R.id.SaveData);
        delete = findViewById(R.id.deleteData);
        reset = findViewById(R.id.ResetData);

        infoname.setText(dal.getName(_id));
        infopass.setText(dal.getPassword(_id));
        infouser.setText(dal.getUserName(_id));

    }
    public void onClick(View view) {
        if(view.getId()==R.id.UserToClocks)
            i = new Intent(this, ClocksPage.class);
        else if(view.getId()==R.id.to_Meetings_Display)
            i = new Intent(this, MeetingsList.class);
        startActivity(i);
    }
    public void DeleteUser(View view){
        dal.deleteUser(_id);
    }
    public Boolean SaveUser(View view){
        users.setName(infoname.getText().toString());
        users.setUsername(infouser.getText().toString());
        users.setPassword(infopass.getText().toString());
        Boolean bool = dal.updateUser(_id, users);
        return bool;
    }
    public void Reset(View view){
        infoname.setText(dal.getName(_id));
        infopass.setText(dal.getPassword(_id));
        infouser.setText(dal.getUserName(_id));
    }
    public void UpdateUser(View view) {

    }
}