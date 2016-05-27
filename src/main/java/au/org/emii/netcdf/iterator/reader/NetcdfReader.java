package au.org.emii.netcdf.iterator.reader;

import java.io.IOException;
import java.util.Set;

import au.org.emii.netcdf.iterator.IndexRange;
import au.org.emii.netcdf.iterator.IndexRangesBuilder;
import au.org.emii.netcdf.iterator.IndexValue;
import au.org.emii.netcdf.iterator.IndexValuesConverter;
import ucar.ma2.Array;
import ucar.ma2.ArrayChar;
import ucar.ma2.Index;
import ucar.nc2.Variable;
import ucar.nc2.dataset.EnhanceScaleMissing;

public class NetcdfReader {

    private Array data;
    
    private IndexValuesConverter indexValuesConverter;
    private Index index;
    private final Variable variable;

    public NetcdfReader(Variable variable) throws IOException {
        this.variable = variable;

        data = variable.read();
        
        IndexRangesBuilder indexRangesBuilder = new IndexRangesBuilder();
        indexRangesBuilder.addDimensions(variable);
        Set<IndexRange> indexRanges = indexRangesBuilder.getIndexRanges();
        
        indexValuesConverter = new IndexValuesConverter(indexRanges);
        
        index = data.getIndex();
    }
    
    public boolean getBoolean(Set<IndexValue> indexValues) {
        return data.getBoolean(indexValuesConverter.getVectorIndexValue(indexValues));
    }

    public byte getByte(Set<IndexValue> indexValues) {
        return data.getByte(indexValuesConverter.getVectorIndexValue(indexValues));
    }

    public char getChar(Set<IndexValue> indexValues) {
        return data.getChar(indexValuesConverter.getVectorIndexValue(indexValues));
    }

    public double getDouble(Set<IndexValue> indexValues) {
        return data.getDouble(indexValuesConverter.getVectorIndexValue(indexValues));
    }

    public float getFloat(Set<IndexValue> indexValues) {
        return data.getFloat(indexValuesConverter.getVectorIndexValue(indexValues));
    }
    
    public int getInt(Set<IndexValue> indexValues) {
        return data.getInt(indexValuesConverter.getVectorIndexValue(indexValues));
    }

    public long getLong(Set<IndexValue> indexValues) {
        return data.getLong(indexValuesConverter.getVectorIndexValue(indexValues));
    }

    public short getShort(Set<IndexValue> indexValues) {
        return data.getShort(indexValuesConverter.getVectorIndexValue(indexValues));
    }

    public Object getObject(Set<IndexValue> indexValues) {
        return data.getObject(indexValuesConverter.getVectorIndexValue(indexValues));
    }

    public String getString(Set<IndexValue> indexValues) {
        if (data instanceof ArrayChar && data.getShape().length > 0) {
            index.setCurrentCounter(indexValuesConverter.getVectorIndexValue(indexValues));
            return ((ArrayChar)data).getString(index);
        } else {
            return data.getObject(indexValuesConverter.getVectorIndexValue(indexValues)).toString();
        }
    }

    public String getDisplayValue(Set<IndexValue> indexValues) {
        EnhanceScaleMissing scaleMissingDecorator = (EnhanceScaleMissing) variable;

        if (scaleMissingDecorator.hasMissing() && scaleMissingDecorator.isMissing(getDouble(indexValues))) {
            return "";
        } else {
            return getString(indexValues);
        }
    }
}
