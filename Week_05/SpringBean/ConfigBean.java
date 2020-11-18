import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan
public class ConfigBean {

    @Bean
    public ConfigBean create() {
        return new ConfigBean();
    }
}