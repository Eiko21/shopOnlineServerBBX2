package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.model;

import lombok.Getter;
import lombok.Setter;

public enum StateEnum {

    ACTIVE(1, "ACTIVE"),
    DISCONTINUED(2, "DISCONTINUED");

    @Getter @Setter Integer stateid;
    @Getter @Setter String statename;

    StateEnum(Integer stateId, String statename){
        this.stateid = stateId;
        this.statename = statename;
    }

    public static StateEnum getStateNameFromId(Integer id){
        for(StateEnum e: values()){
            if(e.stateid.equals(id)) return e;
        }
        return DISCONTINUED;
    }

}
