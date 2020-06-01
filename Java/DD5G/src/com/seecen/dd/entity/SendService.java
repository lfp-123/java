package com.seecen.dd.entity;

/**
 * @author ${林锋鹏}
 * @Title: SendService
 * @ProjectName Java
 * @date 2019/8/15 16:34
 */
public interface SendService  {
    public int send(int Smscount,MobileCard card) throws  Exception;
}
