package com.inireksa.tikreguler;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.List;

public class Login extends AppCompatActivity {

    private final AppCompatActivity activity = Login.this;

    private RelativeLayout mrelativeLayout;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextPassword;

    private Button ButtonLogin;

    private InputValidation inputValidation;
    private DatabaseHandler databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        DatabaseHandler db = new DatabaseHandler(this);

        /**
         * CRUD Operations
         * */
        // Inserting Mahasiswas
        Log.d("Insert: ", "Inserting ..");
        db.addMahasiswa(new Mahasiswa("Muhamad Angga Reksa", "15371016"));
        db.addMahasiswa(new Mahasiswa("Nurjaman", "15371029"));
        db.addMahasiswa(new Mahasiswa("Diki Dermawan", "15371038"));
        db.addMahasiswa(new Mahasiswa("Angga Ganda Winata", "15371051"));
        db.addMahasiswa(new Mahasiswa("Mupti Maulana Bahri", "15371065"));
        db.addMahasiswa(new Mahasiswa("Aldy Putra Indra", "15371068"));
        db.addMahasiswa(new Mahasiswa("Muhamad Andita Bagas", "15371067"));
        db.addMahasiswa(new Mahasiswa("Mas Muhammad Derian", "15371017"));

        // Reading all Mahasiswas
        Log.d("Reading: ", "Reading all Mahasiswas..");
        List<Mahasiswa> Mahasiswas = db.getAllMahasiswa();

        for (Mahasiswa cn : Mahasiswas) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getnpm();
            // Writing Mahasiswas to log
            Log.d("Name: ", log);
        }

        initView();
        initListener();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initView(){
        mrelativeLayout = (RelativeLayout) findViewById(R.id.relativeLogin);

        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        ButtonLogin = (Button) findViewById(R.id.ButtonLogin);
    }

    /**
     * This method is to initialize listeners
     */
    private void initListener(){
       ButtonLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               verifyFromSQLite();
           }
       });
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DatabaseHandler(activity);
        inputValidation = new InputValidation(activity);

    }


    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_pwd))) {
            return;
        }
        if (!inputValidation.isInputEditTextName(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_pwd))) {
            return;
        }

        if (databaseHelper.checkUser(textInputEditTextName.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {


            Intent accountsIntent = new Intent(activity, MainActivity.class);
            accountsIntent.putExtra("Name", textInputEditTextName.getText().toString().trim());
            accountsIntent.putExtra("npm", textInputEditTextPassword.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);


        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(mrelativeLayout, getString(R.string.error_valid_Name_password), Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextPassword.setText(null);
    }
}
