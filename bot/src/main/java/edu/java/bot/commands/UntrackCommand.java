package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class UntrackCommand implements Command {

    static final String STOPPED_TRACKING = "You stopped tracking this link";

    @Override
    public String command() {
        return "\\untrack";
    }

    @Override
    public String description() {
        return "Stops tracking the link, enter the link after the command separated by a space";
    }

    @Override
    public SendMessage handle(Update update) {
        String message = update.message().text();
        String link = message.split(" ")[1];

        long chatId = update.message().chat().id();
        return new SendMessage(chatId, STOPPED_TRACKING);
    }
}
