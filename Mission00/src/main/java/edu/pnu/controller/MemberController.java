package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;

@RestController
public class MemberController {
    private List<MemberVO> list;

    public MemberController() {
        System.out.println("===> MemberController 생성");
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(MemberVO.builder()
                    .id(i)
                    .name("이름 : " + i)
                    .pass("비밀번호 : " + i)
                    .regidate(new Date())
                    .build());
        }
    }

    @GetMapping("/member") // 회원 조회
    public ResponseEntity<?> getMembers(@PathVariable(required = false) Integer id) {
        if (id != null) {
            // 특정 회원 조회
            MemberVO member = findMemberById(id);
            if (member != null) {
                return new ResponseEntity<>(member, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("회원을 찾을 수 없습니다", HttpStatus.NOT_FOUND);
            }
        } else {
            // 모든 회원 조회
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    private MemberVO findMemberById(Integer id) {
        for (MemberVO m : list) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    // 특정 회원 데이터가 존재하는지 확인
    private MemberVO getMember(Integer id) {
        for (MemberVO m : list) {
            if (m.getId() == id)
                return m;
        }
        return null;
    }

    @PostMapping("/member") // 멤버 추가
    public int addMember(@RequestBody MemberVO memberVO) {
        if (getMember(memberVO.getId()) != null)
            return 0;
        list.add(memberVO);
        return 1;
    }

    @PutMapping("/member") // 멤버 정보 수정
    public int updateMembers(@RequestBody MemberVO memberVO) {
        MemberVO existingMember = getMember(memberVO.getId());
        if (existingMember == null)
            return 0;
        existingMember.setName(memberVO.getName());
        existingMember.setPass(memberVO.getPass());
        return 1;
    }

    @DeleteMapping("/member/{id}") // 멤버 정보 삭제
    public int removeMember(@PathVariable Integer id) {
        MemberVO m = getMember(id);
        if (m != null) {
            list.remove(m);
            return 1;
        } else
            return 0;
    }
}