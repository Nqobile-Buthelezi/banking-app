package controller;

import io.javalin.http.Context;
import java.util.Map;

/**
 * The RenderingController class handles rendering of HTML content and templates.
 * It provides methods to render HTML content directly and render templates with view models.
 */
public class RenderingController {

    /**
     * Renders HTML content without using a template.
     *
     * @param ctx          The Javalin context object representing the HTTP request and response.
     * @param templatePath The path to the HTML template.
     */
    public void renderHtml(Context ctx, String templatePath) {
        ctx.render(templatePath);
    }

    /**
     * Renders an HTML template with a specified view model.
     *
     * @param ctx          The Javalin context object representing the HTTP request and response.
     * @param templatePath The path to the HTML template (relative to the templates directory).
     * @param viewModel    The view model containing data to be passed to the template.
     */
    public void renderTemplate(Context ctx, String templatePath, Map<String, Object> viewModel) {
        ctx.render(templatePath, viewModel);
    }
}
