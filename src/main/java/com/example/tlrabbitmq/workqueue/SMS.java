package com.example.tlrabbitmq.workqueue;

import lombok.Data;

/**
 * @Description: TODO
 * @author: wangjing
 * @date: 2021年04月10日 14:54
 */
@Data
public class SMS {
    private String name;
    private String mobile;
    private String content;

    public SMS(String s, String s1, String s2) {
    }
}
