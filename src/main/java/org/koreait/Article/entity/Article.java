package org.koreait.Article.entity;

import org.koreait.member.entity.Member;

public class Article {
    private int id;
    private String title;
    private String content;
    private String regDateTime; //작성 날짜 및 시간
    private String updateDateTime;
    private Member author;

    public Article(int id, String title, String content, String regDateTime, String updateDateTime, Member author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.regDateTime = regDateTime;
        this.updateDateTime = updateDateTime;
        this.author = author;
    }

    public Member getAuthor() {
        return author;
    }

    public void setAuthor(Member author) {
        this.author = author;
    }

    public String getRegDateTime() {
        return regDateTime;
    }

    public void setRegDateTime(String regDateTime) {
        this.regDateTime = regDateTime;
    }

    public String getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(String updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
