package com.jiujitsuhub.jobfinder.mappers;


import com.jiujitsuhub.jobfinder.api.model.JobDetailsDTO;
import com.jiujitsuhub.jobfinder.domain.model.JobDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobDetailsMapper {
    JobDetailsDTO toDTO(JobDetails jobDetails);

    JobDetails toDAO(JobDetailsDTO jobDTO);

}
