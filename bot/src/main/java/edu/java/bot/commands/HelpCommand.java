package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import static edu.java.bot.utils.CommandUtils.getAllCommands;

public class HelpCommand implements Command {
    @Override
    public String command() {
        return "/help";
    }

    @Override
    public String description() {
        return "Shows descriptions to all commands";
    }

    @Override
    public SendMessage handle(Update update) {
        long chatId = update.message().chat().id();

        StringBuilder message = new StringBuilder("Available commands:\n");

        message.append(createCommandList());

        return new SendMessage(chatId, message.toString());

    }

    StringBuilder createCommandList() {

        StringBuilder result = new StringBuilder();

        for (var command : getAllCommands()) {
            result.append(command.command());
            result.append(" - ");
            result.append(command.description());
            result.append("\n");
        }
        return result;
    }
}
