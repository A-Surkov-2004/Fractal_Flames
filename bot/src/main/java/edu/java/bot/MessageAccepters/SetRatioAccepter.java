package edu.java.bot.MessageAccepters;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import static edu.java.bot.UserDataMapClass.userData;

public class SetRatioAccepter extends BasicAccepter {

    public SetRatioAccepter(String requiredState) {
        super(requiredState);
    }

    @Override
    public SendMessage accept(Update update) {
        long id = update.message().chat().id();
        String message = update.message().text();

        boolean ok = false;

        try {
            ok = userData.get(id).setRatio(Double.parseDouble(update.message().text()));
        }catch (Exception e){
            System.out.println(e);
        }
        if(ok) {
            reply = (new SendMessage(id, "Установлен новый масштаб: " + userData.get(id).getRatio()));
        }else {
            reply = (new SendMessage(id, "Произошла ошибка. Масштаб не изменен. Текущий масштаб: " + userData.get(id).getRatio()));
        }
        userData.get(id).stateReset();
        return this.reply;
    }

    private boolean checkLink(String link) {
        return true;
    }
}
