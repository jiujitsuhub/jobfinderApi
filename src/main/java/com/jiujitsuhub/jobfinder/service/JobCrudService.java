package com.jiujitsuhub.jobfinder.service;

import com.jiujitsuhub.jobfinder.api.JobsApiDelegate;
import com.jiujitsuhub.jobfinder.api.model.JobDTO;
import com.jiujitsuhub.jobfinder.domain.model.JiuJitsuJob;
import com.jiujitsuhub.jobfinder.domain.model.PublishedStatus;
import com.jiujitsuhub.jobfinder.domain.model.UserReference;
import com.jiujitsuhub.jobfinder.exception.JobNotFoundException;
import com.jiujitsuhub.jobfinder.mappers.JobDetailsMapper;
import com.jiujitsuhub.jobfinder.repository.JobRepository;
import com.jiujitsuhub.jobfinder.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobCrudService implements JobsApiDelegate {

    private final JobRepository jobRepository;
    private final JobDetailsMapper jobDetailsMapper;
    private final JwtDecoder jwtDecoder;

    @Autowired
    public JobCrudService(JobRepository jobRepository, JobDetailsMapper jobDetailsMapper, JwtDecoder jwtDecoder) {
        this.jobRepository = jobRepository;
        this.jobDetailsMapper = jobDetailsMapper;
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    public ResponseEntity<List<JobDTO>> getAll() {
        return ResponseEntity.ok(jobRepository
                .findAll()
                .stream()
                .map(job -> JobDTO
                        .builder()
                        .id(job.getId())
                        .jobDetails(jobDetailsMapper.toDTO(job.getDetails()))
                        .build())
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<JobDTO> findJobById(Long id) {
        return ResponseEntity.ok(jobRepository
                .findById(id)
                .map(job -> JobDTO
                        .builder()
                        .id(job.getId())
                        .jobDetails(jobDetailsMapper.toDTO(job.getDetails()))
                        .build())
                .orElseThrow(() -> new JobNotFoundException(String.format("Job with id %s was not found", id))));
    }

    @Override
    public ResponseEntity<JobDTO> createJob(JobDTO jobDTO) {
        String userID = (String) jwtDecoder
                .decode(JwtUtils.getJwt(JwtUtils.getCurrentRequest()))
                .getClaims()
                .get("sub");
        JiuJitsuJob jiuJitsuJob = JiuJitsuJob
                .builder().publishedStatus(PublishedStatus.UNPUBLISHED)
                .creator(new UserReference(userID))
                .details(jobDetailsMapper.toDAO(jobDTO.getJobDetails()))
                .build();
        jobRepository
                .save(jiuJitsuJob);
        return ResponseEntity.ok(
                JobDTO
                        .builder()
                        .id(jiuJitsuJob.getId())
                        .jobDetails(jobDetailsMapper.toDTO(jiuJitsuJob.getDetails()))
                        .build());
    }

    @Override
    public ResponseEntity<JobDTO> updateJob(Long id, JobDTO jobDTO) {
        JiuJitsuJob job = jobRepository
                .findById(id)
                .orElseThrow(() -> new JobNotFoundException(String.format("Job with id %s was not found", id)));
        job.setDetails(jobDetailsMapper.toDAO(jobDTO.getJobDetails()));
        jobRepository.save(job);
        return ResponseEntity.ok(
                JobDTO
                        .builder()
                        .id(job.getId())
                        .jobDetails(jobDetailsMapper.toDTO(job.getDetails()))
                        .build());

    }
}
