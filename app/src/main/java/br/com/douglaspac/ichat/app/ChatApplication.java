package br.com.douglaspac.ichat.app;

import android.app.Application;

import br.com.douglaspac.ichat.component.ChatComponent;
import br.com.douglaspac.ichat.component.DaggerChatComponent;

/**
 * Created by douglasmartins on 31/05/17.
 */

public class ChatApplication extends Application
{
    private ChatComponent component;

    public void onCreate()
    {
        component =  DaggerChatComponent.builder().build();
    }

    public ChatComponent getComponent()
    {
        return component;
    }
}
