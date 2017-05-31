package br.com.douglaspac.ichat.service;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import br.com.douglaspac.ichat.activity.MainActivity;
import br.com.douglaspac.ichat.modelo.Mensagem;

/**
 * Created by douglasmartins on 30/05/17.
 */

public class ChatService
{
    private MainActivity activity;
    public ChatService(MainActivity mainActivity)
    {
        this.activity = mainActivity;
    }

    public void enviar(final Mensagem mensagem)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                String texto = mensagem.getTexto();

                try
                {
                    HttpURLConnection httpConnection = (HttpURLConnection) new URL("http://192.168.0.21:8080/polling").openConnection();
                    httpConnection.setRequestMethod("POST");
                    httpConnection.setRequestProperty("content/type", "application/json");

                    JSONStringer json = new JSONStringer()
                            .object()
                            .key("text")
                            .value(texto)
                            .key("id")
                            .value(mensagem.getId()).endObject();

                    OutputStream saida = httpConnection.getOutputStream();
                    PrintStream ps = new PrintStream(saida);
                    ps.println(json.toString());

                    httpConnection.connect();
                    httpConnection.getInputStream();

                } catch (Exception e)
                {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public void ouvirMensagem()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    HttpURLConnection httpConnection = (HttpURLConnection) new URL("http://192.168.0.21:8080/polling").openConnection();
                    httpConnection.setRequestMethod("GET");
                    httpConnection.setRequestProperty("Accept", "application/json");

                    httpConnection.connect();
                    Scanner scanner = new Scanner(httpConnection.getInputStream());
                    StringBuilder builder = new StringBuilder();
                    while(scanner.hasNextLine())
                    {
                        builder.append(scanner.nextLine());
                    }

                    String json = builder.toString();

                    JSONObject jsonObject = new JSONObject(json);
                    final Mensagem mensagem = new Mensagem(jsonObject.getInt("id"), jsonObject.getString("text"));

                    activity.runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            activity.colocarNaLista(mensagem);
                        }
                    });
                } catch (Exception e)
                {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
