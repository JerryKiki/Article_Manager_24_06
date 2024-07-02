package org.koreait.Article.controller;

import org.koreait.Article.entity.Article;
import org.koreait.Container;
import org.koreait.Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class AMController {

    Map<Integer, Article> articles = new HashMap<>();
    int lastId = 0;
    int articleCount = 0;

    public void write() {
        lastId++;
        articleCount++;
        System.out.print("제목: ");
        String title = Container.getSc().nextLine();
        System.out.print("내용: ");
        String content = Container.getSc().nextLine();
        System.out.printf("%d번 글이 생성되었습니다.\n", lastId);
        String regDate = Util.getNow();
        String updateDate = regDate;
        articles.put(lastId, new Article(lastId, title, content, regDate, updateDate));
    }

    public void list() {
        if(articles.isEmpty()) System.out.println("아직 아무런 글도 없습니다.");
        else {
            System.out.println("========== 게시글 목록 =========");
            System.out.println(" 번호  /    날짜    /    제목    /    내용   ");

            for (int i = lastId; i > 0; i--) {
                if (articles.containsKey(i)) {
                    Article article = articles.get(i);
                    String displayTitle = subForList(article.getTitle(), "Title");
                    String displayContent = subForList(article.getContent(), "Content");
                    String thisArticleRegDate = article.getRegDateTime();
                    String[] DateAndTime = thisArticleRegDate.split(" ");
                    if (Util.getNow().split(" ")[0].equals(DateAndTime[0])) {
                        System.out.printf("   %d   /    %s    /   %s   /   %s   \n", article.getId(), DateAndTime[1], displayTitle, displayContent);
                    } else {
                        System.out.printf("   %d   /    %s    /   %s   /   %s   \n", article.getId(), DateAndTime[0], displayTitle, displayContent);
                    }
                }
            }
        }
    }

    public void detail(int idxOfSelectedArticle) {
        if (articles.containsKey(idxOfSelectedArticle)) {
            System.out.println("===== 게시글 상세보기 =====");
            Article article = articles.get(idxOfSelectedArticle);
            System.out.println("번호 : " + article.getId());
            System.out.println("작성 날짜 : " + article.getRegDateTime());
            System.out.println("마지막 수정 날짜 : " + article.getUpdateDateTime());
            System.out.println("제목 : " + article.getTitle());
            System.out.println("내용 : " + article.getContent());
        } else {
            System.out.println(idxOfSelectedArticle + "번 게시글은 없습니다.");
        }
    }

    public void delete(int idxOfSelectedArticle) {
        if (articles.containsKey(idxOfSelectedArticle)) {
            articles.remove(idxOfSelectedArticle);
            System.out.println(idxOfSelectedArticle + "번 게시글이 삭제되었습니다.");
            articleCount--;
        } else {
            System.out.println(idxOfSelectedArticle + "번 게시글은 없습니다.");
        }
    }

    public void modify(int idxOfSelectedArticle) {
        if (articles.containsKey(idxOfSelectedArticle)) {
            Article oldArticle = articles.get(idxOfSelectedArticle);
            System.out.println("기존 제목 : " + oldArticle.getTitle());
            System.out.println("기존 내용 : " + oldArticle.getContent());
            System.out.print("제목: ");
            String title = Container.getSc().nextLine();
            System.out.print("내용: ");
            String content = Container.getSc().nextLine();
            oldArticle.setTitle(title);
            oldArticle.setContent(content);
            oldArticle.setUpdateDateTime(Util.getNow());
            System.out.println(idxOfSelectedArticle + "번 게시글이 수정되었습니다.");
        } else {
            System.out.println(idxOfSelectedArticle + "번 게시글은 없습니다.");
        }
    }

    String subForList(String willSub, String thisCase) {
        String subedString = willSub;

        switch (thisCase) {
            case "Title":
                if (willSub.length() > 3) {
                    subedString = willSub.substring(0, 3) + "...";
                } else if (willSub.length() == 3) subedString = willSub + "   ";
                else if (willSub.length() == 2) subedString = willSub + "    ";
                else if (willSub.length() == 1) subedString = willSub + "     ";
                break;
            case "Content":
                if (willSub.length() > 7) {
                    subedString = willSub.substring(0, 7) + "...";
                }
                break;
        }

        return subedString;
    }
}