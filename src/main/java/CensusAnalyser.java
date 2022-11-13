import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CensusAnalyser {
 List<IndianCensusDAO> csvFileList;
 public CensusAnalyser(){
     this.csvFileList = new ArrayList<IndianCensusDAO>();
 }
 public int loadIndianCensusData(String csvFilePath) throws CensusAnalyserException {
     try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
         ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
         Iterator<IndiaCensusCSV> csvIterator = csvBuilder.getCSVFileIterator(reader, IndiaCensusCSV.class);
         while (csvIterator.hasNext()) {
             this.csvFileList.add(new IndianCensusDAO(csvIterator.next()));
         }
         return csvFileList.size();

     } catch (IOException e) {
         throw new CensusAnalyserException(e.getMessage(),
                 CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
     } catch (CSVBuilderException e) {
         throw new CensusAnalyserException(e.getMessage(), e.type.name());
     } catch (NullPointerException e) {
         throw new CensusAnalyserException(e.getMessage(),
                 CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
     } catch (RuntimeException e) {
         throw new CensusAnalyserException(e.getMessage(),
                 CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
     }
     public int loadStateCode(String URI indiaCensusCSVFilePath = null;
     URI indiaCensusCSVFilePath1 = indiaCensusCSVFilePath;{
         try (Reader reader = Files.newBufferedReader(Paths.get(indiaCensusCSVFilePath))) {
             ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
             List<IndiaStateCodeCSV> csvFileListcsvFileList = csvBuilder.getCSVFileList(reader, IndiaStateCodeCSV.class);
             return csvFileList.size();
         } catch (IOException e) {
             throw new CensusAnalyserException(e.getMessage(),
                     CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
         } catch (CSVBuilderException e) {
             throw new CensusAnalyserException(e.getMessage(), e.type.name());
         }
     }
 }

    public int loadIndiaCensusData(String indiaCensusCsvFilePath) {
    }

    public int loadStateCode(String indianCsvStatePath) {
    }

    public String getPopulationWiseSortedCensusData(String indiaCensusCsvFilePath) {
    }

    public String getAreaInSqKmWiseSortedCensusData(String indiaCensusCsvFilePath) {
    }

    public String getStateWiseSortedCensusData(String indiaCensusCsvFilePath) {
    }
}



