package br.com.douglaspac.ichat.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.douglaspac.ichat.R;
import br.com.douglaspac.ichat.adapter.MensagemAdapter;
import br.com.douglaspac.ichat.modelo.Mensagem;
import br.com.douglaspac.ichat.service.ChatService;


public class MainActivity extends AppCompatActivity
{

    private int idDoCliente = 1;
    private ListView listaDeMensagens;
    private  List<Mensagem> mensagens;
    ChatService chatService;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaDeMensagens = (ListView) findViewById(R.id.lv_mensagens);
        mensagens = new ArrayList<>();

        MensagemAdapter adapter = new MensagemAdapter(idDoCliente, mensagens, this);
        listaDeMensagens.setAdapter(adapter);

        final EditText editText = (EditText) findViewById(R.id.et_texto);
        Button button = (Button) findViewById(R.id.btn_enviar);
        chatService = new ChatService(this);
        chatService.ouvirMensagem();

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                chatService.enviar(new Mensagem(idDoCliente, editText.getText().toString()));
            }
        });
    }

    public void colocarNaLista(Mensagem mensagem)
    {
        mensagens.add(mensagem);
        MensagemAdapter mensagemAdapter = new MensagemAdapter(idDoCliente, mensagens, this);
        listaDeMensagens.setAdapter(mensagemAdapter);
        chatService.ouvirMensagem();
    }
}
