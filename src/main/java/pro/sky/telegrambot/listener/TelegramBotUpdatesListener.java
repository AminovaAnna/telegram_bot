package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.pengrad.telegrambot.request.SendMessage;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;


import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    NotificationTask task = new NotificationTask();


    @Autowired
    private TelegramBot telegramBot;

    @Value("${telegram.bot.token}") // взяли токен из application.properties
    private String botToken;


    @PostConstruct
    public void init() {
        telegramBot = new TelegramBot(botToken);
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            String text = update.message().text();
            Pattern pattern = Pattern.compile("([0-9\\.\\:\\s]{16})(\\s)([\\W+]+)");
            Matcher matcher = pattern.matcher(text);
            if (matcher.matches()) {
                String date = matcher.group(1);
                String item = matcher.group(3);
                LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
                System.out.println("Дата и время: " + dateTime);
                task.setDateTime(dateTime);
                task.setItem(item);
            }
            if (text.equals("/start")) {
                Long chatId = update.message().chat().id(); // кому отправить
                String message = "Hello!"; // что отправить
                SendMessage sendMessage = new SendMessage(chatId, message); // создаём запрос на отправку сообщения
                telegramBot.execute(sendMessage); // выполняем запрос
            }

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
