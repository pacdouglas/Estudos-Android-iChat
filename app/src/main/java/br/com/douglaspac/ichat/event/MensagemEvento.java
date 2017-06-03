package br.com.douglaspac.ichat.event;

import br.com.douglaspac.ichat.modelo.Mensagem;

/**
 * Created by douglasmartins on 02/06/17.
 */

public class MensagemEvento
{
    public Mensagem mensagem;

    public MensagemEvento(Mensagem mensagem)
    {
        this.mensagem = mensagem;
    }
}
