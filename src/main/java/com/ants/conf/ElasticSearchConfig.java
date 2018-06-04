package com.ants.conf;


import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
  * @description  ElasticSearch6.x的配置.
  * @author ants·ht
  * @date 2018/6/4 10:50
*/
@Slf4j
@Configuration
public class ElasticSearchConfig {


    @Value("${tuanche.elasticsearch.cluster-name}")
    private String elasticsearchClusterName;

    @Value("${tuanche.elasticsearch.cluster-nodes}")
    private String elasticsearchClusterNodes;

    @Value("${tuanche.elasticsearch.thread-pool}")
    private Integer elasticsearchThreadPool;

    @Value("${tuanche.elasticsearch.connect-timeout}")
    private int connectTimeOut;

    @Value("${tuanche.elasticsearch.socket-timeout}")
    private int socketTimeOut;

    @Value("${tuanche.elasticsearch.connection-request-timeout}")
    private int connectionRquestTimeOut;

    @Value("${tuanche.elasticsearch.max-connect-num}")
    private int maxConnectNum;

    @Value("${tuanche.elasticsearch.max-connect-per-route}")
    private int maxConnectPerRout;

    @Value("${tuanche.elasticsearch.unique-connect-time-config}")
    private boolean uniqueConnectTimeConfig;

    @Value("${tuanche.elasticsearch.unique-connect-num-config}")
    private boolean uniqueConnectNumConfig;


    private String defaultHost = "127.0.0.1";
    private Integer defaultPort = 9200;
    private String defaultScheme = "http";



    private RestClientBuilder restClientBuilder;
    private RestHighLevelClient restHighLevelClient;
    private HttpHost[] httpHosts;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        initConfig();
        restClientBuilder = RestClient.builder(httpHosts);
        if(uniqueConnectTimeConfig) {
            setConnectTimeConfig();
        }
        if(uniqueConnectNumConfig) {
            setConnectNumConfig();
        }
        restHighLevelClient = new RestHighLevelClient(restClientBuilder);
        log.info("已获取到客户端链接.......................");
        return restHighLevelClient;
    }

    /**
     * @description 关于异步httpclient 的 链接 延时配置
     * @author ants·ht
     * @date 2018/6/4 14:27
     * @param
     * @return
    */
    private void setConnectTimeConfig() {
        restClientBuilder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder builder) {
                builder.setConnectTimeout(connectTimeOut);
                builder.setSocketTimeout(socketTimeOut);
                builder.setConnectionRequestTimeout(connectionRquestTimeOut);
                return builder;
            }
        });
    }

    /**
     * @description 关于异步httpclient的链接数量的配置
     * @author ants·ht
     * @date 2018/6/4 14:29
     * @param
     * @return
    */
    private void setConnectNumConfig() {
        restClientBuilder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                httpAsyncClientBuilder.setMaxConnTotal(maxConnectNum);
                httpAsyncClientBuilder.setMaxConnPerRoute(maxConnectPerRout);
                return httpAsyncClientBuilder;
            }
        });
    }

    /**
     * @description
     * @author ants·ht 初始化客户端的配置
     * @date 2018/6/4 14:36
     * @param
     * @return
    */
    private void initConfig() {
        printElasticSearchYmlConf();
        if(null != elasticsearchClusterNodes && "" != elasticsearchClusterNodes && !"null".equals(elasticsearchClusterNodes)) {
            String[] splitNodes = elasticsearchClusterNodes.split(",");
            httpHosts = new HttpHost[splitNodes.length];
            for (int i = 0; i < splitNodes.length; i++) {
                httpHosts[i] = new HttpHost(splitNodes[i].split(":")[0],Integer.valueOf(splitNodes[i].split(":")[1]),defaultScheme);
            }
        } else {
            httpHosts = new HttpHost[]{new HttpHost(defaultHost,defaultPort,defaultScheme)};
        }
    }

    public void closeClient() {
        if(null != restHighLevelClient) {
            try {
                restHighLevelClient.close();
            } catch (Exception e) {
                log.error("close client error");
            }
        }
    }

    private void printElasticSearchYmlConf() {
        log.info("#################################ElasticSearch Config#########################");
        log.info("elasticsearchClusterName-->{}",elasticsearchClusterName);
        log.info("elasticsearchClusterNodes-->{}",elasticsearchClusterNodes);
        log.info("elasticsearchThreadPool-->{}",elasticsearchThreadPool);
        log.info("connectTimeOut-->{}",connectTimeOut);
        log.info("socketTimeOut-->{}",socketTimeOut);
        log.info("connectionRquestTimeOut-->{}",connectionRquestTimeOut);
        log.info("maxConnectNum-->{}",maxConnectNum);
        log.info("maxConnectPerRout-->{}",maxConnectPerRout);
        log.info("uniqueConnectTimeConfig-->{}",uniqueConnectTimeConfig);
        log.info("uniqueConnectNumConfig-->{}",uniqueConnectNumConfig);
        log.info("******************************************************************************");
    }

}
