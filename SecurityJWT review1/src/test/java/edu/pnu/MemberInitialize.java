package edu.pnu;

import edu.pnu.domain.Member;
import edu.pnu.domain.Role;
import edu.pnu.persistence.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class MemberInitialize {
    @Autowired
    MemberRepository memberRepository; //Table member CRUD 인터페이스

    PasswordEncoder encoder = new BCryptPasswordEncoder(); // 비밀번호 암호화 인터페이스/구현체

    @Test
    public void doWork(){
        memberRepository.save(Member.builder()
                .username("member")
                .password(encoder.encode("abcd"))
                .role(Role.ROLE_MEMBER)
                .enabled(true)
                .build());

        memberRepository.save(Member.builder()
                        .username("manager")
                        .password(encoder.encode("abcd"))
                        .role(Role.ROLE_MANAGER)
                        .enabled(true)
                        .build());
        memberRepository.save(Member.builder()
                        .username("admin")
                        .password(encoder.encode("abcd"))
                        .role(Role.ROLE_ADMIN)
                        .enabled(true)
                        .build());
    }
}
