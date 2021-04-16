package com.example.final_project_shop.controller.command;

import com.example.final_project_shop.controller.command.impl.*;

public enum CommandType {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    REGISTRATION {
        {
            this.command = new RegistrationCommand();
        }
    },
    FIND_ALL_USERS {
        {
            this.command = new FindAllUsersCommand();
        }
    },
    FIND_ALL_PRODUCTS {
        {
            this.command = new FindAllProductsCommand();
        }
    },
    FIND_USERS_BY_NICKNAME {
        {
            this.command = new FindUsersByNicknameCommand();
        }
    },
    TO_LOGIN_PAGE {
        {
            this.command = new ToLoginPageCommand();
        }
    },
    TO_REGISTRATION_PAGE {
        {
            this.command = new ToRegistrationPageCommand();
        }
    };
    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
