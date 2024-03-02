package edu.java.bot.configuration;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import edu.java.bot.BotListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdatesListenerConfig {

    @Bean
    public UpdatesListener updatesListener(TelegramBot bot) {
        return new BotListener(bot);
    }
}
