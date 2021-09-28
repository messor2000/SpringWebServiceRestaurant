package epam.project.spring.util;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author Aleksandr Ovcharenko
 */
public class XSSRequestWrapper extends HttpServletRequestWrapper {

    static final Logger logger = LogManager.getLogger();

    public XSSRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        logger.info("InarameterValues parameter ");
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = cleanXSS(values[i]);
        }
        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        logger.info("Inarameter parameter ");
        String value = super.getParameter(parameter);
        if (value == null) {
            return null;
        }
        logger.info("Inarameter RequestWrapper value ");
        return cleanXSS(value);
    }

    @Override
    public String getHeader(String name) {
        logger.info("Ineader parameter ");
        String value = super.getHeader(name);
        if (value == null)
            return null;
        logger.info("Ineader RequestWrapper value ");
        return cleanXSS(value);
    }

    private String cleanXSS(String value) {
        logger.info("InnXSS RequestWrapper " + value);
        value = value.replace("<", "& lt;").replace(">", "& gt;");
        value = value.replace("\\(", "& #40;").replace("\\)", "& #41;");
        value = value.replace("'", "& #39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");

        value = value.replaceAll("(?i)<script.*?>.*?<script.*?>", "");
        value = value.replaceAll("(?i)<script.*?>.*?</script.*?>", "");
        value = value.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "");
        value = value.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");
        value = value.replace("<script>", "");
        value = value.replace("</script>", "");
        logger.info("OutnXSS RequestWrapper value " + value);
        return value;
    }
}
