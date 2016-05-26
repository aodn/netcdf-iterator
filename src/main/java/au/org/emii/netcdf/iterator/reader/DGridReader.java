package au.org.emii.netcdf.iterator.reader;

import opendap.dap.DGrid;

public class DGridReader extends DArrayReader {

    public DGridReader(DGrid variable) {
        super(variable.getArray());
    }
    
}

