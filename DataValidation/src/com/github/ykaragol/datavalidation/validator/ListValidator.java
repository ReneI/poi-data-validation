package com.github.ykaragol.datavalidation.validator;

import java.util.HashSet;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;

import com.github.ykaragol.datavalidation.ValidationResult;
import com.github.ykaragol.datavalidation.ValidationResultImpl;
import com.github.ykaragol.datavalidation.Validator;

public class ListValidator implements Validator {

	private HashSet<String> referenceValues;

	public ListValidator(List<String> referenceValues) {
		// Does not trim! 
		// If trim needed then both cell value and reference values will be trimed. 
		this.referenceValues = new HashSet<String>(referenceValues);
	}

	@Override
	public ValidationResult validate(XSSFCell cell) {
		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			if(referenceValues.contains(cell.getStringCellValue())){
				return new ValidationResultImpl(true,null);
			}
			return new ValidationResultImpl(false, "Value type is not as expected!");
		} else {
			// TODO how to handle this situation
			return null;
		}
	}

}
