package com.min.service;


import com.min.entity.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author jxm
 * aims 相关联的接口
 */
public interface AimsService {


    /**==============================仓库相关===============================*/

    /**
     * 获取所有的仓库
     * @return
     */
    List<AIMSRepository> getAllRepositories();

    /**
     * 根据仓库id获取仓库下的所有标签
     * @param repositoryId
     * @return
     */
    List<AIMSLabel> getLabelsByRepositoryId(String repositoryId);

    /**==============================文档相关==============================*/

    /**
     * 根据仓库id,获取仓库下的所有文档
     * @param repositoryId
     * @return
     */
    List<AIMSDocument> getDocsByRepositoryId(String repositoryId);

    /**
     * 根据仓库id 和文档id  获取仓库下的特定文档
     * @param repositoryId
     * @param docIds  documents的id集合
     * @return
     */
    List<AIMSDocument> getDocsByRepositoryIdAndDocIds(String repositoryId, List<String> docIds);

    /**
     * 根据仓库id + 文档id 下载文档
     * @param repositoryId
     * @param documentId
     * @return
     */
    ResponseEntity<byte[]> downloadDocument(String repositoryId, String documentId);

    /**==============================item相关==============================*/

    /**
     * 批量更新属性
     * @param repositoryId
     * @param itemId
     * @param properties
     */
    void batchUpdateProperties(String repositoryId, String itemId, List<AIMSProperty> properties);




    /**
     * 获取item的所有祖先，包括自身
     * @param repositoryId
     * @param itemId
     * @return
     */
    List<AIMSItem> getAncestorsOfItem(String repositoryId, String itemId);



}
