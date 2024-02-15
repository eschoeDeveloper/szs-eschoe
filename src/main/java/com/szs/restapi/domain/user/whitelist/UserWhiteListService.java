package com.szs.restapi.domain.user.whitelist;

public interface UserWhiteListService {

    boolean checkUserWhiteList( UserWhiteListDTO userWhiteListDTO ) throws RuntimeException;

}
