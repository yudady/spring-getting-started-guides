package devops04.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "custom")
public class CustomProperties {
    private boolean nextCall = false;
    private String[] urls = {"http://localhost:8080/module"};
    private long delay = 0;
    private String message = "This is a test message";
    private boolean delayMethod = false;
    private boolean HttpStatusSuccess = true;

    public boolean isNextCall() {
        return nextCall;
    }

    public void setNextCall(boolean nextCall) {
        this.nextCall = nextCall;
    }

    public String[] getUrls() {
        return urls;
    }

    public void setUrls(String[] urls) {
        this.urls = urls;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isDelayMethod() {
        return delayMethod;
    }

    public void setDelayMethod(boolean delayMethod) {
        this.delayMethod = delayMethod;
    }

    public boolean isHttpStatusSuccess() {
        return HttpStatusSuccess;
    }

    public void setHttpStatusSuccess(boolean httpStatusSuccess) {
        HttpStatusSuccess = httpStatusSuccess;
    }
}