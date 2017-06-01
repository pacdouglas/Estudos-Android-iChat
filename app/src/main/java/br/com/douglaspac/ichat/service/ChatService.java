package br.com.douglaspac.ichat.service;

import br.com.douglaspac.ichat.modelo.Mensagem;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by douglasmartins on 30/05/17.
 */

public interface ChatService
{
    @POST("polling")
    Call<Void> enviar(@Body Mensagem mensagem);

    @GET("polling")
    Call<Mensagem> ouvirMensagem();
}
