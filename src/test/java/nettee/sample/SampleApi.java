package nettee.sample;

import nettee.sample.type.SampleStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("samples")
public class SampleApi {

    private final SampleRepository repository;

    public SampleApi(SampleRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Sample create() {
        return repository.save(new Sample(SampleStatus.ACTIVE));
    }
}