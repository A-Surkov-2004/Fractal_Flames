package edu.java.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import edu.java.bot.configuration.ApplicationConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.yaml.snakeyaml.Yaml;

@SpringBootApplication
@Log4j2
@EnableConfigurationProperties(ApplicationConfig.class)
public class BotApplication {

    private static final Logger LOGGER = LogManager.getLogger();

    public static TelegramBot bot;
    public static String exitWord = getExitWord();

    private static final Set<String> EXCLUSIONS = Set.of(".\\.git", ".\\.github", ".\\bot\\target", ".\\.idea",
        ".\\target", ".\\.mvn", ".\\bot\\src\\test"
    );

    public static void main(String[] args) throws IOException {

        File logfile = Path.of("./data/log.txt").toFile();
        PrintWriter writer = new PrintWriter(logfile.toString(), StandardCharsets.UTF_8);

        try {

            Collection<File> all = new ArrayList<File>();
            addTree(new File("./"), all);
            //System.out.println(all);

            Path tokenpath = Path.of("./").toAbsolutePath();
            LOGGER.error(tokenpath);
            File file = new File(
                "./token.txt");

            String token;

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                token = br.readLine();

            } catch (FileNotFoundException e) {
                LOGGER.error("no token.txt found. Using YML");
                Yaml yaml = new Yaml();
                InputStream inputStream = ApplicationConfig.class
                    .getClassLoader()
                    .getResourceAsStream("application.yml");
                Map<String, Map<String, String>> obj = yaml.load(inputStream);
                token = obj.get("app").get("telegram-token");
            }

            LOGGER.always().log(token);

            LOGGER.always().log(exitWord);
            LOGGER.always().log("^exitWord");

            bot = new TelegramBot(token);
            LOGGER.always().log(bot);
            SpringApplication.run(BotApplication.class, args);
            CommandReader cmdReader = new CommandReader();

// Register for updates
            bot.setUpdatesListener(updates -> {

                Update update = updates.get(0);
                try {
                    cmdReader.read(update);

                } catch (Exception e) {
                    String erm = "Read command ex";
                    LOGGER.warn(erm);
                    LOGGER.error(e);
                    writer.println(erm);
                    writer.println(e);
                }

                // return id of last processed update or confirm them all

                return update.updateId();
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

        } catch (Exception e) {
            LOGGER.warn("ex in Bot application");
            LOGGER.warn(e);
            //PrintWriter writer = new PrintWriter("/log.txt", "UTF-8");
            writer.println(e);
            writer.close();
        }
    }

    private static void addTree(File file, Collection<File> all) {
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                if (!EXCLUSIONS.contains(child.toString())) {
                    LOGGER.always().log(child.toString());
                    all.add(child);
                    addTree(child, all);
                }
            }
        }
    }

    private static String getExitWord() {
        String exWord = null;
        try {

            File exitWordFile = new File(
                "./exitWord.txt");
            BufferedReader br = new BufferedReader(new FileReader(exitWordFile));
            exWord = br.readLine();
            if (Objects.equals(exWord, "NULL") || exWord.isEmpty()) {

                exWord = null;
            }
        } catch (IOException e) {
            LOGGER.error("exitWord.txt reading fail");
        }
        return exWord;
    }
}
