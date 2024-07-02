package org.koreait;

import java.util.InputMismatchException;

public class Rq {
    String actionMethod;
    int idxOfSelectedArticle;
    String errMessage = "";

    public Rq(String cmd) {

        String[] cmdBits = cmd.split(" ");

        if (cmdBits.length == 1) this.actionMethod = cmdBits[0];
        else if (cmdBits.length == 2) this.actionMethod = cmdBits[0] + " " + cmdBits[1];
        else if (cmdBits.length == 3) {
            this.actionMethod = cmdBits[0] + " " + cmdBits[1];
            try {
                this.idxOfSelectedArticle = Integer.parseInt(cmdBits[2]);
            } catch (NumberFormatException e) {
                errMessage = "Article Id는 숫자여야 합니다.";
            }
        }
    }

    public String getActionMethod() {
        return actionMethod;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public int getIdxOfSelectedArticle() {
        return idxOfSelectedArticle;
    }
}
