package com.example.admin.service.admin;

import javax.persistence.PersistenceException;

public class UserRoleAdminException extends PersistenceException {
    public UserRoleAdminException(String message){
        super(message);
    }
}
