package br.com.douglaspac.ichat.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by douglasmartins on 30/05/17.
 */

public class Mensagem implements Serializable
{
    @SerializedName("text")
    private String texto;
    private int id;

    public Mensagem(int id, String texto)
    {
        this.id = id;
        this.texto = texto;
    }

    public String getTexto()
    {
        return texto;
    }

    public int getId()
    {
        return id;
    }
}
