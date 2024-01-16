package edu.pnu.domain;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class MemberVO {
	private int id;
	private String pass;
	private String name;
    @Builder.Default
	private Date regidate = new Date();
}
