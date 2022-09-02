package com.course7.telegrambotforpets.model;


import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long chatId;
    private String userName;
    private Date dateBegin;
    private Integer extraDay;
    private Boolean isEnd;

    @OneToMany(mappedBy = "id")
    private Collection<Report> reports;


    public User(Long chatId, String userName, Date dateBegin, Integer extraDay, Boolean isEnd) {
        this.chatId = chatId;
        this.userName = userName;
        this.dateBegin = dateBegin;
        this.extraDay = extraDay;
        this.isEnd = isEnd;
    }
    public User(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Integer getExtraDay() {
        return extraDay;
    }

    public void setExtraDay(Integer extraDay) {
        this.extraDay = extraDay;
    }

    public Boolean getEnd() {
        return isEnd;
    }

    public void setEnd(Boolean end) {
        isEnd = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(chatId, user.chatId) && Objects.equals(userName, user.userName) && Objects.equals(dateBegin, user.dateBegin) && Objects.equals(extraDay, user.extraDay) && Objects.equals(isEnd, user.isEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, userName, dateBegin, extraDay, isEnd);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", userName='" + userName + '\'' +
                ", dateBegin=" + dateBegin +
                ", extraDay=" + extraDay +
                ", isEnd=" + isEnd +
                '}';
    }
}
