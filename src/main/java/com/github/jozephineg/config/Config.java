package com.github.jozephineg.config;

import com.github.jozephineg.controller.resource.CandidateResource;
import com.github.jozephineg.controller.resource.VotingResource;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.github.jozephineg.model.repository")
@ComponentScan(basePackages = { "com.github.jozephineg.controller.service" })
public class Config extends ResourceConfig {

    public Config() {
        register(CandidateResource.class);
        register(VotingResource.class);
    }

    @Bean
    public Client client() throws UnknownHostException {
        Settings elasticsearchSettings = Settings.builder()
                .put("client.transport.sniff", false)
                .put("cluster.name", "docker-cluster").build();
        TransportClient client = new PreBuiltTransportClient(elasticsearchSettings);
        client.addTransportAddress(new TransportAddress(new InetSocketAddress(InetAddress.getByName("localhost"), 9300)));
        return client;
    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() {
        try {
            return new ElasticsearchTemplate(client());
        } catch (UnknownHostException e) {
            System.exit(1);
        }
        return null;
    }
}