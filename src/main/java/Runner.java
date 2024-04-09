import model.ValueSorse;
import org.springframework.boot.CommandLineRunner;

public class Runner implements CommandLineRunner {
    private final ValueSorse valueSorse;

    public Runner(ValueSorse valueSorse){
        this.valueSorse = valueSorse;
    }
    @Override
    public  void run(String... args){
        valueSorse.generate();
    }
}
