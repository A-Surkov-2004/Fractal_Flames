package edu.java.bot.CommandExecuters.addComandWorldExecuters;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.CommandExecuters.BasicCommandExecuter;
import static edu.java.bot.UserDataMapClass.userData;

public class AddWordExecuter extends BasicCommandExecuter {

    public AddWordExecuter(String name, String description) {
        super(name, description);
    }

    @Override
    public BaseRequest execute(Update update) {
        long id = update.message().chat().id();
        boolean ok = false;
        try {
            ok = userData.get(id).addCommandWord(update.message().text().substring(1));
        }catch (Exception e){
            System.out.println(e);
        }
        if(ok) {
            reply = (new SendMessage(id, "Модификатор добавлен.\n" +  userData.get(id).printCommands()));
        }else {
            reply = (new SendMessage(id, "Произошла ошибка. Модификатор не был добавлен"));
        }
        return this.reply;
    }
}
