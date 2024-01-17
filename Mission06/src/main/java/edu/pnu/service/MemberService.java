package edu.pnu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.domain.RequestLog;
import edu.pnu.persistence.MemberRepository;
import edu.pnu.persistence.RequestLogRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final RequestLogRepository requestLogRepository;

    // 모든 멤버 조회
    public List<Member> getAllMembers() {
        List<Member> list;
        String sqlString = "getAllMembers";
        
        try {
            // 모든 멤버를 조회하여 리스트에 저장
            list = memberRepository.findAll();
        } catch (Exception e) {
            // 조회 중 오류 발생 시 예외 메시지 저장
            list = null;
            sqlString = e.getMessage();
        }
        // 요청 로그 저장
        requestLogRepository.save(RequestLog.builder()
                .method("GET")
                .sqlString(sqlString)
                .success(list != null)
                .build());
        return list;
    }

    // 특정 ID의 멤버 조회
    public Member getMemberById(Long id) {
        Member member = null;
        String sqlString = "getMemberById";
        try {
            // ID로 멤버를 조회하여 Optional로 반환
            Optional<Member> opt = memberRepository.findById(id);
            if (opt.isPresent()) {
                // Optional이 존재하면 해당 멤버를 가져옴
                member = opt.get();
            }
        } catch (Exception e) {
            // 조회 중 오류 발생 시 예외 메시지 저장
            sqlString = e.getMessage();
        }
        // 요청 로그 저장
        requestLogRepository.save(RequestLog.builder()
                .method("GET")
                .sqlString(sqlString + "(" + id + ")")
                .success(member != null)
                .build());
        return member;
    }

    // 멤버 추가
    public Member addMember(Member member) {
        String sqlString = "addMember";
        try {
            // 멤버를 저장하고 저장된 멤버를 반환
            member = memberRepository.save(member);
        } catch (Exception e) {
            // 저장 중 오류 발생 시 예외 메시지 저장
            sqlString = e.getMessage();
        }
        // 요청 로그 저장
        requestLogRepository.save(RequestLog.builder()
                .method("POST")
                .sqlString(sqlString + "(" + member + ")")
                .success(member != null)
                .build());
        return member;
    }

    // 멤버 삭제
    public long removeMember(Long id) {
        String sqlString = "removeMember";
        try {
            // ID로 멤버 삭제
            memberRepository.deleteById(id);
        } catch (Exception e) {
            // 삭제 중 오류 발생 시 예외 메시지 저장
            sqlString = e.getMessage();
            return 0;
        }
        // 요청 로그 저장
        requestLogRepository.save(RequestLog.builder()
                .method("DELETE")
                .sqlString(sqlString + "(" + id + ")")
                .success(true)
                .build());
        return 1L;
    }

    // 멤버 업데이트
    public Member updateMember(Member member) {
        String sqlString = "updateMember";
        try {
            // 멤버를 업데이트하고 업데이트된 멤버를 반환
            member = memberRepository.save(member);
        } catch (Exception e) {
            // 업데이트 중 오류 발생 시 예외 메시지 저장
            sqlString = e.getMessage();
        }
        // 요청 로그 저장
        requestLogRepository.save(RequestLog.builder()
                .method("PUT")
                .sqlString(sqlString + "(" + member + ")")
                .success(member != null)
                .build());
        return member;
    }
}