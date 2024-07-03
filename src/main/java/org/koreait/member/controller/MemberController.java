package org.koreait.member.controller;

import org.koreait.Container;
import org.koreait.Util;
import org.koreait.member.entity.Member;
import org.koreait.system.controller.SystemController;

import java.util.HashMap;
import java.util.Map;

public class MemberController {

    private int lastMemberId = 0;
    private Map<Integer, Member> members = new HashMap<Integer, Member>();

    public void addTestMember(int howMany) {
        for (int i = 0; i < howMany; i++) {
            lastMemberId++;
            String testId = "test" + lastMemberId;
            String testPW = "pw" + lastMemberId;
            String testName = "name" + lastMemberId;
            String regDate = Util.getNow();
            Member testMember = new Member(lastMemberId, regDate, testId, testPW, testName);
            members.put(lastMemberId, testMember);
        }
    }

    public void join() {
        lastMemberId++;
        System.out.println("===== 회원가입 =====");
        System.out.print("loginId : ");
        String loginId = Container.getSc().nextLine();
        Member memberCheck = findMemberByLoginId(loginId);
        if (memberCheck != null) {
            System.out.println("이미 존재하는 아이디입니다. 다시 입력해주세요.");
            join();
        } else {
            System.out.print("PW : ");
            String loginPW = Container.getSc().nextLine();
            System.out.print("name : ");
            String name = Container.getSc().nextLine();
            String regDate = Util.getNow();
            Member newMember = new Member(lastMemberId, regDate, loginId, loginPW, name);
            members.put(lastMemberId, newMember);
            System.out.println("========= 회원가입 완료 ========");
            System.out.println("== 로그인 화면으로 돌아갑니다 ==");
        }
    }

    public Member logIn() {

        boolean loginStatus = false;

        while (!loginStatus) {
            System.out.print("회원이십니까? (yes/no) : ");
            String isMember = Container.getSc().nextLine();
            if (isMember.equals("yes")) {
                System.out.println("===== 로그인 =====");
                System.out.print("your loginId : ");
                String loginId = Container.getSc().nextLine();
                System.out.print("your loginPW : ");
                String loginPW = Container.getSc().nextLine();
                Member nowMember = findMemberByLoginId(loginId);
                if (nowMember != null) {
                    if (nowMember.getLoginPw().equals(loginPW)) {
                        System.out.println("===== 로그인 성공 =====");
                        loginStatus = true;
                        return nowMember;
                    } else {
                        System.out.println("비밀번호가 틀립니다. 다시 입력하십시오.");
                    }
                } else {
                    System.out.println("해당 아이디는 회원이 아닙니다. 다시 입력하십시오.");
                }
            } else if (isMember.equals("no")) {
                join();
            } else {
                System.out.println("yes 혹은 no를 입력해주세요.");
            }
        }
        return null;
    }

    public Member findMemberByLoginId(String loginId) {
        for (Member member : members.values()) {
            if (member.getLoginId().equals(loginId)) {
                return member;
            }
        }
        return null;
    }
}
