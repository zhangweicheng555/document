package com.kuandeng.tags.export.service;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.kuandeng.tags.export.model.Person;
import com.kuandeng.tags.export.util.CsvUtil;

/**
 * 
 * @author a-zhangweicheng
 *
 */
@Service
public class ExportService {

	private final static Logger logger = LoggerFactory.getLogger(ExportService.class);

	 /**
     * 导出用户到csv文件
     *
     * @param users 导出的数据（用户）
     * @return
     */
    public byte[] exportTagToCsv(List<Person> users) {
        List<LinkedHashMap<String, Object>> exportData = new ArrayList<>(users.size());
        // 行数据
        for (Person user : users) {
            LinkedHashMap<String, Object> rowData = new LinkedHashMap<>();
            rowData.put("1", user.getId());
            rowData.put("2", user.getName());
            rowData.put("3", user.getLevel());
            exportData.add(rowData);
        }
        //标题
        LinkedHashMap<String, String> header = new LinkedHashMap<>();
        header.put("1", "用户账号");
        header.put("2", "用户昵称");
        header.put("3", "用户等级");
        return CsvUtil.exportCSV(header, exportData);
    }


}
