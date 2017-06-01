package br.com.douglaspac.ichat.component;

import br.com.douglaspac.ichat.activity.MainActivity;
import br.com.douglaspac.ichat.module.ChatModule;
import dagger.Component;

/**
 * Created by douglasmartins on 31/05/17.
 */

@Component(modules = ChatModule.class)
public interface ChatComponent
{
    void inject(MainActivity mainActivity);
}
