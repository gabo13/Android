package com.example.socket;

import androidx.annotation.NonNull;

public class Command {
    private String name;
    private String cmd;

    Command() {
        this.name = "Exit";
        this.cmd = "exit";
    }

    Command(String name, String cmd) {
        this.name = name;
        this.cmd = cmd;
    }

    public String getName() {
        return name;
    }

    public String getCmd() {
        return cmd;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name + ": " + this.cmd;
    }
}
