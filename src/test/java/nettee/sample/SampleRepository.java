package nettee.sample;

import org.springframework.data.repository.CrudRepository;

public interface SampleRepository extends CrudRepository<Sample, Long> {
}