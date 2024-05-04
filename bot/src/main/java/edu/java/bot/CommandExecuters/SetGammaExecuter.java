package edu.java.bot.CommandExecuters;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import static edu.java.bot.UserDataMapClass.userData;

public class SetGammaExecuter extends BasicCommandExecuter {

    public final static String GIVEN_STATE = "changing gamma";

    public SetGammaExecuter(String name, String description) {
        super(name, description);
    }

    @Override
    public BaseRequest execute(Update update) {
        long id = update.message().chat().id();
        userData.get(id).stateSet(GIVEN_STATE);

        reply = (new SendMessage(id, "Укажите новую яркость. (от 0.1 [тускло] до 10 [ярко])"));

        return this.reply;
    }
}
