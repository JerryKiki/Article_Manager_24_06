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
    private Member loginedMember = null;

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
            System.out.print("PW check : ");
            String checkPW = Container.getSc().nextLine();
            if (!checkPW.equals(loginPW)) {
                System.out.println("비밀번호 확인에 실패했습니다. 다시 입력해주세요.");
                join();
            } else {
                System.out.print("name : ");
                String name = Container.getSc().nextLine();
                String regDate = Util.getNow();
                Member newMember = new Member(lastMemberId, regDate, loginId, loginPW, name);
                members.put(lastMemberId, newMember);
                System.out.println("========= 회원가입 완료 ========");
                System.out.println("== 이전 화면으로 돌아갑니다 ==");
            }
        }
    }

    public void logIn() {

        //boolean loginStatus = false;

        //while (!loginStatus) {
            System.out.println("===== 로그인 =====");
            System.out.print("your loginId : ");
            String loginId = Container.getSc().nextLine();
            System.out.print("your loginPW : ");
            String loginPW = Container.getSc().nextLine();
            Member nowMember = findMemberByLoginId(loginId);
            if (nowMember != null) {
                if (nowMember.getLoginPw().equals(loginPW)) {
                    System.out.printf("===== %s님 환영합니다 =====\n", nowMember.getName());
                    //loginStatus = true;
                    loginedMember = nowMember;
                } else {
                    System.out.println("비밀번호가 틀립니다. 다시 입력하십시오.");
                    //loginedMember = null;
                }
            } else {
                System.out.println("해당 아이디는 회원이 아닙니다. 다시 입력하십시오.");
                System.out.println("가입을 원하신다면 명령어에 member join을 입력해주십시오.");
                //loginedMember = null;
            }
        //}
    }

    public void logOut() {
        System.out.println("== 로그아웃 합니다 ==");
        loginedMember = null;
    }

    public Member findMemberByLoginId(String loginId) {
        for (Member member : members.values()) {
            if (member.getLoginId().equals(loginId)) {
                return member;
            }
        }
        return null;
    }

    public void myPage(Member nowMember) {
        for (Member member : members.values()) {
            if (nowMember == member) {
                System.out.println("가입 순서 : " + member.getId());
                System.out.println("가입 일시 : " + member.getRegDate());
                System.out.println("로그인 아이디 : " + member.getLoginId());
                System.out.println("이름 : " + member.getName());
            }
        }
    }

    public int getLastMemberId() {
        return lastMemberId;
    }

    public Map<Integer, Member> getMembers() {
        return members;
    }

    public Member getLoginedMember() {
        return loginedMember;
    }
}
