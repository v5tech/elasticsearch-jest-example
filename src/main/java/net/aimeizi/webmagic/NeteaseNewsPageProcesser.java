package net.aimeizi.webmagic;

import net.aimeizi.model.Article;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by Administrator on 2015/9/9 0009.
 */
public class NeteaseNewsPageProcesser implements PageProcessor {

    private Site site = Site.me().setDomain("news.163.com")
            .setRetryTimes(3)
            .setSleepTime(1000)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36");

    public static final String URL_LIST = "http://news\\.163\\.com/special/\\w+/\\w+\\.html";

    public static final String URL_POST = "http://news\\.163\\.com/.+\\.html";

    public void process(Page page) {
        //列表页
        if (page.getUrl().regex(URL_LIST).match()||page.getUrl().regex("http://news\\.163\\.com/domestic").match()||page.getUrl().regex("http://news\\.163\\.com/shehui").match()) {
            page.addTargetRequests(page.getHtml().links().regex(URL_POST).all());
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
        }else{

            page.putField("title", Utils.replaceHTML(page.getHtml().xpath("//h1[@id='h1title']").toString()));
            page.putField("content", Utils.replaceHTML(page.getHtml().xpath("//div[@id='endText']").toString()));
            page.putField("create", Utils.replaceHTML(page.getHtml().xpath("//div[@class=\"ep-time-soure cDGray\"]").toString()));
            page.putField("source", Utils.replaceHTML(page.getHtml().xpath("//a[@id=\"ne_article_source\"]/text()").toString()));
            page.putField("url", page.getUrl().get());

            String title = (String)page.getResultItems().get("title");
            String content = (String)page.getResultItems().get("content");
            String create = (String)page.getResultItems().get("create");
            String source = (String)page.getResultItems().get("source");
            String url = (String)page.getResultItems().get("url");
            String author = "";

            // 创建article
            Article article = Utils.createArticle(title, content, source, author, url, create);

            // 索引

            Utils.index(article);

        }
    }

    public Site getSite() {
        return site;
    }



    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        JdbcPipeline jdbcPipeline = (JdbcPipeline)applicationContext.getBean("jdbcPipeline");
        Spider.create(new NeteaseNewsPageProcesser())
                .addUrl("http://news.163.com/domestic")
                .addUrl("http://news.163.com/shehui")
                .addPipeline(jdbcPipeline)
                .thread(5)
                .run();
    }
}
