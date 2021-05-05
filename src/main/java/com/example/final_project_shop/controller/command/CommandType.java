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
    FIND_PRODUCTS_BY_TEAM {
        {
            this.command = new FindProductsByTeamCommand();
        }
    },
    FIND_USERS_BY_NICKNAME {
        {
            this.command = new FindUsersByNicknameCommand();
        }
    },
    ADD_PRODUCT_TO_CART {
        {
            this.command = new AddProductToCartCommand();
        }
    },
    DELETE_PRODUCT_FROM_CART {
        {
            this.command = new DeleteProductFromCart();
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
