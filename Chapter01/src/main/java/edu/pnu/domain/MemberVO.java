package edu.pnu.domain;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder //체인코딩을 이용할 수 있게 함

public class MemberVO {
	private int id;
	private String pass;
	private String name;
	private Date regidate = new Date();
}
