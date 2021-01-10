package koreahacks.woowabros.uniconn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.config.EnableReactiveElasticsearchAuditing;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;

@EnableReactiveElasticsearchRepositories
@EnableReactiveElasticsearchAuditing
@SpringBootApplication
public class UniconnApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniconnApplication.class, args);
    }

}
