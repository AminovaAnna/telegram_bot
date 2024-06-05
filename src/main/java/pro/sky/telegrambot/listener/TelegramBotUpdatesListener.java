package pro.sky.telegrambot.listener;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pengrad.telegrambot.request.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

   private ExecutorService executor = Executors.newSingleThreadExecutor();

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            if (update.message().text().equals("/start")) {
                SendMessage message = new SendMessage(update.message().chat().id(), "Здравствуйте");
                executor.execute(() -> {
                    try {
                        telegramBot.execute(message);
                    } catch (TelegramApiException e) {
                        logger.error("Ошибка", e);
                    }
                });
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
