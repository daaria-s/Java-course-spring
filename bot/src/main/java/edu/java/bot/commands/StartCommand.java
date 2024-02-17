package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import static edu.java.bot.utils.UsersUtils.addNewUser;
import static edu.java.bot.utils.UsersUtils.checkUser;

public class StartCommand implements Command {
    static final String SUCCESSFULLY_REGISTERED = "You have been successfully registered in LinkTrackingBot";

    static final String ALREADY_REGISTERED = "You are already registered in LinkTrackingBot";

    @Override
    public String command() {
        return "\\start";
    }

    @Override
    public String description() {
        return "Registrates user into LinkTrackingBot";
    }

    @Override
    public SendMessage handle(Update update) {
        long chatId = update.message().chat().id();
        if (checkUser(chatId)) {
            return new SendMessage(chatId, ALREADY_REGISTERED);
        }
        addNewUser(chatId);
        return new SendMessage(update.message().chat().id(), SUCCESSFULLY_REGISTERED);
    }
}
