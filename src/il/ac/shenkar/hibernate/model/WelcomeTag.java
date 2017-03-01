package il.ac.shenkar.hibernate.model;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * A class that implements custom tags
 * @see SimpleTagSupport
 * @author Arel Gindos
 * @author Dassi Rosen
 * @author Lior Lerner
 */
@SuppressWarnings("ALL")
public class WelcomeTag extends SimpleTagSupport {
    /**
     * A method that print to the client the main title
     * @throws JspException
     * @throws IOException
     */
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.println("ToDo list");
    }
}