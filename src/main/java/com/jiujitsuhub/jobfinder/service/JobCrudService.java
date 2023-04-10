package com.jiujitsuhub.jobfinder.service;

import com.jiujitsuhub.jobfinder.api.JobsApiDelegate;
import com.jiujitsuhub.jobfinder.api.model.JobDTO;
import com.jiujitsuhub.jobfinder.domain.model.UserReference;
import com.jiujitsuhub.jobfinder.mappers.JiuJitsuJobMapper;
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
    private final JiuJitsuJobMapper jiuJitsuJobMapper;

    private final JwtDecoder jwtDecoder;

    @Autowired
    public JobCrudService(JobRepository jobRepository, JiuJitsuJobMapper jiuJitsuJobMapper, JwtDecoder jwtDecoder) {
        this.jobRepository = jobRepository;
        this.jiuJitsuJobMapper = jiuJitsuJobMapper;
        this.jwtDecoder = jwtDecoder;
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
        String userID = (String) jwtDecoder
                .decode(JwtUtils.getJwt(JwtUtils.getCurrentRequest()))
                .getClaims()
                .get("sub");
        jobRepository.save(jiuJitsuJobMapper.toDAO(jobDTO, new UserReference(userID)));
        return ResponseEntity.ok(null);

    }
}
