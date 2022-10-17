package com.dokuny.mini_campus.admin.service;

import com.dokuny.mini_campus.admin.dto.BannerAddInput;
import com.dokuny.mini_campus.admin.dto.BannerDto;
import com.dokuny.mini_campus.commons.dto.SearchInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BannerService {

    boolean add(BannerAddInput input);

    boolean update(BannerDto dto, MultipartFile file);

    boolean remove(String idList);

    Page<BannerDto> list(SearchInput input, Pageable pageable);

    BannerDto detail(Long id);

    List<BannerDto> front();



}
