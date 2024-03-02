package edu.java.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.commands.Command;
import edu.java.bot.commands.StartCommand;
import edu.java.bot.utils.CommandUtils;
import java.util.List;
import java.util.Optional;
import static edu.java.bot.utils.UsersUtils.checkUser;

public class BotListener implements UpdatesListener {

    TelegramBot bot;

    public BotListener(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public int process(List<Update> updates) {

        for (Update update : updates) {
            bot.execute(processUpdate(update));
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    SendMessage unknownCommandMessage(long chatId) {
        return new SendMessage(chatId, "Unknown command");
    }

    SendMessage notRegisteredUser(long chatId) {
        return new SendMessage(chatId, "You should register first");
    }

    SendMessage processUpdate(Update update) {

        long chatId = update.message().chat().id();

        String message = update.message().text();

        Optional<Command> command = CommandUtils.getCommand(message);

        if (command.isPresent()) {
            if (command.get().getClass() != StartCommand.class && !checkUser(chatId)) {
                return notRegisteredUser(chatId);
            }
            return command.get().handle(update);
        } else {
            return unknownCommandMessage(chatId);
        }
    }

}
