package com.example.socket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String HOST = "192.168.1.100";
    private int PORT = 6666;

    private Button btnSend;
    private EditText etCmd;
    private EditText etIP;
    private EditText etPORT;
    private GridView gw;
    private String line;
    private List<Command> commands= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControls();
    }
    private void initControls() {
        btnSend = (Button) findViewById(R.id.btnSend);
        etCmd = (EditText) findViewById(R.id.cmdtext);
        etIP = (EditText) findViewById(R.id.etIP);
        etPORT = (EditText) findViewById(R.id.etPORT);
        gw = (GridView) findViewById(R.id.gridView);
        commands = new ArrayList();
        commands.add(new Command("Volume up","vol+"));
        commands.add(new Command("Volume down","vol-"));
        commands.add(new Command("Volume mute","mute"));
        commands.add(new Command("Play/Pause","pause"));
        commands.add(new Command("Space","space"));
        commands.add(new Command("Exit","exit"));

        CommandsAdapter commandsAdapter = new CommandsAdapter(this, commands);
        gw.setAdapter(commandsAdapter);
        btnSend.setOnClickListener(new ReceiverListener());
        gw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SendToSocket(commands.get(position).getCmd());
            }
        });
    }
    private void SendToSocket(final String msg){
        new Thread() {
            @Override
            public void run() {
                try {

                    HOST = etIP.getText().toString();
                    PORT = Integer.parseInt(etPORT.getText().toString());
                    Socket socket = new Socket(HOST, PORT);

                    if(socket.isConnected()) {
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        bw.write(msg);
                        bw.flush();
                        bw.close();
                        socket.close();
                    }

                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
        }.start();
    }
    class ReceiverListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            SendToSocket(etCmd.getText().toString());
        }
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            etCmd.setText(line);
            Log.i("PDA", "----->"+line);
        }
    };

}
