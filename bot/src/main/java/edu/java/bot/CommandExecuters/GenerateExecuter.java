package edu.java.bot.CommandExecuters;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineQueryResultArticle;
import com.pengrad.telegrambot.model.request.InlineQueryResultPhoto;
import com.pengrad.telegrambot.request.AnswerInlineQuery;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.SendResponse;
import edu.java.bot.UserClass;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import static edu.java.bot.UserDataMapClass.userData;

public class GenerateExecuter extends BasicCommandExecuter {

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

        reply = new SendPhoto(id, UserClass.TEMPERAL_IMAGE_PATH.toFile()).caption(userData.get(id).printAllSettings());

        return this.reply;
    }
}
