package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class ListCommand implements Command {

    static final private String TRACKING_LIST = "Here will be links for tracking";

    @Override
    public String command() {
        return "/list";
    }

    @Override
    public String description() {
        return "Shows list of tracking links";
    }

    @Override
    public SendMessage handle(Update update) {
        long chatId = update.message().chat().id();
        return new SendMessage(chatId, TRACKING_LIST);
    }
}

