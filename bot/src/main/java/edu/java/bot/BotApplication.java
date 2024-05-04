package edu.java.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import edu.java.bot.configuration.ApplicationConfig;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.yaml.snakeyaml.Yaml;


@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class BotApplication {

    public static TelegramBot bot;
    //private static final Set<String> exclusions = Set.of(".\\.git", ".\\.github", ".\\bot\\target", ".\\.idea",
    //    ".\\target", ".\\.mvn", ".\\bot\\src\\test");

    private static final Set<String> exclusions = new HashSet<>();

    public static void main(String[] args) throws FileNotFoundException {

        Collection<File> all = new ArrayList<File>();
        addTree(new File("/data"), all);
        //System.out.println(all);

        Yaml yaml = new Yaml();
        InputStream inputStream = ApplicationConfig.class
            .getClassLoader()
            .getResourceAsStream("application.yml");
        Map<String, Map<String, String>> obj = yaml.load(inputStream);

        bot = new TelegramBot(obj.get("app").get("telegram-token"));
        System.out.println(bot);
        SpringApplication.run(BotApplication.class, args);
        CommandReader cmdReader = new CommandReader();

// Register for updates
        bot.setUpdatesListener(updates -> {

            // ... process updates
            if (updates != null) {
                for (Update i : updates) {
                    cmdReader.read(i);
                }
            }

            // return id of last processed update or confirm them all

            return UpdatesListener.CONFIRMED_UPDATES_ALL;
// Create Exception Handler
        }, e -> {
            if (e.response() != null) {

                // got bad response from telegram
                e.response().errorCode();
                e.response().description();
            } else {
                // probably network error
            }
        });
    }

    static void addTree(File file, Collection<File> all) {
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                if(!exclusions.contains(child.toString())) {
                    System.out.println(child.toString());
                    all.add(child);
                    addTree(child, all);
                }
            }
        }
    }

}
