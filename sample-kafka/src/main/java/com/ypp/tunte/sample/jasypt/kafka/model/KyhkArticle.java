package com.ypp.tunte.sample.jasypt.kafka.model;

import lombok.Data;
import lombok.ToString;
import org.apache.kafka.common.protocol.types.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/19
 **/
@Data
@ToString
public class KyhkArticle implements Serializable {

    private long id;
    private String articleCode;
    private String articleTitle;
    private String articleBrief;
    private String content;
    private String articleKey;
    private String articleType;
    private String articleState;
    private String stateOper;
    private Date stateTime;
    private String operRemark;
    private String isRecommend;
    private String recommendUser;
    private Date recommendTime;
    private String isShieldSearch;
    private String showAuthorCode;
    private String showAuthorName;
    private String authType;
    private String isPublic;
    private Date publishTime;
    private String forwardArtId;
    private String machineAuditId;
    private String robotApprResult;
    private String articleApprConfId;
    private String apprStatusCurrent;
    private String apprRemark;
    private String apprResult;
    private String apprResultTime;
    private String approverOne;
    private Date apprOneTime;
    private String apprTwo;
    private Date apprTwoTime;
    private String likeNum;
    private String readingNum;
    private String forwardNum;
    private String tenantId;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;

}
