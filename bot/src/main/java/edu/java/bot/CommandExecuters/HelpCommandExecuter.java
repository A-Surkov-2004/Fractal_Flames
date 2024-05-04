package edu.java.bot.CommandExecuters;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import java.util.Set;
import static edu.java.bot.CommandReader.allExe;
import static edu.java.bot.UserDataMapClass.userData;




public class HelpCommandExecuter extends BasicCommandExecuter {
    public HelpCommandExecuter(String name, String description) {
        super(name, description);
    }

    @Override
    public BaseRequest execute(Update update) {

        String info = """
            Данный телеграмм бот предназначен для генерации изображений фрактального пламени. Генератор обладает высокой гибкостью, но может быть сложен в управлении. Для решения этой проблемы существует данная справка, а также несколько вспомогательных команд.

            Основы взаимодействия.
            Для генерации изображения используется набор модификаторов. Все доступные модификаторы можно найти по команде /mods
            Генератор принимает одну или несколько \"командных строк\", каждая строка содержит упрядоченный список модификаторов. Эксперементируйте с конфигурацией модификаторов для получения уникальных изображений!
            Команды управления конфигурацией: /configuration
            Просмотреть выставленные настройки: /settings

            Дополнительные настройки.
            Помимо изменения конфигурации модификаторов, вы также можете:
            Изменять масштаб изображения /ratio
            Устанавливать цветовую гамму /colors
            Менять яркость изображения /gamma

            Когда будете готовы запустить генератор, используйте команду /generate. Будьте терпеливы, генерация и отправка изображения занимает какое-то время!
            """;


        long id = update.message().chat().id();

        reply = (new SendMessage(id, info));

        return reply;
    }
}
