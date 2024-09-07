package com.gousade.desensit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    // 演示自定义脱敏
    @Desensitization(type = DesensitizationTypeEnum.MY_RULE, startInclude = 4, endExclude = 7)
    private String userid;

    @Desensitization(type = DesensitizationTypeEnum.MOBILE_PHONE)
    private String phone;

    @Desensitization(type = DesensitizationTypeEnum.EMAIL)
    private String email;

}