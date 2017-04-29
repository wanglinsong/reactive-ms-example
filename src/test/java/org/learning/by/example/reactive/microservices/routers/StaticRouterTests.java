package org.learning.by.example.reactive.microservices.routers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.learning.by.example.reactive.microservices.test.BasicRouterTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.IsNot.not;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StaticRouterTests extends BasicRouterTest {

    private static final String STATIC_PATH = "/index.html";
    private static final String DEFAULT_TITLE = "Swagger UI";
    private static final String TITLE_TAG = "title";

    @Before
    public void setup() {
        super.setup(StaticRouter.doRoute());
    }

    @Test
    public void staticContentTest() {
        String result = get(builder -> builder.path(STATIC_PATH).build());
        assertThat(result, not(isEmptyOrNullString()));
        verifyTitleIs(result, DEFAULT_TITLE);
    }

    private void verifyTitleIs(final String html, final String title) {
        Document doc = Jsoup.parse(html);
        Element element = doc.head().getElementsByTag(TITLE_TAG).get(0);
        String text = element.text();
        assertThat(text, is(title));
    }
}