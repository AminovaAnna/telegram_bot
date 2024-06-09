package pro.sky.telegrambot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class NotificationTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public long chat_id;
    public String text_msg;
    LocalDateTime notification_time;


    public NotificationTask(long id, long chat_id, String text_msg, LocalDateTime notification_time) {
        this.id = id;
        this.chat_id = chat_id;
        this.text_msg = text_msg;
        this.notification_time = notification_time;
    }

    public NotificationTask() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getChat_id() {
        return chat_id;
    }

    public void setChat_id(long chat_id) {
        this.chat_id = chat_id;
    }

    public String getText_msg() {
        return text_msg;
    }

    public void setText_msg(String text_msg) {
        this.text_msg = text_msg;
    }

    public LocalDateTime getNotification_time() {
        return notification_time;
    }

    public void setNotification_time(LocalDateTime notification_time) {
        this.notification_time = notification_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationTask that = (NotificationTask) o;
        return id == that.id && chat_id == that.chat_id && Objects.equals(text_msg, that.text_msg) && Objects.equals(notification_time, that.notification_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chat_id, text_msg, notification_time);
    }

    @Override
    public String toString() {
        return "NotificationTask{" +
                "id=" + id +
                ", chat_id=" + chat_id +
                ", text_msg='" + text_msg + '\'' +
                ", notification_time=" + notification_time +
                '}';
    }
}
