package edu.java.bot.CommandExecuters;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import edu.java.bot.UserClass;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.java.bot.UserDataMapClass.userData;

public class GenerateExecuter extends BasicCommandExecuter {

    private static final Logger LOGGER = LogManager.getLogger();

    public GenerateExecuter(String name, String description) {
        super(name, description);
    }

    @Override
    public BaseRequest execute(Update update) {
        long id = update.message().chat().id();
        SendPhoto sendPhoto;
        try {
            userData.get(id).generate();
        } catch (IOException e) {
            LOGGER.error(e);
            reply = new SendMessage(id, "Произошла ошибка!");
            return this.reply;
        }

        reply = new SendPhoto(id, UserClass.TEMPERAL_IMAGE_PATH.toFile()).caption(userData.get(id).printAllSettings());

        return this.reply;
    }
}
