package net.aimeizi.client.elasticsearch;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.node.Node;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/**
 * Created by Administrator on 2015/9/9 0009.
 */
public class NodeClient {

    private static Client getNodeClient(){
        Node node = nodeBuilder().clusterName("elasticsearch")
                .settings(ImmutableSettings.settingsBuilder().put("http.enabled", false))
                .node();
        Client client = node.client();
        return client;
    }
}
