package at.fhtw.swen2.tutorial.presentation.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class ApplicationErrorEvent extends ApplicationEvent {
    @Getter private String message;
    @Getter private Throwable throwable;

    public ApplicationErrorEvent(Object source, String message, Throwable throwable) {
        super(source);

        this.message = message;
        this.throwable = throwable;
    }
}
