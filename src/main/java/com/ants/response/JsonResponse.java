package com.ants.response;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
  * @description 返回结果集
  * @author ants·ht
  * @date 2018/5/24 13:45
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResponse<T> {

    private Integer code;

    private String desc;

    private T result;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
