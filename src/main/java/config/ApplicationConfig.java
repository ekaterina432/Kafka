package config;


import com.fasterxml.jackson.databind.JsonSerializer;
import model.MyDataSender;
import model.StringValue;
import model.StringValueSourse;


import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.JacksonUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.slf4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;


public class ApplicationConfig {
    private static final Logger log = LoggerFactory.getLogger(ApplicationConfig.class);
    public final String topicName;


    public ApplicationConfig(@Value("${application.kafka.topic}") String topicName) {
        this.topicName = topicName;
    }
    //один обждектмайкр исользуется в кафке и для сериализациии и для десириализаии, и используетяв дургих чатях приложения апример в интерфейсе
   @Bean
    public ObjectMapper objectMapper(){
        return JacksonUtils.enhancedObjectMapper();
    }

    @Bean
    public ProducerFactory<String, StringValue> producerFactory(KafkaProperties kafkaProperties, ObjectMapper mapper) {
        Map<String, Object> props = new HashMap<>();
        /*props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        props.put(ProducerConfig.CLIENT_ID_CONFIG, kafkaProperties.getClientId());*/
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        var kafkaProducerFactory = new DefaultKafkaProducerFactory<String, StringValue>(props);
        kafkaProducerFactory.setValueSerializer(new org.springframework.kafka.support.serializer.JsonSerializer<>(mapper));
        return kafkaProducerFactory;
    }

    @Bean
    public KafkaTemplate<String, StringValue> kafkaTemplate(
        ProducerFactory<String, StringValue> producerFactory)
    {
            return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public NewTopic topic (){
        return TopicBuilder.name(topicName).partitions(1).replicas(1).build();
    }

    @Bean
    public DataSenderKafka dataSender(NewTopic topic, KafkaTemplate<String, StringValue> kafkaTemplate, Logger log){
        return  new DataSenderKafka(
                topic.name(),
                kafkaTemplate,
                stringValue -> log.info("asked, value:{}", stringValue));

    }

    @Bean
    public StringValueSourse stringValueSourse(MyDataSender dataSenderMy){
        return  new StringValueSourse(dataSenderMy);
    }

}

