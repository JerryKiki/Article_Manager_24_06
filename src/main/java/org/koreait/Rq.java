package org.koreait;

import java.util.InputMismatchException;

public class Rq {
    private String actionMethod;
    private int idxOfSelectedArticle;
    private String listIdxKeyword = "";
    private String errMessage = "";

    public Rq(String cmd) {

        String[] cmdBits = cmd.split(" ");

        if (cmdBits.length == 1) this.actionMethod = cmdBits[0];
        else if (cmdBits.length == 2) this.actionMethod = cmdBits[1];
        else if (cmdBits.length == 3) {
            this.actionMethod = cmdBits[1];
            if (this.actionMethod.equals("detail") || this.actionMethod.equals("delete") || this.actionMethod.equals("modify")) {
                try {
                    this.idxOfSelectedArticle = Integer.parseInt(cmdBits[2]);
                } catch (NumberFormatException e) {
                    errMessage = "Article Id는 숫자여야 합니다.";
                }
            } else if (this.actionMethod.equals("list")) {
                    this.listIdxKeyword = cmdBits[2];
            }

        }
    }

    public String getListIdxChar() {
        return listIdxKeyword;
    }

    public void setListIdxChar(String listIdxChar) {
        this.listIdxKeyword = listIdxChar;
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
