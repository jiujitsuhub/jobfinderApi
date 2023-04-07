package com.jiujitsuhub.jobfinder.repository;

import com.jiujitsuhub.jobfinder.domain.model.JiuJitsuJob;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<JiuJitsuJob, Long> {
}
