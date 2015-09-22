<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en-US">
<head>
    <meta charset="utf-8">
    <title>elasticsearch-jest</title>
    <meta name="keywords" content="free icons, icon search, iconfinder, market place"/>
    <meta name="description" content="elasticsearch jest"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <link href="/css/pager.css" rel="stylesheet" type="text/css" />
    <script src="/js/jquery.pager.js" type="text/javascript" charset="utf-8"></script>
    <style>
        .bs-docs-footer {
            padding-top: 40px;
            padding-bottom: 40px;
            margin-top: 420px;
            color: #767676;
            text-align: center;
            border-top: 1px solid #e5e5e5;
        }
        .search{
            text-align: center;
            border-bottom: 1px solid #e5e5e5;
            padding-bottom: 50px;
        }
        em{
            font-style: normal;
            color: red;
        }
        p{
            margin-top: 15px;
            text-align: right;
        }
    </style>
</head>
<body>
<div class="header">
    <div class="navbar">
        <div class="navbar-inner">
            <div class="container">
                <div style="width: 276px;">
                    <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
                         x="0px" y="0px" viewBox="0 0 400 130" preserveAspectRatio="xMinYMin meet"
                         enable-background="new 0 0 400 130" xml:space="preserve">
                            <g>
                                <g>
                                    <path fill="#FFFFFF" d="M122.5,67.8c0-10.3-6.4-19.2-15.9-22.7c0.4-2.2,0.6-4.3,0.6-6.6c0-19.1-15.5-34.6-34.6-34.6
                                        c-11.1,0-21.5,5.3-28,14.4c-3.2-2.5-7.1-3.8-11.2-3.8c-10.1,0-18.4,8.2-18.4,18.4c0,2.2,0.4,4.4,1.1,6.4C6.5,42.6,0,51.8,0,62
                                        c0,10.3,6.4,19.3,16,22.8c-0.4,2.1-0.6,4.3-0.6,6.6c0,19,15.5,34.5,34.5,34.5c11.2,0,21.5-5.4,28-14.4c3.2,2.5,7.2,3.9,11.3,3.9
                                        c10.1,0,18.4-8.2,18.4-18.4c0-2.2-0.4-4.4-1.1-6.4C115.9,87.2,122.5,78,122.5,67.8z"></path>
                                    <g>
                                        <path fill="#F4BD19" d="M47.9,56.3l27.3,12.5l27.6-24.2c0.4-2,0.6-4,0.6-6.1c0-17-13.8-30.8-30.8-30.8c-10.2,0-19.7,5-25.4,13.4
                                            l-4.6,23.8L47.9,56.3z"></path>
                                    </g>
                                    <g>
                                        <path fill="#3CBEB1" d="M19.6,85.2c-0.4,2-0.6,4.1-0.6,6.2c0,17,13.9,30.9,30.9,30.9c10.3,0,19.8-5.1,25.6-13.5L80,85l-6.1-11.6
                                            L46.5,60.9L19.6,85.2z"></path>
                                    </g>
                                    <g>
                                        <path fill="#E9478C" d="M19.4,37.9l18.7,4.4L42.3,21c-2.6-2-5.7-3-9-3c-8.1,0-14.8,6.6-14.8,14.8C18.5,34.5,18.8,36.3,19.4,37.9z
                                            "></path>
                                    </g>
                                    <g>
                                        <path fill="#2C458F"
                                              d="M17.8,42.4C9.4,45.1,3.6,53.2,3.6,62c0,8.6,5.3,16.3,13.3,19.3l26.3-23.8l-4.8-10.3L17.8,42.4z"></path>
                                    </g>
                                    <g>
                                        <path fill="#95C63D" d="M80.3,108.7c2.6,2,5.7,3.1,8.9,3.1c8.1,0,14.8-6.6,14.8-14.8c0-1.8-0.3-3.5-0.9-5.1l-18.7-4.4L80.3,108.7
                                            z"></path>
                                    </g>
                                    <g>
                                        <path fill="#176655"
                                              d="M84.1,82.6l20.6,4.8c8.4-2.8,14.2-10.8,14.2-19.6c0-8.6-5.3-16.2-13.3-19.3l-27,23.6L84.1,82.6z"></path>
                                    </g>
                                </g>
                                <path d="M162.8,76.3c0,0.1,0,0.6,0.1,1.4c0.1,0.8,0.3,1.8,0.6,3c0.3,1.1,0.8,2.4,1.4,3.7c0.6,1.3,1.4,2.5,2.5,3.6
                                    c1,1.1,2.3,2,3.8,2.7c1.5,0.7,3.4,1.1,5.6,1.1c1.8,0,3.3-0.1,4.6-0.4c1.3-0.3,2.4-0.7,3.4-1.3c1-0.6,1.9-1.3,2.7-2.2
                                    c0.8-0.9,1.6-1.9,2.4-3.1c0.5-0.7,1-1.2,1.6-1.5c0.6-0.3,1.2-0.4,1.8-0.4c1,0,2,0.4,2.8,1.1c0.9,0.7,1.3,1.7,1.3,2.8
                                    c0,0.7-0.4,1.7-1.1,3.1c-0.7,1.4-1.9,2.8-3.6,4.1c-1.6,1.4-3.7,2.6-6.3,3.6c-2.6,1-5.7,1.5-9.5,1.5c-3.8,0-7.2-0.7-10.2-2.1
                                    c-3-1.4-5.5-3.2-7.5-5.6c-2-2.3-3.6-5-4.7-8.1c-1.1-3.1-1.6-6.3-1.6-9.7c0-3.5,0.5-6.8,1.6-9.9c1.1-3.1,2.7-5.8,4.7-8.1
                                    c2-2.3,4.5-4.1,7.4-5.5c2.9-1.3,6.1-2,9.6-2c3.3,0,6.3,0.5,9.2,1.5c2.8,1,5.3,2.4,7.4,4.3c2.1,1.9,3.7,4.1,5,6.8
                                    c1.2,2.7,1.8,5.8,1.8,9.2c0,2.1-0.5,3.7-1.6,4.8c-1,1.1-2.2,1.6-3.6,1.6H162.8z M170.9,56.4c-1.5,0.6-2.7,1.4-3.7,2.4
                                    c-1,0.9-1.8,2-2.4,3.1c-0.6,1.1-1,2.2-1.3,3.2c-0.3,1-0.5,1.8-0.5,2.5c-0.1,0.7-0.1,1.1-0.1,1.2h27.9c0-1.8-0.3-3.5-0.9-5.1
                                    c-0.6-1.6-1.5-3-2.6-4.3c-1.2-1.2-2.7-2.2-4.5-3c-1.8-0.7-4-1.1-6.5-1.1C174.2,55.5,172.4,55.8,170.9,56.4z"></path>
                                <path d="M214.3,94.6c0,1.3-0.4,2.3-1.3,3.2c-0.9,0.9-1.9,1.3-3.2,1.3c-1.3,0-2.3-0.4-3.2-1.3c-0.9-0.9-1.3-2-1.3-3.2V35.1
                                    c0-1.3,0.4-2.3,1.3-3.2c0.9-0.9,2-1.3,3.2-1.3c1.3,0,2.3,0.4,3.2,1.3c0.9,0.9,1.3,1.9,1.3,3.2V94.6z"></path>
                                <path d="M229.8,63.5c-0.6,0.3-1.2,0.5-1.9,0.5c-1.2,0-2.2-0.4-3.2-1.1c-1-0.7-1.5-1.8-1.5-3.1c0-0.5,0.1-1.1,0.4-1.9
                                    c0.5-1.2,1.2-2.4,2.1-3.6c0.9-1.2,2-2.2,3.5-3.1s3.3-1.7,5.4-2.2c2.2-0.5,4.8-0.8,7.9-0.8c5.9,0,10.5,1.2,14,3.5
                                    c3.5,2.3,5.2,5.8,5.2,10.3v32.8c0,1.3-0.4,2.3-1.3,3.2c-0.9,0.9-1.9,1.3-3.2,1.3c-1.3,0-2.3-0.4-3.2-1.3c-0.9-0.9-1.3-2-1.3-3.2v-2
                                    c-1.2,2-4.7,4.5-7.5,5.3c-2.8,0.8-5.7,1.2-8.8,1.2c-2,0-3.9-0.3-5.9-0.8c-2-0.5-3.8-1.4-5.3-2.5c-1.6-1.1-2.9-2.6-3.8-4.3
                                    c-1-1.7-1.5-3.8-1.5-6.2c0-2.8,0.5-5.7,1.6-7.5c1.1-1.9,2.6-3.4,4.4-4.6c1.8-1.2,3.9-2.1,6.3-2.8c2.4-0.6,4.9-1.1,7.5-1.4
                                    c3-0.3,5.4-0.7,7.2-1.2c1.7-0.4,3-0.9,3.9-1.5c0.9-0.6,1.4-1.2,1.6-2c0.2-0.7,0.3-1.6,0.3-2.5c0-1.1-0.3-2-0.9-2.8
                                    c-0.6-0.8-1.4-1.5-2.4-2c-1-0.5-2.1-0.9-3.3-1.2s-2.4-0.4-3.5-0.4c-2.1,0-3.7,0.2-5,0.5c-1.3,0.3-2.3,0.8-3.1,1.4
                                    c-0.8,0.6-1.5,1.3-1.9,2.1c-0.5,0.8-0.9,1.6-1.3,2.5C230.9,62.6,230.4,63.1,229.8,63.5z M250.6,74.4c-1.1,0.3-2.4,0.6-3.8,0.8
                                    c-1.5,0.2-3,0.4-4.6,0.6c-1.6,0.2-3.1,0.5-4.6,0.9c-1.5,0.3-2.8,0.8-4,1.5c-1.2,0.7-2.2,1.5-2.9,2.5c-0.7,1-1.1,3-1.1,4.6
                                    c0,1.3,0.3,2.3,0.8,3.1c0.5,0.8,1.2,1.5,2.1,2c0.9,0.5,1.8,0.8,2.9,1c1.1,0.2,2.2,0.3,3.3,0.3c1.7,0,3.3-0.2,5-0.6
                                    c1.7-0.4,3.2-1,4.5-1.9s2.4-2,3.3-3.5c0.8-1.4,1.2-3.8,1.2-5.9v-6.1L250.6,74.4z"></path>
                                <path d="M307.6,90.9c-1.1,2-2.7,3.6-4.6,4.8s-4.1,2.1-6.6,2.7c-2.5,0.5-5.2,0.8-7.9,0.8c-3.9,0-7.2-0.6-9.9-1.8
                                    c-2.7-1.2-4.9-2.6-6.5-4.1c-1.7-1.6-2.9-3.1-3.7-4.7c-0.7-1.6-1.1-2.7-1.1-3.5c0-1.3,0.5-2.4,1.4-3.2c0.9-0.8,2-1.2,3.1-1.2
                                    c0.7,0,1.3,0.2,2,0.6c0.6,0.4,1.1,1,1.5,1.9c1,2.5,2.5,4.5,4.7,6.2c2.2,1.7,5.2,2.5,9.3,2.5c1.8,0,3.4-0.2,4.7-0.6
                                    c1.3-0.4,2.4-1,3.2-1.7c0.8-0.7,1.4-1.5,1.9-2.4c0.4-0.9,0.6-1.8,0.6-2.8c0-2-0.8-3.4-2.3-4.4c-1.5-1-3.5-1.7-5.8-2.3
                                    c-2.3-0.6-4.8-1.1-7.5-1.6c-2.7-0.5-5.2-1.2-7.5-2.2c-2.3-0.9-4.2-2.3-5.8-4c-1.6-1.8-2.3-4.2-2.3-7.3c0-4.5,1.6-8,4.9-10.5
                                    c3.3-2.5,7.8-3.8,13.7-3.8c3.3,0,6,0.3,8.3,1c2.2,0.7,4.1,1.6,5.5,2.7c0.6,0.5,1.2,1,1.8,1.7c0.6,0.7,1.2,1.4,1.8,2.2
                                    c0.6,0.8,1,1.5,1.4,2.3c0.4,0.7,0.6,1.4,0.6,2c0,1.3-0.5,2.4-1.6,3.1c-1,0.8-2.2,1.2-3.4,1.2c-0.7,0-1.5-0.1-2.1-0.6
                                    c-0.6-0.6-1-1.3-1.3-1.8c-0.5-1.1-1.1-2-1.7-2.8c-0.5-0.8-1.2-1.5-2-2c-0.8-0.5-1.8-0.9-3-1.2c-1.2-0.3-2.7-0.4-4.4-0.4
                                    c-3.5,0-6,0.6-7.6,1.7c-1.6,1.1-2.4,2.5-2.4,4.2c0,1.3,0.5,2.3,1.4,3.1c0.9,0.8,2.1,1.4,3.7,2c1.5,0.5,3.3,1,5.2,1.4
                                    c2,0.4,3.9,0.8,5.9,1.3c2,0.5,4,1.1,5.9,1.7c1.9,0.7,3.7,1.6,5.2,2.7c1.5,1.1,2.7,2.5,3.7,4.1c0.9,1.6,1.4,3.6,1.4,6
                                    C309.4,86.4,308.8,88.9,307.6,90.9z"></path>
                                <path d="M327.4,48.8h4.7c1.1,0,2,0.4,2.8,1.2c0.8,0.8,1.2,1.7,1.2,2.8c0,1.1-0.4,2.1-1.2,2.8s-1.7,1.1-2.8,1.1h-4.7v37.9
                                    c0,1.3-0.4,2.3-1.3,3.2c-0.9,0.9-2,1.3-3.2,1.3c-1.3,0-2.3-0.4-3.2-1.3c-0.9-0.9-1.3-2-1.3-3.2V56.7h-4.6c-1.1,0-2-0.4-2.8-1.1
                                    c-0.8-0.7-1.2-1.7-1.2-2.8c0-1.1,0.4-2,1.2-2.8c0.8-0.8,1.7-1.2,2.8-1.2h4.6V39c0-1.3,0.4-2.3,1.3-3.2s1.9-1.3,3.2-1.3
                                    c1.3,0,2.3,0.4,3.2,1.3s1.3,1.9,1.3,3.2V48.8z"></path>
                                <path d="M341.5,32.1c1-1,2.2-1.5,3.7-1.5c1.4,0,2.6,0.5,3.6,1.5c1,1,1.5,2.2,1.5,3.6c0,1.4-0.5,2.6-1.5,3.6c-1,1-2.2,1.5-3.6,1.5
                                    c-1.4,0-2.7-0.5-3.7-1.5c-1-1-1.5-2.2-1.5-3.6C340,34.2,340.5,33,341.5,32.1z M349.6,94.6c0,1.3-0.4,2.3-1.3,3.2
                                    c-0.9,0.9-1.9,1.3-3.2,1.3c-1.3,0-2.3-0.4-3.2-1.3c-0.9-0.9-1.3-2-1.3-3.2V53.3c0-1.3,0.4-2.3,1.3-3.2c0.9-0.9,2-1.3,3.2-1.3
                                    c1.3,0,2.3,0.4,3.2,1.3c0.9,0.9,1.3,2,1.3,3.2V94.6z"></path>
                                <path d="M369.1,97.3c-2.9-1.2-5.4-3-7.5-5.2c-2.1-2.2-3.8-4.9-5-8.1c-1.2-3.1-1.8-6.6-1.8-10.4c0-3.8,0.6-7.3,1.8-10.5
                                    c1.2-3.1,2.9-5.8,5-8.1c2.1-2.2,4.6-4,7.5-5.2c2.9-1.2,6-1.9,9.4-1.9c3.4,0,6.4,0.5,9,1.5c2.5,1,4.6,2.2,6.2,3.6
                                    c1.6,1.4,3.2,2.9,4,4.4c0.5,0.9,0.9,1.5,0.9,2.6c0,1.3-0.5,2.3-1.4,3.1c-0.9,0.7-2,1.1-3.1,1.1c-0.8,0-1.6-0.2-2.2-0.6
                                    c-0.7-0.4-1.2-1.3-1.8-2.1c-1-1.4-0.5-0.6-1.1-1.5c-0.7-0.9-1.5-1.6-2.5-2.3c-1-0.7-2.2-1.2-3.5-1.6c-1.4-0.4-2.8-0.6-4.4-0.6
                                    c-1.8,0-3.5,0.3-5.3,1c-1.8,0.7-3.3,1.7-4.7,3.1c-1.4,1.4-2.5,3.3-3.4,5.6s-1.3,5.1-1.3,8.4c0,3.3,0.4,6.1,1.3,8.4s2,4.2,3.4,5.6
                                    c1.4,1.4,3,2.5,4.7,3.1c1.8,0.7,3.5,1,5.3,1c1.6,0,3.1-0.2,4.4-0.6c1.3-0.4,2.5-0.9,3.5-1.6c1-0.7,1.8-1.5,2.5-2.3
                                    c0.7-0.9,1.1-1.8,1.4-2.7c0.3-1,0.8-1.7,1.5-2.1c0.7-0.4,1.4-0.6,2.2-0.6c1.1,0,2.2,0.4,3.1,1.1c0.9,0.7,1.4,1.7,1.4,3
                                    c0,0.6-0.3,1.7-0.9,3.1c-0.6,1.5-1.6,2.9-3.1,4.4c-1.5,1.5-3.5,2.8-6.2,4c-2.6,1.1-6,1.7-10,1.7C375.1,99.2,372,98.5,369.1,97.3z"></path>
                            </g>
                        </svg>
                </div>
            </div>
        </div>
    </div>
    <div class="search">
        <div class="container">
            <section>
                <div class="row-fluid">
                    <div>
                        <form id="searchform" name="searchform" action="/search" class="form-inline" method="post">
                            <div class="form-group">
                                <select class="form-control" name="field">
                                    <option value="all" <c:if test="${field==\"all\"}">selected</c:if>>全部</option>
                                    <option value="title" <c:if test="${field==\"title\"}">selected</c:if>>标题</option>
                                    <option value="content" <c:if test="${field==\"content\"}">selected</c:if>>内容</option>
                                    <option value="source" <c:if test="${field==\"source\"}">selected</c:if>>来源</option>
                                    <option value="author" <c:if test="${field==\"author\"}">selected</c:if>>作者</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" name="queryString" value="${queryString}" style="width: 396px;">
                            </div>
                            <div class="form-group">
                                <select class="form-control" name="older">
                                    <option value="asc" <c:if test="${older==\"asc\"}">selected</c:if>>升序</option>
                                    <option value="desc" <c:if test="${older==\"desc\"}">selected</c:if>>降序</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <select class="form-control" name="pageSize" id="pageSize">
                                    <option value="10" <c:if test="${pageSize==10}">selected</c:if>>10</option>
                                    <option value="20" <c:if test="${pageSize==20}">selected</c:if>>20</option>
                                    <option value="50" <c:if test="${pageSize==50}">selected</c:if>>50</option>
                                    <option value="100" <c:if test="${pageSize==100}">selected</c:if>>100</option>
                                </select>
                            </div>
                            <input type="hidden" name="pageNumber" value="${pageNumber}" id="pageNumber">
                            <button class="btn btn-primary" type="submit">搜索</button>
                        </form>
                    </div>
                </div>
            </section>
            <c:if test="${!empty count}">
                <h2><span class="label label-success">搜索关键字<em>${queryString}</em>共检索到<em>${count}</em>条记录，共<em>${totalPages}</em>页，每页显示<em>${pageSize}</em>条。共耗时<em>${took}</em>毫秒。</span></h2>
            </c:if>
        </div>
    </div>
</div>
<div class="content">
    <div class="container">
        <c:if test="${empty articles}">
            <h2><span class="label label-success">没有要查询的内容</span></h2>
        </c:if>
        <c:forEach items="${articles}" var="article">
            <div>
                <h3><a href="${article.url}" target="_blank">${article.title}</a></h3>
                <span>${article.content}</span>
                <p>来源：${article.source} 编辑：${article.author} 发表日期：<fmt:formatDate pattern="yyyy-MM-dd" value="${article.pubdate}" /></p>
            </div>
        </c:forEach>
        <div class="pager" id="pageNav">
        </div>
    </div>
</div>
<footer class="bs-docs-footer">
    <div class="container">
        <h1>elasticsearch jest client</h1>
    </div>
</footer>
<!--返回顶部开始-->
<div id="full" style="width:0px; height:0px; position:fixed; right:70px; bottom:150px; z-index:100; text-align:center; background-color:transparent; cursor:pointer;">
    <a href="#" onclick="goTop();return false;"><img src="/images/top.png" width="70px;" height="70px;" border=0 alt="回到顶部"></a>
</div>
<script src="/js/top.js" type="text/javascript"></script>
<!--返回顶部结束-->
<script type="text/javascript">
<c:if test="${totalPages!=0}">
$(document).ready(function() {
    $("#pageNav").pager(
            {
                pagenumber:${pageNumber},
                pagecount:${totalPages},
                buttonClickCallback:function(pageCurrent) {
                    document.getElementById('pageNumber').value = pageCurrent;
                    document.getElementById('searchform').submit();
                }
            });
});
</c:if>
$(function(){
    $('select').change(function(){
        document.getElementById('searchform').submit();
    });
});
</script>
</body>
</html>
