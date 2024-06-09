package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.listener.TelegramBotUpdatesListener;


import java.util.LinkedList;


@Service
public class NotificationTaskService {

    private final LinkedList<NotificationTask> notificationTasks = new LinkedList<>();
    private final TelegramBot telegramBot;

    private final TelegramBotUpdatesListener listener;

    private OkHttpClient client;
    TelegramBot bot = new TelegramBot.Builder("mini_universe").okHttpClient(client).build();

    public NotificationTaskService(TelegramBot telegramBot, TelegramBotUpdatesListener listener, OkHttpClient client) {
        this.telegramBot = telegramBot;
        this.listener = listener;
        this.client = client;
    }


    // добавление NotificationTask в очередь
    public void addNotificationTask(NotificationTask task) {
        notificationTasks.offer(task);
    }


}



class NotificationTask {
    private long chatId;
    private String message;

    public NotificationTask(long chatId, String message) {
        this.chatId = chatId;
        this.message = message;
    }


    public long getChatId() {
        return chatId;
    }

    public String getMessage() {
        return message;
    }
}


