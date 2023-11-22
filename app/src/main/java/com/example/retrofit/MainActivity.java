package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.retrofit.Model.CEP;
import com.example.retrofit.Service.CEPService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button btData;
    private Button btnCep;
    private EditText txtCep;
    private TextView txtresultado;
    private Retrofit retrofit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btData = findViewById(R.id.btdata);
        txtresultado = findViewById(R.id.txtResultado);
        btnCep = findViewById(R.id.btncep);
        txtCep =findViewById(R.id.txtcep);

        retrofit = new Retrofit.Builder().baseUrl("https://viacep.com.br/ws/").addConverterFactory(GsonConverterFactory.create()).build();

        btData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecuperaRetrofit();
            }
        });
        btnCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecuperaDadosByCEP(txtCep.getText().toString());

            }
        });

    }
    public void RecuperaDadosByCEP(String num_cep){
        CEPService cepService = retrofit.create(CEPService.class);
        Call<CEP> callcep = cepService.RecuperabyCep(num_cep);
        callcep.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                if (response.isSuccessful()){
                    CEP cep = response.body();
                    txtresultado.setText("Recupera por cep: " +cep.getCep() +"\nLogradouro: " +cep.getLogradouro() +"\nLocalidade: " +cep.getLocalidade()+"\nBairro: " +cep.getBairro());

                }
            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {

            }
        });

    }

    private void RecuperaRetrofit(){
        CEPService service = retrofit.create(CEPService.class);
        Call<CEP> callcep= service.RecuperaCEP();

        callcep.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                if (response.isSuccessful()){
                    CEP cep = response.body();
                    txtresultado.setText("cep: " +cep.getCep() +"\nLogradouro: " +cep.getLogradouro() +"\nLocalidade: " +cep.getLocalidade()+"\nBairro: " +cep.getBairro());
                }
            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {

            }
        });

    }
}