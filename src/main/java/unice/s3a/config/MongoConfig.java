package unice.s3a.config;

import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoTypeMapper;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.net.UnknownHostException;

/**
 * The type Mongo config.
 */
@Configuration
@Lazy
    // @EnableMongoRepositories(basePackageClasses = Application.class)
class MongoConfig {
    /**
     * Mongo converter mapping mongo converter.
     * @return the mapping mongo converter
     * @throws UnknownHostException the unknown host exception
     */
    @Bean
    public MappingMongoConverter mongoConverter() throws UnknownHostException {
        MappingMongoConverter converter = new MappingMongoConverter(mongoDbFactory(), mongoMappingContext());
        converter.setTypeMapper(mongoTypeMapper());
        return converter;
    }

    /**
     * Mongo db factory mongo db factory.
     * @return the mongo db factory
     * @throws UnknownHostException the unknown host exception
     */
    @Bean
    public MongoDbFactory mongoDbFactory() throws UnknownHostException {
        return new SimpleMongoDbFactory(new Mongo(), "bbus");
    }

    /**
     * Mongo mapping context mongo mapping context.
     * @return the mongo mapping context
     */
    @Bean
    public MongoMappingContext mongoMappingContext() {
        return new MongoMappingContext();
    }

    /**
     * Mongo template mongo template.
     * @return the mongo template
     * @throws UnknownHostException the unknown host exception
     */
    @Bean
    public MongoTemplate mongoTemplate() throws UnknownHostException {
        MongoTemplate template = new MongoTemplate(mongoDbFactory(), mongoConverter());
        return template;
    }

    /**
     * Mongo type mapper mongo type mapper.
     * @return the mongo type mapper
     */
    @Bean
    public MongoTypeMapper mongoTypeMapper() {
        return new DefaultMongoTypeMapper(null);
    }
}
