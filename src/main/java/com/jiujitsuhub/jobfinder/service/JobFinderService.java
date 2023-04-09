package com.jiujitsuhub.jobfinder.service;

import com.jiujitsuhub.jobfinder.api.JobsApiDelegate;
import com.jiujitsuhub.jobfinder.api.model.JobDTO;
import com.jiujitsuhub.jobfinder.domain.model.JiuJitsuJob;
import com.jiujitsuhub.jobfinder.domain.model.Payment;
import com.jiujitsuhub.jobfinder.domain.model.PaymentType;
import com.jiujitsuhub.jobfinder.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobFinderService implements JobsApiDelegate {

    private final JobRepository jobRepository;

    @Override
    public ResponseEntity<List<JobDTO>> getAll() {
        return ResponseEntity.ok(jobRepository
                .findAll()
                .stream()
                .map(jiuJitsuJob -> JobDTO
                        .builder()
                        .title(jiuJitsuJob.getTitle())
                        .build())
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<JobDTO> createJob(JobDTO jobDTO) {

        jobRepository.save(
                JiuJitsuJob
                        .builder()
                        .payment(new Payment(jobDTO.getPaymentAmount().intValue(), PaymentType.MONTHLY))
                        .title(jobDTO.getTitle())
                        .description(jobDTO.getDescription())
                        .startingDate(jobDTO.getStartingDate())
                        .endingDate(jobDTO.getEndingDate())
                        .build()
        );
        return ResponseEntity.ok(null);

    }
}
