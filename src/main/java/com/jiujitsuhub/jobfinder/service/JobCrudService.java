package com.jiujitsuhub.jobfinder.service;

import com.jiujitsuhub.jobfinder.api.JobsApiDelegate;
import com.jiujitsuhub.jobfinder.api.model.JobDTO;
import com.jiujitsuhub.jobfinder.mappers.JiuJitsuJobMapper;
import com.jiujitsuhub.jobfinder.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobCrudService implements JobsApiDelegate {

    private JobRepository jobRepository;
    private JiuJitsuJobMapper jiuJitsuJobMapper;

    @Autowired
    public JobCrudService(JobRepository jobRepository, JiuJitsuJobMapper jiuJitsuJobMapper) {
        this.jobRepository = jobRepository;
        this.jiuJitsuJobMapper = jiuJitsuJobMapper;
    }

    @Override
    public ResponseEntity<List<JobDTO>> getAll() {
        return ResponseEntity.ok(jobRepository
                .findAll()
                .stream()
                .map(jiuJitsuJobMapper::toDTO)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<JobDTO> createJob(JobDTO jobDTO) {

        jobRepository.save(jiuJitsuJobMapper.toDAO(jobDTO));
        return ResponseEntity.ok(null);

    }
}
