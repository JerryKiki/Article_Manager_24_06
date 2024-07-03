package org.koreait;

import org.koreait.Article.controller.AMController;
import org.koreait.member.controller.MemberController;
import org.koreait.member.entity.Member;
import org.koreait.system.controller.SystemController;

public class App {

    byte system_status = 1;
    MemberController memberController = new MemberController();
    AMController amController = new AMController(memberController);

    public void run() {

        System.out.println("== Article Manager Execution ==");

        makeTestMember();

        boolean loginStatus = false;
        Member logedInMember = memberController.getLoginedMember();

        //if (logedInMember != null) loginStatus = true;

        makeTestData();

        while (system_status == 1) {

            System.out.print("명령어) ");
            String cmd = Container.getSc().nextLine();

            if (cmd.isEmpty()) {
                System.out.println("명령어를 입력하세요.");
                continue;
            }

            Rq rq = new Rq(cmd);

            if (loginStatus) {

                if (rq.getErrMessage().equals("Article Id는 숫자여야 합니다.")) {
                    System.out.println("Article Id는 숫자여야 합니다.");
                    continue;
                }

                switch (rq.getActionMethod()) {
                    case "exit":
                        SystemController.exit();
                        system_status = 0;
                        break;
                    case "write":
                        amController.write(logedInMember);
                        break;
                    case "list":
                        if (String.valueOf(rq.getListIdxChar()).isEmpty()) amController.list();
                        else amController.list(String.valueOf(rq.getListIdxChar()));
                        break;
                    case "detail":
                        amController.detail(rq.getIdxOfSelectedArticle());
                        break;
                    case "delete":
                        amController.delete(rq.getIdxOfSelectedArticle(), logedInMember);
                        break;
                    case "modify":
                        amController.modify(rq.getIdxOfSelectedArticle(), logedInMember);
                        break;
                    case "login":
                        System.out.println("이미 로그인 중입니다.");
                        break;
                    case "logout":
                        memberController.logOut();
                        logedInMember = memberController.getLoginedMember();
                        loginStatus = false;
                        break;
                    case "mypage":
                        memberController.myPage(logedInMember);
                        break;
                    case "join":
                        System.out.println("회원 가입은 로그아웃 상태에서만 가능합니다.");
                        break;
                    default:
                        System.out.println("올바른 명령어를 입력하세요.");
                        break;
                }
            } else {
                switch (rq.getActionMethod()) {
                    case "exit":
                        SystemController.exit();
                        system_status = 0;
                        break;
                    case "login":
                        memberController.logIn();
                        logedInMember = memberController.getLoginedMember();
                        if (logedInMember != null) loginStatus = true;
                        break;
                    case "logout":
                        System.out.println("이미 로그아웃 상태입니다.");
                        break;
                    case "join":
                        memberController.join();
                        break;
                    case "write", "detail", "delete", "modify", "mypage":
                        System.out.println("로그인 전에는 사용하실 수 없습니다.");
                        break;
                    case "list":
                        if (String.valueOf(rq.getListIdxChar()).isEmpty()) amController.list();
                        else amController.list(String.valueOf(rq.getListIdxChar()));
                        break;
                    default:
                        System.out.println("올바른 명령어를 입력하세요.");
                        break;
                }
            }
        }
    }

    private void makeTestData() {
        amController.addTestArticle(3);
    }

    private void makeTestMember() {
        memberController.addTestMember(3);
    }
}
