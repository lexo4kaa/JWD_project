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
    FIND_PRODUCTS_BY_TEAM_AND_TYPE {
        {
            this.command = new FindProductsByTeamAndTypeCommand();
        }
    },
    FIND_PRODUCTS_BY_TYPE {
        {
            this.command = new FindProductsByTypeCommand();
        }
    },
    FIND_PRODUCTS_BY_IDS {
        {
            this.command = new FindProductsByIdsCommand();
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
    FIND_USER_BY_NICKNAME {
        {
            this.command = new FindUserByNicknameCommand();
        }
    },
    FIND_ALL_USERS {
        {
            this.command = new FindAllUsersCommand();
        }
    },
    FIND_ORDERS_BY_NICKNAME {
        {
            this.command = new FindOrdersByNicknameCommand();
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
    CHANGE_QUANTITY_OF_PRODUCT_IN_CART {
        {
            this.command = new ChangeQuantityOfProductInCartCommand();
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
    CHANGE_ROLE {
        {
            this.command = new ChangeUserRoleCommand();
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
    TO_CHANGE_PASSWORD_PAGE {
        {
            this.command = new ToChangePasswordPage();
        }
    },
    FIRST_INIT {
        {
            this.command = new FirstInitCommand();
        }
    },
    UPDATE_ACCOUNT {
        {
            this.command = new UpdateAccountCommand();
        }
    },
    CHANGE_PASSWORD {
        {
            this.command = new ChangePasswordCommand();
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
