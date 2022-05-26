package com.socialmedia.foodsvilla;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.socialmedia.foodsvilla.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;

    ImageView increment, decrement;
    TextView display;

    int count = 1;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


         increment = findViewById(R.id.add);
         decrement = findViewById(R.id.subtract);
         display = findViewById(R.id.quantity);

         increment.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 count++;
                 display.setText(""+count);
             }
         });
         decrement.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(count <= 1) count = 1;
                 else
                     count--;
                 display.setText(""+count);
             }
         });


        final DBHelper helper = new DBHelper(this);

        if (getIntent().getIntExtra("type", 0) == 1) {
            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("desc");

            binding.detailImage.setImageResource(image);
            binding.priceLabl.setText(String.format("%d", price));
            binding.nameBox.setText(name);
            binding.detailDescription.setText(description);



            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    boolean isInserted = helper.insertOrder(binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            price,
                            image,
                            name,
                            description,
                            Integer.parseInt(binding.quantity.getText().toString()));

                    if (isInserted)
                        Toast.makeText(DetailActivity.this, "Order Successfully", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }else{

            int id = getIntent().getIntExtra("id", 0);
            Cursor cursor = helper.getOrderById(id);
            int image = cursor.getInt(4);

            binding.detailImage.setImageResource(image);
            binding.priceLabl.setText(String.format("%d", cursor.getInt(3)));

            binding.detailDescription.setText(cursor.getString(5));

            binding.nameBox.setText(cursor.getString(1));
            binding.phoneBox.setText(cursor.getString(2));
            binding.insertBtn.setText("Update Now");
            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  boolean isUpdated =   helper.updateOrder(
                            binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            Integer.parseInt(binding.priceLabl.getText().toString()),
                            image,
                    binding.detailDescription.getText().toString(),
                    binding.textView.getText().toString(),
                    1,
                    id
                    );
                  if(isUpdated)
                      Toast.makeText(DetailActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                  else
                      Toast.makeText(DetailActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}