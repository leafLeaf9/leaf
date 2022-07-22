package com.gousade.constant;

public enum SystemJobMisfirePolicy {
    DEFAULT("0", "默认"),
    IGNORE_MISFIRES("1", "立即补执行所有"),
    FIRE_AND_PROCEED("2", "触发最近一次"),
    DO_NOTHING("3", "不触发执行"),
    ;

    private String id;
    private String name;

    SystemJobMisfirePolicy(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static SystemJobMisfirePolicy get(String id) {
        for (SystemJobMisfirePolicy jobMisfireStrategy : values()) {
            if (jobMisfireStrategy.getId().equals(id)) {
                return jobMisfireStrategy;
            }
        }
        return null;
    }

    public static String getName(String id) {
        SystemJobMisfirePolicy misfirePolicy = get(id);
        if (misfirePolicy == null) {
            return null;
        }
        return misfirePolicy.getName();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
