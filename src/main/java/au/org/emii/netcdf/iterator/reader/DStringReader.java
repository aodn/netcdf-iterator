package au.org.emii.netcdf.iterator.reader;

import java.util.Set;

import opendap.dap.DString;
import au.org.emii.netcdf.iterator.IndexValue;

public class DStringReader extends DReader {
    private DString variable;
    
    public DStringReader(DString variable) {
        this.variable = variable;
    }
    
    public String getString(Set<IndexValue> indexValues) {
        return variable.getValue();
    }
    
};

