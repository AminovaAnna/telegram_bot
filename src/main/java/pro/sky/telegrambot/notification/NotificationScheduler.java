package pro.sky.telegrambot.notification;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class NotificationScheduler {
    private static final Logger log = LoggerFactory.getLogger(NotificationScheduler.class);
    private final TelegramBot telegramBot;
    private final NotificationTaskRepository repository;

    public NotificationScheduler(TelegramBot telegramBot, NotificationTaskRepository repository) {
        this.telegramBot = telegramBot;
        this.repository = repository;
    }
    @Scheduled(cron = "0 0/1 * * * *")
    public void checkNotifications(){

        repository.findNotificationTaskByDate(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .forEach(notificationTask -> {
                    telegramBot.execute(new SendMessage(notificationTask.getChat_id(), notificationTask.getText_msg()));
                    log.info("Message has been sent");
                    repository.deleteById(notificationTask.getId());
                });

    }
}
