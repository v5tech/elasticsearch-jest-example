jest
====

ElasticSearch Java Rest Client Examples

### 高亮查询(highlight)

```
POST http://127.0.0.1:9200/news/_search?q=李克强
{
    "query" : {
        match_all:{}
    },
    "highlight" : {
        "pre_tags" : ["<font color='red'>", "<b>", "<em>"],
        "post_tags" : ["</font>", "<b>", "</em>"],
        "fields" : [
            {"title" : {}},
            {"content" : {
                "fragment_size" : 350,
                "number_of_fragments" : 3,
                "no_match_size": 150
            }}
        ]
    }
}
```

```
POST http://127.0.0.1:9200/news/_search?q=李克强
{
    "query" : {
        match_all:{}
    },
    "highlight" : {
        "pre_tags" : ["<font color='red'><b><em>"],
        "post_tags" : ["</font><b></em>"],
        "fields" : [
            {"title" : {}},
            {"content" : {
                "fragment_size" : 350,
                "number_of_fragments" : 3,
                "no_match_size": 150
            }}
        ]
    }
}
```

### 删除索引

https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-delete-index.html

```
DELETE http://127.0.0.1:9200/news
```

### 创建索引

https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-create-index.html

```
PUT http://127.0.0.1:9200/news
```

### 创建或修改mapping

https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-put-mapping.html

```
PUT /{index}/_mapping/{type}
```

```
PUT http://127.0.0.1:9200/news/_mapping/article
{
  "article": {
    "properties": {
      "pubdate": {
        "type": "date",
        "format": "dateOptionalTime"
      },
      "author": {
        "type": "string"
      },
      "content": {
        "type": "string"
      },
      "id": {
        "type": "long"
      },
      "source": {
        "type": "string"
      },
      "title": {
        "type": "string"
      },
      "url": {
        "type": "string"
      }
    }
  }
}
```

### 查看mapping

https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-get-mapping.html


```
GET http://127.0.0.1:9200/_all/_mapping

GET http://127.0.0.1:9200/_mapping
```

```
GET http://127.0.0.1:9200/news/_mapping/article
```

输出:

```
{
  "news": {
    "mappings": {
      "article": {
        "properties": {
          "author": {
            "type": "string"
          },
          "content": {
            "type": "string"
          },
          "id": {
            "type": "long"
          },
          "pubdate": {
            "type": "date",
            "store": true,
            "format": "yyyy-MM-dd HH:mm:ss"
          },
          "source": {
            "type": "string"
          },
          "title": {
            "type": "string"
          },
          "url": {
            "type": "string"
          }
        }
      }
    }
  }
}
```

### 删除mapping

https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-delete-mapping.html

```
[DELETE] /{index}/{type}

[DELETE] /{index}/{type}/_mapping

[DELETE] /{index}/_mapping/{type}
```

```
DELETE http://127.0.0.1:9200/news/_mapping/article
```

### ansj分词器测试

http://127.0.0.1:9200/news/_analyze?analyzer=ansj_index&text=习近平

http://127.0.0.1:9200/news/_analyze?analyzer=ansj_index&text=我是中国人

http://127.0.0.1:9200/news/_analyze?analyzer=ansj_index&text=汪东兴同志遗体在京火化汪东兴同志病重期间和逝世后，习近平李克强张德江俞正声刘云山王岐山张高丽江泽民胡锦涛等同志，前往医院看望或通过各种形式对汪东兴同志逝世表示沉痛哀悼并向其亲属表示深切慰问新华社北京8月27日电中国共产党的优秀党员

### ansj分词器查询

* 普通查询

http://127.0.0.1:9200/news/_search?q=习近平&analyzer=ansj_index&size=50

* 指定term查询

http://127.0.0.1:9200/news/_search?q=content:江泽民&analyzer=ansj_index&size=50

http://127.0.0.1:9200/news/_search?q=title:江泽民&analyzer=ansj_index&size=50

http://127.0.0.1:9200/news/_search?q=source:新华网&analyzer=ansj_index&size=50

* 其中`ansj_index`为在`elasticsearch.yml`文件中配置的`ansj`分词器

[elasticsearch rest api 快速上手](https://github.com/sxyx2008/elasticsearch/issues/5)

### elasticsearch-jdbc

```bash
@echo off

set DIR=%~dp0
set LIB="%DIR%\..\lib\*"
set BIN="%DIR%\..\bin\*"

REM ???
echo {^
    "type" : "jdbc",^
    "jdbc" : {^
        "url" : "jdbc:mysql://localhost:3306/news",^
        "user" : "root",^
        "password" : "root",^
        "schedule" : "0 0/15 * ? * *",^
        "sql" :  [^
             {"statement":"SELECT title,content,url,source,author,pubdate FROM news"},^
             {^
                "statement":"SELECT title,content,url,source,author,pubdate FROM news where pubdate > ?",^
                "parameter" : [ "$metrics.lastexecutionstart" ]^
             }^
	],^
	"autocommit" : true,^
        "treat_binary_as_string" : true,^
        "elasticsearch" : {^
             "cluster" : "elasticsearch",^
             "host" : "localhost",^
             "port" : 9300^
        },^
        "index" : "news",^
        "type" : "article"^
      }^
}^ | "%JAVA_HOME%\bin\java" -cp "%LIB%" -Dlog4j.configurationFile="file://%DIR%\log4j2.xml" "org.xbib.tools.Runner" "org.xbib.tools.JDBCImporter"
```

[elasticsearch-jdbc 插件的使用](https://github.com/sxyx2008/elasticsearch/issues/3)
