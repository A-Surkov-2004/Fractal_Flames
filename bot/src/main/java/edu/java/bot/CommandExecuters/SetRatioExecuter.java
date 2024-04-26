package edu.java.bot.CommandExecuters;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.CommandExecuters.SetColorCommands.ColorsEnum;
import static edu.java.bot.UserDataMapClass.userData;

public class SetRatioExecuter extends BasicCommandExecuter {

    public final static String GIVEN_STATE = "changing ratio";

    public SetRatioExecuter(String name, String description) {
        super(name, description);
    }

    @Override
    public BaseRequest execute(Update update) {
        long id = update.message().chat().id();
        userData.get(id).stateSet(GIVEN_STATE);

        reply = (new SendMessage(id, "Укажите новый масштаб. (от 0.1 [приближение 10x] до 10 [отдаление 10x])"));

        return this.reply;
    }
}
