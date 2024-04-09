package config;

import model.MyDataSender;
import model.StringValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.function.Consumer;

class DataSenderKafka implements MyDataSender {
    private static final Logger log = LoggerFactory.getLogger(DataSenderKafka.class);

    private final KafkaTemplate<String, StringValue> template;
    private final Consumer<StringValue> sendAck;

    private final String topic;

    public DataSenderKafka(String topic, KafkaTemplate<String, StringValue> template, Consumer<StringValue> sendAsk) {
        this.topic = topic;
        this.template = template;
        this.sendAck = sendAsk;
    }

    @Override
    public void send(StringValue value) {
        try {
            log.info("valye:{}", value);
            template.send(topic, value);/*.whenComplete((result, ex) -> {
               if(ex == null){
                   log.info("message id:{} was sent< offset:{}", value.getId(), result.getRecordMetadata().offset());
                   sendAck.accept(value);
               }else {
                   log.error("massage id:{} was not sent", value.getId(), ex);
               }}*/
        } catch (Exception ex) {
            log.error("send error, value:{}", value, ex);
        }
    }


}
