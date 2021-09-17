package com.parameta.api.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class UserSessionDTO implements Serializable {

    private String userID;
    private String userName;

    public com.parameta.api.dto.UserSessionDTO getUserSessionBusiness() {
        com.parameta.api.dto.UserSessionDTO result = new com.parameta.api.dto.UserSessionDTO();
        result.setUserID(this.getUserID());
        result.setUserName(this.getUserName());
        return result;
    }
}
