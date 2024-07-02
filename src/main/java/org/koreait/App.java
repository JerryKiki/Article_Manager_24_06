package org.koreait;

import org.koreait.Article.controller.AMController;
import org.koreait.member.controller.MemberController;
import org.koreait.system.controller.SystemController;

public class App {

    byte system_status = 1;
    AMController amController = new AMController();
    MemberController memberController = new MemberController();


    public void run() {

        System.out.println("== Article Manager Execution ==");

        boolean loginState = memberController.logIn();

        if (loginState) {

            makeTestData();

            while (system_status == 1) {
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
}
