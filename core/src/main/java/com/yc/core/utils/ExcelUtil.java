package com.yc.core.utils;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * @description:
 * @author: fengqx
 * @date: 19-3-18 16:16
 */

public class ExcelUtil {
    private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);
    private static Sheet initSheet;

    static {
        initSheet = new Sheet(1, 0);
        initSheet.setSheetName("sheet");
        //设置自适应宽度
        initSheet.setAutoWidth(Boolean.TRUE);
    }

    public static void main(String[] args) {
        List<Object> result = readLessThan1000Row("/Users/josan.tang/Library/CloudStorage/OneDrive-JLL/Desktop/workspace/yc-demo/core/src/main/resources/结果.xls");
        System.out.println("result = " + JSONArray.toJSONString(result));
    }

    /**
     * 读取少于1000行数据
     *
     * @param filePath 文件绝对路径
     * @return
     */
    public static List<Object> readLessThan1000Row(String filePath) {
        return readLessThan1000RowBySheet(filePath, null);
    }

    /**
     * 读小于1000行数据, 带样式
     * filePath 文件绝对路径
     * initSheet ：
     * sheetNo: sheet页码，默认为1
     * headLineMun: 从第几行开始读取数据，默认为0, 表示从第一行开始读取
     * clazz: 返回数据List<Object> 中Object的类名
     */
    public static List<Object> readLessThan1000RowBySheet(String filePath, Sheet sheet) {
        if (!StringUtils.hasText(filePath)) {
            return null;
        }

        sheet = sheet != null ? sheet : initSheet;

        InputStream fileStream = null;
        try {
            fileStream = new FileInputStream(filePath);
            return EasyExcelFactory.read(fileStream, sheet);
        } catch (FileNotFoundException e) {
            log.info("找不到文件或文件路径错误, 文件：{}", filePath);
        } finally {
            try {
                if (fileStream != null) {
                    fileStream.close();
                }
            } catch (IOException e) {
                log.info("excel文件读取失败, 失败原因：{}", e);
            }
        }
        return null;
    }

    /**
     * 读大于1000行数据
     *
     * @param filePath 文件觉得路径
     * @return
     */
    public static List<Object> readMoreThan1000Row(String filePath) {
        return readMoreThan1000RowBySheet(filePath, null);
    }

    /**
     * 读大于1000行数据, 带样式
     *
     * @param filePath 文件觉得路径
     * @return
     */
    public static List<Object> readMoreThan1000RowBySheet(String filePath, Sheet sheet) {
        if (!StringUtils.hasText(filePath)) {
            return null;
        }

        sheet = sheet != null ? sheet : initSheet;

        InputStream fileStream = null;
        try {
            fileStream = new FileInputStream(filePath);
            ExcelListener excelListener = new ExcelListener();
            EasyExcelFactory.readBySax(fileStream, sheet, excelListener);
            return excelListener.getDatas();
        } catch (FileNotFoundException e) {
            log.error("找不到文件或文件路径错误, 文件：{}", filePath);
        } finally {
            try {
                if (fileStream != null) {
                    fileStream.close();
                }
            } catch (IOException e) {
                log.error("excel文件读取失败, 失败原因：{}", e);
            }
        }
        return null;
    }

    /**
     * 生成excle
     *
     * @param filePath 绝对路径, 如：/home/chenmingjian/Downloads/aaa.xlsx
     * @param data     数据源
     * @param head     表头
     */
    public static void writeBySimple(String filePath, List<List<Object>> data, List<String> head) {
        writeSimpleBySheet(filePath, data, head, null);
    }

    /**
     * 生成excle
     *
     * @param filePath 绝对路径, 如：/home/chenmingjian/Downloads/aaa.xlsx
     * @param data     数据源
     * @param sheet    excle页面样式
     * @param head     表头
     */
    public static void writeSimpleBySheet(String filePath, List<List<Object>> data, List<String> head, Sheet sheet) {
        sheet = (sheet != null) ? sheet : initSheet;

        if (head != null) {
            List<List<String>> list = new ArrayList<>();
            head.forEach(h -> list.add(Collections.singletonList(h)));
            sheet.setHead(list);
        }

        OutputStream outputStream = null;
        ExcelWriter writer = null;
        try {
            outputStream = new FileOutputStream(filePath);
            writer = EasyExcelFactory.getWriter(outputStream);
            writer.write1(data, sheet);
        } catch (FileNotFoundException e) {
            log.error("找不到文件或文件路径错误, 文件：{}", filePath);
        } finally {
            try {
                if (writer != null) {
                    writer.finish();
                }

                if (outputStream != null) {
                    outputStream.close();
                }

            } catch (IOException e) {
                log.error("excel文件导出失败, 失败原因：{}", e);
            }
        }

    }

    /**
     * 生成excle
     *
     * @param filePath 绝对路径, 如：/home/chenmingjian/Downloads/aaa.xlsx
     * @param data     数据源
     */
    public static void writeWithTemplate(String filePath, List<? extends BaseRowModel> data) {
        writeWithTemplateAndSheet(filePath, data, null);
    }

    /**
     * 生成excle
     *
     * @param filePath 绝对路径, 如：/home/chenmingjian/Downloads/aaa.xlsx
     * @param data     数据源
     * @param sheet    excle页面样式
     */
    public static void writeWithTemplateAndSheet(String filePath, List<? extends BaseRowModel> data, Sheet sheet) {
        if (CollectionUtils.isEmpty(data)) {
            return;
        }

        sheet = (sheet != null) ? sheet : initSheet;
        sheet.setClazz(data.get(0).getClass());

        OutputStream outputStream = null;
        ExcelWriter writer = null;
        try {
            outputStream = new FileOutputStream(filePath);
            writer = EasyExcelFactory.getWriter(outputStream);
            writer.write(data, sheet);
        } catch (FileNotFoundException e) {
            log.error("找不到文件或文件路径错误, 文件：{}", filePath);
        } finally {
            try {
                if (writer != null) {
                    writer.finish();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                log.error("excel文件导出失败, 失败原因：{}", e);
            }
        }

    }

    /**
     * 生成多Sheet的excle
     *
     * @param filePath              绝对路径, 如：/home/chenmingjian/Downloads/aaa.xlsx
     * @param multipleSheelPropetys
     */
    public static void writeWithMultipleSheel(String filePath, List<MultipleSheelPropety> multipleSheelPropetys) {
        if (CollectionUtils.isEmpty(multipleSheelPropetys)) {
            return;
        }

        OutputStream outputStream = null;
        ExcelWriter writer = null;
        try {
            outputStream = new FileOutputStream(filePath);
            writer = EasyExcelFactory.getWriter(outputStream);
            for (MultipleSheelPropety multipleSheelPropety : multipleSheelPropetys) {
                Sheet sheet = multipleSheelPropety.getSheet() != null ? multipleSheelPropety.getSheet() : initSheet;
                if (!CollectionUtils.isEmpty(multipleSheelPropety.getData())) {
                    sheet.setClazz(multipleSheelPropety.getData().get(0).getClass());
                }
                writer.write(multipleSheelPropety.getData(), sheet);
            }

        } catch (FileNotFoundException e) {
            log.error("找不到文件或文件路径错误, 文件：{}", filePath);
        } finally {
            try {
                if (writer != null) {
                    writer.finish();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                log.error("excel文件导出失败, 失败原因：{}", e);
            }
        }

    }

    /**
     * 导出 Excel ：一个 sheet，带表头. 直接下载excel
     *
     * @param response  HttpServletResponse
     * @param list      数据 list，每个元素为一个 BaseRowModel
     * @param fileName  导出的文件名
     * @param sheetName 导入文件的 sheet 名
     * @param model     映射实体类，Excel 模型
     * @throws Exception 异常
     */
    public static void writeExcel(
            HttpServletResponse response, List<? extends BaseRowModel> list,
            String fileName, String sheetName, BaseRowModel model) throws Exception {
        ExcelWriter writer =
                new ExcelWriter(getOutputStream(fileName, response), ExcelTypeEnum.XLSX);
        Sheet sheet = new Sheet(1, 0, model.getClass());
        sheet.setSheetName(sheetName);
        writer.write(list, sheet);
        writer.finish();
    }

    /**
     * 导出文件时为Writer生成OutputStream.
     *
     * @param fileName 文件名
     * @param response response
     * @return ""
     */
    private static OutputStream getOutputStream(String fileName,
                                                HttpServletResponse response) throws Exception {
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf8");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "no-store");
            response.addHeader("Cache-Control", "max-age=0");
            return response.getOutputStream();
        } catch (IOException e) {
            throw new Exception("导出excel表格失败!", e);
        }
    }


    /**
     * JSON数据 保存到excel中 生成到指定路径文件下
     **/
    public static void jsonToExcel(String filePath, JSONArray jsonArray) {
        if (jsonArray.size() < 1) {
            return;
        }
        List<List<Object>> data = new ArrayList<>();
        JSONObject first = jsonArray.getJSONObject(0);
        List<String> head = new LinkedList<String>();
        for (Map.Entry<String, Object> entry : first.entrySet()) {
            head.add(entry.getKey());
        }

        for (int i = 0; i < jsonArray.size(); i++) {
            List<Object> oos = new LinkedList<Object>();
            for (Map.Entry<String, Object> entry : jsonArray.getJSONObject(i).entrySet()) {
                oos.add(entry.getValue());
            }
            data.add(oos);
        }

        writeBySimple(filePath, data, head);
    }

    /***
     * @description: jsonArray数据 导出下载方法
     * @param: [response, fileName, sheetName, jsonArray]
     * @return: void
     * @author Fengqx
     * @date: 2021/8/16 13:49
     */
    public static void JsonWriteExcel(
            HttpServletResponse response,
            String fileName,
            String sheetName,
            JSONArray jsonArray) throws Exception {
        if (jsonArray.size() < 1) {
            return;
        }
        ExcelWriter writer =
                new ExcelWriter(getOutputStream(fileName, response), ExcelTypeEnum.XLSX);
        Sheet sheet = initSheet;
        //解析jsonArray 的数据对象data
        List<List<Object>> data = new ArrayList<>();
        JSONObject first = jsonArray.getJSONObject(0);
        //解析jsonArray 的head对象
        List<String> head = new LinkedList<String>();
        for (Map.Entry<String, Object> entry : first.entrySet()) {
            head.add(entry.getKey());
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            List<Object> oos = new LinkedList<Object>();
            for (Map.Entry<String, Object> entry : jsonArray.getJSONObject(i).entrySet()) {
                oos.add(entry.getValue());
            }
            data.add(oos);
        }
        if (head != null) {
            List<List<String>> list = new ArrayList<>();
            head.forEach(h -> list.add(Collections.singletonList(h)));
            sheet.setHead(list);
        }
        sheet.setSheetName(sheetName);
        writer.write1(data, sheet);
        writer.finish();
    }


    /*********************匿名内部类开始，可以提取出去******************************/
    public static class MultipleSheelPropety {

        private List<? extends BaseRowModel> data;

        private Sheet sheet;

        public List<? extends BaseRowModel> getData() {
            return data;
        }

        public void setData(List<? extends BaseRowModel> data) {
            this.data = data;
        }

        public Sheet getSheet() {
            return sheet;
        }

        public void setSheet(Sheet sheet) {
            this.sheet = sheet;
        }
    }

    /**
     * 解析监听器，
     * 每解析一行会回调invoke()方法。
     * 整个excel解析结束会执行doAfterAllAnalysed()方法
     *
     * @author: chenmingjian
     * @date: 19-4-3 14:11
     */
    public static class ExcelListener extends AnalysisEventListener {
        public List<Object> getDatas() {
            return datas;
        }

        public void setDatas(List<Object> datas) {
            this.datas = datas;
        }

        private List<Object> datas = new ArrayList<>();

        /**
         * 逐行解析
         * object : 当前行的数据
         */
        @Override
        public void invoke(Object object, AnalysisContext context) {
            //当前行
            // context.getCurrentRowNum()
            if (object != null) {
                datas.add(object);
            }
        }


        /**
         * 解析完所有数据后会调用该方法
         */
        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            //解析结束销毁不用的资源
        }

    }

    /************************匿名内部类结束，可以提取出去***************************/

}
