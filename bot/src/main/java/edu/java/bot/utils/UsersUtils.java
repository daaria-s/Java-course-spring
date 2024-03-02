package edu.java.bot.utils;

import java.util.HashSet;

public class UsersUtils {

    private UsersUtils() {
    }

    static final HashSet<Long> ALL_USERS = new HashSet<>();

    static public void addNewUser(long chatId) {
        ALL_USERS.add(chatId);
    }

    static public void removeUser(long chatId) {
        ALL_USERS.remove(chatId);
    }

    static public boolean checkUser(long chatId) {
        return ALL_USERS.contains(chatId);
    }

}
