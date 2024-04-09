package model;

import org.apache.catalina.tribes.transport.DataSender;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;


public class StringValueSourse implements ValueSorse{
    private static final Logger log = LoggerFactory.getLogger(StringValueSourse.class);
    private final AtomicLong nextValue = new AtomicLong(1);
    private final MyDataSender valueCossumer;

    public StringValueSourse(MyDataSender dataSender){
        this.valueCossumer = dataSender;
    }

    @Override
    public void generate() {
        var executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> valueCossumer.send(makeValue()), 0, 1 , TimeUnit.SECONDS);
        log.info("generation started");
    }

    private StringValue makeValue(){
        var id = nextValue.getAndIncrement();
        return new StringValue(id, "stVal:" + id);
    }
}
