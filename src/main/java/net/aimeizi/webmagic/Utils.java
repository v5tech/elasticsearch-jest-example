package net.aimeizi.webmagic;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.aimeizi.client.elasticsearch.TransportClient;
import net.aimeizi.model.Article;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/9/11.
 */
public class Utils {

    /**
     * 创建Article
     * @return
     */
    public static Article createArticle(String title,String content,String source,String author,String url,String create){
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setSource(source);
        article.setAuthor(author);
        try {
            Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}");
            Matcher matcher = pattern.matcher(create);
            if(matcher.find()){
                create = matcher.group(0);
            }
            article.setPubdate(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(create));
        }catch (Exception e){
        }
        article.setUrl(url);
        return article;
    }

    /**
     * 创建索引
     * @param article
     */
    public static void index(Article article){
        // 创建索引
        ObjectMapper mapper = new ObjectMapper();
        try {
            TransportClient.createIndex("news", "article", mapper.writeValueAsString(article));
        }catch (Exception e){
        }
    }

    /**
     * html字符过滤
     * @param str
     * @return
     */
    public static String replaceHTML(String str){
        return str!=null?str.replaceAll("\\<.*?>","").replaceAll("&nbsp;",""):"";
    }
}
