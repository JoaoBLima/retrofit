package com.example.retrofit.Service;

import com.example.retrofit.Model.CEP;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CEPService {

    @GET("41650010/json")
    Call<CEP> RecuperaCEP();

    @GET("{cep}/json")
    Call<CEP> RecuperabyCep(@Path("cep") String cep);

    @GET("{uf}/{localidade}/{logradouro}/json")

    Call<List<CEP>> RecuperabyParams(
            @Path("uf") String uf,
            @Path("localidade") String localidade,
            @Path("logradouro") String logradouro
    );
}
