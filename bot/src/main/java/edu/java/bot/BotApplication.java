package edu.java.bot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.yaml.snakeyaml.Yaml;


@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class BotApplication {

    public static TelegramBot bot;
    public static String exitWord = getExitWord();

    //private static final Set<String> exclusions = Set.of(".\\.git", ".\\.github", ".\\bot\\target", ".\\.idea",
    //    ".\\target", ".\\.mvn", ".\\bot\\src\\test");

    private static final Set<String> exclusions = new HashSet<>();

    public static void main(String[] args) throws IOException {


        File logfile =  Path.of("./data/log.txt").toFile();
        PrintWriter writer = new PrintWriter(logfile.toString(), StandardCharsets.UTF_8);

        try{

            Collection<File> all = new ArrayList<File>();
            addTree(new File("./"), all);
            //System.out.println(all);

            // File path is passed as parameter
            Path tokenpath = Path.of("./").toAbsolutePath();
            System.out.println(tokenpath);
            File file = new File(
                "./token.txt");

            String token;

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                token = br.readLine();

            }catch (FileNotFoundException e){
                System.out.println("no token.txt found. Using YML");
                Yaml yaml = new Yaml();
                InputStream inputStream = ApplicationConfig.class
                    .getClassLoader()
                    .getResourceAsStream("application.yml");
                Map<String, Map<String, String>> obj = yaml.load(inputStream);
                token = obj.get("app").get("telegram-token");
            }

            System.out.println(token);
            System.out.println("^token");

            System.out.println(exitWord);
            System.out.println("^exitWord");

            bot = new TelegramBot(token);
            System.out.println(bot);
            SpringApplication.run(BotApplication.class, args);
            CommandReader cmdReader = new CommandReader();

// Register for updates
            bot.setUpdatesListener(updates -> {

                Update update = updates.get(0);
                try {
                    cmdReader.read(update);

                }catch (Exception e){
                    System.out.println("Read command ex");
                    System.out.println(e);
                    writer.println("Read command ex");
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

        }catch (Exception e){
            System.out.println("ex in Bot application");
            System.out.println(e);
            //PrintWriter writer = new PrintWriter("/log.txt", "UTF-8");
            writer.println(e);
            writer.close();
        }
    }

    private static void addTree(File file, Collection<File> all) {
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

    private static String getExitWord(){
        String exitWord = null;
        try {

            File exitWordFile = new File(
                "./exitWord.txt");
            BufferedReader br = new BufferedReader(new FileReader(exitWordFile));
            exitWord = br.readLine();
            if (Objects.equals(exitWord, "NULL")|| exitWord.isEmpty()){

                exitWord = null;
            }
        }catch (IOException e){
            System.out.println("exitWord.txt reading fail");
        }
        return exitWord;
    }
}
