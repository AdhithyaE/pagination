package org.example.repository;

import org.example.entity.PageEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PaginationRepo extends JpaRepository<PageEntity, Long> {

    List<PageEntity> findAllByOrderByUuid(Pageable page);
    List<PageEntity> findALLByUuidGreaterThanOrderByUuid(Long uuid, Pageable pageable);
}
