package com.zairui.project1.tools;
import com.zairui.project1.pojo.Insurances;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelTool{
    //从Excel表中获取软件信息
    public static List<Insurances> getInsuranceExcel(String excelPath) {
        List<Insurances> insuranceList = new ArrayList<>();
        try {
            File excel = new File(excelPath);
            if (excel.isFile() && excel.exists()) {   //判断文件是否存在
                String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！！！！！
                Workbook wb;

                //根据文件后缀（xls/xlsx）进行判断
                if ( "xls".equals(split[1])){
                    FileInputStream fis = new FileInputStream(excel);   //文件流对象
                    wb = new HSSFWorkbook(fis);
                }else if ("xlsx".equals(split[1])){
                    wb = new XSSFWorkbook(excel);
                }else {
                    //文件类型错误!
                    return null;
                }

                //开始解析
                Sheet sheet = wb.getSheetAt(0);     //读取sheet 0
                //第一行(首行是列名，所以不读)//最后一行
                for(int rIndex = sheet.getFirstRowNum() + 1; rIndex <= sheet.getLastRowNum(); rIndex++) {   //遍历行
                    Row row = sheet.getRow(rIndex);
                    if (row != null) {
                        Insurances insurances = new Insurances();
                        insurances.setBatch(row.getCell(row.getFirstCellNum()).toString().trim());
                        insurances.setId(row.getCell(row.getFirstCellNum()+1).toString().trim());
                        insurances.setName(row.getCell(row.getFirstCellNum()+2).toString().trim());
                        insurances.setPhone(row.getCell(row.getFirstCellNum()+3).toString().trim());
                        insuranceList.add(insurances);
                        //row获取到的Excel行//TODO
                        /*for (int cIndex = row.getFirstCellNum(); cIndex < row.getLastCellNum(); cIndex++) {   //遍历列
                            Cell cell = row.getCell(cIndex);
                            if (cell != null) {
                                System.out.print(cell.toString() + "  ");
                            }
                        }*/
                    }
                }
            } else {
                System.out.println("找不到指定的文件");
                //文件类型错误!
                insuranceList.add(null);
                return insuranceList;
            }
            return insuranceList;
        } catch (Exception e) {
            //文件类型错误
            return null;
        }
    }


    /**
     * 是需要写入excel中的数据，通过map中的k-v来将数据写入excel
     */
    public static boolean createExcel(String path,String name,String[] titel,List<Insurances> list){
        // 1.创建HSSFWorkbook，一个HSSFWorkbook对应一个Excel文件
        XSSFWorkbook wb = new XSSFWorkbook();
        // 2.在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = wb.createSheet("sheet1");
        // 3.1创建第一行
        XSSFRow row = sheet.createRow(0);
        // 将列名写入
        for (int i = 0; i < titel.length; i++) {
            // 给列写入数据,创建单元格，写入数据
            row.createCell(i).setCellValue(titel[i]);
        }
        // 写入正式数据
        for (int i = 0; i <list.size() ; i++) {
            // 创建行
            row = sheet.createRow(i+1);
            // 批次号
            row.createCell(0).setCellValue(list.get(i).getBatch().toString());
            // 身份证
            row.createCell(1).setCellValue(list.get(i).getId().toString());
            // 姓名
            row.createCell(2).setCellValue(list.get(i).getName().toString());
            // 手机号
            row.createCell(3).setCellValue(list.get(i).getPhone().toString());
        }
        /*
         * 上面的操作已经是生成一个完整的文件了，只需要将生成的流转换成文件即可；
         * 下面的设置宽度可有可无，对整体影响不大
         */
        // 设置单元格宽度
        int curColWidth = 0;
        for (int i = 0; i <= titel.length; i++) {
            // 列自适应宽度，对于中文半角不友好，如果列内包含中文需要对包含中文的重新设置。
            sheet.autoSizeColumn(i, true);
            // 为每一列设置一个最小值，方便中文显示
            curColWidth = sheet.getColumnWidth(i);
            if(curColWidth<2500){
                sheet.setColumnWidth(i, 2500);
            }
            // 第3列文字较多，设置较大点。
            sheet.setColumnWidth(3, 8000);
        }

        // getTime()是一个返回当前时间的字符串，用于做文件名称
        String filePath = path + name + ".xlsx";
        try{
            // TODO 生成的wb对象传输
            FileOutputStream outputStream = new FileOutputStream(new File(filePath));
            wb.write(outputStream);
            outputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
}
