package br.com.douglaspac.ichat.module;

import android.app.Application;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import br.com.douglaspac.ichat.service.ChatService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by douglasmartins on 31/05/17.
 */

@Module
public class ChatModule
{
    private Application app;

    public ChatModule(Application app)
    {
        this.app = app;
    }

    @Provides
    public ChatService getChatService()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.21:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ChatService chatService = retrofit.create(ChatService.class);
        return chatService;
    }

    @Provides
    public Picasso getPicasso()
    {
        Picasso picasso = new Picasso.Builder(app).build();
        return picasso;
    }

    @Provides
    public EventBus getEventBus()
    {
        return EventBus.builder().build();
    }
}
