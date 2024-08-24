package com.yc.core.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.yc.core.annotation.Excel;
import com.yc.core.annotation.Excel.Type;
import com.yc.core.annotation.Excels;
import com.yc.core.config.AiConfig;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Excel相关处理
 *
 * @author ai-srd-bd2
 */
@Component(value = "public_excel_util")
@NoArgsConstructor
public class ExcelUtils<T> {

    public ExcelUtils(Class<T> clazz) {
        this.clazz = clazz;
    }
    private static final Logger log = LoggerFactory.getLogger(ExcelUtils.class);

    /**
     * excel 单个sheet支持的最大行数
     */
    private static int END_ROW_NUM = 1048575;

    /**
     * Excel sheet最大行数，默认65536
     */
    public static final int SHEET_SIZE = 65536;

    /**
     * 工作表名称
     */
    private String sheetName;

    /**
     * 导出类型（EXPORT:导出数据；IMPORT：导入模板）
     */
    private Type type;

    /**
     * 工作薄对象
     */
    private Workbook wb;

    /**
     * 工作表对象
     */
    private Sheet sheet;

    /**
     * 样式列表
     */
    private Map<String, CellStyle> styles;

    /**
     * 导入导出数据列表
     */
    private List<T> list;

    /**
     * 注解列表
     */
    private List<Object[]> fields;

    /**
     * 统计列表
     */
    private final Map<Integer, Double> statistics = new HashMap<Integer, Double>();

    /**
     * 数字格式
     */
    private static final DecimalFormat DOUBLE_FORMAT = new DecimalFormat("######0.00");

    /**
     * 实体对象
     */
    public Class<T> clazz;

    public void init(List<T> list, String sheetName, Type type) {
        if (list == null) {
            list = new ArrayList<T>();
        }
        this.list = list;
        this.sheetName = sheetName;
        this.type = type;
        createExcelField();
        createWorkbook();
    }

    public void initMulSheet(List<T> list, String sheetName, Type type) {
        if (list == null) {
            list = new ArrayList<T>();
        }
        this.list = list;
        this.sheetName = sheetName;
        this.type = type;
        createExcelField();
    }

    /**
     * 对excel表单默认第一个索引名转换成list
     *
     * @param is 输入流
     * @return 转换后集合
     */
    public List<T> importExcel(InputStream is) throws Exception {
        return importExcel(StringUtils.EMPTY, is, false, null);
    }

    /**
     * 对excel表单默认第一个索引名转换成list
     *
     * @param is 输入流
     * @param checkTemplate 是否校验模板
     * @param templateHeadList 模版表头
     * @return 转换后集合
     */
    public List<T> importExcel(InputStream is, boolean checkTemplate, List<String> templateHeadList) throws Exception {
        return importExcel(StringUtils.EMPTY, is, checkTemplate, templateHeadList);
    }


    /**
     * 检验模版是否正确，需要满足模版和导入表头完全一致，顺序不做要求
     * @param templateHeadList 模版表头
     * @param excelHeadList 导入表头
     */
    private void checkTemplate(List<String> templateHeadList, List<String> excelHeadList) {
        if (templateHeadList == null || excelHeadList == null) {
            throw new RuntimeException("导入的文件有误，请确认后再导入");
        }
        if (templateHeadList.size() != excelHeadList.size()) {
            throw new RuntimeException("导入的文件有误，请确认后再导入");
        }
        if (!templateHeadList.containsAll(excelHeadList) || !excelHeadList.containsAll(templateHeadList)) {
            throw new RuntimeException("导入的文件有误，请确认后再导入");
        }
    }

    /**
     * 对excel表单指定表格索引名转换成list
     *
     * @param sheetName 表格索引名
     * @param is        输入流
     * @param checkTemplate 是否校验模板
     * @param templateHeadList 模版表头
     * @return 转换后集合
     */
    public List<T> importExcel(String sheetName, InputStream is, boolean checkTemplate, List<String> templateHeadList) throws Exception {
        this.type = Type.IMPORT;
        this.wb = WorkbookFactory.create(is);
        List<T> list = new ArrayList<T>();
        Sheet sheet = null;
        if (StringUtils.isNotEmpty(sheetName)) {
            // 如果指定sheet名,则取指定sheet中的内容.
            sheet = wb.getSheet(sheetName);
        } else {
            // 如果传入的sheet名不存在则默认指向第1个sheet.
            sheet = wb.getSheetAt(0);
        }

        if (sheet == null) {
            throw new IOException("文件sheet不存在");
        }

        int rows = sheet.getPhysicalNumberOfRows();

        if (rows > 0) {
            // 定义一个map用于存放excel列的序号和field.
            Map<String, Integer> cellMap = new HashMap<String, Integer>();
            // 获取表头,用于校验模版
            List<String> excelHeadList = new ArrayList<>();
            // 获取表头
            Row heard = sheet.getRow(0);
            for (int i = 0; i < heard.getPhysicalNumberOfCells(); i++) {
                Cell cell = heard.getCell(i);
                if (StringUtils.isNotNull(cell)) {
                    String value = this.getCellValue(heard, i).toString();
                    cellMap.put(value, i);
                    excelHeadList.add(value);
                } else {
                    cellMap.put(null, i);
                }
            }
            // 校验模版
            if (checkTemplate && CollUtil.isNotEmpty(templateHeadList)) {
                checkTemplate(templateHeadList, excelHeadList);
            }
            // 有数据时才处理 得到类的所有field.
            Field[] allFields = clazz.getDeclaredFields();
            // 定义一个map用于存放列的序号和field.
            Map<Integer, Field> fieldsMap = new HashMap<Integer, Field>();
            for (Field field : allFields) {
                Excel attr = field.getAnnotation(Excel.class);
                if (attr != null && (attr.type() == Type.ALL || attr.type() == type)) {
                    // 设置类的私有字段属性可访问.
                    field.setAccessible(true);
                    Integer column = cellMap.get(attr.name());
                    if (column != null) {
                        fieldsMap.put(column, field);
                    }
                }
            }
            for (int i = 1; i < rows; i++) {
                // 从第2行开始取数据,默认第一行是表头.
                Row row = sheet.getRow(i);
                T entity = null;
                for (Map.Entry<Integer, Field> entry : fieldsMap.entrySet()) {
                    Object val = this.getCellValue(row, entry.getKey());

                    // 如果不存在实例则新建.
                    entity = (entity == null ? clazz.newInstance() : entity);
                    // 从map中得到对应列的field.
                    Field field = fieldsMap.get(entry.getKey());
                    // 取得类型,并根据对象类型设置值.
                    Class<?> fieldType = field.getType();
                    if (String.class == fieldType) {
                        String s = Convert.toStr(val);
                        if (StringUtils.endsWith(s, ".0")) {
                            val = StringUtils.substringBefore(s, ".0");
                        } else {
                            val = Convert.toStr(val);
                        }
                    } else if ((Integer.TYPE == fieldType || Integer.class == fieldType) && StringUtils.isNumeric(Convert.toStr(val))) {
                        val = Convert.toInt(val);
                    } else if (Long.TYPE == fieldType || Long.class == fieldType) {
                        val = Convert.toLong(val);
                    } else if (Double.TYPE == fieldType || Double.class == fieldType) {
                        val = Convert.toDouble(val);
                    } else if (Float.TYPE == fieldType || Float.class == fieldType) {
                        val = Convert.toFloat(val);
                    } else if (BigDecimal.class == fieldType) {
                        val = Convert.toBigDecimal(val);
                    } else if (Date.class == fieldType) {
                        if (val instanceof String) {
                            val = DateUtils.parseDate(val);
                        } else if (val instanceof Double) {
                            val = DateUtil.getJavaDate((Double) val);
                        }
                    } else if (Boolean.TYPE == fieldType || Boolean.class == fieldType) {
                        val = Convert.toBool(val, false);
                    }
                    Excel attr = field.getAnnotation(Excel.class);
                    String propertyName = field.getName();
                    if (StringUtils.isNotEmpty(attr.targetAttr())) {
                        propertyName = field.getName() + "." + attr.targetAttr();
                    } else if (StringUtils.isNotEmpty(attr.readConverterExp())) {
                        val = reverseByExp(Convert.toStr(val), attr.readConverterExp(), attr.separator());
                    }
                    ReflectUtils.invokeSetter(entity, propertyName, val);
                }
                list.add(entity);
            }
        }
        return list;
    }

    /**
     * 对list数据源将其里面的数据导入到excel表单
     *
     * @param list      导出数据集合
     * @param sheetName 工作表的名称
     * @return 结果
     */
    public R exportExcel(List<T> list, String sheetName) {
        this.init(list, sheetName, Type.EXPORT);
        return exportExcel();
    }

    /**
     * 对list数据源将其里面的数据导入到excel表单
     *
     * @param sheetName 工作表的名称
     * @return 结果
     */
    public R importTemplateExcel(String sheetName) {
        this.init(null, sheetName, Type.IMPORT);
        return exportExcel();
    }


    /**
     * 对list数据源将其里面的数据导入到excel表单
     *
     * @return 结果
     */
    public R exportExcel() {
        OutputStream out = null;

        try {
            // 取出一共有多少个sheet.
            double sheetNo = Math.ceil(list.size() / SHEET_SIZE);
            for (int index = 0; index <= sheetNo; index++) {
                createSheet(sheetNo, index);

                // 产生一行
                Row row = sheet.createRow(0);
                int column = 0;
                // 写入各个字段的列头名称
                for (Object[] os : fields) {
                    Excel excel = (Excel) os[1];
                    this.createCell(excel, row, column++,false);
                }
                if (Type.EXPORT.equals(type)) {
                    fillExcelData(index, row);
                    addStatisticsRow();
                }
            }
            String filename = encodingFilename(sheetName);
            String filePath = getAbsoluteFile(filename);
            out = Files.newOutputStream(Paths.get(filePath));
            wb.write(out);
            return R.success(filename);
        } catch (Exception e) {
            log.error("导出Excel异常", e);
            throw new CustomException("导出Excel失败，请联系网站管理员！");
        } finally {
            if (wb != null) {
                try {
                    wb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void uploadOssService(String filePath, String fileName) {
        InputStream ins = null;
        String logId = SnowFlakeUtils.getId();
        try {
            File toFile = new File(filePath);
            ins = Files.newInputStream(toFile.toPath());
        } catch (Exception e) {
            log.error("OSS文件存储异常,异常ID:{}", logId, e);
            throw new CustomException("OSS文件存储异常!异常ID:" + logId);
        } finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void uploadMinioService(String filePath, String fileName) {
        InputStream ins = null;
        String logId = SnowFlakeUtils.getId();
        try {
            File toFile = new File(filePath);
            ins = Files.newInputStream(toFile.toPath());
        } catch (Exception e) {
            log.error("Minio文件存储异常,异常ID:{}", logId, e);
            throw new CustomException("Minio文件存储异常!异常ID:" + logId);
        } finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 多Sheet导出
     *
     * @return
     */
    public R exportExcelMultiple(int sheetNo, Map<String, List<?>> listMap) {
        OutputStream out = null;
        try {
            String sheetName = "";
            int index = 0;

            // 循环Map并映射字段放入Excel表格中
            for (Map.Entry<String, List<?>> entry : listMap.entrySet()) {
                this.initMulSheet((List<T>) entry.getValue(), entry.getKey(), Type.EXPORT);
                createMultipleSheet(sheetNo, index, entry.getKey());
                if (index == 0) {
                    sheetName = entry.getKey();
                }
                // 产生一行
                Row row = sheet.createRow(0);
                int column = 0;
                List<Object[]> fields = createExcelField(entry.getValue().get(0).getClass());
                for (Object[] os : fields) {
                    Excel excel = (Excel) os[1];
                    this.createCell(excel, row, column++,false);
                }
                if (Type.EXPORT.equals(type)) {
                    if (entry.getValue().size() > SHEET_SIZE) {
                        fillExcelData(index, row);
                    } else {
                        fillSheetData(0, row, (List<T>) entry.getValue(), fields);
                    }
                    addStatisticsRow();
                }
                index++;
            }
            String filename = encodingFilename(sheetName);
            String filePath = getAbsoluteFile(filename);
            out = Files.newOutputStream(Paths.get(filePath));
            wb.write(out);
            return R.success(filename);
        } catch (Exception e) {
            log.error("导出Excel异常{}", e.getMessage());
            throw new CustomException("导出Excel失败，请联系网站管理员！");
        } finally {
            if (wb != null) {
                try {
                    wb.close();
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 填充excel数据
     *
     * @param index 序号
     * @param row   单元格行
     */
    public void fillExcelData(int index, Row row) {
        int startNo = index * SHEET_SIZE;
        int endNo = Math.min(startNo + SHEET_SIZE, list.size());
        for (int i = startNo; i < endNo; i++) {
            row = sheet.createRow(i + 1 - startNo);
            // 得到导出对象.
            T vo = (T) list.get(i);
            int column = 0;
            for (Object[] os : fields) {
                Field field = (Field) os[0];
                Excel excel = (Excel) os[1];
                // 设置实体类私有属性可访问
                field.setAccessible(true);
                this.addCell(excel, row, vo, field, column++);
            }
        }
    }

    /**
     * 填充excel数据
     *
     * @param index 序号
     * @param row   单元格行
     */
    public void fillSheetData(int index, Row row, List<T> list, List<Object[]> fields) {
        int startNo = index * SHEET_SIZE;
        int endNo = Math.min(startNo + SHEET_SIZE, list.size());
        for (int i = startNo; i < endNo; i++) {
            row = sheet.createRow(i + 1 - startNo);
            // 得到导出对象.
            T vo = list.get(i);
            int column = 0;
            for (Object[] os : fields) {
                Field field = (Field) os[0];
                Excel excel = (Excel) os[1];
                // 设置实体类私有属性可访问
                field.setAccessible(true);
                this.addCell(excel, row, vo, field, column++);
            }
        }
    }

    /**
     * 创建表格样式
     *
     * @param wb 工作薄对象
     * @return 样式列表
     */
    private Map<String, CellStyle> createStyles(Workbook wb) {
        // 写入各条记录,每条记录对应excel表中的一行
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        org.apache.poi.ss.usermodel.Font dataFont = wb.createFont();
        dataFont.setFontName("Arial");
        dataFont.setFontHeightInPoints((short) 10);
        style.setFont(dataFont);
        styles.put("data", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        org.apache.poi.ss.usermodel.Font headerFont = wb.createFont();
        headerFont.setFontName("Arial");
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(headerFont);
        styles.put("header", style);

        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        org.apache.poi.ss.usermodel.Font totalFont = wb.createFont();
        totalFont.setFontName("Arial");
        totalFont.setFontHeightInPoints((short) 10);
        style.setFont(totalFont);
        styles.put("total", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(HorizontalAlignment.LEFT);
        styles.put("data1", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(HorizontalAlignment.CENTER);
        styles.put("data2", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(HorizontalAlignment.RIGHT);
        styles.put("data3", style);

        return styles;
    }

    /**
     * 创建单元格
     */
    public void createCell(Excel attr, Row row, int column,boolean useExtSheetValidate) {
        // 创建列
        Cell cell = row.createCell(column);
        // 写入列信息
        cell.setCellValue(attr.name());
        setDataValidation(attr, row, column,useExtSheetValidate);
        cell.setCellStyle(styles.get("header"));
    }

    /**
     * 设置单元格信息
     *
     * @param value 单元格值
     * @param attr  注解相关
     * @param cell  单元格信息
     */
    public void setCellVo(Object value, Excel attr, Cell cell) {
        if (Excel.ColumnType.STRING == attr.cellType()) {
            cell.setCellValue(StringUtils.isNull(value) ? attr.defaultValue() : value + attr.suffix());
        } else if (Excel.ColumnType.NUMERIC == attr.cellType() && ObjectUtil.isNotEmpty(value)) {
            cell.setCellValue(StringUtils.contains(Convert.toStr(value), ".") ? Convert.toDouble(value) : Convert.toInt(value));
        }
    }

    /**
     * 创建表格样式
     */
    public void setDataValidation(Excel attr, Row row, int column, boolean useExtSheetValidate) {
        if (attr.name().contains("注：")) {
            sheet.setColumnWidth(column, 6000);
        } else {
            // 设置列宽
            sheet.setColumnWidth(column, (int) ((attr.width() + 0.72) * 256));
            row.setHeight((short) (attr.height() * 20));
        }
        // 如果设置了提示信息则鼠标放上去提示.
        if (StringUtils.isNotEmpty(attr.prompt())) {
            // 这里默认设了2-101列提示.
            setXSSFPrompt(sheet, "", attr.prompt(), 1, 100, column, column);
        }
        //查询字典表设置下拉列表
        if (attr.combo().length == 0 && StringUtils.isNotEmpty(attr.dictType())) {
            try {
                Class<?> aClass = SpringUtils.getBean(attr.beanName()).getClass();
                Method method = aClass.getDeclaredMethod(attr.methodName(), String.class);
                List<String> labels = (List<String>) method.invoke(SpringUtils.getBean(attr.beanName()), attr.dictType());
                if (CollectionUtil.isNotEmpty(labels)) {
                    if(useExtSheetValidate) {
                        setXSSFValidationExtForValidate(sheet, attr.methodName(), 1, END_ROW_NUM, column, column);
                    }else {
                        setXSSFValidation(sheet, labels.toArray(new String[0]), 1, END_ROW_NUM, column, column);
                    }
                }
            } catch (Exception e) {
                throw new CustomException(e.getMessage(), e);
            }
        }
        // 如果设置了combo属性则本列只能选择不能输入
        if (attr.combo().length > 0) {
            // 这里默认设了2-501列只能选择不能输入.
            setXSSFValidation(sheet, attr.combo(), 1, END_ROW_NUM, column, column);
        }
        //ly 2022-07-19 添加批注注解
        if (StringUtils.isNotEmpty(attr.comments())) {
            String commont = attr.comments();
            int commentsHeight = attr.commentsHeight();
            int commentsWidth = attr.commentsWidth();
            addCellComments(sheet, row, commont, column, 0, column + 1, 2, commentsWidth, commentsHeight);
        }
        //ly 2022-07-19 添加验证信息注解
        if (attr.numerBetween() != null && attr.numerBetween().length > 0) {
            int[] numers = attr.numerBetween();
            addValidationNumericConstraint(sheet, numers, 1, END_ROW_NUM, column, column);
        }
    }

    /**
     * 添加单元格
     */
    public void addCell(Excel attr, Row row, T vo, Field field, int column) {
        Cell cell = null;
        try {
            // 设置行高
            row.setHeight((short) (attr.height() * 20));
            // 根据Excel中设置情况决定是否导出,有些情况需要保持为空,希望用户填写这一列.
            if (attr.isExport()) {
                // 创建cell
                cell = row.createCell(column);
                int align = attr.align().value();
                cell.setCellStyle(styles.get("data" + (align >= 1 && align <= 3 ? align : "")));

                // 用于读取对象中的属性
                Object value = getTargetValue(vo, field, attr);
                String dateFormat = attr.dateFormat();
                String readConverterExp = attr.readConverterExp();
                String separator = attr.separator();
                String dictType = attr.dictType();
                if (StringUtils.isNotEmpty(dateFormat) && StringUtils.isNotNull(value)) {
                    cell.setCellValue(DateUtils.parseDateToStr(dateFormat, (Date) value));
                } else if (StringUtils.isNotEmpty(readConverterExp) && StringUtils.isNotNull(value)) {
                    cell.setCellValue(convertByExp(Convert.toStr(value), readConverterExp, separator));
                } /*else if (StringUtils.isNotEmpty(dictType) && StringUtils.isNotNull(value)) {
                    // 导出时使用这样的字典转换方式是不对的，当数据量较大的时候频繁查询DB
                    cell.setCellValue(convertDictByExp(Convert.toStr(value), dictType, separator));
                }*/ else if (value instanceof BigDecimal && -1 != attr.scale()) {
                    cell.setCellValue((((BigDecimal) value).setScale(attr.scale(), attr.roundingMode())).toString());
                } else {
                    // 设置列类型
                    setCellVo(value, attr, cell);
                }
                addStatisticsData(column, Convert.toStr(value), attr);
            }
        } catch (Exception e) {
            log.error("导出Excel失败", e);
        }
    }

    /**
     * 设置 POI XSSFSheet 单元格提示
     *
     * @param sheet         表单
     * @param promptTitle   提示标题
     * @param promptContent 提示内容
     * @param firstRow      开始行
     * @param endRow        结束行
     * @param firstCol      开始列
     * @param endCol        结束列
     */
    public void setXSSFPrompt(Sheet sheet, String promptTitle, String promptContent, int firstRow, int endRow,
                              int firstCol, int endCol) {
        DataValidationHelper helper = sheet.getDataValidationHelper();
        DataValidationConstraint constraint = helper.createCustomConstraint("DD1");
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        DataValidation dataValidation = helper.createValidation(constraint, regions);
        dataValidation.createPromptBox(promptTitle, promptContent);
        dataValidation.setShowPromptBox(true);
        sheet.addValidationData(dataValidation);
    }

    /**
     * 设置某些列的值只能输入预制的数据,显示下拉框.
     *
     * @param sheet    要设置的sheet.
     * @param textlist 下拉框显示的内容
     * @param firstRow 开始行
     * @param endRow   结束行
     * @param firstCol 开始列
     * @param endCol   结束列
     * @return 设置好的sheet.
     */
    public void setXSSFValidation(Sheet sheet, String[] textlist, int firstRow, int endRow, int firstCol, int endCol) {
        DataValidationHelper helper = sheet.getDataValidationHelper();
        // 加载下拉列表内容
        DataValidationConstraint constraint = helper.createExplicitListConstraint(textlist);
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        // 数据有效性对象
        DataValidation dataValidation = helper.createValidation(constraint, regions);
        // 处理Excel兼容性问题
        if (dataValidation instanceof XSSFDataValidation) {
            dataValidation.setSuppressDropDownArrow(true);
            dataValidation.setShowErrorBox(true);
        } else {
            dataValidation.setSuppressDropDownArrow(false);
        }

        sheet.addValidationData(dataValidation);
    }

    /**
     * 设置某些列的值只能输入指定范围的数字
     *
     * @param sheet    要设置的sheet.
     * @param numers   数字范围
     * @param firstRow 开始行
     * @param endRow   结束行
     * @param firstCol 开始列
     * @param endCol   结束列
     */
    public void addValidationNumericConstraint(Sheet sheet, int[] numers, int firstRow, int endRow, int firstCol, int endCol) {
        //创建数据验证类
        DataValidationHelper helper = sheet.getDataValidationHelper();
        CellRangeAddressList rangeAddressList = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        DataValidationConstraint constraint = helper.createNumericConstraint(DataValidationConstraint.ValidationType.INTEGER
                , DataValidationConstraint.OperatorType.BETWEEN, String.valueOf(numers[0]), String.valueOf(numers[1]));
        //创建验证对象
        DataValidation validation = helper.createValidation(constraint, rangeAddressList);
        //误提示信息
        validation.createErrorBox("提示", "必须输入数字,且上下限为" + numers[0] + "~" + numers[1]);
        // 处理Excel兼容性问题
        if (validation instanceof XSSFDataValidation) {
            validation.setSuppressDropDownArrow(true);
            validation.setShowErrorBox(true);
        } else {
            validation.setSuppressDropDownArrow(false);
        }
        sheet.addValidationData(validation);

    }

    /**
     * 设置某些列的值只能输入指定范围的数字
     *
     * @param sheet   要设置的sheet.
     * @param commont 批注信息
     * @param col1    指定起始的单元格，下标从0开始
     * @param row1    指定起始的单元格，下标从0开始
     * @param col2    指定结束的单元格 ，下标从0开始
     * @param row2    指定结束的单元格 ，下标从0开始
     */
    private void addCellComments(Sheet sheet, Row row, String commont, int col1, int row1, int col2, int row2,  int commentsWidth, int commentsHeight) {
        Drawing<?> drawing = sheet.createDrawingPatriarch();
        Comment comment = drawing.createCellComment(new XSSFClientAnchor(0, 0, commentsWidth * 9525, commentsHeight * 9525, col1, row1, col2, row2));
        comment.setString(new XSSFRichTextString(commont));
        row.getCell(col1).setCellComment(comment);
    }

    /**
     * 解析导出值 0=男,1=女,2=未知
     *
     * @param propertyValue 参数值
     * @param converterExp  翻译注解
     * @param separator     分隔符
     * @return 解析后值
     */
    public static String convertByExp(String propertyValue, String converterExp, String separator) {
        StringBuilder propertyString = new StringBuilder();
        String[] convertSource = converterExp.split(",");
        for (String item : convertSource) {
            String[] itemArray = item.split("=");
            if (StringUtils.containsAny(separator, propertyValue)) {
                for (String value : propertyValue.split(separator)) {
                    if (itemArray[0].equals(value)) {
                        propertyString.append(itemArray[1]).append(separator);
                        break;
                    }
                }
            } else {
                if (itemArray[0].equals(propertyValue)) {
                    return itemArray[1];
                }
            }
        }
        return StringUtils.stripEnd(propertyString.toString(), separator);
    }

    /**
     * 反向解析值 男=0,女=1,未知=2
     *
     * @param propertyValue 参数值
     * @param converterExp  翻译注解
     * @param separator     分隔符
     * @return 解析后值
     */
    public static String reverseByExp(String propertyValue, String converterExp, String separator) {
        StringBuilder propertyString = new StringBuilder();
        String[] convertSource = converterExp.split(",");
        for (String item : convertSource) {
            String[] itemArray = item.split("=");
            if (StringUtils.containsAny(separator, propertyValue)) {
                for (String value : propertyValue.split(separator)) {
                    if (itemArray[1].equals(value)) {
                        propertyString.append(itemArray[0]).append(separator);
                        break;
                    }
                }
            } else {
                if (itemArray[1].equals(propertyValue)) {
                    return itemArray[0];
                }
            }
        }
        return StringUtils.stripEnd(propertyString.toString(), separator);
    }

    /**
     * 合计统计信息
     */
    private void addStatisticsData(Integer index, String text, Excel entity) {
        if (entity != null && entity.isStatistics()) {
            Double temp = 0D;
            if (!statistics.containsKey(index)) {
                statistics.put(index, temp);
            }
            try {
                temp = Double.valueOf(text);
            } catch (NumberFormatException ignored) {
            }
            statistics.put(index, statistics.get(index) + temp);
        }
    }

    /**
     * 创建统计行
     */
    public void addStatisticsRow() {
        if (statistics.size() > 0) {
            Cell cell = null;
            Row row = sheet.createRow(sheet.getLastRowNum() + 1);
            Set<Integer> keys = statistics.keySet();
            cell = row.createCell(0);
            cell.setCellStyle(styles.get("total"));
            cell.setCellValue("合计");

            for (Integer key : keys) {
                cell = row.createCell(key);
                cell.setCellStyle(styles.get("total"));
                cell.setCellValue(DOUBLE_FORMAT.format(statistics.get(key)));
            }
            statistics.clear();
        }
    }

    /**
     * 编码文件名
     */
    public String encodingFilename(String filename) {
        filename = UUID.randomUUID().toString() + "_" + filename + ".xlsx";
        return filename;
    }

    /**
     * 获取下载路径
     *
     * @param filename 文件名称
     */
    public String getAbsoluteFile(String filename) {
        String downloadPath = AiConfig.getDownloadPath() + filename;
        File desc = new File(downloadPath);
        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        return downloadPath;

    }

    /**
     * 获取bean中的属性值
     *
     * @param vo    实体对象
     * @param field 字段
     * @param excel 注解
     * @return 最终的属性值
     * @throws Exception
     */
    private Object getTargetValue(T vo, Field field, Excel excel) throws Exception {
        Object o = field.get(vo);
        if (StringUtils.isNotEmpty(excel.targetAttr())) {
            String target = excel.targetAttr();
            if (target.contains(".")) {
                String[] targets = target.split("[.]");
                for (String name : targets) {
                    o = getValue(o, name);
                }
            } else {
                o = getValue(o, target);
            }
        }
        return o;
    }

    /**
     * 以类的属性的get方法方法形式获取值
     *
     * @param o
     * @param name
     * @return value
     * @throws Exception
     */
    private Object getValue(Object o, String name) throws Exception {
        if (StringUtils.isNotNull(o) && StringUtils.isNotEmpty(name)) {
            Class<?> clazz = o.getClass();
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            o = field.get(o);
        }
        return o;
    }

    /**
     * 得到所有定义字段
     */
    private void createExcelField() {
        this.fields = new ArrayList<Object[]>();
        List<Field> tempFields = new ArrayList<>();
        tempFields.addAll(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
        tempFields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        for (Field field : tempFields) {
            // 单注解
            if (field.isAnnotationPresent(Excel.class)) {
                putToField(field, field.getAnnotation(Excel.class));
            }

            // 多注解
            if (field.isAnnotationPresent(Excels.class)) {
                Excels attrs = field.getAnnotation(Excels.class);
                Excel[] excels = attrs.value();
                for (Excel excel : excels) {
                    putToField(field, excel);
                }
            }
        }
        this.fields = this.fields.stream().sorted(Comparator.comparing(objects -> ((Excel) objects[1]).sort())).collect(Collectors.toList());
    }

    /**
     * 得到所有定义字段并返回
     */
    private List<Object[]> createExcelField(Class clazz) {
        List<Object[]> fields = new ArrayList<>();
        List<Field> tempFields = new ArrayList<>();
        tempFields.addAll(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
        tempFields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        for (Field field : tempFields) {
            // 单注解
            if (field.isAnnotationPresent(Excel.class)) {
                Excel attr = field.getAnnotation(Excel.class);
                if (attr != null && (attr.type() == Type.ALL || attr.type() == type)) {
                    fields.add(new Object[]{field, attr});
                }
            }

            // 多注解
            if (field.isAnnotationPresent(Excels.class)) {
                Excels attrs = field.getAnnotation(Excels.class);
                Excel[] excels = attrs.value();
                for (Excel excel : excels) {
                    putToField(field, excel);
                }
            }
        }
        fields = fields.stream().sorted(Comparator.comparing(objects -> ((Excel) objects[1]).sort())).collect(Collectors.toList());
        return fields;
    }

    /**
     * 放到字段集合中
     */
    private void putToField(Field field, Excel attr) {
        if (attr != null && (attr.type() == Type.ALL || attr.type() == type)) {
            this.fields.add(new Object[]{field, attr});
        }
    }

    /**
     * 创建一个工作簿
     */
    public void createWorkbook() {
        this.wb = new SXSSFWorkbook(30000);
    }

    /**
     * 创建工作表
     *
     * @param sheetNo sheet数量
     * @param index   序号
     */
    public void createSheet(double sheetNo, int index) {
        this.sheet = wb.createSheet();
        this.styles = createStyles(wb);
        // 设置工作表的名称.
        if (sheetNo == 0) {
            wb.setSheetName(index, sheetName);
        } else {
            wb.setSheetName(index, sheetName + index);
        }
        //painWaterMark(wb,sheet,"AI");
    }

    /**
     * 创建多个工作表
     *
     * @param sheetNo sheet数量
     * @param index   序号
     */
    public void createMultipleSheet(double sheetNo, int index, String sheetName) {
        this.sheet = wb.createSheet();
        this.styles = createStyles(wb);
        // 设置工作表的名称.
        if (sheetNo == 0) {
            wb.setSheetName(index, sheetName);
        } else {
            wb.setSheetName(index, sheetName);
        }
    }

    /**
     * 获取单元格值
     *
     * @param row    获取的行
     * @param column 获取单元格列号
     * @return 单元格值
     */
    public Object getCellValue(Row row, int column) {
        if (row == null) {
            return row;
        }
        Object val = "";
        try {
            Cell cell = row.getCell(column);
            if (StringUtils.isNotNull(cell)) {
                if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
                    val = cell.getNumericCellValue();
                    if (DateUtil.isCellDateFormatted(cell)) {
                        val = DateUtil.getJavaDate((Double) val); // POI Excel 日期格式转换
                    } else {
                        if ((Double) val % 1 > 0) {
                            val = new BigDecimal(val.toString());
                        } else {
                            val = new DecimalFormat("0").format(val);
                        }
                    }
                } else if (cell.getCellType() == CellType.STRING) {
                    val = cell.getStringCellValue();
                } else if (cell.getCellType() == CellType.BOOLEAN) {
                    val = cell.getBooleanCellValue();
                } else if (cell.getCellType() == CellType.ERROR) {
                    val = cell.getErrorCellValue();
                }

            }
        } catch (Exception e) {
            return val;
        }
        return val;
    }

    public R exportMultipleExcel(Map<String, List<?>> listMap) {
        createWorkbook();
        return exportExcelMultiple(listMap.size(), listMap);
    }


    /**
     * 给Excel添加水印
     *
     * @param wb      工作簿
     * @param sheet   sheet页
     * @param content 水印内容
     */
    private static void painWaterMark(Workbook wb, Sheet sheet, String content) {
        String imgFileName = "waterMark_photo_" + content + ".png";
        //创建水印图片  （默认保存到classes目录下）
        createWaterMark(content, imgFileName);
        //将图片写入到excel中
        try {
            //也可以动态获取sheet中的行和列，根据行和列适当的放置水印图片
            //获取excel实际所占行
            //int row = sheet.getFirstRowNum() + sheet.getLastRowNum();
            //获取excel实际所占列
            //int cell = sheet.getRow(sheet.getFirstRowNum()).getLastCellNum() + 1;
            putWaterRemarkToExcel(wb, sheet, imgFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建水印图片
     * 注意：生成的图片会默认保存到classes目录下，可以根据自己的业务进行更改
     *
     * @param content
     * @param fileName
     * @throws IOException
     */
    private static void createWaterMark(String content, String fileName) {
        //生成水印图片的宽度
        int width = 150;
        //水印图片的高度
        int height = 75;
        // 获取bufferedImage对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        String fontType = "宋体";
        int fontStyle = Font.PLAIN;
        int fontSize = 25;
        Font font = new Font(fontType, fontStyle, fontSize);
        // 获取Graphics2d对象
        Graphics2D g2d = image.createGraphics();
        image = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        g2d.dispose();
        g2d = image.createGraphics();
        g2d.setColor(new Color(0, 0, 0, 80)); //设置字体颜色和透明度
        // 设置字体
        g2d.setStroke(new BasicStroke(1));
        // 设置字体类型  加粗 大小
        g2d.setFont(font);
        //设置倾斜度
        g2d.rotate(Math.toRadians(-10), (double) image.getWidth() / 2, (double) image.getHeight() / 2);
        FontRenderContext context = g2d.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(content, context);
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = -bounds.getY();
        double baseY = y + ascent;
        // 写入水印文字原定高度过小，所以累计写水印，增加高度
        g2d.drawString(content, (int) x, (int) baseY);
        // 设置透明度
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        // 释放对象
        g2d.dispose();
        String targetImagePath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath() + fileName;
        try {
            ImageIO.write(image, "png", new File(targetImagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 为Excel打上水印工具函数 请自行确保参数值，以保证水印图片之间不会覆盖。在计算水印的位置的时候，并没有考虑到单元格合并的情况，请注意
     *
     * @param wb              Excel Workbook
     * @param sheet           需要打水印的Excel
     * @param waterRemarkPath 水印地址，classPath，目前只支持png格式的图片，
     *                        因为非png格式的图片打到Excel上后可能会有图片变红的问题，且不容易做出透明效果。
     *                        同时请注意传入的地址格式，应该为类似："\\excelTemplate\\test.png"
     * @throws IOException
     */
    private static void putWaterRemarkToExcel(Workbook wb, Sheet sheet, String waterRemarkPath) throws IOException {
        // 校验传入的水印图片格式
        if (!waterRemarkPath.endsWith("png") && !waterRemarkPath.endsWith("PNG")) {
            throw new RuntimeException("向Excel上面打印水印，目前支持png格式的图片。");
        }
        // 加载图片
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        //InputStream imageIn = new FileInputStream(waterRemarkPath);
        InputStream imageIn = Thread.currentThread().getContextClassLoader().getResourceAsStream(waterRemarkPath);
        if (null == imageIn || imageIn.available() < 1) {
            throw new RuntimeException("向Excel上面打印水印，读取水印图片失败(1)。");
        }
        BufferedImage bufferImg = ImageIO.read(imageIn);
        if (null == bufferImg) {
            throw new RuntimeException("向Excel上面打印水印，读取水印图片失败(2)。");
        }
        ImageIO.write(bufferImg, "png", byteArrayOut);
        // 开始打水印
        Drawing<?> drawing = sheet.createDrawingPatriarch();
        // 按照共需打印多少行水印进行循环
        for (int yCount = 0; yCount < 10; yCount++) {
            // 按照每行需要打印多少个水印进行循环
            for (int xCount = 0; xCount < 2; xCount++) {
                // 创建水印图片位置
                int xIndexInteger = (xCount * 2) + (xCount * 5);
                int yIndexInteger = (yCount * 2) + (yCount * 5);
                /** 参数定义：第一个参数是（x轴的开始节点）；第二个参数是（是y轴的开始节点）；第三个参数是（是x轴的结束节点）；
                 * 第四个参数是（是y轴的结束节点）；第五个参数是（是从Excel的第几列开始插入图片，从0开始计数）；
                 * 第六个参数是（是从excel的第几行开始插入图片，从0开始计数）；第七个参数是（图片宽度，共多少列）；
                 * 第8个参数是（图片高度，共多少行）；*/
                ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, xIndexInteger,
                        yIndexInteger, xIndexInteger + 2, yIndexInteger + 2);
                Picture pic = drawing.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), Workbook.PICTURE_TYPE_PNG));
                pic.resize();
            }
        }
    }




    /**
     * 对list数据源将其里面的数据导入到excel表单
     *
     * @return 结果
     */
    public R exportExcelExtForValidate() {
        OutputStream out = null;

        try {
            this.sheet = wb.createSheet(sheetName);
            this.styles = createStyles(wb);
            // 将数据模板sheet放到最前面
            wb.setSheetOrder(sheetName,0);
            // 产生一行
            Row row = sheet.createRow(0);
            int column = 0;
            // 写入各个字段的列头名称
            for (Object[] os : fields) {
                Excel excel = (Excel) os[1];
                this.createCell(excel, row, column++,true);
            }
            if (Type.EXPORT.equals(type)) {
                fillExcelData(0, row);
                addStatisticsRow();
            }
            String filename = encodingFilename(sheetName);
            String filePath = getAbsoluteFile(filename);
            out = Files.newOutputStream(Paths.get(filePath));

            //设置第一个工作表为默认打开的工作表，并取消及隐藏其他sheet页
            int targetSheetIndex = 0;
            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                if (i != targetSheetIndex) {
                    wb.setSheetHidden(i, true);
                    wb.setSelectedTab(0);
                }
            }
            wb.setActiveSheet(targetSheetIndex);
            wb.setSheetHidden(targetSheetIndex, false);
            wb.setSelectedTab(targetSheetIndex);

            wb.write(out);
            return R.success(filename);
        } catch (Exception e) {
            log.error("导出Excel异常", e);
            throw new CustomException("导出Excel失败，请联系网站管理员！");
        } finally {
            if (wb != null) {
                try {
                    wb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 导出模板并添加校验项
     * @param list      导出数据集合
     * @param sheetName 工作表的名称
     * @return 结果
     */
    public R exportExcelExtForValidate(List<T> list, String sheetName) {
        this.init(list, sheetName, Type.EXPORT);
        addSheetExtForValidate();
        return exportExcelExtForValidate();
    }

    private void addSheetExtForValidate() {
        for (Object[] os : fields) {
            Excel excel = (Excel) os[1];
            //查询字典表设置下拉列表
            if (excel.combo().length == 0 && StringUtils.isNotEmpty(excel.dictType())) {
                try {
                    Class<?> aClass = SpringUtils.getBean(excel.beanName()).getClass();
                    Method method = aClass.getDeclaredMethod(excel.methodName(), String.class);
                    List<String> optionList = (List<String>) method.invoke(SpringUtils.getBean(excel.beanName()), excel.dictType());
                    Sheet sheet = wb.createSheet(excel.methodName());
                    // 在新工作簿中创建选项列表
                    int rownum = 0;
                    for (String option : optionList) {
                        Row row = sheet.createRow(rownum++);
                        Cell cell = row.createCell(0);
                        cell.setCellValue(option);
                    }
                } catch (Exception e) {
                    throw new CustomException(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 设置某些列的值只能输入预制的数据,显示下拉框.(从新的sheet里拿预制值)
     *
     * @param sheet    要设置的sheet.
     * @param SheetName 下拉框显示的内容
     * @param firstRow 开始行
     * @param endRow   结束行
     * @param firstCol 开始列
     * @param endCol   结束列
     * @return 设置好的sheet.
     */
    public void setXSSFValidationExtForValidate(Sheet sheet, String SheetName, int firstRow, int endRow, int firstCol, int endCol) {
        DataValidationHelper helper = sheet.getDataValidationHelper();
        // 加载下拉列表内容
        DataValidationConstraint constraint = helper.createFormulaListConstraint("'"+SheetName+"'!$A:$A");
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        // 数据有效性对象
        DataValidation dataValidation = helper.createValidation(constraint, regions);
        // 处理Excel兼容性问题
        if (dataValidation instanceof XSSFDataValidation) {
            dataValidation.setSuppressDropDownArrow(true);
            dataValidation.setShowErrorBox(true);
        } else {
            dataValidation.setSuppressDropDownArrow(false);
        }

        sheet.addValidationData(dataValidation);
    }
}
