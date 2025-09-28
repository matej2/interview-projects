package com.doctor.file_processor.repositories;

import com.doctor.file_processor.domain.entity.AccessInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessInfoRepository extends CrudRepository<AccessInfo, String>{
}

