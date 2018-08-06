package cn.ep.spring.boot.ch07.s10;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("custom.service")
public class CustomConfig {

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
