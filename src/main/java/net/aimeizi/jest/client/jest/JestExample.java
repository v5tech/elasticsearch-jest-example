package net.aimeizi.jest.client.jest;

import io.searchbox.action.Action;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.cluster.Health;
import io.searchbox.cluster.NodesInfo;
import io.searchbox.cluster.NodesStats;
import io.searchbox.core.Delete;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.SearchResult.Hit;
import io.searchbox.core.Suggest;
import io.searchbox.indices.ClearCache;
import io.searchbox.indices.CloseIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.Flush;
import io.searchbox.indices.IndicesExists;
import io.searchbox.indices.Optimize;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import net.aimeizi.jest.Article;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.highlight.HighlightBuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JestExample {
	
	public static void main(String[] args) throws Exception {
//		deleteIndex();
//		createIndex();
		createSearch("性虐");
//		createSuggest("性虐 床上 淤青 母亲 安全调侃 雾霾");
//		getDocument("article","article","1");
//		getDocument("article","article","2");
//		getDocument("article","article","3");
//		deleteDocument("article","article","1");
//		nodesStats();
//		health();
//		nodesInfo();
//		indicesExists();
//		flush();
//		optimize();
//		closeIndex();
//		clearCache();
	}


	/**
	 * 将删除所有的索引
	 * @throws Exception
	 */
	private static void deleteIndex() throws Exception {
		JestClient jestClient = JestExample.getJestClient();
		DeleteIndex deleteIndex = new DeleteIndex(new DeleteIndex.Builder("article")); 
		JestResult result = jestClient.execute(deleteIndex);
		System.out.println(result.getJsonString());
	}



	/**
	 * 清缓存
	 * @throws Exception
	 */
	private static void clearCache() throws Exception {
		JestClient jestClient = JestExample.getJestClient();
		ClearCache closeIndex = new ClearCache(new ClearCache.Builder()); 
		JestResult result = jestClient.execute(closeIndex);
		System.out.println(result.getJsonString());
	}



	/**
	 * 关闭索引
	 * @throws Exception
	 */
	private static void closeIndex() throws Exception {
		JestClient jestClient = JestExample.getJestClient();
		CloseIndex closeIndex = new CloseIndex.Builder("article").build(); 
		JestResult result = jestClient.execute(closeIndex);
		System.out.println(result.getJsonString());
	}

	/**
	 * 优化索引
	 * @throws Exception
	 */
	private static void optimize() throws Exception {
		JestClient jestClient = JestExample.getJestClient();
		Optimize optimize = new Optimize.Builder().build(); 
		JestResult result = jestClient.execute(optimize);
		System.out.println(result.getJsonString());
	}

	/**
	 * 刷新索引
	 * @throws Exception
	 */
	private static void flush() throws Exception {
		JestClient jestClient = JestExample.getJestClient();
		Flush flush = new Flush.Builder().build(); 
		JestResult result = jestClient.execute(flush);
		System.out.println(result.getJsonString());
	}

	/**
	 * 判断索引目录是否存在
	 * @throws Exception
	 */
	private static void indicesExists() throws Exception {
		JestClient jestClient = JestExample.getJestClient();
		IndicesExists indicesExists = new IndicesExists(new IndicesExists.Builder("article")); 
		JestResult result = jestClient.execute(indicesExists);
		System.out.println(result.getJsonString());
	}

	/**
	 * 查看节点信息
	 * @throws Exception
	 */
	private static void nodesInfo() throws Exception {
		JestClient jestClient = JestExample.getJestClient();
		NodesInfo nodesInfo = new NodesInfo(new NodesInfo.Builder()); 
		JestResult result = jestClient.execute(nodesInfo);
		System.out.println(result.getJsonString());
	}


	/**
	 * 查看集群健康信息
	 * @throws Exception
	 */
	private static void health() throws Exception {
		JestClient jestClient = JestExample.getJestClient();
		Health health = new Health(new Health.Builder()); 
		JestResult result = jestClient.execute(health);
		System.out.println(result.getJsonString());
	}

	/**
	 * 节点状态
	 * @throws Exception
	 */
	private static void nodesStats() throws Exception {
		JestClient jestClient = JestExample.getJestClient();
		NodesStats nodesStats = new NodesStats(new NodesStats.Builder()); 
		JestResult result = jestClient.execute(nodesStats);
		System.out.println(result.getJsonString());
	}
	
	/**
	 * 删除Document
	 * @param index
	 * @param type
	 * @param id
	 * @throws Exception
	 */
	private static void deleteDocument(String index,String type,String id) throws Exception {
		JestClient jestClient = JestExample.getJestClient();
		Delete delete = new Delete.Builder(id).index(index).type(type).build();
		JestResult result = jestClient.execute(delete);
		System.out.println(result.getJsonString());
	}

	/**
	 * 获取Document
	 * @param index
	 * @param type
	 * @param id
	 * @throws Exception
	 */
	private static void getDocument(String index,String type,String id) throws Exception {
		JestClient jestClient = JestExample.getJestClient();
		Get get = new Get.Builder(index, id).type(type).build();
		JestResult result = jestClient.execute(get);
		Article article = result.getSourceAsObject(Article.class);
		System.out.println(article.getTitle()+","+article.getContent());
	}

	/**
	 * 搜索建议
	 * @throws Exception
	 */
	private static void createSuggest(String queryString) throws Exception {
		JestClient jestClient = JestExample.getJestClient();
		String suggestString = "{\"my-suggest\" : {\"text\" : \""+queryString+"\",\"term\" : {\"field\" : \"\"}}}";
		Action<JestResult> suggest = new Suggest.Builder(suggestString)
			.addIndex("article")
			.build();
		JestResult jestResult = jestClient.execute(suggest);
		if(jestResult.isSucceeded()){
			JsonObject jsonObject = jestResult.getJsonObject();
			JsonArray jsonArray = jsonObject.getAsJsonArray("my-suggest");
			System.out.println("搜索建议如下:");
			for (JsonElement jsonElement : jsonArray) {
				System.out.println(jsonElement.getAsJsonObject().get("text").toString().replace("\"", ""));
			}
		}
	}


	private static void createSearch(String queryString) throws Exception {
		JestClient jestClient = JestExample.getJestClient();
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.queryString(queryString));
		HighlightBuilder highlightBuilder = new HighlightBuilder();
		highlightBuilder.field("title");//高亮title
		highlightBuilder.field("content");//高亮content
		highlightBuilder.preTags("<em>").postTags("</em>");//高亮标签
		highlightBuilder.fragmentSize(200);//高亮内容长度
		searchSourceBuilder.highlight(highlightBuilder);
		Search search = new Search.Builder(searchSourceBuilder.toString())
        .addIndex("article")
        .build();
		SearchResult result = jestClient.execute(search);
		System.out.println("本次查询共查到："+result.getTotal()+"篇文章！");
		List<Hit<Article,Void>> hits = result.getHits(Article.class);
		for (Hit<Article, Void> hit : hits) {
			Article source = hit.source;
			//获取高亮后的内容
			Map<String, List<String>> highlight = hit.highlight;
			List<String> titlelist = highlight.get("title");//高亮后的title
			if(titlelist!=null){
				source.setTitle(titlelist.get(0));
			}
			List<String> contentlist = highlight.get("content");//高亮后的content
			if(contentlist!=null){
				source.setContent(contentlist.get(0));
			}
			System.out.println("标题："+source.getTitle());
			System.out.println("内容："+source.getContent());
			System.out.println("url："+source.getUrl());
			System.out.println("来源："+source.getSource());
			System.out.println("作者："+source.getAuthor());
		}
	}


	private static void createIndex() throws Exception {
		JestClient jestClient = JestExample.getJestClient();
		Article article1 = new Article(1,"高圆圆身上淤青 遭家暴还是玩SM遭性虐？","近日，有媒体拍到高圆圆身上的淤青，腿上还有两块伤疤，引起不少人猜测是遭受家暴。" +
				"对于遭到家暴的传闻，高圆圆首次作出澄清，称这是因为照顾母亲而留下的伤痕，她跟赵又廷关系好得很。" +
				"照顾母亲竟然会留下伤痕？究竟是怎么照顾的。" +
				"高圆圆称，“我妈当时住院，她翻身是需要旁人帮助的，我要到床头去，抱起她的上臂，然后她的脚一蹬，这样才能翻过来。" +
				"但我们两个的力气都不够，每次一用力的时候，我的大腿就会刚好撞在那个床框上，所以大腿上就撞出那两块淤青了。" +
				"事情真的这么简单吗？即使稍微一撞，也不至于淤青吧！" +
				"看到那个伤疤以及淤青的皮肤，不得不让人怀疑高圆圆是遭受家暴。" +
				"当然，还有另外一个原因，就是玩SM遭性虐。" +
				"当然，这么变态的事情，相信女神不会做的。" +
				"是照顾母亲留下的伤痕也好，遭受家暴也好，希望女神高圆圆以后都能平平安安健健康康吧！", "http://www.vdfly.com/star/20141119/37968.html", Calendar.getInstance().getTime(), "青春娱乐网", "匿名");
		Article article2 = new Article(2,"习近平:希望新西兰保障输华产品质量安全","新华网惠灵顿11月20日电（记者陈贽 田帆刘华）国家主席习近平20日在惠灵顿同新西兰总理约翰·基举行会谈，双方决定，将中新关系提升为全面战略伙伴关系，共建中新两国利益共同体，推动两国合作不断迈上新台阶。" +
				"习近平指出，中新两国相互理解、相互包容、平等相待，建立了高水平政治互信，开展了宽领域互利合作，两国人民都支持发展中新关系。" +
				"两国决定建立全面战略伙伴关系，为中新关系发展指明了大方向。" +
				"习近平强调，两国要保持高层交往，构筑多层次多渠道交流合作格局。" +
				"中新自由贸易协定是中国同发达国家之间达成的第一个自由贸易协定。双方要采取积极举措，推动两国经贸关系不断迈上新台阶，力争早日实现2020年双边贸易额达到300亿新元的新目标。" +
				"双方要继续巩固和提升农牧业等传统领域合作。中国有13亿多人口，市场大得很。新西兰乳制品、羊毛、牛羊肉、海产品等优质产品在中国很受欢迎。" +
				"希望新方切实保障输华产品质量安全，保障中国消费者权益。金融服务、信息技术、节能环保、生物医药等是中国重点发展的产业，新西兰有竞争力，双方可以开展更多合作。" +
				"习近平强调，前不久，中国同其他各方一道，推动亚太经合组织第二十二次领导人非正式会议启动了亚太自由贸易区进程。中新都是亚太经合组织成员，也都是区域全面经济伙伴关系协定谈判方，双方可以在这些机制中加强协调合作，通过打造惠及各方的地区自由贸易安排、建设好亚洲基础设施投资银行，推进亚太经济一体化进程。" +
				"南太平洋地区也是中方提出的21世纪海上丝绸之路的自然延伸，我们欢迎新方参与进来，使中新经贸合作取得更大发展。习近平强调，中新两国要加强人文交流，增进相互了解和友谊，中方将在新西兰设立中国文化中心。" +
				"中新签署电视合拍协议是中国政府同外国政府签署的首个电视合拍协议。双方要加强防务、执法交流，在打击腐败、追逃追赃等方面开展合作。" +
				"中方愿意同新方在南极、太平洋岛国事务上加强合作。约翰·基表示，建交42年来，新中关系取得长足进展，合作不断深化和扩大，彼此成为好伙伴。" +
				"新方致力于同中方一道建设好两国全面战略伙伴关系，在中方核心利益和重大关切问题上，新方将继续支持中方。新中签署自由贸易协定6年来，两国合作取得丰硕成果，新形势下，要提高双边贸易投资水平。" +
				"新方希望扩大新西兰农产品、乳制品对华出口，欢迎中国企业前来投资。新方重视亚洲基础设施投资银行的作用，将积极参与银行建设。新方将简化签证手续，欢迎更多中国公民前来旅游、留学。" +
				"中方积极节能减排，为国际社会合作应对气候变化树立了典范，新方希望同中方加强合作。我祝贺中方成功举办了亚太经合组织领导人非正式会议，愿意同中方一道，推动区域一体化进程，促进亚太地区和平与繁荣。" +
				"会谈后，双方发表《中国和新西兰关于建立全面战略伙伴关系的联合声明》。习近平和约翰·基共同见证了多项双边合作文件的签署，涉及气候变化、电视、教育、南极、金融、旅游、食品安全等领域。两国领导人还共同会见记者。" +
				"习近平强调，中新关系具有开创性、示范性意义。中新建立了全面战略伙伴关系，为两国关系规划了宏伟蓝图。中新两国签署一系列合作协议，充分展示了两国务实合作的广度和深度。" +
				"中国人说：“兄弟同心，其利断金。”毛利族谚语说：“你我篮子在一起，大家生活更美好。”让我们携手合作，谱写中新关系发展新篇章，更好造福两国人民。约翰·基表示，新西兰钦佩中国的非凡成就，相信中国的发展不仅造福中国人民，也给包括新西兰在内的各国带来巨大机遇。" +
				"我们愿同中方共同推动两国全面战略伙伴关系发展。王沪宁、栗战书、杨洁篪等参加上述活动。", "http://news.163.com/14/1120/15/ABGM1POL00014JB5.html", Calendar.getInstance().getTime(), "新华网", "NN053");
		Article article3 = new Article(3,"周末陕西将迎雨雪天气 关中近日大部雾霾缭绕","本报讯（首席记者 姬娜）前天，我省47个县城出现雾霾。昨天，关中大部分地区依旧雾霾缭绕，能见度差。预计22日-24日有雨雪天气，届时雾霾或将能得到缓解。对于这样的天气，微信上不再如之前有那么多埋怨牢骚，更多的是无奈。" +
				"刘先生说：“现在有雾霾是正常的，天气晴好反倒是不正常的，我已经习惯了。”网友“舟子”调侃称：“与其埋怨世界不如改变自己，多多欣赏一下这霾天气，那是多么美妙的一种朦胧感，让我想起了初恋。”" +
				"省气象台专家称，昨天，最低气温略有回升，陕北大部在-4-4℃，关中大部在0-8℃，陕南大部4-9℃，全省最低温出现在吴起， -3.8℃。有了雾霾和云层的包 围，西安温度在6-17℃之间。" +
				"预计本月下旬全省以阴天、多云为主，22-24日有降水天气。今天：陕北、关中多云间晴天，陕南多云转阴天；21日：全省多云；22日：陕北多云间阴天，关中、陕南多云转阴天，部分地方有小雨；23日：全省阴天，关中部分、陕南部分地方有小雨；24日：全省阴天，陕北部分地方有雨夹雪，关中、陕南有小雨。西安今天晴转多云，3-15℃；明天多云，后天阴天。据省气象局消息：昨天我省晴间多云，无明显冷空气活动，早晨大部分地方出现轻雾，11时后泾河、周至、长安、户县、渭南、蒲城、韩城、华县、咸阳、彬县、武功等地出现霾。" +
				"受雾霾影响，关中地区除宝鸡外，西安、咸阳、渭南、铜川空气质量为中度污染。今晨0℃气温线主要位于延安南部，07时气温具体分布：陕北大部-4～2℃（最低吴起-4.1℃），关中大部、商洛2～6℃（西安泾河5.8℃），汉中、安康5～10℃。渭南市气象台昨天08时发布霾黄色预警信号:预计未来24小时内渭南大部地区将出现中度霾，局地有重度霾，易形成中度到重度空气污染。今明两天大部地方多云 22-24日有降水过程今天白天：陕北、关中多云间晴天，陕南多云间阴天。早晨关中、陕南部分地方有雾或霾。今天晚上：陕北、关中多云间晴天，陕南多云间阴天。" +
				"21日：陕北、关中多云，陕南多云转阴天，南部局地有小雨。22日：陕北、关中多云转阴天，陕南阴天，关中南部和陕南大部有小雨。23日：全省阴天，关中部分有小雨，陕南有小到中雨。24日：全省阴天，陕北部分地方有雨夹雪，关中、陕南有小雨。25日：全省阴天转多云。26日：全省多云。后期天气趋势（27-29日）：我省以阴到多云为主，中南部仍有阴雨天气。", "http://xian.qq.com/a/20141120/011300.htm", Calendar.getInstance().getTime(), "三秦都市报 - 三秦网", "姬娜");
		Index index1 = new Index.Builder(article1).index("article").type("article").build();
		Index index2 = new Index.Builder(article2).index("article").type("article").build();
		Index index3 = new Index.Builder(article3).index("article").type("article").build();
		JestResult jestResult1 = jestClient.execute(index1);
		System.out.println(jestResult1.getJsonString());
		JestResult jestResult2 = jestClient.execute(index2);
		System.out.println(jestResult2.getJsonString());
		JestResult jestResult3 = jestClient.execute(index3);
		System.out.println(jestResult3.getJsonString());
	}


	private static JestClient getJestClient() {
		JestClientFactory factory = new JestClientFactory();
		 factory.setHttpClientConfig(new HttpClientConfig
		                        .Builder("http://127.0.0.1:9200")
		                        .multiThreaded(true)
		                        .build());
		 JestClient client = factory.getObject();
		 return client;
	}

}
