package br.com.douglaspac.ichat.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.douglaspac.ichat.R;
import br.com.douglaspac.ichat.adapter.MensagemAdapter;
import br.com.douglaspac.ichat.app.ChatApplication;
import br.com.douglaspac.ichat.callback.EnviarMensagemCallback;
import br.com.douglaspac.ichat.callback.OuvirMensagemCallback;
import br.com.douglaspac.ichat.component.ChatComponent;
import br.com.douglaspac.ichat.modelo.Mensagem;
import br.com.douglaspac.ichat.service.ChatService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity
{

    private int idDoCliente = 1;

    @BindView(R.id.et_texto)
    EditText editText;
    @BindView(R.id.lv_mensagens)
    ListView listaDeMensagens;
    @BindView(R.id.btn_enviar)
    Button button;
    @BindView(R.id.iv_avatar_usuario)
    ImageView avatar;

    private List<Mensagem> mensagens;

    @Inject
    ChatService chatService;
    @Inject
    Picasso picasso;

    private ChatComponent component;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChatApplication app = (ChatApplication) getApplication();
        component = app.getComponent();
        component.inject(this);

        ButterKnife.bind(this);
        picasso.with(this).load("http://api.adorable.io/avatars/285/"+idDoCliente+".png").into(avatar);

        mensagens = new ArrayList<>();
        MensagemAdapter adapter = new MensagemAdapter(idDoCliente, mensagens, this);
        listaDeMensagens.setAdapter(adapter);

        ouvirMensagem();
    }

    @OnClick(R.id.btn_enviar)
    public void enviarMensagem()
    {
        chatService.enviar(new Mensagem(idDoCliente, editText.getText().toString()))
                .enqueue(new EnviarMensagemCallback());
    }

    public void colocarNaLista(Mensagem mensagem)
    {
        mensagens.add(mensagem);
        MensagemAdapter mensagemAdapter = new MensagemAdapter(idDoCliente, mensagens, this);
        listaDeMensagens.setAdapter(mensagemAdapter);
        ouvirMensagem();

    }

    public void ouvirMensagem()
    {
        chatService.ouvirMensagem().enqueue(new OuvirMensagemCallback(this));
    }
}
