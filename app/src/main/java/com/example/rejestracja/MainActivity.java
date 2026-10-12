package com.example.rejestracja;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private EditText imieE, nazwiskoE, emailE, passE;
    private TextView msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        imieE = findViewById(R.id.imieE);
        nazwiskoE = findViewById(R.id.nazwiskoE);
        emailE = findViewById(R.id.emailE);
        passE = findViewById(R.id.passE);
        msg = findViewById(R.id.msg);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(v -> toFile());
        button.setOnClickListener(v -> validate());
    }



    private boolean checkValues(){
        String imie = imieE.getText().toString().trim();
        String nazwisko = nazwiskoE.getText().toString().trim();
        String email = emailE.getText().toString().trim();
        String haslo = passE.getText().toString();

        imieE.setError(null);
        nazwiskoE.setError(null);
        emailE.setError(null);
        passE.setError(null);


        if (imie.isEmpty()) {
            imieE.setError("Imię nie może być puste");
            return false;
        }
        if (nazwisko.isEmpty()) {
            nazwiskoE.setError("Nazwisko nie może być puste");
            return false;
        }
        if (email.isEmpty()) {
            emailE.setError("E-mail nie może być pusty");
            return false;
        }
        if (haslo.isEmpty()) {
            passE.setError("Hasło nie może być puste");
            return false;
        } else {
            String blad = checkPassword(haslo);
            if (blad != null) {
                passE.setError(blad);
                return true;
            }
        }
        return true;
    }

    private boolean checkEmail(){
        String email = emailE.getText().toString().trim();
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailE.setError("Niepoprawny adres e-mail");
            return false;
        }
        return true;
    }


    private void validate() {
        if (checkEmail() && checkValues()) {
            msg.setTextColor(Color.parseColor("#2E7D32"));
            imieE.setText("");
            nazwiskoE.setText("");
            emailE.setText("");
            passE.setText("");
            msg.setText("Rejestracja zakończona sukcesem!");
            toFile();
        } else {
            msg.setTextColor(Color.RED);
            msg.setText("Popraw błędy w formularzu");
        }
    }

    private String checkPassword(String haslo) {
        if (haslo.length() < 8) {
            return "Hasło musi mieć min. 8 znaków";
        }
        if (!haslo.matches(".*[A-Z].*")) {
            return "Hasło musi zawierać wielką literę";
        }
        if (!haslo.matches(".*[a-z].*")) {
            return "Hasło musi zawierać małą literę";
        }
        if (!haslo.matches(".*\\d.*")) {
            return "Hasło musi zawierać cyfrę";
        }
        if (!haslo.matches(".*[^A-Za-z0-9].*")) {
            return "Hasło musi zawierać znak specjalny";
        }
        return null;
    }

    private void toFile() {
        String imie = imieE.getText().toString().trim();
        String nazwisko = nazwiskoE.getText().toString().trim();
        String email = emailE.getText().toString().trim();

        String text = "Imie:"+imie+"\n"+"Nazwisko:"+nazwisko+"\n"+"Email:"+email+"\n";

        try (FileOutputStream fos = new FileOutputStream("dane.txt")) {

            byte[] dataBytes = text.getBytes();

            fos.write(dataBytes);

        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}