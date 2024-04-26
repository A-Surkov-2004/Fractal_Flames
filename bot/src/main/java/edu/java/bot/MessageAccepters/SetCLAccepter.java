package edu.java.bot.MessageAccepters;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import static edu.java.bot.UserDataMapClass.userData;

public class SetCLAccepter extends BasicAccepter {

    public SetCLAccepter(String requiredState) {
        super(requiredState);
    }

    @Override
    public SendMessage accept(Update update) {
        long id = update.message().chat().id();
        String message = update.message().text();

        boolean ok = false;



        try {
            ok = userData.get(id).setCL(Integer.parseInt(update.message().text()));
        }catch (Exception e){
            System.out.println(e);
        }
        if(ok) {
            reply = (new SendMessage(id, "Выбраная командная строка: " + userData.get(id).getCL()+"\n" + userData.get(id).printCommands()));
        }else {
            reply = (new SendMessage(id, "Произошла ошибка. Командная строка не изменена." + userData.get(id).getRatio()));
        }
        userData.get(id).stateReset();
        return this.reply;
    }

    private boolean checkLink(String link) {
        return true;
    }
}
