package br.com.douglaspac.ichat.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import br.com.douglaspac.ichat.R;
import br.com.douglaspac.ichat.modelo.Mensagem;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by douglasmartins on 30/05/17.
 */

public class MensagemAdapter extends BaseAdapter
{
    private List<Mensagem> mensagens;
    private Activity activity;
    private int idCliente;

    @Inject
    Picasso picasso;

    @BindView(R.id.tv_texto)
    TextView texto;
    @BindView(R.id.iv_avatar_mensagem)
    ImageView avatar;

    public MensagemAdapter(int idCliente, List<Mensagem> mensagens, Activity activity)
    {
        this.idCliente = idCliente;
        this.mensagens = mensagens;
        this.activity = activity;
    }

    @Override
    public int getCount()
    {
        return mensagens.size();
    }

    @Override
    public Mensagem getItem(int position)
    {
        return mensagens.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View linha = convertView;
        if(linha == null)
            linha = activity.getLayoutInflater().inflate(R.layout.mensagem, parent, false);
        ButterKnife.bind(linha);

        Mensagem mensagem = getItem(position);
        if (idCliente != mensagem.getId())
        {
            linha.setBackgroundColor(Color.CYAN);
        }
        texto.setText(mensagem.getTexto());
        picasso.with(activity).load("http://api.adorable.io/avatars/285/"+mensagem.getId()+".png").into(avatar);

        return linha;
    }
}
