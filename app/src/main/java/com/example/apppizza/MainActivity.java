package com.example.apppizza;
import android.view.MenuItem; // Agrega esta línea
import androidx.appcompat.widget.Toolbar; // Importar la clase Toolbar

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivityLifecycle";

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Log para indicar que la actividad ha sido creada
        Log.d(TAG, "La actividad ha sido creada.");

        // Inicializar la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Habilitar el botón de regreso
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Regresar"); // Título dinámico

        // Mantener el comportamiento EdgeToEdge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configurar el formulario de login
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        // Configurar el RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Datos de ejemplo
        userList = new ArrayList<>();
        userList.add(new User("Carlos", R.drawable.ic_profile1, 10));
        userList.add(new User("Ana", R.drawable.ic_profile2, 15));
        userList.add(new User("Luis", R.drawable.ic_profile3, 20));

        userAdapter = new UserAdapter(this, userList);
        recyclerView.setAdapter(userAdapter);

        // Configurar el botón de inicio de sesión
        loginButton.setOnClickListener(view -> validateLogin());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: // ID del botón de regreso
                onBackPressed(); // Regresar a la actividad anterior
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        // Log para indicar que la actividad ha sido iniciada
        Log.d(TAG, "La actividad ha sido iniciada.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Log para indicar que la actividad ha sido detenida
        Log.d(TAG, "La actividad ha sido detenida.");
    }

    private void validateLogin() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
            // Aquí puedes ocultar el formulario de login y mostrar el RecyclerView si lo deseas
            emailEditText.setVisibility(View.GONE);
            passwordEditText.setVisibility(View.GONE);
            loginButton.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}
