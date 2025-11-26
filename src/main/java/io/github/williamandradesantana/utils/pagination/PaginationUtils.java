package io.github.williamandradesantana.utils.pagination;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PaginationUtils {
    public static Pageable buildPagination(Integer page, Integer pageSize, String fieldToBeSorted) {
        return PageRequest.of(page, pageSize, Sort.by(fieldToBeSorted).ascending());
    }
}
