package br.com.douglaspac.ichat.callback;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import org.greenrobot.eventbus.EventBus;

import br.com.douglaspac.ichat.activity.MainActivity;
import br.com.douglaspac.ichat.event.FailureEvent;
import br.com.douglaspac.ichat.event.MensagemEvento;
import br.com.douglaspac.ichat.modelo.Mensagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by douglasmartins on 31/05/17.
 */

public class OuvirMensagemCallback implements Callback<Mensagem>
{
    private final Context context;
    private EventBus eventBus;
    public OuvirMensagemCallback(EventBus bus, Context context)
    {
        this.context = context;
        eventBus = bus;
    }

    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response)
    {
        if(response.isSuccessful())
        {
            Mensagem mensagem = response.body();

            eventBus.post(new MensagemEvento(mensagem));
        }

    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable t)
    {
        eventBus.post(new FailureEvent());
    }
}
