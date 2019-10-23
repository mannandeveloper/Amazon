package com.example.amazon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PaymentsActivity extends AppCompatActivity
{
    private Button button;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);

        button = findViewById(R.id.buy_button);
        progressDialog = new ProgressDialog(this);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                orderConfirmMessage();
                startActivity(new Intent(PaymentsActivity.this, MainActivity.class));
            }
        });
    }

    private void orderConfirmMessage()
    {
        progressDialog.setTitle("Order Confirm");
        progressDialog.setMessage("Thank you for shopping! Your order will be deliver soon");
        progressDialog.show();
    }
}
