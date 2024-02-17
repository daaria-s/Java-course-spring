package edu.java.bot.utils;

import edu.java.bot.commands.Command;
import edu.java.bot.commands.HelpCommand;
import edu.java.bot.commands.ListCommand;
import edu.java.bot.commands.StartCommand;
import edu.java.bot.commands.TrackCommand;
import edu.java.bot.commands.UntrackCommand;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandUtils {

    private CommandUtils() {
    }

    static final Map<String, Command> COMMANDS = createCommandsMap();


    static Map<String, Command> createCommandsMap() {
        Map<String, Command> commands = new HashMap<>();

        var start = new StartCommand();
        commands.put(start.command(), start);

        var help = new HelpCommand();
        commands.put(help.command(), help);

        var list = new ListCommand();
        commands.put(list.command(), list);

        return commands;
    }

    static final Map<String, Command> TRACK_COMMANDS = createTrackCommandsMap();


    static Map<String, Command> createTrackCommandsMap() {
        Map<String, Command> commands = new HashMap<>();

        var track = new TrackCommand();
        commands.put(track.command(), track);

        var untrack = new UntrackCommand();
        commands.put(untrack.command(), untrack);

        return commands;
    }

    public static Optional<Command> getCommand(String message) {
        if (COMMANDS.containsKey(message)) {
            return Optional.ofNullable(COMMANDS.get(message));
        }
        String[] splittedMessage = message.split(" ");

        if (splittedMessage.length == 2) {
            if (TRACK_COMMANDS.containsKey(splittedMessage[0])) {
                return Optional.ofNullable(TRACK_COMMANDS.get(splittedMessage[0]));
            }
        }
        return Optional.empty();
    }

    public static Command[] getAllCommands() {
        return new Command[] {COMMANDS.get("/start"), COMMANDS.get("/help"), COMMANDS.get("/list"),
            TRACK_COMMANDS.get("/track"), TRACK_COMMANDS.get("/untrack")};
    }
}
