package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.model;

import lombok.Getter;
import lombok.Setter;

public enum RoleEnum {

    ADMIN(1, "ADMIN"),
    USER(2, "USER");

    @Getter @Setter Integer roleid;
    @Getter @Setter String rolename;

    RoleEnum(Integer roleid, String rolename){
        this.roleid = roleid;
        this.rolename = rolename;
    }

    public static RoleEnum getRoleNameFromId(Integer id){
        for(RoleEnum e: values()){
            if(e.roleid.equals(id)) return e;
        }
        return USER;
    }
}
