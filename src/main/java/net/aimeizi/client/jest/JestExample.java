package net.aimeizi.client.jest;

import com.google.gson.GsonBuilder;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.cluster.Health;
import io.searchbox.cluster.NodesInfo;
import io.searchbox.cluster.NodesStats;
import io.searchbox.core.*;
import io.searchbox.core.SearchResult.Hit;
import io.searchbox.indices.*;
import net.aimeizi.model.Article;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.highlight.HighlightBuilder;

import java.text.SimpleDateFormat;
import java.util.*;

public class JestExample {
	
	public static void main(String[] args) throws Exception {
//		deleteIndex();
//		createIndex();
//		bulkIndex();
		createSearch("性虐");
		searchAll();
//		getDocument("article", "article", "1");
//		getDocument("article","article","2");
//		getDocument("article","article","3");
//		updateDocument("article", "article", "3");
//		getDocument("article", "article", "3");
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
		DeleteIndex deleteIndex = new DeleteIndex.Builder("article").build();
		JestResult result = jestClient.execute(deleteIndex);
		System.out.println(result.getJsonString());
	}



	/**
	 * 清缓存
	 * @throws Exception
	 */
	private static void clearCache() throws Exception {
		JestClient jestClient = JestExample.getJestClient();
		ClearCache closeIndex = new ClearCache.Builder().build();
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
		IndicesExists indicesExists = new IndicesExists.Builder("article").build();
		JestResult result = jestClient.execute(indicesExists);
		System.out.println(result.getJsonString());
	}

	/**
	 * 查看节点信息
	 * @throws Exception
	 */
	private static void nodesInfo() throws Exception {
		JestClient jestClient = JestExample.getJestClient();
		NodesInfo nodesInfo = new NodesInfo.Builder().build();
		JestResult result = jestClient.execute(nodesInfo);
		System.out.println(result.getJsonString());
	}


	/**
	 * 查看集群健康信息
	 * @throws Exception
	 */
	private static void health() throws Exception {
		JestClient jestClient = JestExample.getJestClient();
		Health health = new Health.Builder().build();
		JestResult result = jestClient.execute(health);
		System.out.println(result.getJsonString());
	}

	/**
	 * 节点状态
	 * @throws Exception
	 */
	private static void nodesStats() throws Exception {
		JestClient jestClient = JestExample.getJestClient();
		NodesStats nodesStats = new NodesStats.Builder().build();
		JestResult result = jestClient.execute(nodesStats);
		System.out.println(result.getJsonString());
	}

	/**
	 * 更新Document
	 * @param index
	 * @param type
	 * @param id
	 * @throws Exception
	 */
	private static void updateDocument(String index,String type,String id) throws Exception {
		JestClient jestClient = JestExample.getJestClient();
		Article article = new Article();
		article.setId(Integer.parseInt(id));
		article.setTitle("中国3颗卫星拍到阅兵现场高清照");
		article.setContent("据中国资源卫星应用中心报道，9月3日，纪念中国人民抗日战争暨世界反法西斯战争胜利70周年大阅兵在天安门广场举行。资源卫星中心针对此次盛事，综合调度在轨卫星，9月1日至3日连续三天持续观测首都北京天安门附近区域，共计安排5次高分辨率卫星成像。在阅兵当日，高分二号卫星、资源三号卫星及实践九号卫星实现三星联合、密集观测，捕捉到了阅兵现场精彩瞬间。为了保证卫星准确拍摄天安门及周边区域，提高数据处理效率，及时制作合格的光学产品，资源卫星中心运行服务人员从卫星观测计划制定、复核、优化到系统运行保障、光学产品图像制作，提前进行了周密部署，并拟定了应急预案，为圆满完成既定任务奠定了基础。");
		article.setPubdate(new Date());
		article.setAuthor("匿名");
		article.setSource("新华网");
		article.setUrl("http://news.163.com/15/0909/07/B32AGCDT00014JB5.html");
		String script = "{" +
				"    \"doc\" : {" +
				"        \"title\" : \""+article.getTitle()+"\"," +
				"        \"content\" : \""+article.getContent()+"\"," +
				"        \"author\" : \""+article.getAuthor()+"\"," +
				"        \"source\" : \""+article.getSource()+"\"," +
				"        \"url\" : \""+article.getUrl()+"\"," +
				"        \"pubdate\" : \""+new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(article.getPubdate())+"\"" +
				"    }" +
				"}";
		Update update = new Update.Builder(script).index(index).type(type).id(id).build();
		JestResult result = jestClient.execute(update);
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
	 * Suggestion
	 * @throws Exception
	 */
	private static void suggest() throws Exception{
		String suggestionName = "my-suggestion";
		JestClient jestClient = JestExample.getJestClient();
		Suggest suggest = new Suggest.Builder("{" +
				"  \"" + suggestionName + "\" : {" +
				"    \"text\" : \"the amsterdma meetpu\"," +
				"    \"term\" : {" +
				"      \"field\" : \"body\"" +
				"    }" +
				"  }" +
				"}").build();
		SuggestResult suggestResult = jestClient.execute(suggest);
		System.out.println(suggestResult.isSucceeded());
		List<SuggestResult.Suggestion> suggestionList = suggestResult.getSuggestions(suggestionName);
		System.out.println(suggestionList.size());
		for(SuggestResult.Suggestion suggestion:suggestionList){
			System.out.println(suggestion.text);
		}
	}

	/**
	 * 查询全部
	 * @throws Exception
	 */
	private static void searchAll() throws Exception {
		JestClient jestClient = JestExample.getJestClient();
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchAllQuery());
		Search search = new Search.Builder(searchSourceBuilder.toString())
				.addIndex("article")
				.build();
		SearchResult result = jestClient.execute(search);
		System.out.println("本次查询共查到："+result.getTotal()+"篇文章！");
		List<Hit<Article,Void>> hits = result.getHits(Article.class);
		for (Hit<Article, Void> hit : hits) {
			Article source = hit.source;
			System.out.println("标题："+source.getTitle());
			System.out.println("内容："+source.getContent());
			System.out.println("url："+source.getUrl());
			System.out.println("来源："+source.getSource());
			System.out.println("作者："+source.getAuthor());
		}
	}


	private static void createSearch(String queryString) throws Exception {
		JestClient jestClient = JestExample.getJestClient();
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.queryStringQuery(queryString));
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

	private static void bulkIndex() throws Exception {
		JestClient jestClient = JestExample.getJestClient();

		Article article1 = new Article(4,"中国获租巴基斯坦瓜达尔港2000亩土地 为期43年","巴基斯坦(瓜达尔港)港务局表示，将把瓜达尔港2000亩土地，长期租赁给中方，用于建设(瓜达尔港)首个经济特区。分析指，瓜港首个经济特区的建立，不但能对巴基斯坦的经济发展模式，产生示范作用，还能进一步提振经济水平。" +
				"据了解，瓜达尔港务局于今年6月完成了1500亩土地的征收工作，另外500亩的征收工作也将很快完成，所征土地主要来自巴基斯坦海军和俾路支省政府紧邻规划的集装箱货堆区，该经济特区相关基础设施建设预计耗资3500万美元。瓜港务局表示，目前已将这2000亩地租赁给中国，中方将享有43年的租赁权。巴基斯坦前驻华大使马苏德·汗表示，这对提振巴基斯坦经济大有助益：“我认为巴基斯坦所能获得的最大益处主要还是在于经济互通领域”。" +
				"为了鼓励国内外投资者到经济特区投资，巴政府特别针对能源、税制等国内投资短板，专门为投资者出台了利好政策来鼓励投资。这些举措包括三个方面，一是保障能源，即保障经济特区的电力和天然气供应方面享有优先权；二是减免税收，即为经济特区投资的企业提供为期10年的税收假期，并为企业有关生产设备的进口给予免关税待遇；三是一站式服务，即为有意投资经济特区的投资者提供一站式快捷服务，包括向投资者提供有关优惠政策的详尽资讯。马苏德·汗还指出，为了让巴基斯坦从投资中受益，巴政府尽可能提供了各种优惠政策：“(由于)巴基斯坦想从中获益，为此做了大量力所能及的工作”。" +
				"巴政府高度重视瓜港经济特区建设，将其视为现代化港口的“标配”，期待其能够最大化利用瓜港深水良港的自然禀赋，吸引国内外投资者建立生产、组装以及加工企业。为鼓励投资，巴方还开出了20年免税的优惠条件。" +
				"除了瓜达尔港，中巴还有哪些项目？" +
				"根据中国和巴基斯坦两国4月20日发表的联合声明，双方将积极推进喀喇昆仑公路升级改造二期(塔科特至哈维连段)、瓜达尔港东湾快速路、新国际机场、卡拉奇至拉合尔高速公路(木尔坦至苏库尔段)、拉合尔轨道交通橙线、海尔－鲁巴经济区、中巴跨境光缆、在巴实行地面数字电视传输标准等重点合作项目及一批基础设施和能源电力项目。" +
				"应巴基斯坦总统侯赛因和总理谢里夫邀请，中国国家主席习近平于4月20日至21日对巴基斯坦进行国事访问。访问期间，习近平主席会见了侯赛因总统、谢里夫总理以及巴基斯坦议会、军队和政党领导人，同巴各界人士进行了广泛接触。" +
				"双方高度评价将中巴经济走廊打造成丝绸之路经济带和21世纪海上丝绸之路倡议重大项目所取得的进展。巴方欢迎中方设立丝路基金并将该基金用于中巴经济走廊相关项目。" +
				"巴方将坚定支持并积极参与“一带一路”建设。丝路基金宣布入股三峡南亚公司，与长江三峡集团等机构联合开发巴基斯坦卡洛特水电站等清洁能源项目，这是丝路基金成立后的首个投资项目。丝路基金愿积极扩展中巴经济走廊框架下的其他项目投融资机会，为“一带一路”建设发挥助推作用。" +
				"双方认为，“一带一路”倡议是区域合作和南南合作的新模式，将为实现亚洲整体振兴和各国共同繁荣带来新机遇。" +
				"双方对中巴经济走廊建设取得的进展表示满意，强调走廊规划发展将覆盖巴全国各地区，造福巴全体人民，促进中巴两国及本地区各国共同发展繁荣。" +
				"双方同意，以中巴经济走廊为引领，以瓜达尔港、能源、交通基础设施和产业合作为重点，形成“1+4”经济合作布局。双方欢迎中巴经济走廊联委会第四次会议成功举行，同意尽快完成《中巴经济走廊远景规划》。", "http://news.163.com/15/0909/14/B332O90E0001124J.html", Calendar.getInstance().getTime(), "中国青年网", "匿名");
		Article article2 = new Article(5,"中央党校举行秋季学期开学典礼 刘云山出席讲话","新华网北京9月7日电 中共中央党校7日上午举行2015年秋季学期开学典礼。中共中央政治局常委、中央党校校长刘云山出席并讲话，就深入学习贯彻习近平总书记系列重要讲话精神、坚持党校姓党提出要求。" +
				"刘云山指出，党校姓党是党校工作的根本原则。坚持党校姓党，重要的是坚持坚定正确的政治方向、贯彻实事求是的思想路线、落实从严治校的基本方针。要把党的基本理论教育和党性党风教育作为主课，深化中国特色社会主义理论体系学习教育，深化对习近平总书记系列重要讲话精神的学习教育，深化党章和党纪党规的学习教育。要坚持实事求是的思想方法和工作方法，弘扬理论联系实际的学风，提高教学和科研工作的针对性实效性。要严明制度、严肃纪律，把从严治校要求体现到党校工作和学员管理各方面，使党校成为不正之风的“净化器”。" +
				"刘云山指出，坚持党校姓党，既是对党校教职工的要求，也是对党校学员的要求。每一位学员都要强化党的意识，保持对党忠诚的政治品格，忠诚于党的信仰，坚定道路自信、理论自信、制度自信；忠诚于党的宗旨，牢记为了人民是天职、服务人民是本职，自觉践行党的群众路线；忠诚于党的事业，勤政敬业、先之劳之、敢于担当，保持干事创业的进取心和精气神。要强化党的纪律规矩意识，经常看一看党的政治准则、组织原则执行得怎么样，看一看党的路线方针政策落实得怎么样，看一看重大事项请示报告制度贯彻得怎么样，找差距、明不足，做政治上的明白人、遵规守纪的老实人。" +
				"刘云山强调，领导干部来党校学习，就要自觉接受党的优良作风的洗礼，修好作风建设这门大课。要重温党的光荣传统，学习革命先辈、英雄模范和优秀典型的先进事迹和崇高风范，自觉践行社会主义核心价值观，永葆共产党人的先进性纯洁性，以人格力量传递作风建设正能量。要认真落实党中央关于从严治党、改进作风的一系列要求，贯彻从严精神、突出问题导向，把自己摆进去、把职责摆进去，推动思想问题和实际问题一起解决，履行好党和人民赋予的职责使命。" +
				"赵乐际出席开学典礼。" +
				"中央有关部门负责同志，中央党校校委成员、全体学员和教职工在主会场参加开学典礼。中国浦东、井冈山、延安干部学院全体学员和教职工在分会场参加开学典礼。", "http://news.163.com/15/0907/20/B2UGF9860001124J.html", Calendar.getInstance().getTime(), "新华网", "NN053");
		Article article3 = new Article(6,"俞正声率中央代表团赴大昭寺看望宗教界人士","国际在线报道：7号上午，赴西藏出席自治区50周年庆祝活动的中共中央政治局常委、全国政协主席俞正声与中央代表团主要成员赴大昭寺慰问宗教界爱国人士。俞正声向大昭寺赠送了习近平总书记题写的“加强民族团结，建设美丽西藏”贺幛，珐琅彩平安瓶，并向僧人发放布施，他在会见僧众时表示，希望藏传佛教坚持爱国爱教传统。" +
				"至今已有1300多年历史的大昭寺，在藏传佛教中拥有至高无上的地位。西藏的寺院归属于某一藏传佛教教派，大昭寺则是各教派共尊的神圣寺院。一年四季不论雨雪风霜，大昭寺外都有从四面八方赶来磕长头拜谒的虔诚信众。" +
				"7号上午，赴西藏出席自治区50周年庆祝活动的中共中央政治局常委、全国政协主席俞正声与中央代表团主要成员专门到大昭寺看望僧众，“我代表党中央、国务院和习主席向大家问好。藏传佛教有许多爱国爱教的传统，有许多高僧大德维护祖国统一和民族团结，坚持传播爱国爱教的正信。党和政府对此一贯给予充分肯定，并对藏传佛教的发展给予了支持。”" +
				"俞正声回忆这已是他自1995年以来第三次来大昭寺。他表示，过去二十年发生了巨大的变化，而藏传佛教的发展与祖国的发展、西藏的发展息息相关。藏传佛教要更好发展必须与社会主义社会相适应。他也向僧人们提出期望：“佛教既是信仰，也是一种文化和学问，希望大家不断提高自己对佛教的认识和理解，提高自己的水平。希望大家更好地管理好大昭寺，搞好民主管理、科学管理，使我们的管理更加规范。”" +
				"今天是中央代表团抵达拉萨的第二天，当天安排有多项与自治区成立五十周年相关活动。继上午接见自治区领导成员、离退休老同志、各族各界群众代表宗教界爱国人士，参观自治区50年成就展外，中央代表团下午还慰问了解放军驻拉萨部队、武警总队等。当天晚些时候，庆祝西藏自治区成立50周年招待会、文艺晚会将在拉萨举行。（来源：环球资讯）", "http://news.163.com/15/0907/16/B2U3O30R00014JB5.html", Calendar.getInstance().getTime(), "国际在线", "全宇虹");
		Article article4 = new Article(7,"张德江:发挥人大主导作用 加快完备法律规范体系","新华网广州9月7日电 中共中央政治局常委、全国人大常委会委员长张德江9月6日至7日在广东出席第21次全国地方立法研讨会，并在佛山市就地方人大工作进行调研。他强调，要全面贯彻落实党的十八大和十八届三中、四中全会精神，深入学习贯彻习近平总书记系列重要讲话精神，认真实施立法法，充分发挥人大及其常委会在立法工作中的主导作用，抓住提高立法质量这个关键，加快形成完备的法律规范体系，为协调推进“四个全面”战略布局提供法治保障。" +
				"张德江指出，立法权是人大及其常委会的重要职权。做好新形势下立法工作，要坚持党的领导，贯彻党的路线方针政策和中央重大决策部署，切实增强思想自觉和行动自觉。要牢固树立依法立法、为民立法、科学立法理念，尊重改革发展客观规律和法治建设内在规律，加强重点领域立法，做到立法主动适应改革和经济社会发展需要。充分发挥在立法工作中的主导作用，把握立项、起草、审议等关键环节，科学确定立法项目，协调做好立法工作，研究解决立法中的重点难点问题，建立健全立法工作格局，形成立法工作合力。" +
				"张德江说，地方性法规是我国法律体系的重要组成部分。地方立法关键是在本地特色上下功夫、在有效管用上做文章。当前要落实好立法法的规定，扎实推进赋予设区的市地方立法权工作，明确步骤和时间，做好各项准备工作，标准不能降低，底线不能突破，坚持“成熟一个、确定一个”，确保立法质量。" +
				"张德江强调，加强和改进立法工作，要有高素质的立法工作队伍作为保障。要把思想政治建设摆在首位，全面提升专业素质能力，充实力量，培养人才，努力造就一支忠于党、忠于国家、忠于人民、忠于法律的立法工作队伍。" +
				"张德江一直非常关注地方人大特别是基层人大工作。在粤期间，他来到佛山市人大常委会，详细询问立法、监督等工作情况，希望他们与时俱进、开拓创新，切实担负起宪法法律赋予的职责。他走进南海区人大常委会、顺德区乐从镇人大主席团，同基层人大代表和人大工作者亲切交谈，肯定基层人大代表联系群众的有益做法，强调人大代表不能脱离人民群众，必须把人民利益放在心中，时刻为群众着想，听取群众意见，反映群众意愿，帮助群众解决实际问题。张德江指出，县乡人大在基层治理体系和治理能力建设中具有重要作用。要贯彻落实中央关于加强县乡人大工作和建设的精神，认真实施新修改的地方组织法、选举法、代表法，不断提高基层人大工作水平，推动人大工作迈上新台阶。" +
				"中共中央政治局委员、广东省委书记胡春华参加上述活动。", "http://news.163.com/15/0907/20/B2UGEUTJ00014PRF.html", Calendar.getInstance().getTime(), "新华网", "陈菲");

		Bulk bulk = new Bulk.Builder()
				.defaultIndex("article")
				.defaultType("article")
				.addAction(Arrays.asList(
						new Index.Builder(article1).build(),
						new Index.Builder(article2).build(),
						new Index.Builder(article3).build(),
						new Index.Builder(article4).build()
				)).build();
		jestClient.execute(bulk);
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
		                        .gson(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create())
		                        .multiThreaded(true)
		                        .readTimeout(10000)
		                        .build());
		 JestClient client = factory.getObject();
		 return client;
	}

}