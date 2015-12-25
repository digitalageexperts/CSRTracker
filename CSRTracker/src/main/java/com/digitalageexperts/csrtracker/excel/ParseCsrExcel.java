package com.digitalageexperts.csrtracker.excel;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.digitalageexperts.csrtracker.model.Csr;

public class ParseCsrExcel {

	private Workbook _workbook;
	private Iterator<Row> _rowIterator;
	private int _rownum;
	
	public ParseCsrExcel(InputStream objectData,String fileType) throws Exception
	{
		_workbook = null;
		
		_workbook = null;
        if(fileType.toLowerCase().endsWith("xlsx")){
        	_workbook = new XSSFWorkbook(objectData);
        }
        else if(fileType.toLowerCase().endsWith("xls")){
        	_workbook = new HSSFWorkbook(objectData);
        }
        else 
        	throw new Exception("File Format Not As Expected: File Type");
        
        if (_workbook.getNumberOfSheets() > 1)
	    	throw new Exception("File Format Not As Expected: Number of Sheets");
	    
        
	    _rowIterator = _workbook.getSheetAt(0).iterator();
	    
	    if(!_rowIterator.hasNext())
	    	throw new Exception("File Format Not As Expected: Blank Sheet");
	    
	    
	    Iterator<Cell> cellIterator = _rowIterator.next().cellIterator();
	    int cellnum = 0;
        while (cellIterator.hasNext()) 
        {
            //Get the Cell object
            Cell cell = cellIterator.next();
            /*
    		 *  if it is the first row insure header is as the 
    		 *  original file dictated if not throw error to 
    		 *  alert file is not formated as expected
    		 */
    		if(cellnum > 11)
    			throw new Exception("File Format Not As Expected: More columns than expected");
    		
            if(cell.getCellType() != Cell.CELL_TYPE_STRING)
            	throw new Exception("File Format Not As Expected: header row not a string");
            
            switch(cellnum)
            { 
                case 0:
                	if(!cell.getStringCellValue().trim()
                			.equals("Number"))
                		throw new Exception("File Format Not As Expected: Header column "+cellnum+" not as expected. '"+cell.getStringCellValue().trim()+"'");
                	break;
                case 1:
                	if(!cell.getStringCellValue().trim()
                			.equals("Type"))
                		throw new Exception("File Format Not As Expected: Header column "+cellnum+" not as expected. '"+cell.getStringCellValue().trim()+"'");
                	break;
                case 2:
                	if(!cell.getStringCellValue().trim()
                			.equals("Description"))
                		throw new Exception("File Format Not As Expected: Header column "+cellnum+" not as expected. '"+cell.getStringCellValue().trim()+"'");
                	break;
                case 3:
                	if(!cell.getStringCellValue().trim()
                			.equals("FTE"))
                		throw new Exception("File Format Not As Expected: Header column "+cellnum+" not as expected. '"+cell.getStringCellValue().trim()+"'");
                	break;
                case 4:
                	if(!cell.getStringCellValue().trim()
                			.equals("Role/Skill"))
                		throw new Exception("File Format Not As Expected: Header column "+cellnum+" not as expected. '"+cell.getStringCellValue().trim()+"'");
                	break;
                case 5:
                	if(!cell.getStringCellValue().trim()
                			.equals("Level"))
                		throw new Exception("File Format Not As Expected: Header column "+cellnum+" not as expected. '"+cell.getStringCellValue().trim()+"'");
                	break;
                case 6:
                	if(!cell.getStringCellValue().trim()
                			.equals("Required Certification(s)"))
                		throw new Exception("File Format Not As Expected: Header column "+cellnum+" not as expected. '"+cell.getStringCellValue().trim()+"'");
                	break;
                case 7:
                	if(!cell.getStringCellValue().trim()
                			.equals("Mandatory Skills"))
                		throw new Exception("File Format Not As Expected: Header column "+cellnum+" not as expected. '"+cell.getStringCellValue().trim()+"'");
                	break;
                case 8:
                	if(!cell.getStringCellValue().trim()
                			.equals("Optional Skills"))
                		throw new Exception("File Format Not As Expected: Header column "+cellnum+" not as expected. '"+cell.getStringCellValue().trim()+"'");
                	break;
                case 9:
                	if(!cell.getStringCellValue().trim()
                			.equals("Required Clearance"))
                		throw new Exception("File Format Not As Expected: Header column "+cellnum+" not as expected. '"+cell.getStringCellValue().trim()+"'");
                	break;
                case 10:
                	if(!cell.getStringCellValue().trim()
                			.equals("Location"))
                		throw new Exception("File Format Not As Expected: Header column "+cellnum+" not as expected. '"+cell.getStringCellValue().trim()+"'");
                	break;
                case 11:
                	if(!cell.getStringCellValue().trim()
                			.equals("Resumes Due Date"))
                		throw new Exception("File Format Not As Expected: Header column "+cellnum+" not as expected. '"+cell.getStringCellValue().trim()+"'");
                	break;
            }
            
            cellnum++;
        }
        
        _rownum = 1;
	}
	
	public boolean HasNextItem()
	{
		return _rowIterator.hasNext();
	}
	
	public Csr ReadNextItem() throws Exception
	{
		//Every row has columns, get the column iterator and iterate over them
		Iterator<Cell> cellIterator = _rowIterator.next().cellIterator();
		
		String CsrNumber = null;
		String CsrType = null;
		String Description = null;
		String Fte = null;
		String RoleSkill = null;
		String Level = null;
		String RequiredCerts = null;
		String SkillsMandated = null;
		String SkillsOptional = null;
		String Clearance = null;
		String Location = null;
		Date ResumeDueDate = null;
		
		int cellnum = 0;
	    while (cellIterator.hasNext()) 
	    {
	    	if(cellnum > 11)
	    		throw new Exception("File Format Not As Expected: More columns than expected");
	    	
	        //Get the Cell object
	        Cell cell = cellIterator.next();
	        
        	switch(cellnum)
            {
                case 0:
                	if(cell.getCellType() != Cell.CELL_TYPE_STRING)
                		throw new Exception("File Format Not As Expected: Row:"+_rownum+" Cell:"+cellnum+" not a string");
                	CsrNumber = cell.getStringCellValue().trim();
                	break;
                case 1:
                	if(cell.getCellType() != Cell.CELL_TYPE_STRING)
                		throw new Exception("File Format Not As Expected: Row:"+_rownum+" Cell:"+cellnum+" not a string");
                	CsrType = cell.getStringCellValue().trim();
                	break;
                case 2:
                	if(cell.getCellType() != Cell.CELL_TYPE_STRING)
                		throw new Exception("File Format Not As Expected: Row:"+_rownum+" Cell:"+cellnum+" not a string");
                	Description = cell.getStringCellValue().trim();
                	break;
                case 3:
                	if(cell.getCellType() != Cell.CELL_TYPE_STRING)
                		throw new Exception("File Format Not As Expected: Row:"+_rownum+" Cell:"+cellnum+" not a string");
                	Fte = cell.getStringCellValue().trim();
                	break;
                case 4:
                	if(cell.getCellType() != Cell.CELL_TYPE_STRING)
                		throw new Exception("File Format Not As Expected: Row:"+_rownum+" Cell:"+cellnum+" not a string");
                	RoleSkill = cell.getStringCellValue().trim();
                	break;
                case 5:
                	if(cell.getCellType() != Cell.CELL_TYPE_STRING)
                		throw new Exception("File Format Not As Expected: Row:"+_rownum+" Cell:"+cellnum+" not a string");
                	Level = cell.getStringCellValue().trim();
                	break;
                case 6:
                	if(cell.getCellType() != Cell.CELL_TYPE_STRING)
                		throw new Exception("File Format Not As Expected: Row:"+_rownum+" Cell:"+cellnum+" not a string");
                	RequiredCerts = cell.getStringCellValue().trim();
                	break;
                case 7:
                	if(cell.getCellType() != Cell.CELL_TYPE_STRING)
                		throw new Exception("File Format Not As Expected: Row:"+_rownum+" Cell:"+cellnum+" not a string");
                	SkillsMandated = cell.getStringCellValue().trim();
                	break;
                case 8:
                	if(cell.getCellType() != Cell.CELL_TYPE_STRING)
                		throw new Exception("File Format Not As Expected: Row:"+_rownum+" Cell:"+cellnum+" not a string");
                	SkillsOptional = cell.getStringCellValue().trim();
                	break;
                case 9:
                	if(cell.getCellType() != Cell.CELL_TYPE_STRING)
                		throw new Exception("File Format Not As Expected: Row:"+_rownum+" Cell:"+cellnum+" not a string");
                	Clearance = cell.getStringCellValue().trim();
                	break;
                case 10:
                	if(cell.getCellType() != Cell.CELL_TYPE_STRING)
                		throw new Exception("File Format Not As Expected: Row:"+_rownum+" Cell:"+cellnum+" not a string");
                	Location = cell.getStringCellValue().trim();
                	break;
                case 11:
                	if(cell.getCellType() != Cell.CELL_TYPE_NUMERIC && HSSFDateUtil.isCellDateFormatted(cell))
                		throw new Exception("File Format Not As Expected: Row:"+_rownum+" Cell:"+cellnum+" not a date");
                	ResumeDueDate = cell.getDateCellValue();
                	break;
            }
	        
    		cellnum++; 
	    } //end of cell iterator
	    
	    Csr item = new Csr(CsrNumber, CsrType, Description, ResumeDueDate, Fte, RoleSkill,
	    		Location, RequiredCerts, Level, SkillsMandated, SkillsOptional);
	    
	    
		
	    _rownum++;
		return item;
	}
	
	public int GetNextRowNumber()
	{
		return _rownum;
	}
	
	/*
	 * Attempts to set the next row number to the input number
	 * returns true if the row number was set to the input
	 * returns false if the row number was not able to be set to the input
	 */
	public boolean setNextRowTo(int rownum)
	{
		_rowIterator = _workbook.getSheetAt(0).iterator();
		_rownum = 0;
		while(_rowIterator.hasNext() && _rownum != rownum)
		{
			_rowIterator.next();
			_rownum++;
		}
		if(_rownum == rownum)
			return true;
		return false;
		
	}
	
}
