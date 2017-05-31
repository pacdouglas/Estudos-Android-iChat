package br.com.douglaspac.ichat.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import br.com.douglaspac.ichat.R;
import br.com.douglaspac.ichat.adapter.MensagemAdapter;
import br.com.douglaspac.ichat.modelo.Mensagem;


public class MainActivity extends AppCompatActivity
{

    private int idDoCliente = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listaDeMensagens = (ListView) findViewById(R.id.lv_mensagens);

        List<Mensagem> mensagens = Arrays.asList(new Mensagem(1, "Alunos de Android"),
                                                    new Mensagem(2, "Oi"));
        MensagemAdapter adapter = new MensagemAdapter(idDoCliente, mensagens, this);

        listaDeMensagens.setAdapter(adapter);
    }
}
