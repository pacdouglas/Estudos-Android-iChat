package br.com.douglaspac.ichat.callback;

import br.com.douglaspac.ichat.activity.MainActivity;
import br.com.douglaspac.ichat.modelo.Mensagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by douglasmartins on 31/05/17.
 */

public class OuvirMensagemCallback implements Callback<Mensagem>
{
    private final MainActivity activity;

    public OuvirMensagemCallback(MainActivity activity)
    {
        this.activity = activity;
    }

    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response)
    {
        if(response.isSuccessful())
        {
            activity.colocarNaLista(response.body());
        }

    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable t)
    {
        activity.ouvirMensagem();
    }
}
