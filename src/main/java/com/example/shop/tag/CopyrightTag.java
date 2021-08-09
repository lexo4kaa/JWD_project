package com.example.shop.tag;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.example.shop.controller.command.ParameterAndAttribute.CURRENT_LOCALE;

public class CopyrightTag extends TagSupport {
    private static final Logger logger = LogManager.getLogger();
    private static final String RU_RU_LOCALE = "ru_RU";
    private static final String ADDRESS_EN = "Our address: Minsk, Oktyabrskaya st., 10/2";
    private static final String OPENING_HOURS_EN = "Opening hours: daily 10.00-18.00";
    private static final String ADDRESS_RU = "Наш адрес: г. Минск, ул. Октябрьская, 10/2";
    private static final String OPENING_HOURS_RU = "График работы: ежедневно 10.00-18.00";
    private static final String PHONE_NUMBER = "+375-29-212-46-54";
    private static final String FOOTER_TAG_START = "<footer>";
    private static final String FOOTER_TAG_END = "</footer>";
    private static final String P_TAG_START = "<p>";
    private static final String P_TAG_END = "</p>";

    @Override
    public int doStartTag() throws JspTagException {
        HttpSession session = pageContext.getSession();
        String locale = (String)session.getAttribute(CURRENT_LOCALE);
        try {
            JspWriter out = pageContext.getOut();
            out.write(FOOTER_TAG_START);
            out.write(P_TAG_START);
            if(locale.equals(RU_RU_LOCALE)) {
                out.write(ADDRESS_RU);
                out.write(P_TAG_END);
                out.write(P_TAG_START);
                out.write(OPENING_HOURS_RU);
            } else {
                out.write(ADDRESS_EN);
                out.write(P_TAG_END);
                out.write(P_TAG_START);
                out.write(OPENING_HOURS_EN);
            }
            out.write(P_TAG_END);
            out.write(P_TAG_START);
            out.write(PHONE_NUMBER);
            out.write(P_TAG_END);
            out.write(FOOTER_TAG_END);
        } catch (IOException e) {
            logger.error(" I/O error occurs", e);
            throw new JspTagException(e);
        }
        return SKIP_BODY;
    }
}
