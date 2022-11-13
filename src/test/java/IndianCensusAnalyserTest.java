import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IndianCensusAnalyserTest {
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String INDIAN_CSV_STATE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String INDIAN_CENSUS_CSV_WRONG_DELIMITER = "./src/test/resources/IndiaStateCensusDataWrongDelimiter.csv";
    private static final String INDIAN_CENSUS_CSV_MISSING = "./src/test/resources/IndiaStateCensusDataMissingHeader.csv";
    private static final String INDIAN_CENSUS_EMPTY_FILE = "./src/test/resources/IndianCensusData.csv";

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        Assert.assertEquals(29, numOfRecords);
    }

    @Test
    public void givenWrongDelimiter_InIndiaCensusData_ShouldReturnCustomExceptionType() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIAN_CENSUS_CSV_WRONG_DELIMITER);
    }

    @Test
    public void givenMissingHeader_InIndiaCensusData_ShouldReturnCustomExceptionType() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIAN_CENSUS_CSV_WRONG_DELIMITER);
    }

    @Test
    public void givenEmptyCsvFile_ShouldReturnCustomExceptionType() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIAN_CENSUS_CSV_WRONG_DELIMITER);
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        ExpectedException exceptionRule = ExpectedException.none();
        exceptionRule.expect(CensusAnalyserException.class);
        censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
    }

    @Test
    public void givenIndianStateCSV_shouldReturnExactCount() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        int numOfStateCode = censusAnalyser.loadStateCode(INDIAN_CSV_STATE_PATH);
        Assert.assertEquals(37, numOfStateCode);
    }
    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedResult() {

        String sortedCensusData = null;
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        sortedCensusData = censusAnalyser.getStateWiseSortedCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
        Assert.assertEquals("Andhra Pradesh",censusCSV[0].state);
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {

        String sortedCensusData = null;
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        sortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
        Assert.assertEquals(199812341,censusCSV[censusCSV.length - 1].population);
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnAreaInSqKm_ShouldReturnSortedResult() {

        String sortedCensusData = null;
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        sortedCensusData = censusAnalyser.getAreaInSqKmWiseSortedCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
        Assert.assertEquals(342239,censusCSV[censusCSV.length - 1].areaInSqKm);
    }
}
