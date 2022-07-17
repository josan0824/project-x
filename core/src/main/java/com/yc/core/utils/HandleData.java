package com.yc.core.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.metadata.Sheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给斌哥处理数据
 */
public class HandleData {
    public static void main(String[] args) {
        //读入
        read();
    }
    public static void read(){
        String fileName = "C:\\Users\\22872\\Desktop\\data0.xlsx";
        EasyExcel.read(fileName, DataModel.class, new DataModelExcelListener())
                .sheet("MODULUS_timed_16_05_22")
                .doRead();
    }







}
