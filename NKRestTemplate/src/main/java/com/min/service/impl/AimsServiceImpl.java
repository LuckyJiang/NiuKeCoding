package com.min.service.impl;

import com.alibaba.fastjson.JSON;
import com.min.entity.*;
import com.min.service.AimsService;
import com.min.util.PidRequestHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import java.util.*;


/**
 * @author jxm
 */
@Service
public class AimsServiceImpl  implements AimsService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${aims.url}")
    private String aimsUrl;

    @Override
    public List<AIMSRepository> getAllRepositories() {
        String url = aimsUrl + "/repositories";
        String jsonStr = restTemplate.getForObject(url, String.class);
        List<AIMSRepository> aimsRepositories = JSON.parseArray(jsonStr, AIMSRepository.class);
        return aimsRepositories;
    }

    /**
     * url中有一个参数
     * @param repositoryId
     * @return
     */
    @Override
    public List<AIMSLabel> getLabelsByRepositoryId(String repositoryId) {
        String url = aimsUrl + "/repositories/{repositoryId}/labels";
        String jsonStr = restTemplate.getForObject(url, String.class, repositoryId);
        List<AIMSLabel> aimsLabels = JSON.parseArray(jsonStr, AIMSLabel.class);
        return aimsLabels;
    }

    /**
     * rest 返回List:  方法1
     * @param repositoryId
     * @return
     */
    @Override
    public List<AIMSDocument> getDocsByRepositoryId(String repositoryId) {
        String url = aimsUrl + "/repositories/{repositoryId}/documents";
        String jsonStr = restTemplate.getForObject(url, String.class, repositoryId);
        List<AIMSDocument> aimsDocuments = JSON.parseArray(jsonStr, AIMSDocument.class);
        return aimsDocuments;
    }

    /**
     * rest 返回List:   方法2
     * @param repositoryId
     * @param itemId
     * @return
     */
    @Override
    public List<AIMSItem> getAncestorsOfItem(String repositoryId, String itemId) {
        String url = aimsUrl + "/repositories/"+ repositoryId +"/items/" + itemId + "/ancestors";
        Map<String, String> map = new HashMap<>();
        map.put("repositoryId", repositoryId);
        map.put("itemId", itemId);
        HttpEntity<String> httpEntity = createHttpEntity(null);
        ResponseEntity<AIMSItem[]> exchange = restTemplate.exchange(url, HttpMethod.GET, httpEntity, AIMSItem[].class, map);
        return Arrays.asList(exchange.getBody());
    }


    /**
     * 参数在body中
     * @param repositoryId
     * @param docIds  documents的id集合
     * @return
     */
    @Override
    public List<AIMSDocument> getDocsByRepositoryIdAndDocIds(String repositoryId, List<String> docIds) {
        String url = aimsUrl + "/repositories/" + repositoryId +"/documents";
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        //将list转换为Json
        String listStr = JSON.toJSONString(docIds);
        HttpEntity<String> httpEntity = new HttpEntity<>(listStr, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        List<AIMSDocument> aimsDocuments = JSON.parseArray(response.getBody(), AIMSDocument.class);
        return aimsDocuments;
    }

    /**
     * 1) url中有两个参数：  2）返回的结果为：ResponseEntity<byte[]>
     * @param repositoryId
     * @param documentId
     * @return
     */
    @Override
    public ResponseEntity<byte[]> downloadDocument(String repositoryId, String documentId) {
        String url = aimsUrl + "/repositories/{repositoryId}/documents/{documentId}";
        ResponseEntity<byte[]> entity = restTemplate.getForEntity(url, byte[].class, repositoryId, documentId);
        return entity;
    }

    /**
     * -------------todo copy
     * @param repositoryId
     * @param itemId
     * @param properties
     */
    @Override
    public void batchUpdateProperties(String repositoryId, String itemId, List<AIMSProperty> properties) {
        String url = aimsUrl + "/repositories/" + repositoryId + "/items/batchSetProperties";

        Map<String, Object> map = new HashMap<>();
        map.put("itemId", itemId);
        map.put("properties", properties);
        List<Object> list = new ArrayList<>();
        list.add(map);

        HttpEntity<String> httpEntity = createHttpEntity(JSON.toJSONString(list));
        restTemplate.put(url, httpEntity);
    }
    private HttpEntity<String> createHttpEntity(String body) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.setContentType(type);
        headers.add("Cookie",PidRequestHolder.getCurrentCookie());

        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
        return requestEntity;
    }

    /**
     * json转List
     */

   /* public List<Role> getAllRoles() {
        final String roles = restTemplate.getForObject(uamsApiUrl + "/API/roles", String.class);
        try {
            return new ObjectMapper().readValue(roles, new TypeReference<List<Role>>() {
            });
        } catch (final IOException e) {
            e.printStackTrace();
            return null;
        }
    }*/



}
