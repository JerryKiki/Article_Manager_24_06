package org.koreait.member.entity;

public class Member {
    private int id = 0;
    private String regDate = "";
    private String loginId = "";
    private String loginPw = "";
    private String name = "";

    public Member(int id, String regDate, String loginId, String loginPw, String name) {
        this.id = id;
        this.regDate = regDate;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
    }
}
