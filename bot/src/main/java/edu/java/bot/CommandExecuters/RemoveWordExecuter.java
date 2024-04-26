package edu.java.bot.CommandExecuters;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import static edu.java.bot.UserDataMapClass.userData;

public class RemoveWordExecuter extends BasicCommandExecuter {

    public RemoveWordExecuter(String name, String description) {
        super(name, description);
    }

    @Override
    public BaseRequest execute(Update update) {
        long id = update.message().chat().id();
        boolean ok = false;
        try {
            ok = userData.get(id).removeWordFromLine();
        }catch (Exception e){
            System.out.println(e);
        }
        if(ok) {
            reply = (new SendMessage(id, "Модификатор был удален.\n" + userData.get(id).printCommands()));
        }else {
            reply = (new SendMessage(id, "Произошла ошибка. Модификатор не удален." + userData.get(id).getRatio()));
        }
        return this.reply;
    }
}
