import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Predicate;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Properties;

public class KafkaStreamExemple {
    public static void main(String[] args) {
        // Настройка Kafka Streams приложения
        StreamsBuilder streamsBuilder = new StreamsBuilder();

        // Чтение данных из топика "input-topic"
        KStream<String, String> inputStream = streamsBuilder.stream("input-topic");

        // Фильтрация данных с помощью Predicate
        KStream<String, String> filteredStream = inputStream.filter((key, value) -> {
            // Ваш фильтр здесь
            return value.contains("some-condition");
        });

        // Запись отфильтрованных данных в топик "output-topic"
        filteredStream.to("output-topic", Produced.with(Serdes.String(), Serdes.String()));

        Properties streamsConfig = new Properties();
        // The name must be unique on the Kafka cluster
        streamsConfig.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-example");
        // Brokers
        streamsConfig.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, args[0]);
        // SerDes for key and values
       /* streamsConfig.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        streamsConfig.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        // Запуск Kafka Streams приложения
        KafkaStreams streams = new KafkaStreams(streamsBuilder.build(),
                // Настройка конфигурации Kafka Streams
                StreamsConfig.builder.build());*/

        // Запуск и блокировка потока до его остановки
     /*   streams.start();
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));*/
    }
}