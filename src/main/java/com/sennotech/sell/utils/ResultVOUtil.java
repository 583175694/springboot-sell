package com.sennotech.sell.utils;
/*
 *   @author 吴少航
 *   @date 2019/10/9-16:34
 */

import com.sennotech.sell.VO.ResultVO;

public class ResultVOUtil {

    public static ResultVO success(Object object) {

        ResultVO resultVO = new ResultVO();

        //  最后的最后把这个类目列表添加到data里面，over
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("success");

        return  resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();

        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
