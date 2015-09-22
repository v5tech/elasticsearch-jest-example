package net.aimeizi.client.elasticsearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.aimeizi.model.Article;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.flush.FlushRequest;
import org.elasticsearch.action.admin.indices.flush.FlushResponse;
import org.elasticsearch.action.admin.indices.optimize.OptimizeRequest;
import org.elasticsearch.action.admin.indices.optimize.OptimizeResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.count.CountResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.*;
import org.elasticsearch.script.ScriptService;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.highlight.HighlightField;
import org.elasticsearch.search.suggest.Suggest.Suggestion;
import org.elasticsearch.search.suggest.Suggest.Suggestion.Entry;
import org.elasticsearch.search.suggest.term.TermSuggestion;
import org.elasticsearch.search.suggest.term.TermSuggestion.Entry.Option;
import org.elasticsearch.search.suggest.term.TermSuggestionBuilder;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

/**
 * 使用原生的Transport Client
 * http://www.elasticsearch.org/guide/en/elasticsearch/client/java-api/current/query-dsl-queries.html
 * @author welcome
 *
 */
public class TransportClient {
	
	/**
	 * 创建TransportClient
	 * @return
	 */
	private static Client createTransportClient() {
		//创建settings
		Settings settings = ImmutableSettings.settingsBuilder()
			.put("cluster.name", "elasticsearch")//设置集群名称
//		    .put("shield.user", "admin:sysadmin")
			.build();
		Client client = null;
		try {
			client = new org.elasticsearch.client.transport.TransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress("127.0.0.1", 9300));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return client;
	}

	public static void main(String[] args) throws Exception {

//		deleteIndices("article");
//		deleteIndices("book");
//
//		creatJsonStringIndex();
//		creatMapIndex();
//		creatBeanIndex();
//		useXContentBuilderCreatIndex();

//		deleteIndex("book","book","1");
//		deleteIndex("book","book","2");
//		deleteIndex("book","book","3");

//		updateIndexByDoc("book", "book", "AU-ytzQZ2hJMRScy9rds");
//		updateIndexByScript("book", "book", "AU-ytzQZ2hJMRScy9rds");

//		upsertIndex("book", "book", "1");

//		bulkIndex();

//		scrollSearchDelete("book", "desc", "语言");

//		deleteIndex("article","article","1");
//		deleteIndex("article","article","2");
//		deleteIndex("article","article","3");

//		deleteIndices("article");

//		getIndex("book","book","1");
//		getIndex("book","book","2");
//		getIndex("book","book","3");
//		getIndex("article","article","1");
//
//		querySearch("book", "book", "desc", "方面");
//		querySearch("book", "book", "desc", "语言");
//		querySearch("book", "book", "desc", "设计");
//		querySearch("article", "article", "content", "圆圆");
//
//		querySearch("book", "book", "desc", "浅出");
//		querySearch("book", "book", "desc", "语言");
//		querySearch("book", "book", "desc", "编程");
//		querySearch("article", "article", "content", "性虐");

//		multiSearch("圆圆");
//
//		count("book","desc","编程");
//
//		matchQuery("book","desc","编程");
//
//		booleanQuery();
//
//		fuzzyLikeQuery();
//
//		fuzzyQuery();
//
//		matchAllQuery();
//
//		prefixQuery();
//
//		queryString();
//
//		rangeQuery();
//
//		termsQuery();
//
//		wildcardQuery();
//
//		indicesQuery();
//
//		regexpQuery();

//		suggest();
	}

	
	/**
	 * 创建索引
	 * @param index 索引名称
	 * @param type  索引type
	 * @param sourcecontent 要索引的内容
	 */
	public static void createIndex(String index,String type,String sourcecontent) {
		Client client = createTransportClient();
		IndexResponse response = client.prepareIndex(index, type).setSource(sourcecontent).execute().actionGet();
		printIndexInfo(response);
	}

	/**
	 * bulkIndex
	 * @throws Exception
	 */
	private static void bulkIndex() throws Exception{
		Client client = createTransportClient();
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		bulkRequest.add(client.prepareIndex("book", "book", "3")
						.setSource(jsonBuilder()
										.startObject()
										.field("name", "Docker开发实践")
										.field("author", "曾金龙 肖新华 刘清")
										.field("pubinfo", "人民邮电出版社")
										.field("pubtime", "2015-07-01")
										.field("desc", "《Docker开发实践》由浅入深地介绍了Docker的实践之道，首先讲解Docker的概念、容器和镜像的相关操作、容器的数据管理等内容，接着通过不同类型的应用说明Docker的实际应用，然后介绍了网络、安全、API、管理工具Fig、Kubernetes、shipyard以及Docker三件套（Machine+Swarm+Compose）等，最后列举了常见镜像、Docker API等内容。")
										.endObject()
						)
		);

		bulkRequest.add(client.prepareIndex("book", "book", "4")
						.setSource(jsonBuilder()
										.startObject()
										.field("name", "图灵程序设计丛书：Hadoop基础教程")
										.field("author", "张治起")
										.field("pubinfo", "人民邮电出版社")
										.field("pubtime", "2014-01-01")
										.field("desc", "《图灵程序设计丛书：Hadoop基础教程》包括三个主要部分：第1~5章讲述了Hadoop的核心机制及Hadoop的工作模式；第6~7章涵盖了Hadoop更多可操作的内容；第8~11章介绍了Hadoop与其他产品和技术的组合使用。《图灵程序设计丛书：Hadoop基础教程》目的在于帮助读者了解什么是Hadoop，Hadoop是如何工作的，以及如何使用Hadoop从数据中提取有价值的信息，并用它解决大数据问题")
										.endObject()
						)
		);

		BulkResponse bulkResponse = bulkRequest.execute().actionGet();
		if (bulkResponse.hasFailures()) {
			BulkItemResponse[] bulkItemResponse = bulkResponse.getItems();
			for (int i = 0; i <bulkItemResponse.length ; i++) {
				System.out.println(bulkItemResponse[i].getItemId()+":"+bulkItemResponse[i].getIndex()+":"+bulkItemResponse[i].getFailureMessage());
			}
		}
	}


	/**
	 *
	 * @param index
	 * @param type
	 * @param id
	 * @throws Exception
	 */
	private static void upsertIndex(String index,String type,String id) throws Exception{
		Client client = createTransportClient();
		IndexRequest indexRequest = new IndexRequest(index, type, id)
				.source(jsonBuilder()
						.startObject()
						.field("name", "Hadoop权威指南（第3版 修订版）")
						.field("author", "Tom White")
						.field("pubinfo", "清华大学出版社")
						.field("pubtime", "2015-01-01")
						.field("desc", "《Hadoop权威指南（第3版 修订版）》通过丰富的案例学习来解释Hadoop的幕后机理，阐述了Hadoop如何解决现实生活中的具体问题。第3版覆盖Hadoop的最新动态，包括新增的MapReduceAPI，以及MapReduce2及其灵活性更强的执行模型（YARN）")
						.endObject());
		UpdateRequest updateRequest = new UpdateRequest(index, type, id)
				.doc(jsonBuilder()
						.startObject()
						.field("author", "华东师范大学数据科学与工程学院")
						.endObject())
				.upsert(indexRequest);
		UpdateResponse response = client.update(updateRequest).get();
		System.out.println("索引是否更新:"+response.isCreated());
		System.out.println("****************index ***********************");
		// Index name
		String _index = response.getIndex();
		// Type name
		String _type = response.getType();
		// Document ID (generated or not)
		String _id = response.getId();
		// Version (if it's the first time you index this document, you will get: 1)
		long _version = response.getVersion();
		System.out.println(_index + "," + _type + "," + _id + "," + _version);
	}

	/**
	 * 更新索引  https://www.elastic.co/guide/en/elasticsearch/reference/current/modules-scripting.html
	 * @param index
	 * @param type
	 * @param id
	 */
	private static void updateIndexByScript(String index, String type, String id) throws Exception{
		Client client = createTransportClient();
		UpdateResponse response = client.prepareUpdate(index, type, id)
				.setScript("ctx._source.author = \"闫洪磊\";" +
						"ctx._source.name = \"Activiti实战\";" +
						"ctx._source.pubinfo = \"机械工业出版社\";" +
						"ctx._source.pubtime = \"2015-01-01\";" +
						"ctx._source.desc = \"《Activiti实战 》立足于实践，不仅让读者知其然，全面掌握Activiti架构、功能、用法、技巧和最佳实践，广度足够；而且让读者知其所以然，深入理解Activiti的源代码实现、设计模式和PVM，深度也足够。《Activiti实战 》一共四个部分：准备篇（1~2章）介绍了Activiti的概念、特点、应用、体系结构，以及开发环境的搭建和配置；基础篇（3~4章）首先讲解了Activiti Modeler、Activiti Designer两种流程设计工具的详细使用，然后详细讲解了BPMN2.0规范；实战篇（5~14章）系统讲解了Activiti的用法、技巧和最佳实践，包含流程定义、流程实例、任务、子流程、多实例、事件以及监听器等；高级篇（15~21）通过集成WebService、规则引擎、JPA、ESB等各种服务和中间件来阐述了Activiti不仅仅是引擎，实际上是一个BPM平台，最后还通过源代码对它的设计模式及PVM进行了分析。\"", ScriptService.ScriptType.INLINE)
//				.setScript("ctx._source.author = \"闫洪磊\"", ScriptService.ScriptType.INLINE)
//				.setScript("ctx._source.name = \"Activiti实战\"", ScriptService.ScriptType.INLINE)
//				.setScript("ctx._source.pubinfo = \"机械工业出版社\"", ScriptService.ScriptType.INLINE)
//				.setScript("ctx._source.pubtime = \"2015-01-01\"", ScriptService.ScriptType.INLINE)
//				.setScript("ctx._source.desc = \"《Activiti实战 》立足于实践，不仅让读者知其然，全面掌握Activiti架构、功能、用法、技巧和最佳实践，广度足够；而且让读者知其所以然，深入理解Activiti的源代码实现、设计模式和PVM，深度也足够。《Activiti实战 》一共四个部分：准备篇（1~2章）介绍了Activiti的概念、特点、应用、体系结构，以及开发环境的搭建和配置；基础篇（3~4章）首先讲解了Activiti Modeler、Activiti Designer两种流程设计工具的详细使用，然后详细讲解了BPMN2.0规范；实战篇（5~14章）系统讲解了Activiti的用法、技巧和最佳实践，包含流程定义、流程实例、任务、子流程、多实例、事件以及监听器等；高级篇（15~21）通过集成WebService、规则引擎、JPA、ESB等各种服务和中间件来阐述了Activiti不仅仅是引擎，实际上是一个BPM平台，最后还通过源代码对它的设计模式及PVM进行了分析。\"", ScriptService.ScriptType.INLINE)
				.execute()
				.actionGet();
		System.out.println("索引是否更新:"+response.isCreated());
		System.out.println("****************index ***********************");
		// Index name
		String _index = response.getIndex();
		// Type name
		String _type = response.getType();
		// Document ID (generated or not)
		String _id = response.getId();
		// Version (if it's the first time you index this document, you will get: 1)
		long _version = response.getVersion();
		System.out.println(_index + "," + _type + "," + _id + "," + _version);
	}

	/**
	 * 更新索引
	 * @param index
	 * @param type
	 * @param id
	 */
	private static void updateIndexByDoc(String index, String type, String id) throws Exception{
		Client client = createTransportClient();
		UpdateResponse response = client.prepareUpdate(index, type, id)
				.setDoc(jsonBuilder()
						.startObject()
						.field("name", "Hadoop权威指南（第3版 修订版）")
						.field("author", "Tom White")
						.field("pubinfo", "清华大学出版社")
						.field("pubtime", "2015-01-01")
						.field("desc", "《Hadoop权威指南（第3版 修订版）》通过丰富的案例学习来解释Hadoop的幕后机理，阐述了Hadoop如何解决现实生活中的具体问题。第3版覆盖Hadoop的最新动态，包括新增的MapReduceAPI，以及MapReduce2及其灵活性更强的执行模型（YARN）")
						.endObject())
				.execute()
				.actionGet();
		System.out.println("索引是否更新:"+response.isCreated());
		System.out.println("****************index ***********************");
		// Index name
		String _index = response.getIndex();
		// Type name
		String _type = response.getType();
		// Document ID (generated or not)
		String _id = response.getId();
		// Version (if it's the first time you index this document, you will get: 1)
		long _version = response.getVersion();
		System.out.println(_index + "," + _type + "," + _id + "," + _version);
	}
	
	/**
	 * 仅仅只删除索引
	 * @param index
	 * @param type
	 * @param id
	 */
	private static void deleteIndex(String index, String type, String id){
		Client client = createTransportClient();
		DeleteResponse response = client.prepareDelete(index, type, id)
				.execute()
				.actionGet();
		boolean isFound = response.isFound();
		System.out.println("索引是否 存在:"+isFound); // 发现doc已删除则返回true
		System.out.println("****************index ***********************");
		// Index name
		String _index = response.getIndex();
		// Type name
		String _type = response.getType();
		// Document ID (generated or not)
		String _id = response.getId();
		// Version (if it's the first time you index this document, you will get: 1)
		long _version = response.getVersion();
		System.out.println(_index+","+_type+","+_id+","+_version);
		
		//优化索引
		OptimizeRequest optimizeRequest = new OptimizeRequest(index);
	    OptimizeResponse optimizeResponse = client.admin().indices().optimize(optimizeRequest).actionGet();
	    System.out.println(optimizeResponse.getTotalShards()+","+optimizeResponse.getSuccessfulShards()+","+optimizeResponse.getFailedShards());
	    
	    //刷新索引
		FlushRequest flushRequest = new FlushRequest(index);
		flushRequest.force(true);
		FlushResponse flushResponse = client.admin().indices().flush(flushRequest).actionGet();
		System.out.println(flushResponse.getTotalShards()+","+flushResponse.getSuccessfulShards()+","+flushResponse.getFailedShards());
		
	}

	/**
	 * 删除查询到的文档
	 * @param index
	 * @param name
	 * @param value
	 */
	private static void scrollSearchDelete(String index,String name,String value){
		Client client = createTransportClient();
		QueryBuilder qb = termQuery(name, value);
		SearchResponse scrollResp = client.prepareSearch(index)
				.setSearchType(SearchType.SCAN)
				.setScroll(new TimeValue(60000))
				.setQuery(qb)
				.setSize(100).execute().actionGet(); //100 hits per shard will be returned for each scroll

		BulkRequestBuilder bulkRequest = client.prepareBulk();

		while (true) {
			for (SearchHit hit : scrollResp.getHits().getHits()) {
				bulkRequest.add(client.prepareDelete(hit.getIndex(),hit.getType(),hit.getId()));
			}
			scrollResp = client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(600000)).execute().actionGet();
			if (scrollResp.getHits().getHits().length == 0) {
				break;
			}
		}
		BulkResponse bulkResponse = bulkRequest.execute().actionGet();
		if (bulkResponse.hasFailures()) {
			BulkItemResponse[] bulkItemResponse = bulkResponse.getItems();
			for (int i = 0; i <bulkItemResponse.length ; i++) {
				System.out.println(bulkItemResponse[i].getItemId()+":"+bulkItemResponse[i].getIndex()+":"+bulkItemResponse[i].getFailureMessage());
			}
		}
	}


	/**
	 * 删除所有索引
	 * @param indices
	 */
	private static void deleteIndices(String indices){
		Client client = createTransportClient();
		DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indices);
		DeleteIndexResponse response = client.admin().indices().delete(deleteIndexRequest)
		        .actionGet();
		if(response.isAcknowledged()){
			System.out.println("删除成功!");
//			FlushRequest flushRequest = new FlushRequest(indices);
//			flushRequest.force(true);
//			FlushResponse flushResponse = client.admin().indices().flush(flushRequest).actionGet();
//			System.out.println(flushResponse.getTotalShards()+","+flushResponse.getSuccessfulShards()+","+flushResponse.getFailedShards());
		}
	}
	
	/**
	 * 获取索引信息
	 * @param index
	 * @param type
	 * @param id
	 */
	private static void getIndex(String index, String type, String id){
		Client client = createTransportClient();
		GetResponse response = client.prepareGet(index, type, id)
		        .execute()
		        .actionGet();
		boolean exists = response.isExists();
		System.out.println(exists);// 判断索引是否存在
		String sourceString = response.getSourceAsString();
		System.out.println(sourceString);// 获取索引,并且打印出索引内容
		System.out.println("****************index ***********************");
		// Index name
		String _index = response.getIndex();
		// Type name
		String _type = response.getType();
		// Document ID (generated or not)
		String _id = response.getId();
		// Version (if it's the first time you index this document, you will get: 1)
		long _version = response.getVersion();
		System.out.println(_index+","+_type+","+_id+","+_version);
	}
	
	/**
	 * Query Search
	 * @param index
	 * @param type
	 * @param term
	 * @param queryString
	 */
	private static void querySearch(String index, String type,String term,String queryString){
		Client client = createTransportClient();
		SearchResponse response = client.prepareSearch(index)
        .setTypes(type)
        // 设置查询类型
		// 1.SearchType.DFS_QUERY_THEN_FETCH = 精确查询
		// 2.SearchType.SCAN = 扫描查询,无序
		// 3.SearchType.COUNT = 不设置的话,这个为默认值,还有的自己去试试吧
        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
        // 设置查询关键词
        .setQuery(termQuery(term, queryString))             // Query
        //.setPostFilter(FilterBuilders.rangeFilter("age").from(12).to(18))   // Filter
        .addHighlightedField(term)
        .setHighlighterPreTags("<em>")
        .setHighlighterPostTags("</em>")
        // 设置查询数据的位置,分页用
		.setFrom(0)
		// 设置查询结果集的最大条数
		.setSize(60)
		// 设置是否按查询匹配度排序
		.setExplain(true)
		// 最后就是返回搜索响应信息
        .execute()
        .actionGet();
		SearchHits searchHits = response.getHits();
		System.out.println("-----------------在["+term+"]中搜索关键字["+queryString+"]---------------------");
		System.out.println("共匹配到:"+searchHits.getTotalHits()+"条记录!");
		SearchHit[] hits = searchHits.getHits();
		for (SearchHit searchHit : hits) {
			//获取高亮的字段
			Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
			HighlightField highlightField = highlightFields.get(term);
			System.out.println("高亮字段:"+highlightField.getName()+"\n高亮部分内容:"+highlightField.getFragments()[0].string());
			Map<String, Object> sourceAsMap = searchHit.sourceAsMap();
			Set<String> keySet = sourceAsMap.keySet();
			for (String string : keySet) {
				System.out.println(string+":"+sourceAsMap.get(string));
			}
			System.out.println();
		}
	}
	
	/**
	 * Query Search
	 * @param index
	 * @param type
	 * @param term
	 * @param queryString
	 */
	private static void scrollSearch(String index, String type,String term,String queryString){
		Client client = createTransportClient();
		SearchResponse scrollResp = client.prepareSearch(index)
		        .setScroll(new TimeValue(60000))
		        .setTypes(type)
		        // 设置查询类型
				// 1.SearchType.DFS_QUERY_THEN_FETCH = 精确查询
				// 2.SearchType.SCAN = 扫描查询,无序
				// 3.SearchType.COUNT = 不设置的话,这个为默认值,还有的自己去试试吧
		        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
		        // 设置查询关键词
		        .setQuery(termQuery(term, queryString))             // Query
		        //.setPostFilter(FilterBuilders.rangeFilter("age").from(12).to(18))   // Filter
		        .addHighlightedField(term)
		        .setHighlighterPreTags("<em>")
		        .setHighlighterPostTags("</em>")
		        .setSize(100).execute().actionGet(); //100 hits per shard will be returned for each scroll
		while (true) {
		    for (SearchHit searchHit : scrollResp.getHits()) {
		    	//获取高亮的字段
				Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
				HighlightField highlightField = highlightFields.get(term);
				System.out.println("高亮字段:"+highlightField.getName()+"\n高亮部分内容:"+highlightField.getFragments()[0].string());
				Map<String, Object> sourceAsMap = searchHit.sourceAsMap();
				Set<String> keySet = sourceAsMap.keySet();
				for (String string : keySet) {
					System.out.println(string+":"+sourceAsMap.get(string));
				}
				System.out.println();
		    }
		    scrollResp = client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(600000)).execute().actionGet();
		    if (scrollResp.getHits().getHits().length == 0) {
		        break;
		    }
		}
	}
	
	
	/**
	 * 
	 * @param queryString
	 */
	private static void multiSearch(String queryString){
		Client client = createTransportClient();
		SearchRequestBuilder srb1 = client.prepareSearch()
				.setQuery(QueryBuilders.queryStringQuery(queryString));
		
		SearchRequestBuilder srb2 = client.prepareSearch()
		        .setQuery(QueryBuilders.matchQuery("desc", queryString));

		MultiSearchResponse sr = client.prepareMultiSearch()
		        .add(srb1)
		        .add(srb2)
		        .execute().actionGet();

		long nbHits = 0;
		for (MultiSearchResponse.Item item : sr.getResponses()) {
		    SearchResponse response = item.getResponse();
		    nbHits += response.getHits().getTotalHits();
		    System.out.println("本次查询共匹配到:"+nbHits+"记录");
		    SearchHits searchHits = response.getHits();
			System.out.println("-----------------搜索关键字为:["+queryString+"]---------------------");
			System.out.println("共匹配到:"+searchHits.getTotalHits()+"条记录!");
			SearchHit[] hits = searchHits.getHits();
			for (SearchHit searchHit : hits) {
				Map<String, Object> sourceAsMap = searchHit.sourceAsMap();
				Set<String> keySet = sourceAsMap.keySet();
				for (String string : keySet) {
					System.out.println(string+":"+sourceAsMap.get(string));
				}
				System.out.println();
			}
		}
	}
	
	/**
	 * 
	 * @param indices
	 * @param field
	 * @param queryString
	 */
	private static void count(String indices,String field,String queryString){
		Client client = createTransportClient();
		CountResponse response = client.prepareCount(indices)
		        .setQuery(termQuery(field, queryString))
		        .execute()
		        .actionGet();
		long count = response.getCount();
		System.out.println("在文档"+indices+"中搜索字段"+field+"查询关键字:"+queryString+"共匹配到"+count+"条记录!");
	}

	private static void suggest(){
		Client client = createTransportClient();
		
//		CompletionSuggestionBuilder completionSuggestion = new CompletionSuggestionBuilder("suggestions");
//		completionSuggestion.field("text");
//		completionSuggestion.text("园");
//		completionSuggestion.size(10);
//		
//		SuggestRequestBuilder suggestRequestBuilder = client.prepareSuggest("article");
//		suggestRequestBuilder.addSuggestion(completionSuggestion);
//		SuggestResponse suggestResponse = suggestRequestBuilder.execute().actionGet();
//		
//		Suggestion<? extends Entry<? extends Option>> suggestion = suggestResponse.getSuggest().getSuggestion("suggestions");
//		for(Entry<? extends Option> entry:suggestion){
//			for (Option option : entry) {
//				System.out.println(option.getText().string());
//			}
//		}
		
		TermSuggestionBuilder termSuggestionBuilder = new TermSuggestionBuilder("suggest"); 
		termSuggestionBuilder.text("编程");
		termSuggestionBuilder.field("desc");
		TermSuggestion termSuggestion = client.prepareSuggest("book")
				.addSuggestion(termSuggestionBuilder)
				.execute()
				.actionGet()
				.getSuggest()
				.getSuggestion("suggest");
		Suggestion<? extends Entry<? extends Option>> suggestion = termSuggestion;
		for(Entry<? extends Option> entry:suggestion){
			for (Option option : entry) {
				System.out.println(option.getText().string());
			}
		}
		
	}
	
	
	private static void regexpQuery() {
		Client client = createTransportClient();
		RegexpQueryBuilder regexpQuery = QueryBuilders.regexpQuery("content", "健健|康康|圆圆|平平|安安|女神");
		SearchResponse searchResponse = client.prepareSearch("article")
				.setQuery(regexpQuery)
		        .addHighlightedField("content")
		        .setHighlighterPreTags("<em>")
		        .setHighlighterPostTags("</em>")
		        .setHighlighterFragmentSize(250)//设置高亮内容长度
		        // 设置查询数据的位置,分页用
				.setFrom(0)
				// 设置查询结果集的最大条数
				.setSize(60)
				// 设置是否按查询匹配度排序
				.setExplain(true)
				.execute()
				.actionGet();
		SearchHits searchHits = searchResponse.getHits();
		System.out.println("-----------------regexpQuery---------------------");
		System.out.println("共匹配到:"+searchHits.getTotalHits()+"条记录!");
		SearchHit[] hits = searchHits.getHits();
		for (SearchHit searchHit : hits) {
			//获取高亮的字段
			Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
			HighlightField highlightField = highlightFields.get("content");
			System.out.println("高亮字段:"+highlightField.getName()+"\n高亮部分内容:"+highlightField.getFragments()[0].string());
			Map<String, Object> sourceAsMap = searchHit.sourceAsMap();
			Set<String> keySet = sourceAsMap.keySet();
			for (String string : keySet) {
				System.out.println(string+":"+sourceAsMap.get(string));
			}
			System.out.println();
		}
	}
	
	
	private static void indicesQuery() {
		Client client = createTransportClient();
		IndicesQueryBuilder indicesQuery = QueryBuilders.indicesQuery(termQuery("content", "性虐"), "news","article","book");
		SearchResponse searchResponse = client.prepareSearch("article")
				.setQuery(indicesQuery)
		        .addHighlightedField("content")
		        .setHighlighterPreTags("<em>")
		        .setHighlighterPostTags("</em>")
		        .setHighlighterFragmentSize(250)//设置高亮内容长度
		        // 设置查询数据的位置,分页用
				.setFrom(0)
				// 设置查询结果集的最大条数
				.setSize(60)
				// 设置是否按查询匹配度排序
				.setExplain(true)
				.execute()
				.actionGet();
		SearchHits searchHits = searchResponse.getHits();
		System.out.println("-----------------indicesQuery---------------------");
		System.out.println("共匹配到:"+searchHits.getTotalHits()+"条记录!");
		SearchHit[] hits = searchHits.getHits();
		for (SearchHit searchHit : hits) {
			//获取高亮的字段
			Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
			HighlightField highlightField = highlightFields.get("content");
			System.out.println("高亮字段:"+highlightField.getName()+"\n高亮部分内容:"+highlightField.getFragments()[0].string());
			Map<String, Object> sourceAsMap = searchHit.sourceAsMap();
			Set<String> keySet = sourceAsMap.keySet();
			for (String string : keySet) {
				System.out.println(string+":"+sourceAsMap.get(string));
			}
			System.out.println();
		}
	}


	private static void wildcardQuery() {
		Client client = createTransportClient();
		WildcardQueryBuilder wildcardQuery = QueryBuilders.wildcardQuery("content", "S?");
		SearchResponse searchResponse = client.prepareSearch("article")
				.setQuery(wildcardQuery)
		        .addHighlightedField("content")
		        .setHighlighterPreTags("<em>")
		        .setHighlighterPostTags("</em>")
		        .setHighlighterFragmentSize(250)//设置高亮内容长度
		        // 设置查询数据的位置,分页用
				.setFrom(0)
				// 设置查询结果集的最大条数
				.setSize(60)
				// 设置是否按查询匹配度排序
				.setExplain(true)
				.execute()
				.actionGet();
		SearchHits searchHits = searchResponse.getHits();
		System.out.println("-----------------wildcardQuery---------------------");
		System.out.println("共匹配到:"+searchHits.getTotalHits()+"条记录!");
		SearchHit[] hits = searchHits.getHits();
		for (SearchHit searchHit : hits) {
			//获取高亮的字段
			Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
			HighlightField highlightField = highlightFields.get("content");
			System.out.println("高亮字段:"+highlightField.getName()+"\n高亮部分内容:"+highlightField.getFragments()[0].string());
			Map<String, Object> sourceAsMap = searchHit.sourceAsMap();
			Set<String> keySet = sourceAsMap.keySet();
			for (String string : keySet) {
				System.out.println(string+":"+sourceAsMap.get(string));
			}
			System.out.println();
		}
	}


	private static void termsQuery() {
		Client client = createTransportClient();
		TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery("content", "家暴","澄清","帮助","女神","性虐");
		SearchResponse searchResponse = client.prepareSearch("article")
				.setQuery(termsQueryBuilder)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
		        .addHighlightedField("content")
		        .setHighlighterPreTags("<em>")
		        .setHighlighterPostTags("</em>")
		        .setHighlighterFragmentSize(250)//设置高亮内容长度
		        // 设置查询数据的位置,分页用
				.setFrom(0)
				// 设置查询结果集的最大条数
				.setSize(60)
				// 设置是否按查询匹配度排序
				.setExplain(true)
				.execute()
				.actionGet();
		SearchHits searchHits = searchResponse.getHits();
		System.out.println("-----------------termsQuery---------------------");
		System.out.println("共匹配到:"+searchHits.getTotalHits()+"条记录!");
		SearchHit[] hits = searchHits.getHits();
		for (SearchHit searchHit : hits) {
			//获取高亮的字段
			Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
			HighlightField highlightField = highlightFields.get("content");
			System.out.println("高亮字段:"+highlightField.getName()+"\n高亮部分内容:"+highlightField.getFragments()[0].string());
			Map<String, Object> sourceAsMap = searchHit.sourceAsMap();
			Set<String> keySet = sourceAsMap.keySet();
			for (String string : keySet) {
				System.out.println(string+":"+sourceAsMap.get(string));
			}
			System.out.println();
		}
	}


	private static void rangeQuery() {
		Client client = createTransportClient();
		RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("id")
			.from(5)
			.to(25)
			.includeLower(true)
			.includeUpper(false);//rangeQuery 查询id在5到24之间的内容
		SearchResponse searchResponse = client.prepareSearch("article")
				.setQuery(rangeQuery)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
		        .addHighlightedField("content")
		        .setHighlighterPreTags("<em>")
		        .setHighlighterPostTags("</em>")
		        .setHighlighterFragmentSize(250)//设置高亮内容长度
		        // 设置查询数据的位置,分页用
				.setFrom(0)
				// 设置查询结果集的最大条数
				.setSize(60)
				// 设置是否按查询匹配度排序
				.setExplain(true)
				.execute()
				.actionGet();
		SearchHits searchHits = searchResponse.getHits();
		System.out.println("-----------------rangeQuery---------------------");
		System.out.println("共匹配到:"+searchHits.getTotalHits()+"条记录!");
		SearchHit[] hits = searchHits.getHits();
		for (SearchHit searchHit : hits) {
			//获取高亮的字段
			Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
			HighlightField highlightField = highlightFields.get("content");
			System.out.println("高亮字段:"+highlightField.getName()+"\n高亮部分内容:"+highlightField.getFragments()[0].string());
			Map<String, Object> sourceAsMap = searchHit.sourceAsMap();
			Set<String> keySet = sourceAsMap.keySet();
			for (String string : keySet) {
				System.out.println(string+":"+sourceAsMap.get(string));
			}
			System.out.println();
		}
	}


	private static void queryString() {
		Client client = createTransportClient();
		SearchResponse searchResponse = client.prepareSearch("article")
				.setQuery(QueryBuilders.queryStringQuery("女神 高圆圆 淤青 伤痕 床头"))
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
		        .addHighlightedField("content")
		        .setHighlighterPreTags("<em>")
		        .setHighlighterPostTags("</em>")
		        .setHighlighterFragmentSize(250)//设置高亮内容长度
		        // 设置查询数据的位置,分页用
				.setFrom(0)
				// 设置查询结果集的最大条数
				.setSize(60)
				// 设置是否按查询匹配度排序
				.setExplain(true)
				.execute()
				.actionGet();
		SearchHits searchHits = searchResponse.getHits();
		System.out.println("-----------------queryString---------------------");
		System.out.println("共匹配到:"+searchHits.getTotalHits()+"条记录!");
		SearchHit[] hits = searchHits.getHits();
		for (SearchHit searchHit : hits) {
			//获取高亮的字段
			Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
			HighlightField highlightField = highlightFields.get("content");
			System.out.println("高亮字段:"+highlightField.getName()+"\n高亮部分内容:"+highlightField.getFragments()[0].string());
			Map<String, Object> sourceAsMap = searchHit.sourceAsMap();
			Set<String> keySet = sourceAsMap.keySet();
			for (String string : keySet) {
				System.out.println(string+":"+sourceAsMap.get(string));
			}
			System.out.println();
		}
	}


	private static void prefixQuery() {
		Client client = createTransportClient();
		SearchResponse searchResponse = client.prepareSearch("article")
				.setQuery(QueryBuilders.prefixQuery("content","母亲"))
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
		        .addHighlightedField("content")
		        .setHighlighterPreTags("<em>")
		        .setHighlighterPostTags("</em>")
		        // 设置查询数据的位置,分页用
				.setFrom(0)
				// 设置查询结果集的最大条数
				.setSize(60)
				// 设置是否按查询匹配度排序
				.setExplain(true)
				.execute()
				.actionGet();
		SearchHits searchHits = searchResponse.getHits();
		System.out.println("-----------------prefixQuery---------------------");
		System.out.println("共匹配到:"+searchHits.getTotalHits()+"条记录!");
		SearchHit[] hits = searchHits.getHits();
		for (SearchHit searchHit : hits) {
			//获取高亮的字段
			Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
			HighlightField highlightField = highlightFields.get("content");
			System.out.println("高亮字段:"+highlightField.getName()+"\n高亮部分内容:"+highlightField.getFragments()[0].string());
			Map<String, Object> sourceAsMap = searchHit.sourceAsMap();
			Set<String> keySet = sourceAsMap.keySet();
			for (String string : keySet) {
				System.out.println(string+":"+sourceAsMap.get(string));
			}
			System.out.println();
		}
	}


	private static void matchAllQuery() {
		Client client = createTransportClient();
		SearchResponse searchResponse = client.prepareSearch("book")
				.setQuery(QueryBuilders.matchAllQuery())
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
		        // 设置查询数据的位置,分页用
				.setFrom(0)
				// 设置查询结果集的最大条数
				.setSize(60)
				// 设置是否按查询匹配度排序
				.setExplain(true)
				.execute()
				.actionGet();
		SearchHits searchHits = searchResponse.getHits();
		System.out.println("-----------------matchAllQuery---------------------");
		System.out.println("共匹配到:"+searchHits.getTotalHits()+"条记录!");
		SearchHit[] hits = searchHits.getHits();
		for (SearchHit searchHit : hits) {
			Map<String, Object> sourceAsMap = searchHit.sourceAsMap();
			Set<String> keySet = sourceAsMap.keySet();
			for (String string : keySet) {
				System.out.println(string+":"+sourceAsMap.get(string));
			}
			System.out.println();
		}
	}


	private static void fuzzyQuery() {
		Client client = createTransportClient();
		SearchResponse searchResponse = client.prepareSearch("article")
				.setQuery(QueryBuilders.fuzzyQuery("content","床头"))
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
		        .addHighlightedField("content")
		        .setHighlighterPreTags("<em>")
		        .setHighlighterPostTags("</em>")
		        // 设置查询数据的位置,分页用
				.setFrom(0)
				// 设置查询结果集的最大条数
				.setSize(60)
				// 设置是否按查询匹配度排序
				.setExplain(true)
				.execute()
				.actionGet();
		SearchHits searchHits = searchResponse.getHits();
		System.out.println("-----------------fuzzyQuery---------------------");
		System.out.println("共匹配到:"+searchHits.getTotalHits()+"条记录!");
		SearchHit[] hits = searchHits.getHits();
		for (SearchHit searchHit : hits) {
			//获取高亮的字段
			Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
			HighlightField highlightField = highlightFields.get("content");
			System.out.println("高亮字段:"+highlightField.getName()+"\n高亮部分内容:"+highlightField.getFragments()[0].string());
			Map<String, Object> sourceAsMap = searchHit.sourceAsMap();
			Set<String> keySet = sourceAsMap.keySet();
			for (String string : keySet) {
				System.out.println(string+":"+sourceAsMap.get(string));
			}
			System.out.println();
		}
	}

	@SuppressWarnings("deprecation")
	private static void fuzzyLikeQuery() {
		Client client = createTransportClient();
		SearchResponse searchResponse = client.prepareSearch("article")
				.setQuery(QueryBuilders.fuzzyLikeThisQuery("content").likeText("我 要 到 床头 去"))
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
		        .addHighlightedField("content")
		        .setHighlighterPreTags("<em>")
		        .setHighlighterPostTags("</em>")
		        // 设置查询数据的位置,分页用
				.setFrom(0)
				// 设置查询结果集的最大条数
				.setSize(60)
				// 设置是否按查询匹配度排序
				.setExplain(true)
				.execute()
				.actionGet();
		SearchHits searchHits = searchResponse.getHits();
		System.out.println("-----------------fuzzyLikeQuery---------------------");
		System.out.println("共匹配到:"+searchHits.getTotalHits()+"条记录!");
		SearchHit[] hits = searchHits.getHits();
		for (SearchHit searchHit : hits) {
			//获取高亮的字段
			Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
			HighlightField highlightField = highlightFields.get("content");
			System.out.println("高亮字段:"+highlightField.getName()+"\n高亮部分内容:"+highlightField.getFragments()[0].string());
			Map<String, Object> sourceAsMap = searchHit.sourceAsMap();
			Set<String> keySet = sourceAsMap.keySet();
			for (String string : keySet) {
				System.out.println(string+":"+sourceAsMap.get(string));
			}
			System.out.println();
		}
	}


	/**
	 * boolean query
	 */
	private static void booleanQuery() {
		Client client = createTransportClient();
		QueryBuilder queryBuilder = QueryBuilders
                .boolQuery()
                .must(termQuery("desc", "结构"))
                .must(termQuery("name", "深入"))
                .mustNot(termQuery("desc", "性虐"))
                .should(termQuery("desc", "GoWeb"));
		SearchResponse searchResponse = client.prepareSearch("book")
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
		        .addHighlightedField("desc")
		        .addHighlightedField("name")
		        .setHighlighterPreTags("<em>")
		        .setHighlighterPostTags("</em>")
		        // 设置查询数据的位置,分页用
				.setFrom(0)
				// 设置查询结果集的最大条数
				.setSize(60)
				// 设置是否按查询匹配度排序
				.setExplain(true)
				.setQuery(queryBuilder)
				.execute()
				.actionGet();
		SearchHits searchHits = searchResponse.getHits();
		System.out.println("-----------------boolQuery---------------------");
		System.out.println("共匹配到:"+searchHits.getTotalHits()+"条记录!");
		SearchHit[] hits = searchHits.getHits();
		for (SearchHit searchHit : hits) {
			//获取高亮的字段
			Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
			HighlightField deschighlightField = highlightFields.get("desc");
			System.out.println("高亮字段:"+deschighlightField.getName()+"\n高亮部分内容:"+deschighlightField.getFragments()[0].string());
			HighlightField namehighlightField = highlightFields.get("name");
			System.out.println("高亮字段:"+namehighlightField.getName()+"\n高亮部分内容:"+namehighlightField.getFragments()[0].string());
			Map<String, Object> sourceAsMap = searchHit.sourceAsMap();
			Set<String> keySet = sourceAsMap.keySet();
			for (String string : keySet) {
				System.out.println(string+":"+sourceAsMap.get(string));
			}
			System.out.println();
		}
	}


	/**
	 * 
	 * @param indices
	 * @param field
	 * @param queryString
	 */
	private static void matchQuery(String indices,String field,String queryString){
		Client client = createTransportClient();
		SearchResponse searchResponse = client.prepareSearch(indices)
		        .setQuery(QueryBuilders.matchQuery(field, queryString))
		        .execute()
		        .actionGet();
		SearchHits searchHits = searchResponse.getHits();
		System.out.println("---------------matchquery--在["+field+"]中搜索关键字["+queryString+"]---------------------");
		System.out.println("共匹配到:"+searchHits.getTotalHits()+"条记录!");
		SearchHit[] hits = searchHits.getHits();
		for (SearchHit searchHit : hits) {
			Map<String, Object> sourceAsMap = searchHit.sourceAsMap();
			Set<String> keySet = sourceAsMap.keySet();
			for (String string : keySet) {
				System.out.println(string+":"+sourceAsMap.get(string));
			}
			System.out.println();
		}
	}


	/**
	 * 创建Json字符串格式的索引
	 */
	private static void creatJsonStringIndex() {
		String json = "{" +
		        "\"name\":\"深入浅出Node.js\"," +
		        "\"author\":\"朴灵 \"," +
		        "\"pubinfo\":\"人民邮电出版社 \"," +
		        "\"pubtime\":\"2013-12-1 \"," +
		        "\"desc\":\"本书从不同的视角介绍了 Node 内在的特点和结构。由首章Node 介绍为索引，涉及Node 的各个方面，主要内容包含模块机制的揭示、异步I/O 实现原理的展现、异步编程的探讨、内存控制的介绍、二进制数据Buffer 的细节、Node 中的网络编程基础、Node 中的Web 开发、进程间的消息传递、Node 测试以及通过Node 构建产品需要的注意事项。最后的附录介绍了Node 的安装、调试、编码规范和NPM 仓库等事宜。本书适合想深入了解 Node 的人员阅读。\"" +
		    "}";
		createIndex("book","book",json);
	}
	
	/**
	 * 创建Map类型的索引
	 */
	private static void creatMapIndex() {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("name","Go Web编程");
		json.put("author","谢孟军 ");
		json.put("pubinfo","电子工业出版社");
		json.put("pubtime","2013-6");
		json.put("desc","《Go Web编程》介绍如何使用Go语言编写Web，包含了Go语言的入门、Web相关的一些知识、Go中如何处理Web的各方面设计（表单、session、cookie等）、数据库以及如何编写GoWeb应用等相关知识。通过《Go Web编程》的学习能够让读者了解Go的运行机制，如何用Go编写Web应用，以及Go的应用程序的部署和维护等，让读者对整个的Go的开发了如指掌。");
		Client client = createTransportClient();
		IndexResponse response = client.prepareIndex("book", "book").setSource(json).execute().actionGet();
		printIndexInfo(response);
	}

	/**
	 * 打印索引信息
	 * @param response
	 */
	private static void printIndexInfo(IndexResponse response) {
		System.out.println("****************index ***********************");
		// Index name
		String _index = response.getIndex();
		// Type name
		String _type = response.getType();
		// Document ID (generated or not)
		String _id = response.getId();
		// Version (if it's the first time you index this document, you will get: 1)
		long _version = response.getVersion();
		System.out.println(_index+","+_type+","+_id+","+_version);
	}
	
	/**
	 * 序列化Bean的方式创建索引
	 * @throws JsonProcessingException 
	 */
	private static void creatBeanIndex() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Article article = new Article(1,"高圆圆身上淤青 遭家暴还是玩SM遭性虐？","近日，有媒体拍到高圆圆身上的淤青，腿上还有两块伤疤，引起不少人猜测是遭受家暴。" +
				"对于遭到家暴的传闻，高圆圆首次作出澄清，称这是因为照顾母亲而留下的伤痕，她跟赵又廷关系好得很。" +
				"照顾母亲竟然会留下伤痕？究竟是怎么照顾的。" +
				"高圆圆称，“我妈当时住院，她翻身是需要旁人帮助的，我要到床头去，抱起她的上臂，然后她的脚一蹬，这样才能翻过来。" +
				"但我们两个的力气都不够，每次一用力的时候，我的大腿就会刚好撞在那个床框上，所以大腿上就撞出那两块淤青了。" +
				"事情真的这么简单吗？即使稍微一撞，也不至于淤青吧！" +
				"看到那个伤疤以及淤青的皮肤，不得不让人怀疑高圆圆是遭受家暴。" +
				"当然，还有另外一个原因，就是玩SM遭性虐。" +
				"当然，这么变态的事情，相信女神不会做的。" +
				"是照顾母亲留下的伤痕也好，遭受家暴也好，希望女神高圆圆以后都能平平安安健健康康吧！", "http://www.vdfly.com/star/20141119/37968.html", Calendar.getInstance().getTime(), "青春娱乐网", "匿名");
		String json = mapper.writeValueAsString(article);
		createIndex("article","article",json);
	}
	
	
	/**
	 * 使用Elasticsearch XContentBuilder 创建索引
	 * @throws Exception 
	 */
	private static void useXContentBuilderCreatIndex() throws Exception {
		XContentBuilder builder = jsonBuilder()
			    .startObject()
			    .field("name","Go Web编程")
				.field("author","谢孟军 ")
				.field("pubinfo","电子工业出版社")
				.field("pubtime","2013-6")
				.field("desc","《Go Web编程》介绍如何使用Go语言编写Web，包含了Go语言的入门、Web相关的一些知识、Go中如何处理Web的各方面设计（表单、session、cookie等）、数据库以及如何编写GoWeb应用等相关知识。通过《Go Web编程》的学习能够让读者了解Go的运行机制，如何用Go编写Web应用，以及Go的应用程序的部署和维护等，让读者对整个的Go的开发了如指掌。")
			    .endObject();
		String jsonstring = builder.string();
		createIndex("book","book",jsonstring);
	}
	
	
}
