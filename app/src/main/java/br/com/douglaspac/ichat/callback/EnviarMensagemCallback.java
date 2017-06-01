package br.com.douglaspac.ichat.callback;

import android.view.View;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by douglasmartins on 31/05/17.
 */

public class EnviarMensagemCallback implements Callback<Void>
{
    public EnviarMensagemCallback()
    {
    }

    @Override
    public void onResponse(Call<Void> call, Response<Void> response)
    {

    }

    @Override
    public void onFailure(Call<Void> call, Throwable t)
    {

    }
}
