package edu.java.bot.CommandExecuters.SetColorCommands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.CommandExecuters.BasicCommandExecuter;

import static edu.java.bot.UserDataMapClass.userData;

public class SetCorolPink extends BasicCommandExecuter {

    public SetCorolPink(String name, String description) {
        super(name, description);
    }

    @Override
    public BaseRequest execute(Update update) {
        long id = update.message().chat().id();
        userData.get(id).setColor(ColorsEnum.PURPLE);
        reply = (new SendMessage(id, "Установлен новый цвет: " + userData.get(id).getColor().toString()));
        return this.reply;
    }
}
