package edu.java.bot.MessageAccepters;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.java.bot.UserDataMapClass.userData;

public class SetGammaAccepter extends BasicAccepter {

    private static final Logger LOGGER = LogManager.getLogger();

    public SetGammaAccepter(String requiredState) {
        super(requiredState);
    }

    @Override
    public SendMessage accept(Update update) {
        long id = update.message().chat().id();
        String message = update.message().text();

        boolean ok = false;

        try {
            ok = userData.get(id).setGamma(Double.parseDouble(update.message().text()));
        } catch (Exception e) {
            LOGGER.error(e);
        }
        if (ok) {
            reply = (new SendMessage(id, "Установлена новая гамма: " + userData.get(id).getGamma()));
        } else {
            reply = (new SendMessage(
                id,
                "Произошла ошибка. Масштаб не изменен. Текущий масштаб: " + userData.get(id).getGamma()
            ));
        }
        userData.get(id).stateReset();
        return this.reply;
    }

    private boolean checkLink(String link) {
        return true;
    }
}
