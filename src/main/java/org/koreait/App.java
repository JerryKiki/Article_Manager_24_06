package org.koreait;

import org.koreait.Article.controller.AMController;
import org.koreait.member.controller.MemberController;
import org.koreait.member.entity.Member;
import org.koreait.system.controller.SystemController;

public class App {

    byte system_status = 1;
    AMController amController = new AMController();
    MemberController memberController = new MemberController();


    public void run() {

        System.out.println("== Article Manager Execution ==");

        makeTestMember();

        boolean loginStatus = false;
        Member logedInMember = memberController.logIn();

        if (logedInMember != null) loginStatus = true;

        makeTestData();

        while (system_status == 1) {
            if (loginStatus) {
                System.out.print("명령어) ");
                String cmd = Container.getSc().nextLine();

                if (cmd.isEmpty()) {
                    System.out.println("명령어를 입력하세요.");
                    continue;
                }

                Rq rq = new Rq(cmd);

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
                        amController.write();
                        break;
                    case "list":
                        if (String.valueOf(rq.getListIdxChar()).isEmpty()) amController.list();
                        else amController.list(String.valueOf(rq.getListIdxChar()));
                        break;
                    case "detail":
                        amController.detail(rq.getIdxOfSelectedArticle());
                        break;
                    case "delete":
                        amController.delete(rq.getIdxOfSelectedArticle());
                        break;
                    case "modify":
                        amController.modify(rq.getIdxOfSelectedArticle());
                        break;
                    case "logout":
                        System.out.println("== 로그아웃 합니다 ==");
                        logedInMember = null;
                        loginStatus = false;
                        break;
                    default:
                        System.out.println("올바른 명령어를 입력하세요.");
                        break;
                }
            } else {
                System.out.println("===== 로그아웃 상태입니다 =====");
                System.out.println("사용 가능 명령어 : login, exit");
                System.out.print("명령어) ");
                String cmd = Container.getSc().nextLine();

                if (cmd.isEmpty()) {
                    System.out.println("명령어를 입력하세요.");
                    continue;
                }

                Rq rq = new Rq(cmd);

                switch (rq.getActionMethod()) {
                    case "exit":
                        SystemController.exit();
                        system_status = 0;
                        break;
                    case "login":
                        logedInMember = memberController.logIn();
                        loginStatus = true;
                        break;
                    case "write", "list", "detail", "delete", "modify":
                        System.out.println("로그인 전에는 사용하실 수 없습니다.");
                        break;
                    default :
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
