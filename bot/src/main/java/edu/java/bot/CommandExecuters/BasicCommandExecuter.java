package edu.java.bot.CommandExecuters;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

public abstract class BasicCommandExecuter {
    private String name;
    private String description;
    protected BaseRequest reply;

    public BasicCommandExecuter(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public BaseRequest execute(Update update) {
        return reply;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
