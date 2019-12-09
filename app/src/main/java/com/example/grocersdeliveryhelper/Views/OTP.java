package com.example.grocersdeliveryhelper.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.grocersdeliveryhelper.R;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;

public class OTP extends AppCompatActivity {
    private ProgressBar progressBar;
    EditText txtContact;
    Button btnProceed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        progressBar = findViewById(R.id.spin_kit);
        Sprite doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);
        btnProceed=findViewById(R.id.btn_proceed);
        txtContact=findViewById(R.id.editPhone);
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(OTP.this,ChangePassword.class);
                intent.putExtra("contact",txtContact.getText().toString());
                startActivity(intent);
            }
        });
    }
}
