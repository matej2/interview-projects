package com.doctor.file_processor.service;

import com.doctor.file_processor.domain.entity.AccessInfo;
import com.doctor.file_processor.repositories.AccessInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccessInfoServiceTest {
    @Mock
    private AccessInfoRepository accessInfoRepository;

    @InjectMocks
    private AccessInfoService accessInfoService;

    @Captor
    ArgumentCaptor<AccessInfo> accessInfoArgumentCaptor;
    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @Test
    void testThatCreateEntryCreatesEntityInDatabase() {
        when(accessInfoRepository.save(any())).thenReturn(new AccessInfo());

        accessInfoService.createEntry("192.168.0.250");

        verify(accessInfoRepository).save(accessInfoArgumentCaptor.capture());

        AccessInfo createdValue = accessInfoArgumentCaptor.getValue();
        assertThat(createdValue.getIp()).isEqualTo("192.168.0.250");
        assertThat(createdValue.getNumOfCallsLastMinute()).isEqualTo(1L);
        assertThat(createdValue.getCreated()).isInSameHourWindowAs(new Date());
    }

    @Test
    void testThatUpdateAccessInfoSetsNumOfCallsForExistingObject() {
        AccessInfo accessInfo = new AccessInfo();
        accessInfo.setNumOfCallsLastMinute(5L);
        accessInfo.setIp("127.0.0.1");
        accessInfo.setCreated(new Date());
        when(accessInfoRepository.findById(anyString())).thenReturn(Optional.of(accessInfo));

        accessInfoService.updateAccessInfo("127.0.0.1");

        verify(accessInfoRepository).findById(stringArgumentCaptor.capture());
        verify(accessInfoRepository).save(accessInfoArgumentCaptor.capture());

        assertThat(stringArgumentCaptor.getValue()).isEqualTo("127.0.0.1");
        AccessInfo capturedValue = accessInfoArgumentCaptor.getValue();
        assertThat(capturedValue.getIp()).isEqualTo("127.0.0.1");
        assertThat(capturedValue.getCreated()).isInSameHourWindowAs(new Date());
        assertThat(capturedValue.getNumOfCallsLastMinute()).isEqualTo(6L);
    }

    @Test
    void testThatDeletAllRemovedRecord() {
        accessInfoService.deleteAll();

        verify(accessInfoRepository).deleteAll();
    }

    @Test
    void testThatFindByIpReturnAccessInfo() {
        AccessInfo accessInfo = new AccessInfo();
        accessInfo.setIp("127.0.0.1");
        when(accessInfoRepository.findById(anyString())).thenReturn(Optional.of(accessInfo));

        AccessInfo result = accessInfoService.findByIp("127.0.0.1");

        assertThat(result).isNotNull();
    }

    @Test
    void testThatFindByIpReturnsNullForNonExistingRecord() {
        when(accessInfoRepository.findById(anyString())).thenReturn(Optional.empty());

        AccessInfo result = accessInfoService.findByIp("127.0.0.1");

        assertThat(result).isNull();
    }
}