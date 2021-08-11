package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.controller.command.Router;
import com.example.shop.entity.Product;
import com.example.shop.entity.User;
import com.example.shop.model.service.ProductService;
import com.example.shop.model.service.ServiceException;
import com.example.shop.model.service.UserService;
import com.example.shop.model.service.impl.ProductServiceImpl;
import com.example.shop.model.service.impl.UserServiceImpl;
import com.example.shop.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;

import static com.example.shop.controller.command.ParameterAndAttribute.*;

public class ChangeStatusOfFavouriteProductCommand implements ActionCommand {
    private static final ProductService productService = new ProductServiceImpl();
    private static final UserService userService = new UserServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page;
        String user_role = (String) session.getAttribute(USER_ROLE);
        String nickname = (String) session.getAttribute(NICKNAME);
        try {
            if(!user_role.equals(GUEST)) {
                User user = userService.findUserByNickname(nickname).get();
                int userId = user.getUserId();
                String stringProductId = request.getParameter(PARAM_NAME_PRODUCT_ID);
                int productId = Integer.parseInt(stringProductId);
                productService.changeStatusOfFavouriteProduct(userId, productId);
                Set<Integer> productsIds = productService.findFavouriteProducts(userId);
                session.setAttribute(FAVOURITES, productsIds);
                session.setAttribute(FAVOURITE_PRODUCTS, productService.findProductsByIds(productsIds));
                page = (String) session.getAttribute(CURRENT_PAGE);
            } else {
                page = ConfigurationManager.getProperty("path.page.login");
            }
        } catch (ServiceException e) {
            logger.error("Exception in 'AddProductToFavouriteCommand', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return new Router(page, Router.RouteType.REDIRECT);
    }
}
