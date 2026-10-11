package org.example.service;

import org.example.entity.PageEntity;
import org.example.repository.PaginationRepo;
import org.example.response.CursorPageResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaginationService {
    @Autowired
    PaginationRepo paginationRepo;


    public Page<PageEntity> getAllPages(Pageable pageable) {
        return paginationRepo.findAll(pageable);
    }

    public CursorPageResponse getAllPagesByCursor(Long afterUuid, int size) {
        PageRequest pagable = PageRequest.of(0, size+1);

        List<PageEntity> pageEntityList;
        if(afterUuid==null){
            pageEntityList = paginationRepo.findAllByOrderByUuid(pagable);
        } else {
            pageEntityList = paginationRepo.findALLByUuidGreaterThanOrderByUuid(afterUuid, pagable);
        }

        boolean hasNext = pageEntityList.size() > size;

        if(hasNext){
            pageEntityList = pageEntityList.subList(0, size);
        }

        Long nextCursor = pageEntityList.isEmpty() ? null : pageEntityList.get(
                pageEntityList.size()-1
        ).getUuid();

        return new CursorPageResponse(nextCursor,hasNext, pageEntityList);
    }
}
