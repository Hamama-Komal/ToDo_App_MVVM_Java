package com.example.todoappmvvmjava;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.todoappmvvmjava.databinding.ActivityAddNoteBinding;

public class AddNoteActivity extends AppCompatActivity {

    ActivityAddNoteBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAddNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String type = getIntent().getStringExtra("type");


        if(type.equals("update")){

            binding.toolbar.setTitle("Update Note");
            binding.buttonAdd.setText("Update");
            binding.editTextTitle.setText(getIntent().getStringExtra("title"));
            binding.editTextTDescription.setText(getIntent().getStringExtra("body"));
            int id = getIntent().getIntExtra("id", 0);

            binding.buttonAdd.setOnClickListener(view -> {
                Intent intent = new Intent();
                intent.putExtra("title", binding.editTextTitle.getText().toString());
                intent.putExtra("body", binding.editTextTDescription.getText().toString());
                intent.putExtra("id", id);
                setResult(RESULT_OK, intent);
                finish();
            });
        }
        else {
            binding.toolbar.setTitle("Add New Note");
            binding.buttonAdd.setOnClickListener(view -> {
                Intent intent = new Intent();
                intent.putExtra("title", binding.editTextTitle.getText().toString());
                intent.putExtra("body", binding.editTextTDescription.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
    }
}