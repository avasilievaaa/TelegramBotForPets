package com.course7.telegrambotforpets.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class CallBack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long chatId;

    private String contactName;
    private String telephone;

    private Boolean isOk;

    public CallBack(Long chatId, String contactName, String telephone, Boolean isOk) {
        this.chatId = chatId;
        this.contactName = contactName;
        this.telephone = telephone;
        this.isOk = isOk;
    }

    public CallBack() {
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

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Boolean getOk() {
        return isOk;
    }

    public void setOk(Boolean ok) {
        isOk = ok;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallBack callBack = (CallBack) o;
        return Objects.equals(id, callBack.id) && Objects.equals(chatId, callBack.chatId) && Objects.equals(contactName, callBack.contactName) && Objects.equals(telephone, callBack.telephone) && Objects.equals(isOk, callBack.isOk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, contactName, telephone, isOk);
    }

    @Override
    public String toString() {
        return "CallBack{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", contactName='" + contactName + '\'' +
                ", telephone='" + telephone + '\'' +
                ", isOk=" + isOk +
                '}';
    }
}
