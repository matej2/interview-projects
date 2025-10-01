package com.doctor.file_processor.service;

import com.doctor.file_processor.domain.entity.AccessInfo;
import com.doctor.file_processor.repositories.AccessInfoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AccessInfoService {
    private final AccessInfoRepository accessInfoRepository;

    public AccessInfoService(AccessInfoRepository accessInfoRepository) {
        this.accessInfoRepository = accessInfoRepository;
    }

    public AccessInfo createEntry(String remoteIp) {
        AccessInfo newAccInfo = AccessInfo.builder()
                .created(new Date())
                .ip(remoteIp)
                .numOfCallsLastMinute(1L)
                .build();
        return accessInfoRepository.save(newAccInfo);
    }

    private void updateEntry(AccessInfo accessInfo) {
        Long currNumOfCalls = accessInfo.getNumOfCallsLastMinute() + 1;
        accessInfo.setNumOfCallsLastMinute(currNumOfCalls);
        accessInfoRepository.save(accessInfo);
    }

    public void updateAccessInfo(String remoteIp) {
        Optional<AccessInfo> existingAccessInfo = accessInfoRepository.findById(remoteIp);

        existingAccessInfo.ifPresent(
                this::updateEntry
        );
    }

    public void deleteAll() {
        accessInfoRepository.deleteAll();
    }

    public AccessInfo findByIp(String remoteIp) {
        Optional<AccessInfo> existing = accessInfoRepository.findById(remoteIp);
        return existing.orElse(null);
    }
}
