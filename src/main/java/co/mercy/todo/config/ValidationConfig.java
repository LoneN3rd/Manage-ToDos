package co.mercy.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig {

    // will be triggered before persisting data to DB,
    // and will trigger a constraint violation exception if values passed are null
    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener(){
        return new ValidatingMongoEventListener(validator());
    }

    // LocalValidatorFactoryBean is the implementation class for the validator
    @Bean
    public LocalValidatorFactoryBean validator(){
        return new LocalValidatorFactoryBean();
    }
}
