package com.jiujitsuhub.jobfinder.mappers;


import com.jiujitsuhub.jobfinder.api.model.JobDTO;
import com.jiujitsuhub.jobfinder.domain.model.JiuJitsuJob;
import com.jiujitsuhub.jobfinder.domain.model.UserReference;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JiuJitsuJobMapper {
    @Mapping(source = "payment.amount", target = "paymentAmount")
    @Mapping(source = "payment.type", target = "paymentType")
    @Mapping(source = "creator.userId", target = "creator")
    JobDTO toDTO(JiuJitsuJob jiuJitsuJob);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "userDTO.paymentAmount", target = "payment.amount")
    @Mapping(source = "userDTO.paymentType", target = "payment.type")
    @Mapping(source = "userReference.userId", target = "creator.userId")
    JiuJitsuJob toDAO(JobDTO userDTO, UserReference userReference);
}
