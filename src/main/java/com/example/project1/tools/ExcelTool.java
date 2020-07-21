package com.example.project1.tools;
import com.example.project1.pojo.Insurances;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

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
}
