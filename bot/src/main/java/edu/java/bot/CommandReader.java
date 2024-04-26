package edu.java.bot;

import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.request.SetMyCommands;
import com.pengrad.telegrambot.response.SendResponse;
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
import edu.java.bot.CommandExecuters.SetColorCommands.SetCorolBlue;
import edu.java.bot.CommandExecuters.SetColorCommands.SetCorolGreen;
import edu.java.bot.CommandExecuters.SetColorCommands.SetCorolOrange;
import edu.java.bot.CommandExecuters.SetColorCommands.SetCorolPink;
import edu.java.bot.CommandExecuters.SetColorCommands.SetCorolPurple;
import edu.java.bot.CommandExecuters.SetColorCommands.SetCorolRed;
import edu.java.bot.CommandExecuters.SetColorCommands.SetCorolYellow;
import edu.java.bot.CommandExecuters.SetRatioExecuter;
import edu.java.bot.CommandExecuters.StartCommandExecuter;
import edu.java.bot.CommandExecuters.addComandWorldExecuters.AddWordExecuter;
import edu.java.bot.MessageAccepters.AddLinkAccepter;
import edu.java.bot.MessageAccepters.BasicAccepter;
import edu.java.bot.MessageAccepters.RemoveLinkAccepter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import edu.java.bot.MessageAccepters.SetCLAccepter;
import edu.java.bot.MessageAccepters.SetRatioAccepter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.java.bot.BotApplication.bot;
import static edu.java.bot.UserDataMapClass.userData;

public class CommandReader {
    public static Set<BasicCommandExecuter> allExe = new HashSet<>();
    public static Set<BasicAccepter> allAcc = new HashSet<>();
    private final static Logger LOGGER = LogManager.getLogger();

    public CommandReader() {
        allExe.add(new StartCommandExecuter("/start", "Start conversation with bot"));
        allExe.add(new HelpCommandExecuter("/help", "Get list of commands"));

        allAcc.add(new AddLinkAccepter(AddCommandExecuter.GIVEN_STATE));
        allAcc.add(new RemoveLinkAccepter(RemoveCommandExecuter.GIVEN_STATE));
        allAcc.add(new SetCLAccepter(SetCLExecuter.GIVEN_STATE));
        allAcc.add(new SetRatioAccepter(SetRatioExecuter.GIVEN_STATE));


        allExe.add(new SetCorolRed("/red", "Set color to red"));
        allExe.add(new SetCorolOrange("/orange", "Set color to orange"));
        allExe.add(new SetCorolYellow("/yellow", "Set color to yellow"));
        allExe.add(new SetCorolGreen("/green", "Set color to green"));
        allExe.add(new SetCorolBlue("/blue", "Set color to blue"));
        allExe.add(new SetCorolPurple("/purple", "Set color to purple"));
        allExe.add(new SetCorolPink("/pink", "Set color to pink"));


        allExe.add(new AddWordExecuter("/diamond", "Draws specified shape or shaped lines"));
        allExe.add(new AddWordExecuter("/disk", "Draws specified shape or shaped lines"));
        allExe.add(new AddWordExecuter("/fisheye", "Draws specified shape or shaped lines"));
        allExe.add(new AddWordExecuter("/handkerchief", "Draws specified shape or shaped lines"));
        allExe.add(new AddWordExecuter("/heart", "Draws specified shape or shaped lines"));
        allExe.add(new AddWordExecuter("/horseshoe", "Draws specified shape or shaped lines"));
        allExe.add(new AddWordExecuter("/hyperbolic", "Draws specified shape or shaped lines"));
        allExe.add(new AddWordExecuter("/pdj", "Draws specified shape or shaped lines"));
        allExe.add(new AddWordExecuter("/pillow", "Draws specified shape or shaped lines"));
        allExe.add(new AddWordExecuter("/polar", "Draws specified shape or shaped lines"));
        allExe.add(new AddWordExecuter("/sin", "Draws specified shape or shaped lines"));
        allExe.add(new AddWordExecuter("/sphere", "Draws specified shape or shaped lines"));
        allExe.add(new AddWordExecuter("/spiral", "Draws specified shape or shaped lines"));
        allExe.add(new AddWordExecuter("/swirl", "Draws specified shape or shaped lines"));
        allExe.add(new AddWordExecuter("/waves", "Draws specified shape or shaped lines"));

        //allExe.add(new AddCommandExecuter("/addModifier", "Add modifier to current command line"));
        allExe.add(new ClearLineExecuter("/clear_line", "Clears current command line"));
        allExe.add(new GenerateExecuter("/generate", "Create your image!"));
        allExe.add(new RemoveLineExecuter("/remove_line", "removes current line"));
        allExe.add(new RemoveWordExecuter("/remove_mod", "Removes last modifier from current command line"));
        allExe.add(new SetCLExecuter("/set_current_line", "Changes selected command line"));
        allExe.add(new SetRatioExecuter("/set_ratio", "Sets image ratio"));
        allExe.add(new AddLineExecuter("/add_line", "Ads a new command line and makes it current line"));


        publishCommands(allExe);
    }

    public void read(Update update) {

        System.out.println("reading command");

        String message = update.message().text();
        long id = update.message().chat().id();
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
            if (!commandFound) {
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
