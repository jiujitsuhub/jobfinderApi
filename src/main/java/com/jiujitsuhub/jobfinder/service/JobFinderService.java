package com.jiujitsuhub.jobfinder.service;

import com.jiujitsuhub.api.JobsApiDelegate;
import com.jiujitsuhub.jobfinder.domain.model.JiuJitsuJob;
import com.jiujitsuhub.jobfinder.repository.JobRepository;
import com.jiujitsuhub.model.JobDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobFinderService implements JobsApiDelegate {

    private final JobRepository jobRepository;

    @Override
    public ResponseEntity<JobDTO> createJob(JobDTO jobDTO) {

        jobRepository.save(
                JiuJitsuJob
                        .builder()
                        .title(jobDTO.getTitle())
                        .description(jobDTO.getDescription())
                        .startingDate(jobDTO.getStartingDate())
                        .endingDate(jobDTO.getEndingDate())
                        .build()
        );
        return ResponseEntity.ok(null);

    }
}
