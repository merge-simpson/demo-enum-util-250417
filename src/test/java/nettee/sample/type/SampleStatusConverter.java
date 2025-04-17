package nettee.sample.type;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SampleStatusConverter implements AttributeConverter<SampleStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(SampleStatus sampleStatus) {
        return sampleStatus != null ? sampleStatus.getCode() : null;
    }

    @Override
    public SampleStatus convertToEntityAttribute(Integer code) {
        return SampleStatus.valueOf(code);
    }
}
