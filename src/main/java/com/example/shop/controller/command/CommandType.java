package com.example.shop.controller.command;

import com.example.shop.controller.command.impl.*;

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
    DELETE_USER {
        {
            this.command = new DeleteUserCommand();
        }
    },
    FIND_PRODUCTS_BY_TEAM {
        {
            this.command = new FindProductsByTeamCommand();
        }
    },
    FIND_PRODUCTS_BY_IDS {
        {
            this.command = new FindProductsByIdsCommand();
        }
    },
    FIND_USERS_BY_NICKNAME {
        {
            this.command = new FindUsersByNicknameCommand();
        }
    },
    ADD_ORDER {
        {
            this.command = new AddOrderCommand();
        }
    },
    ADD_PRODUCT_TO_CART {
        {
            this.command = new AddProductToCartCommand();
        }
    },
    DELETE_PRODUCT_FROM_CART {
        {
            this.command = new DeleteProductFromCartCommand();
        }
    },
    ADD_USER_TO_BLACKLIST {
        {
            this.command = new AddUserToBlacklistCommand();
        }
    },
    DELETE_USER_FROM_BLACKLIST {
        {
            this.command = new DeleteUserFromBlacklistCommand();
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
    },
    TO_PRODUCTS_PAGE {
        {
            this.command = new ToProductsPageCommand();
        }
    },
    TO_ADMIN_MAIN_PAGE {
        {
            this.command = new ToAdminMainPageCommand();
        }
    },
    TO_USERS_INFO_PAGE {
        {
            this.command = new ToUsersInfoPageCommand();
        }
    },
    FIRST_INIT {
        {
            this.command = new FirstInitCommand();
        }
    },
    SWITCH_LOCALE {
        {
            this.command = new SwitchLocaleCommand();
        }
    };
    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
