package com.dokuny.mini_campus.admin.service.impl;

import com.dokuny.mini_campus.admin.dto.BannerAddInput;
import com.dokuny.mini_campus.admin.dto.BannerDto;
import com.dokuny.mini_campus.admin.entity.Banner;
import com.dokuny.mini_campus.admin.exception.BannerNotExistException;
import com.dokuny.mini_campus.admin.repository.BannerRepository;
import com.dokuny.mini_campus.admin.repository.cond.BannerCondition;
import com.dokuny.mini_campus.admin.service.BannerService;
import com.dokuny.mini_campus.admin.type.BannerTargetStatus;
import com.dokuny.mini_campus.commons.dto.SearchInput;
import com.dokuny.mini_campus.commons.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;
    private final FileUploadService fileUploadService;

    @Transactional
    @Override
    public boolean add(BannerAddInput input) {

        String urlPath = fileUploadService.uploadImgFile(input.getImg());

        bannerRepository.save(Banner.builder()
                .name(input.getName())
                .openYn(input.isOpenYn())
                .alterText(input.getAlterText())
                .linkUrl(input.getLinkUrl())
                .target(BannerTargetStatus.valueOf(input.getTarget()))
                .sortOrder(input.getSortOrder())
                .imgUrl(urlPath)
                .build());

        return true;
    }

    @Transactional
    @Override
    public boolean update(BannerDto dto, MultipartFile img) {
        if (img.getSize()>0 && !img.isEmpty()) {
            dto.setImgUrl(fileUploadService.uploadImgFile(img));
        }

        Banner banner = bannerRepository.findById(dto.getId())
                .orElseThrow(() -> new BannerNotExistException("존재하지 않는 배너입니다."));

        dto.changeEntity(banner);

        return true;
    }

    @Transactional
    @Override
    public boolean remove(String idList) {
        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");

            for (String s : ids) {
                long id = 0L;

                try {
                    id = Long.parseLong(s);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (id > 0) {
                    bannerRepository.deleteById(id);
                }
            }
        }

        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<BannerDto> list(SearchInput input, Pageable pageable) {
        return bannerRepository.findAllByDto(BannerCondition.of(input), pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public BannerDto detail(Long id) {
        Banner banner =
                bannerRepository
                        .findById(id)
                        .orElseThrow(() -> new BannerNotExistException("존재하지 않는 배너입니다."));
        return BannerDto.of(banner);
    }

    @Override
    public List<BannerDto> front() {
        return bannerRepository.getFrontBanners();
    }
}
