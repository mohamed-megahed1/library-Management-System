package com.mohamedahmed.libraryManagementSystem.mapper;

import com.mohamedahmed.libraryManagementSystem.dto.BorrowingRecordDto;
import com.mohamedahmed.libraryManagementSystem.entities.BorrowingRecord;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",

        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface BorrowingRecordMapper {

    BorrowingRecordDto fromBorrowingRecordToBorrowingRecordDto(BorrowingRecord borrowingRecord);

    BorrowingRecord fromBorrowingRecordDtoToBorrowingRecord(BorrowingRecordDto borrowingRecordDto);

    List<BorrowingRecordDto> fromBorrowingRecordToBorrowingRecordDto(List<BorrowingRecord>borrowingRecords);
}
