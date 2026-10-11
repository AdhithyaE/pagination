package org.example.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.entity.PageEntity;

import java.util.List;

@Data
@AllArgsConstructor
public class CursorPageResponse {
    private Long nextCursor;
    private boolean hasNext;
    private List<PageEntity> content;
}
