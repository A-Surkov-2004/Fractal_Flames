package edu.java.bot.CommandExecuters;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.SendResponse;
import edu.java.bot.UserClass;
import java.io.IOException;
import static edu.java.bot.UserDataMapClass.userData;

public class GenerateExecuter extends BasicCommandExecuter {

    public final static String GIVEN_STATE = "changing ratio";

    public GenerateExecuter(String name, String description) {
        super(name, description);
    }

    @Override
    public BaseRequest execute(Update update) {
        long id = update.message().chat().id();
        SendPhoto sendPhoto;
        try {
            userData.get(id).generate();
        }catch (IOException e){
            System.out.println(e);
            reply = new SendMessage(id, "Произошла ошибка!");
            return  this.reply;
        }

        reply = new SendPhoto(id, UserClass.TEMPERAL_IMAGE_PATH.toFile());

        return this.reply;
    }
}
