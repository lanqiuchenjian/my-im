package com.myim.cluster.controller;

import lombok.Data;

import java.util.List;

@Data
class One {
    private String dtmf;
    private String system;
    private String fixed;
    private List<String> api;
}
