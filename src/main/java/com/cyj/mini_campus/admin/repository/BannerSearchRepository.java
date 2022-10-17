package com.dokuny.mini_campus.admin.repository;

import com.dokuny.mini_campus.admin.dto.BannerDto;
import com.dokuny.mini_campus.admin.repository.cond.BannerCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BannerSearchRepository {
    Page<BannerDto> findAllByDto(BannerCondition cond, Pageable pageable);
}
