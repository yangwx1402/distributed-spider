# distributed-spider
分布式爬虫框架


采用集群的方式来抓取，采用无中心架构，抓取支持selenium、httpclient、jsoup、htmlunit
                                                              解析支持xpath，css-selector
                                                              入库是不是可以采用gora

                    queue
           -----
injector--->crawler--->parser--->persist
           -----
                     login
