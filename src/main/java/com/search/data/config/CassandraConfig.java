package com.search.data.config;

import java.net.InetSocketAddress;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PlainTextAuthProvider;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.Session;
import com.google.common.collect.Lists;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.SimpleUserTypeResolver;

@Configuration
public class CassandraConfig {


    private static final Logger logger = LoggerFactory.getLogger(CassandraConfig.class);

    @Value("${cassandra.host}")
    private String hostList;

    @Value("${cassandra.cluster.name}")
    private String clusterName;

    @Value("${cassandra.cluster.username}")
    private String userName;

    @Value("${cassandra.cluster.password}")
    private String password;

    @Value("${cassandra.keyspace.name}")
    private String keyspace;

    @Value("${cassandra.pool.max-queue-size}")
    private int maxPoolConnection;

    @Value("${cassandra.pool.pool-timeout}")
    private int poolTimeOut;

    private Cluster cluster;
    private Session session;
    private static ConsistencyLevel consistencyLevel;

    @Bean
    public Cluster cluster() throws Exception {
        try {
            PoolingOptions poolingOptions = new PoolingOptions();
            poolingOptions.setMaxConnectionsPerHost(HostDistance.LOCAL, this.maxPoolConnection);
            poolingOptions.setPoolTimeoutMillis(this.poolTimeOut);
            poolingOptions.setCoreConnectionsPerHost(HostDistance.LOCAL, this.maxPoolConnection);

            this.cluster = Cluster.builder()
                    .addContactPointsWithPorts(getHostIntetSocketAddressList(this.hostList))
                    .withClusterName(this.clusterName).withAuthProvider(new PlainTextAuthProvider(this.userName, this.password))
                    .withPoolingOptions(poolingOptions)
                    .build();

            consistencyLevel = getSession().getCluster().getMetadata().getAllHosts().size() > 1
                    ? ConsistencyLevel.ALL
                    : ConsistencyLevel.ONE;
        } catch (Exception ex) {
            throw ex;
        }

        return this.cluster;
    }

    private List<InetSocketAddress> getHostIntetSocketAddressList(String hostList) {
        List<InetSocketAddress> cassandraHosts = Lists.newArrayList();
        for (String host : hostList.split(",")) {
            InetSocketAddress socketAddress = new InetSocketAddress(host.split(":")[0],
                    Integer.valueOf(host.split(":")[1]));
            cassandraHosts.add(socketAddress);
        }
        return cassandraHosts;
    }

    public ConsistencyLevel getConsistencyLevel() {
        return consistencyLevel;
    }

    @Bean
    public CassandraMappingContext mappingContext() throws Exception {

        CassandraMappingContext mappingContext = new CassandraMappingContext();
        mappingContext.setUserTypeResolver(new SimpleUserTypeResolver(cluster(), this.keyspace));
        return mappingContext;
    }

    @Bean
    public CassandraConverter converter() throws Exception {
        return new MappingCassandraConverter(mappingContext());
    }

    @Bean
    public Session getSession() {
        if (this.cluster == null) {
            throw new RuntimeException("Cassandra Cluster in NULL");
        }
        if (this.session == null) {
            this.session = this.cluster.connect(this.keyspace);
        }
        return this.session;
    }

    @Bean
    public CassandraOperations cassandraTemplate() throws Exception {
        return new CassandraTemplate(getSession());
    }

    public void close() {
        if (this.session != null) {
            logger.debug("Closing Session....");
            try {
                this.session.close();
            } catch (Exception e) {
                logger.error("Error while closing the Session ...", e);
            }
        }
        if (this.cluster != null) {
            logger.debug("Closing Cluster....");
            try {
                this.cluster.close();
            } catch (Exception e) {
                logger.error("Error while closing the Cluster ...", e);
            }
        }
    }
}
