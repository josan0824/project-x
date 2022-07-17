package com.yc.core.utils;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.*;

public class DataModelExcelListener extends AnalysisEventListener<DataModel> {

    List<DataModel> writeData = new ArrayList<DataModel>();
    //存储分组的数据
    Map<Integer,List<DataModel>> handleData = new LinkedHashMap();

    @Override
    public void invoke(DataModel dataModel, AnalysisContext analysisContext) {
        System.out.println("invoke index:" + dataModel.getIndex() + ",value: " + dataModel.getValue() + ",value:" + dataModel.getV2());
        int key = ((dataModel.getIndex() - 1) / 5) + 1;
        int mod = dataModel.getIndex() % 5;
        if (mod == 1) {
            //第一个元素，创建List
            List<DataModel> newList = new ArrayList<>();
            newList.add(dataModel);
            handleData.put(key, newList);
        } else {
            //不是第一个元素，取出list，添加到最后
            List<DataModel> oldList = handleData.get(key);
            oldList.add(dataModel);
            handleData.put(key, oldList);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("doAfterAllAnalysed");
        handleData();
        write();
    }
    public DataModelExcelListener() {

    }

    /**
     * 处理数据，算出平均值
     */
    public void handleData() {
        Set<Map.Entry<Integer, List<DataModel>>> entries = handleData.entrySet();
        entries.forEach(entry -> {
            List<DataModel> dataModels = entry.getValue();
            int size = dataModels.size();
            //最后一个有效值，默认第一个
            int lastIndex = -1;
            //有效值个数
            int count = 0;
            double sum = 0;
            for (int i = 0; i < size; i ++) {
                DataModel dataModel = dataModels.get(i);
                if (isEffect(dataModel.getValue())) {
                    count++;
                    lastIndex = i;
                    sum += Double.parseDouble(dataModel.getValue());
                }
            }
            if (lastIndex >= 0) {
                //处理平均数,一组中没有有效数字，则不显示
                double avg = sum / count;
                dataModels.get(lastIndex).setAvg(String.valueOf(avg));
            }
            writeData.addAll(dataModels);
        });
    }

    /**
     * 判断一个数是否有效
     * @param value
     * @return
     */
    private boolean isEffect(String value) {
        try {
            double d = Double.parseDouble(value);
            return d >= 0;
        } catch (Exception e) {

        }
        return false;
    }

    public void write(){
        String fileName = "C:\\Users\\22872\\Desktop\\data2.xlsx";
        EasyExcel.write(fileName, DataModel.class)
                .sheet("MODULUS_timed_16_05_22")
                .doWrite(writeData);
    }

}
