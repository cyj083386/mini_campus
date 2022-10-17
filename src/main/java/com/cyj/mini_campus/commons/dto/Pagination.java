package com.dokuny.mini_campus.commons.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class Pagination {

    private int start;
    private int end;

    private int curNum;

    private int totalPages;

    private List contents;

    private boolean first;
    private boolean last;


    public Pagination(Page page) {
        start = (int) (Math.floor(page.getNumber() / 5) * 5 + 1);
        end = start + 4 < page.getTotalPages() ? start + 4 : page.getTotalPages();
        contents = page.getContent();
        curNum = page.getNumber();
        totalPages = page.getTotalPages();
        first = page.isFirst();
        last = page.isLast();

        if (page.getTotalPages() == 0) {
            end=1;
        }

    }


}
