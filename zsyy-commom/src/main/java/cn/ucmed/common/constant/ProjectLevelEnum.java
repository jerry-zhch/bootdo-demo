package cn.ucmed.common.constant;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 项目级别
 */
public enum ProjectLevelEnum implements IEnum<String>{

    LEVEL_01("01","A级"),
    LEVEL_02("02","S级"),
    LEVEL_03("03","SS级"),
    LEVEL_04("04","SSS级");

    ProjectLevelEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private String value;

    private String desc;

    @Override
    public String getValue() {
        return this.value;
    }

    public static String getDesc(String value) {
        for (ProjectLevelEnum enums : ProjectLevelEnum.values()){
            if (enums.getValue().equals(value)){
                return enums.getDesc();
            }
        }
        return "";
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "ProjectLevelEnum{" +
                "value='" + value + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
