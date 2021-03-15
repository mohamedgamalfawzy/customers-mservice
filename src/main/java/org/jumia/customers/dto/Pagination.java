package org.jumia.customers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Pagination <T>{
    private int page;
    private int pageSize;
    private int totalPages;
    private int totalCount;
    private List<T> items;
}
