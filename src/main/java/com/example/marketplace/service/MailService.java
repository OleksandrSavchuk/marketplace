package com.example.marketplace.service;

import com.example.marketplace.entity.User;
import com.example.marketplace.entity.enums.MailType;

import java.util.Properties;

public interface MailService {

    void sendMail(User user, MailType mailType, Properties properties);

}
