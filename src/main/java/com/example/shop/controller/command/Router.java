package com.example.shop.controller.command;

public class Router {
    public enum RouteType {
        FORWARD, REDIRECT
    }

    private final String pagePath;
    private final RouteType routeType;

    public Router(String pagePath, RouteType routeType) {
        this.pagePath = pagePath;
        this.routeType = routeType;
    }

    public String getPagePath() {
        return pagePath;
    }

    public RouteType getRouteType() {
        return routeType;
    }
}
