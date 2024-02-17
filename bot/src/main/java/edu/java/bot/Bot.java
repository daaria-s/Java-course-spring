package edu.java.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.request.SetMyCommands;
import java.util.Arrays;
import org.springframework.stereotype.Component;
import static edu.java.bot.utils.CommandUtils.getAllCommands;

@Component
public class Bot {

    private Bot(TelegramBot bot, UpdatesListener listener) {
        bot.setUpdatesListener(listener);
    }

    static public SetMyCommands createCommandMenu() {
        return new SetMyCommands(Arrays.stream(getAllCommands()).map(command -> new BotCommand(
            command.command(),
            command.description()
        )).toArray(BotCommand[]::new));
    }
}
