package com.min.service;


import com.min.entity.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author jxm
 * aims 相关联的接口
 */
public interface AimsServiceWithNoExchange {

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
     * 根据itemId + 仓库id，获取item
     * @param repositoryId
     * @param itemId
     * @return
     */
    AIMSItem getItemById(String repositoryId, String itemId);

    /**
     * 获取item的所有后代，包括自身
     * @param repositoryId
     * @param itemId
     * @param category: 可选  根据分类可以筛选出后代中指定分类的item
     * @return
     */
    AIMSItem getDescendantsOfItem(String repositoryId, String itemId, String category);

    /**
     * 获取item的所有祖先，包括自身
     * @param repositoryId
     * @param itemId
     * @return
     */
    List<AIMSItem> getAncestorsOfItem(String repositoryId, String itemId);

    /**
     * 根据itemId获取所有属性
     * @param repositoryId
     * @param itemId
     * @return
     */
    List<AIMSProperty> getPropertiesByItemId(String repositoryId, String itemId);

    /**==============================doc,item,handle=============================*/
    /**
     * 根据itemId获取 item关联的文档
     * @param itemId：元件的item
     * @return
     */
    List<AIMSHandle> getDocsByItemId(String itemId);

    /**
     * 根据docId获取 List<AIMSHandle>
     * @param docId
     * @return
     */
    List<AIMSHandle> getHandlesByDocId(String docId);

    /**
     * 根据docId + itemId获取 List<AIMSHandle>
     * @param docId
     * @param itemId
     * @return
     */
    List<AIMSHandle> getHandlesByRepoIdAndItemId(String docId, String itemId);

    /**
     *根据docId + handle值获取 List<AIMSHandle>
     * @param docId
     * @param handle
     * @return
     */
    List<AIMSHandle> getHandlesByDocIdAndHandle(String docId, String handle);



}
