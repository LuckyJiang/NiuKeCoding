package com.min.entitys;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jxm
 * 1.通过yml配合文件初始化对象A
 *  如果A中有B类的对象，那么B应该是static的，否则A中的b属性无法被初始化
 */
@Component
@ConfigurationProperties(prefix="aims.model")
@Getter
@Setter
public class ModelProperties {

    private String tagNumPropertyName;

    private String branchPointCategory;

    private BlindProperty blindProperty;

    @Getter
    @Setter
    public static class BlindProperty{
        private String blindCategory;

        @Value("#{'${aims.model.blindProperty.categoriesOfAddBlind}'.split(',')}")
        private List<String> categoriesOfAddBlind;

        private String pipeName;

        private String pipeCategory;

        private String mediaName;

        private String nominalDiameter;

        private String pressure;

        private String thickness;
    }
}


