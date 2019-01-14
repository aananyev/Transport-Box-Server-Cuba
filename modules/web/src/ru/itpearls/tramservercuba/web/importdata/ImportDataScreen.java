package ru.itpearls.tramservercuba.web.importdata;

import com.haulmont.cuba.core.sys.AppContext;
import com.haulmont.cuba.core.sys.SecurityContext;
import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.FileUploadField;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import ru.itpearls.tramservercuba.service.DataImportService;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.inject.Inject;
import java.io.IOException;
import java.util.*;

public class ImportDataScreen extends AbstractWindow {

    @Inject
    private FileUploadField upload;
    @Inject
    private Button importBtn;

    @Inject
    private DataImportService dataImportService;

    @WindowParam
    private String metaClass;

    private Map<String, Object> params;

    private static final String COUNT_OF_IMPORT_OR_UPDATE_ENTITIES = "countOfImportOrUpdateEntities";
    private static final String COUNT_OF_REMOVE_ENTITIES = "countOfRemoveEntities";
    private static final String COUNT_OF_ENTITIES_BEFORE_IMPORT = "countOfEntitiesBeforeImport";
    private static final String LIST_WITH_FAIL_IMPORT_ROW_NUMBER = "failRowNumbers";
    private static final String IMPORT_RESULT_CAPTION = "importResultCaption";
    private static final String SPACE = " ";
    private static final String FAIL_MSG = "importFailMsg";
    private static final String FILE_IS_EMPTY = "fileIsEmpty";
    private static final String EXTENSION_IS_NOT_CORRECT = "extensionIsNotCorrect";
    private static final List<String> rightExtensions = Arrays.asList("xls", "xlsx");

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        this.params = params;

        checkAndUsePermissions();
    }

    private void checkAndUsePermissions() {
        SecurityContext securityContext = AppContext.getSecurityContext();

        if (!Constants.USER_ADMIN.equals(securityContext.getUser())) {
            upload.setEnabled(false);
            importBtn.setEnabled(false);
        }
    }

    public void importAction() {
        if (fileIsCorrect(upload)) {
            HSSFWorkbook wb = null;
            try {
                wb = new HSSFWorkbook(upload.getFileContent());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, List> data = new HashMap<>();
            Sheet sheet = wb.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            fillDataFromRows(rowIterator, data);

            //Remove first row dataset because it's captions of columns
            data.remove("0");

            Map<String, String> result = dataImportService.importData(data, metaClass, params);
            generateNotification(result);
            closeAction();
        }
    }

    private Boolean fileIsCorrect(FileUploadField upload) {
        if (upload.getValue() == null) {
            showNotification(getMessage(FILE_IS_EMPTY));
            return false;
        } else if (upload.getFileDescriptor() != null && !rightExtensions.contains(upload.getFileDescriptor().getExtension())) {
            showNotification(getMessage(EXTENSION_IS_NOT_CORRECT));
            return false;
        }
        return true;
    }


    private void generateNotification(Map<String, String> result) {
        if (!result.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append(getMessage(COUNT_OF_IMPORT_OR_UPDATE_ENTITIES)).append(SPACE)
                    .append(result.get(COUNT_OF_IMPORT_OR_UPDATE_ENTITIES)).append("\n");
            sb.append(getMessage(COUNT_OF_REMOVE_ENTITIES)).append(SPACE)
                    .append(result.get(COUNT_OF_REMOVE_ENTITIES)).append("\n");
            sb.append(getMessage(COUNT_OF_ENTITIES_BEFORE_IMPORT)).append(SPACE)
                    .append(result.get(COUNT_OF_ENTITIES_BEFORE_IMPORT));
            String failRowNumbers = result.get(LIST_WITH_FAIL_IMPORT_ROW_NUMBER);
            if (failRowNumbers.length()>0) {
                sb.append("\n").append(getMessage(LIST_WITH_FAIL_IMPORT_ROW_NUMBER))
                        .append(result.get(LIST_WITH_FAIL_IMPORT_ROW_NUMBER));
            }

            showNotification(getMessage(IMPORT_RESULT_CAPTION), sb.toString(), NotificationType.HUMANIZED);
        } else {
            showNotification(getMessage(FAIL_MSG));
        }
    }

    private void fillDataFromRows(Iterator<Row> rowIterator, Map<String, List> data) {
        int numberOfCells = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            // Castyl couse pio couldn't view empty cells in iteration, so we calc it from header row
            if (numberOfCells == 0) {
                numberOfCells = row.getPhysicalNumberOfCells();
            }

            List cellDataList = new ArrayList();
            for (int i = 0; i < numberOfCells; i++) {
                Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

                if (cell == null) {
                    cellDataList.add(null);
                    continue;
                }

                CellType cellType = cell.getCellType();
                switch (cellType) {
                    case STRING:
                        cellDataList.add(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        if (cell.getNumericCellValue() % 1 == 0 && !HSSFDateUtil.isCellDateFormatted(cell)) {
                            cellDataList.add((int) cell.getNumericCellValue());
                        } else  if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            cellDataList.add(cell.getDateCellValue());
                        } else {
                            cellDataList.add(cell.getNumericCellValue());
                        }
                        break;
                    case BLANK:
                        cellDataList.add(StringUtils.EMPTY);
                        break;
                    case _NONE:
                        cellDataList.add(StringUtils.EMPTY);
                        break;
                    default:
                        break;
                }
            }

            data.put(String.valueOf(row.getRowNum()), cellDataList);
        }
    }

    public void closeAction() {
        this.close(CLOSE_ACTION_ID);
    }

}
