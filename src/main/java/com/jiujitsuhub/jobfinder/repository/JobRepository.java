package com.jiujitsuhub.jobfinder.repository;

import com.jiujitsuhub.jobfinder.domain.model.JiuJitsuJob;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JobRepository extends CrudRepository<JiuJitsuJob, Long> {
    @Override
    List<JiuJitsuJob> findAll();
}
