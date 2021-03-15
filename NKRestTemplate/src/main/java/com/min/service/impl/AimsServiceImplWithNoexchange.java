package com.min.service.impl;

import com.alibaba.fastjson.JSON;
import com.min.entity.*;
import com.min.service.AimsServiceWithNoExchange;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author jxm
 */
@Service
public class AimsServiceImplWithNoexchange implements AimsServiceWithNoExchange {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${aims.url}")
    private String aimsUrl;

    /**
     *  todo 数据缺失
     * @return
     */
    @Override
    public List<AIMSRepository> getAllRepositories() {
        String url = aimsUrl + "/repositories";

        String jsonStr = restTemplate.getForObject(url, String.class);
        List<AIMSRepository> aimsRepositories = JSON.parseArray(jsonStr, AIMSRepository.class);
        return aimsRepositories;
    }

    @Override
    public List<AIMSLabel> getLabelsByRepositoryId(String repositoryId) {
        String url = aimsUrl + "/repositories/{repositoryId}/labels";
        String jsonStr = restTemplate.getForObject(url, String.class, repositoryId);
        List<AIMSLabel> aimsLabels = JSON.parseArray(jsonStr, AIMSLabel.class);
        return aimsLabels;
    }

    /**
     *  todo  无法获取到顺序表：也就是无法获取图名，图号
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
     *  todo BUG 需要沟通
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
     * @param repositoryId
     * @param itemId
     * @param properties
     */
    @Override
    public void batchUpdateProperties(String repositoryId, String itemId, List<AIMSProperty> properties) {
        String url = aimsUrl + "repositories/" + repositoryId + "/items/batchSetProperties";
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("itemId", itemId);
        map.add("properties", properties);
        List<Object> list = new ArrayList<>();
        list.add(map);

        HttpEntity<String> httpEntity = new HttpEntity<>(JSON.toJSONString(list), headers);
        restTemplate.put(url, httpEntity);
    }

    @Override
    public AIMSItem getItemById(String repositoryId, String itemId) {
        String url = aimsUrl + "/repositories/" + repositoryId + "/items/" + itemId;
        AIMSItem item = restTemplate.getForObject(url, AIMSItem.class);
        return item;
    }

    @Override
    public AIMSItem getDescendantsOfItem(String repositoryId, String itemId, String category) {
        String url = aimsUrl + "/repositories/" + repositoryId + "/items/" + itemId + "/posterities";
        if(StringUtils.isNoneEmpty(category)){
            url += "?category=" + category;
        }
        AIMSItem item = restTemplate.getForObject(url, AIMSItem.class);
        return item;
    }

    @Override
    public List<AIMSItem> getAncestorsOfItem(String repositoryId, String itemId) {
        String url = aimsUrl + "/repositories/{repositoryId}/items/{itemId}/ancestors";
        Map<String, String> map = new HashMap<>();
        map.put("repositoryId", repositoryId);
        map.put("itemId", itemId);
        AIMSItem[] items = restTemplate.getForObject(url, AIMSItem[].class, map);
        return Arrays.asList(items);
    }

    @Override
    public List<AIMSProperty> getPropertiesByItemId(String repositoryId, String itemId) {
        String url = aimsUrl + "/repositories/{repositoryId}/items/{itemId}/properties";
        Map<String, String> map = new HashMap<>();
        map.put("repositoryId", repositoryId);
        map.put("itemId", itemId);
        String jsonStr = restTemplate.getForObject(url, String.class, map);
        List<AIMSProperty> aimsProperties = JSON.parseArray(jsonStr, AIMSProperty.class);
        return aimsProperties;
    }

    /**
     * @param itemId：元件的item
     * @return
     */
    @Override
    public List<AIMSHandle> getDocsByItemId(String itemId) {
        String url = aimsUrl + "/references?itemId=" + itemId;
        AIMSHandle[] handleArrays = restTemplate.getForObject(url, AIMSHandle[].class);
        List<AIMSHandle> aimsHandles = Arrays.asList(handleArrays);
        return aimsHandles;
    }

    @Override
    public List<AIMSHandle> getHandlesByDocId(String docId) {
        String url = aimsUrl + "/references?documentId=" + docId;
        AIMSHandle[] handleArrays = restTemplate.getForObject(url, AIMSHandle[].class);
        List<AIMSHandle> aimsHandles = Arrays.asList(handleArrays);
        return aimsHandles;
    }

    @Override
    public List<AIMSHandle> getHandlesByRepoIdAndItemId(String docId, String itemId) {
        String url = aimsUrl + "/references?documentId=" + docId + "&itemId=" + itemId;
        AIMSHandle[] handleArrays = restTemplate.getForObject(url, AIMSHandle[].class);
        List<AIMSHandle> aimsHandles = Arrays.asList(handleArrays);
        return aimsHandles;
    }

    /**
     * todo :api “；handle" 报错 404
     * @param docId
     * @param handle
     * @return
     */

    @Override
    public List<AIMSHandle> getHandlesByDocIdAndHandle(String docId, String handle) {
        String url = aimsUrl + "/references?documentId=" + docId + "&handle=" + handle;
        AIMSHandle[] handleArrays = restTemplate.getForObject(url, AIMSHandle[].class);
        List<AIMSHandle> aimsHandles = Arrays.asList(handleArrays);
        return aimsHandles;
    }
}
