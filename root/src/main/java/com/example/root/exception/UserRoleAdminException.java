package com.example.root.exception;

import javax.persistence.PersistenceException;

public class UserRoleAdminException extends PersistenceException {
    public UserRoleAdminException(String message){
        super(message);
    }
}