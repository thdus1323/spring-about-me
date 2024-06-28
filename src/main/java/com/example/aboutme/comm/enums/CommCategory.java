package com.example.aboutme.comm.enums;

public enum CommCategory {
    GENERAL_CONCERNS("일반고민"),
    CAREER_JOBS("취업/진로"),
    WORK_LIFE("직장"),
    RELATIONSHIPS("연애"),
    SOCIAL_INTERACTIONS("대인관계"),
    ADDICTION_OBSESSION("중독/집착"),
    FINANCE_BUSINESS("금전/사업");

    private final String korean;

    CommCategory(String korean) {
        this.korean = korean;
    }

    // 한글로 들어온 이름으로 enum 값 찾기
    public static CommCategory fromKorean(String korean) {
        for (CommCategory category : values()) {
            if (category.getKorean().equals(korean)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Unknown category: " + korean);
    }

    public String getKorean() {
        return korean;
    }
}
