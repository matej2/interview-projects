package com.doctor.file_processor.service;

import com.doctor.file_processor.domain.entity.AccessInfo;
import com.doctor.file_processor.repositories.AccessInfoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccessInfoService {
    private AccessInfoRepository accessInfoRepository;

    public AccessInfoService(AccessInfoRepository accessInfoRepository) {
        this.accessInfoRepository = accessInfoRepository;
    }

    private void createEntry(AccessInfo accessInfo) {
        accessInfoRepository.save(accessInfo);
    }

    private void updateEntry(AccessInfo accessInfo) {
        Long currNumOfCalls = accessInfo.getNumOfCallsLastMinute() + 1;
        accessInfo.setNumOfCallsLastMinute(currNumOfCalls);
        accessInfoRepository.save(accessInfo);
    }

    public void updateAccessInfo(AccessInfo inputAccInfo) {
        Optional<AccessInfo> existingAccessInfo = accessInfoRepository.findById(inputAccInfo.getIp());

        existingAccessInfo.ifPresentOrElse(
                this::updateEntry,
                () -> createEntry(inputAccInfo)
                );

    }
}
