package com.dokuny.mini_campus.admin.controller;


import com.dokuny.mini_campus.admin.dto.MemberDetailDto;
import com.dokuny.mini_campus.admin.dto.MemberListDto;
import com.dokuny.mini_campus.admin.service.AdminMemberService;
import com.dokuny.mini_campus.commons.dto.Pagination;
import com.dokuny.mini_campus.commons.dto.SearchInput;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequiredArgsConstructor
@Controller
public class AdminMemberController {

    private final AdminMemberService adminMemberService;

    @GetMapping({"/admin/member/list.do"})
    public String memberSearch(Model model, SearchInput input,
                               @PageableDefault(size = 10, page = 0) Pageable pageable) {
        Page<MemberListDto> page = adminMemberService.searchMembers(input, pageable);
        model.addAttribute("page", new Pagination(page));
        model.addAttribute("members", page.getContent());
        return "admin/member/list";
    }

    @GetMapping("/admin/member/detail.do")
    public String memberDetail(Model model, @RequestParam("id") String email,  @PageableDefault(size = 10, page = 0) Pageable pageable) {
        MemberDetailDto dto = adminMemberService.getMemberDetail(email, pageable);

        model.addAttribute("memberDetail", dto);
        model.addAttribute("page", new Pagination(dto.getLoginHistories()));

        return "admin/member/member_detail";
    }

    @GetMapping("/admin/member/edit.do")
    public String editMember(Model model,MemberDetailDto dto) {
        adminMemberService.editMemberDetail(dto);
        return "redirect:/admin/member/detail.do?id="+dto.getEmail();
    }
}
