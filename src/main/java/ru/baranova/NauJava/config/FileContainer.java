package ru.baranova.NauJava.config;
import java.util.Map;
import java.util.HashMap;
import ru.baranova.NauJava.objects.File;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class FileContainer {
    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public Map<Long, File> userContainer() {
        return new HashMap<>();
    }
}
