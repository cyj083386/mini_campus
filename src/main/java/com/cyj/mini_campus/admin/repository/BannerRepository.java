package com.dokuny.mini_campus.admin.repository;

import com.dokuny.mini_campus.admin.dto.BannerDto;
import com.dokuny.mini_campus.admin.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BannerRepository extends JpaRepository<Banner, Long>,BannerSearchRepository {


    @Query("select new com.dokuny.mini_campus.admin.dto.BannerDto(b.alterText,b.linkUrl,b.imgUrl,b.target) from Banner b where b.openYn = true order by b.sortOrder asc")
    List<BannerDto> getFrontBanners();

}
