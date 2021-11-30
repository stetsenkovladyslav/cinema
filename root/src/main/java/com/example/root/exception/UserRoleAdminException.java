package com.example.root.exception;

import javax.persistence.PersistenceException;

public class UserRoleAdminException extends ApplicationException {
    public UserRoleAdminException(String message){
        super(message);
    }
}
