package nettee.sample;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import nettee.sample.type.SampleStatus;
import nettee.sample.type.SampleStatusConverter;

@Entity
public class Sample {
    @Id
    @GeneratedValue
    private long id;

    @Convert(converter = SampleStatusConverter.class)
    private SampleStatus status;

    public Sample() {}

    public Sample(SampleStatus status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public SampleStatus getStatus() {
        return status;
    }
}