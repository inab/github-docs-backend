package com.mycompany.documentation.api.logic.v4;

import static com.mycompany.documentation.api.logic.v4.Constants.*;
import java.util.Map;
import org.commonmark.node.Image;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;

/**
 *
 * @author lsimon
 */
public class MdToHtmlParser {

    public MdToHtmlParser() {
    }

    /**
     * This function uses the commonmark library to convert md to HTML
     *
     * @param readme input in md format
     * @param repoName
     * @return HTML String
     */
    public String mdToHtml(String readme, final String repoName) {
        org.commonmark.parser.Parser parser = org.commonmark.parser.Parser.builder().build();
        Node document = parser.parse(readme);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .attributeProviderFactory(new AttributeProviderFactory() {
                    @Override
                    public AttributeProvider create(AttributeProviderContext context) {
                        return new ParseUrls(repoName);
                    }
                })
                .build();
        return renderer.render(document);
    }

    class ParseUrls implements AttributeProvider {

        private final String repoName;

        private ParseUrls(String repoName) {
            this.repoName = repoName;
        }

        /**
         * parses urls for internal links and images on github, creates
         * redirects to the original content
         *
         * @param node
         * @param tagName
         * @param attributes
         */
        @Override
        public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
            if (node instanceof Image) {
                if (!((Image) node).getDestination().contains("http")) {
                    attributes.put("src", "https://raw.githubusercontent.com/" + OWNER + "/" + repoName + "/master/" + ((Image) node).getDestination());
                }
            }
            if (node instanceof Link) {
                if (!((Link) node).getDestination().contains("http")) {
                    attributes.put("href", "https://github.com" + "/" + OWNER + "/" + repoName + "/tree/master/" + ((Link) node).getDestination());
                }
            }
        }
    }
}
