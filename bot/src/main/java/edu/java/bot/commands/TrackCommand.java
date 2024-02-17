package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class TrackCommand implements Command {

    static final String STARTED_TRACKING = "You started tracking this link";

    @Override
    public String command() {
        return "\\track";
    }

    @Override
    public String description() {
        return "Starts tracking the link, enter the link after the command separated by a space";
    }

    @Override
    public SendMessage handle(Update update) {
        String message = update.message().text();
        String link = message.split(" ")[1];

        long chatId = update.message().chat().id();
        return new SendMessage(chatId, STARTED_TRACKING);
    }
}
