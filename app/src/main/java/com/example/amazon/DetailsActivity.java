package com.example.amazon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.amazon.MainActivity.EXTRA_NAME;
import static com.example.amazon.MainActivity.EXTRA_PRICE;
import static com.example.amazon.MainActivity.EXTRA_URL;

public class DetailsActivity extends AppCompatActivity
{
    private Button cartButton;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        cartButton = findViewById(R.id.add_to_card);
        cartButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(DetailsActivity.this, PaymentsActivity.class));
            }
        });

        Intent intent = getIntent();
        String imageURL = intent.getStringExtra(EXTRA_URL);
        String name = intent.getStringExtra(EXTRA_NAME);
        int price = intent.getIntExtra(EXTRA_PRICE, 0);

        ImageView image = findViewById(R.id.shoe_image);
        TextView productName = findViewById(R.id.shoe_name);
        TextView productPrice = findViewById(R.id.shoe_price);

        Picasso.get().load(imageURL).fit().centerInside().into(image);
        productName.setText(name);
        productPrice.setText(String.valueOf("Price : "+ price));


    }
}
