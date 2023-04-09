package com.jiujitsuhub.jobfinder.mappers;


import com.jiujitsuhub.jobfinder.api.model.JobDTO;
import com.jiujitsuhub.jobfinder.domain.model.JiuJitsuJob;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JiuJitsuJobMapper {
    @Mapping(source = "payment.amount", target = "paymentAmount")
    @Mapping(source = "payment.type", target = "paymentType")
    JobDTO toDTO(JiuJitsuJob jiuJitsuJob);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "paymentAmount", target = "payment.amount")
    @Mapping(source = "paymentType", target = "payment.type")
    JiuJitsuJob toDAO(JobDTO userDTO);
}
