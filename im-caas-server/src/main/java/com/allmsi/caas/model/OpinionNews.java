package com.allmsi.caas.model;

import java.util.Date;

public class OpinionNews {
    private String id;

    private String pjid;

    private String sender;

    private String receiver;

    private String title;

    private String content;

    private Date createtime;

    private Date updatetime;

    private int mark;

    private int senderflag;

    private int receiverflag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPjid() {
        return pjid;
    }

    public void setPjid(String pjid) {
        this.pjid = pjid == null ? null : pjid.trim();
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender == null ? null : sender.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getSenderflag() {
        return senderflag;
    }

    public void setSenderflag(int senderflag) {
        this.senderflag = senderflag;
    }

    public int getReceiverflag() {
        return receiverflag;
    }

    public void setReceiverflag(int receiverflag) {
        this.receiverflag = receiverflag;
    }
}