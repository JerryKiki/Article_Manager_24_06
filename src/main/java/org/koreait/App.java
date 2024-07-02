package org.koreait;

import org.koreait.Article.controller.AMController;
import org.koreait.system.controller.SystemController;

public class App {

    byte system_status = 1;
    SystemController systemController = new SystemController();
    AMController amController = new AMController();


    public void run() {

        System.out.println("== Article Manager Execution ==");

        makeTestData();

        while (system_status == 1) {
            System.out.print("명령어) ");
            String cmd = Container.getSc().nextLine();

            if(cmd.isEmpty()) {
                System.out.println("명령어를 입력하세요.");
                continue;
            }

            Rq rq = new Rq(cmd);

            if (rq.getErrMessage().equals("Article Id는 숫자여야 합니다.")) {
                System.out.println("Article Id는 숫자여야 합니다.");
                continue;
            }

            switch (rq.getActionMethod()) {
                case "exit" :
                    systemController.exit();
                    system_status = 0;
                    break;
                case "article write" :
                    amController.write();
                    break;
                case "article list" :
                    amController.list();
                    break;
                case "article detail" :
                    amController.detail(rq.getIdxOfSelectedArticle());
                    break;
                case "article delete" :
                    amController.delete(rq.getIdxOfSelectedArticle());
                    break;
                case "article modify" :
                    amController.modify(rq.getIdxOfSelectedArticle());
                    break;
                default :
                    System.out.println("올바른 명령어를 입력하세요.");
                    break;
            }
        }
    }

    private void makeTestData() {
        amController.addTestArticle(3);
    }
}
