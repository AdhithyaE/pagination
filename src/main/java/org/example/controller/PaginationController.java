package org.example.controller;

import org.example.entity.PageEntity;
import org.example.response.CursorPageResponse;
import org.example.service.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/page")
public class PaginationController {
    @Autowired
    PaginationService paginationService;

    @GetMapping
    public ResponseEntity<Page<PageEntity>> getPages(
            @PageableDefault(size = 5)
            Pageable pageable) {

        Page<PageEntity> pages =
                paginationService.getAllPages(pageable);

        return ResponseEntity.ok(pages);
    }

    @GetMapping(
            value = "/cursor",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CursorPageResponse> getPagesByCursor(
            @RequestParam(required = false) Long afterUuid,
            @RequestParam(defaultValue = "5") int size) {

        CursorPageResponse cursorPageResponse =
                paginationService.getAllPagesByCursor(afterUuid, size);

        return ResponseEntity.ok(cursorPageResponse);
    }

    @GetMapping("/test")
    public String test() {
        return "Controller is working!";
    }

}
