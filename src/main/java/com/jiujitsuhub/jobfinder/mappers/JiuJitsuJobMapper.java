package com.jiujitsuhub.jobfinder.mappers;


import com.jiujitsuhub.jobfinder.api.model.JobDTO;
import com.jiujitsuhub.jobfinder.domain.model.JiuJitsuJob;
import com.jiujitsuhub.jobfinder.domain.model.UserReference;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface JiuJitsuJobMapper {
    @Mapping(source = "payment.amount", target = "paymentAmount")
    @Mapping(source = "payment.type", target = "paymentType")
    @Mapping(source = "creator.userId", target = "creator")
    JobDTO toDTO(JiuJitsuJob jiuJitsuJob);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "jobDTO.paymentAmount", target = "payment.amount")
    @Mapping(source = "jobDTO.paymentType", target = "payment.type")
    @Mapping(source = "userReference.userId", target = "creator.userId")
    JiuJitsuJob toDAO(JobDTO jobDTO, UserReference userReference);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(source = "jobDTO.paymentAmount", target = "payment.amount")
    @Mapping(source = "jobDTO.paymentType", target = "payment.type")
    JiuJitsuJob toDAO(JobDTO jobDTO);

    @Mapping(target = "id", source = "oldVersion.id")
    JiuJitsuJob updateJob(JiuJitsuJob oldVersion, @MappingTarget JiuJitsuJob newJobVersion);
}
