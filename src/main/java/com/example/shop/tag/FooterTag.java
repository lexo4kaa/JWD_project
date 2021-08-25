package com.example.shop.tag;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import static com.example.shop.controller.command.ParameterAndAttribute.CURRENT_LOCALE;

/**
 * Custom footer tag
 */
public class FooterTag extends TagSupport {
    private static final Character UNDERSCORE = '_';
    private static final Character HYPHEN = '-';
    private static final String BUNDLE_NAME = "pagecontent";
    private static final String FOOTER_ADDRESS = "footer.address";
    private static final String FOOTER_OPENING_HOURS = "footer.opening_hours";
    private static final String FOOTER_PHONE_NUMBER = "footer.phone_number";
    private static final String FOOTER_TAG_START = "<footer>";
    private static final String FOOTER_TAG_END = "</footer>";
    private static final String P_TAG_START = "<p>";
    private static final String P_TAG_END = "</p>";

    @Override
    public int doStartTag() throws JspTagException {
        HttpSession session = pageContext.getSession();
        String locale = (String)session.getAttribute(CURRENT_LOCALE);
        String languageTag = locale.replace(UNDERSCORE,HYPHEN);
        ResourceBundle rb = ResourceBundle.getBundle(BUNDLE_NAME, Locale.forLanguageTag(languageTag));
        try {
            JspWriter out = pageContext.getOut();
            out.write(FOOTER_TAG_START);
            out.write(P_TAG_START);
            out.write(rb.getString(FOOTER_ADDRESS));
            out.write(P_TAG_END);
            out.write(P_TAG_START);
            out.write(rb.getString(FOOTER_OPENING_HOURS));
            out.write(P_TAG_END);
            out.write(P_TAG_START);
            out.write(rb.getString(FOOTER_PHONE_NUMBER));
            out.write(P_TAG_END);
            out.write(FOOTER_TAG_END);
        } catch (IOException e) {
            throw new JspTagException(e);
        }
        return SKIP_BODY;
    }
}
