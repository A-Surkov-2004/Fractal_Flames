package edu.java.bot;

import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetMyCommands;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import edu.java.bot.CommandExecuters.AddCommandExecuter;
import edu.java.bot.CommandExecuters.AddLineExecuter;
import edu.java.bot.CommandExecuters.BasicCommandExecuter;
import edu.java.bot.CommandExecuters.ClearLineExecuter;
import edu.java.bot.CommandExecuters.GenerateExecuter;
import edu.java.bot.CommandExecuters.HelpCommandExecuter;
import edu.java.bot.CommandExecuters.RemoveCommandExecuter;
import edu.java.bot.CommandExecuters.RemoveLineExecuter;
import edu.java.bot.CommandExecuters.RemoveWordExecuter;
import edu.java.bot.CommandExecuters.SetCLExecuter;
import edu.java.bot.CommandExecuters.SetColorCommands.SetColorBlue;
import edu.java.bot.CommandExecuters.SetColorCommands.SetColorEvery;
import edu.java.bot.CommandExecuters.SetColorCommands.SetColorGreen;
import edu.java.bot.CommandExecuters.SetColorCommands.SetColorOrange;
import edu.java.bot.CommandExecuters.SetColorCommands.SetColorPink;
import edu.java.bot.CommandExecuters.SetColorCommands.SetColorPurple;
import edu.java.bot.CommandExecuters.SetColorCommands.SetColorRed;
import edu.java.bot.CommandExecuters.SetColorCommands.SetColorYellow;
import edu.java.bot.CommandExecuters.SetGammaExecuter;
import edu.java.bot.CommandExecuters.SetRatioExecuter;
import edu.java.bot.CommandExecuters.ShowCollorsExecuter;
import edu.java.bot.CommandExecuters.ShowConfEdits;
import edu.java.bot.CommandExecuters.ShowModifiers;
import edu.java.bot.CommandExecuters.ShowSettingsExecuter;
import edu.java.bot.CommandExecuters.StartCommandExecuter;
import edu.java.bot.CommandExecuters.addComandWorldExecuters.AddWordExecuter;
import edu.java.bot.MessageAccepters.AddLinkAccepter;
import edu.java.bot.MessageAccepters.BasicAccepter;
import edu.java.bot.MessageAccepters.RemoveLinkAccepter;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import edu.java.bot.MessageAccepters.SetCLAccepter;
import edu.java.bot.MessageAccepters.SetGammaAccepter;
import edu.java.bot.MessageAccepters.SetRatioAccepter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.java.bot.BotApplication.bot;
import static edu.java.bot.UserDataMapClass.userData;

public class CommandReader {
    public static Set<BasicCommandExecuter> allExe = new HashSet<>();
    public static Set<BasicCommandExecuter> allMods = new HashSet<>();
    public static Set<BasicCommandExecuter> allColors = new HashSet<>();
    public static Set<BasicCommandExecuter> allConfigs = new HashSet<>();
    public static Set<BasicAccepter> allAcc = new HashSet<>();
    private final static Logger LOGGER = LogManager.getLogger();

    public CommandReader() {
        allExe.add(new StartCommandExecuter("/start", "Start conversation with bot"));
        allExe.add(new HelpCommandExecuter("/help", "Get list of commands"));

        allAcc.add(new AddLinkAccepter(AddCommandExecuter.GIVEN_STATE));
        allAcc.add(new RemoveLinkAccepter(RemoveCommandExecuter.GIVEN_STATE));
        allAcc.add(new SetCLAccepter(SetCLExecuter.GIVEN_STATE));
        allAcc.add(new SetRatioAccepter(SetRatioExecuter.GIVEN_STATE));
        allAcc.add(new SetGammaAccepter(SetGammaExecuter.GIVEN_STATE));


        allColors.add(new SetColorRed("/red", "Set color to red"));
        allColors.add(new SetColorOrange("/orange", "Set color to orange"));
        allColors.add(new SetColorYellow("/yellow", "Set color to yellow"));
        allColors.add(new SetColorGreen("/green", "Set color to green"));
        allColors.add(new SetColorBlue("/blue", "Set color to blue"));
        allColors.add(new SetColorPurple("/purple", "Set color to purple"));
        allColors.add(new SetColorPink("/pink", "Set color to pink"));
        allColors.add(new SetColorEvery("/white", "Removes all color limitations"));
        allExe.addAll(allColors);


        allMods.add(new AddWordExecuter("/diamond", "Draws specified shape or shaped lines"));
        allMods.add(new AddWordExecuter("/disk", "Draws specified shape or shaped lines"));
        allMods.add(new AddWordExecuter("/handkerchief", "Draws specified shape or shaped lines"));
        allMods.add(new AddWordExecuter("/heart", "Draws specified shape or shaped lines"));
        allMods.add(new AddWordExecuter("/horseshoe", "Draws specified shape or shaped lines"));
        allMods.add(new AddWordExecuter("/hyperbolic", "Draws specified shape or shaped lines"));
        allMods.add(new AddWordExecuter("/pdj", "Draws specified shape or shaped lines"));
        allMods.add(new AddWordExecuter("/pillow", "Draws specified shape or shaped lines"));
        allMods.add(new AddWordExecuter("/polar", "Draws specified shape or shaped lines"));
        allMods.add(new AddWordExecuter("/sin", "Draws specified shape or shaped lines"));
        allMods.add(new AddWordExecuter("/sphere", "Draws specified shape or shaped lines"));
        allMods.add(new AddWordExecuter("/spiral", "Draws specified shape or shaped lines"));
        allMods.add(new AddWordExecuter("/swirl", "Draws specified shape or shaped lines"));
        allExe.addAll(allMods);

        allConfigs.add(new ClearLineExecuter("/clear_line", "Clears current command line"));
        allConfigs.add(new GenerateExecuter("/generate", "Create your image!"));
        allConfigs.add(new RemoveLineExecuter("/remove_line", "removes current line"));
        allConfigs.add(new RemoveWordExecuter("/remove_mod", "Removes last modifier from current command line"));
        allConfigs.add(new SetCLExecuter("/set_current_line", "Changes selected command line"));
        allConfigs.add(new AddLineExecuter("/add_line", "Ads a new command line and makes it current line"));
        allConfigs.add(new ShowSettingsExecuter("/settings", "Shows all current generation settings"));
        allExe.addAll(allConfigs);

        allExe.add(new SetRatioExecuter("/" +
            "ratio", "Sets image ratio"));
        allExe.add(new ShowModifiers("/mods","Shows all available modifiers"));
        allExe.add(new ShowCollorsExecuter("/colors","Shows all available colors"));
        allExe.add(new ShowConfEdits("/configuration","Shows all methods to edit command lines"));
        allExe.add(new SetGammaExecuter("/gamma","Sets gamma to new value"));



        publishCommands(allExe);
    }

    public void read(Update update) {


        String message = update.message().text();
        long id = update.message().chat().id();
        System.out.println("reading command: " + update.message().text());
        if(userData.containsKey(id)) {
            if (Objects.equals(message, BotApplication.exitWord) && message != null) {
                if (!userData.get(id).shutdownReq) {
                    bot.execute(new SendMessage(
                        id,
                        "Ввод команды " + BotApplication.exitWord + " два раза подряд остановит программу."
                    ));
                    userData.get(id).shutdownReq = true;
                } else {
                    bot.execute(new SendMessage(id, "Завершение работы"));
                    System.exit(130);
                }
                return;
            } else if (userData.get(id).shutdownReq) {
                userData.get(id).shutdownReq = false;
            }
        }


        if (!userData.containsKey(id) || Objects.equals(userData.get(id).stateGet(), UserClass.DEFAULT_STATE)) {
            boolean commandFound = false;
            for (BasicCommandExecuter command : allExe) {
                if (Objects.equals(message, command.getName())) {
                    BaseRequest response;
                    if(!userData.containsKey(id) && !Objects.equals(message, "/start")){
                        response =
                            (new SendMessage(id, "Вы не зарегестрированы в системе. Используйте команду /start, чтобы начать."));
                    }
                    else {
                        commandFound = true;
                        response = command.execute(update);
                    }
                    bot.execute(response);
                }
            }
            if (!commandFound && !Objects.equals(message, BotApplication.exitWord)) {
                bot.execute(new SendMessage(
                    id,
                    "Команда не распознана. Используйте команду /help для получения списка допустимых команд"
                ));
            }
        } else {
            for (BasicAccepter accepter : allAcc) {
                if (Objects.equals(userData.get(id).stateGet(), accepter.getRequiredState())) {
                    SendMessage response = accepter.accept(update);
                    bot.execute(response);
                    break;
                }
            }
        }
    }

    public Set<BasicCommandExecuter> getExecuters() {
        return allExe;
    }

    private void publishCommands(Set<BasicCommandExecuter> allExe) {

        BotCommand[] botCommands = new BotCommand[allExe.size()];
        int i = 0;
        for (BasicCommandExecuter executer : allExe) {
            botCommands[i] = new BotCommand(executer.getName(), executer.getDescription());
            i++;

        }
        SetMyCommands commandsUpdate = new SetMyCommands(botCommands);
        bot.execute(commandsUpdate);
    }
}
