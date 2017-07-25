package com.rosteringester.filecategorization;

/**
 * Created by Michael Chrisco on 07/24/2017.
 * The FileContext class uses the CategorizeFileStrategy to dictate which State should be used to handle a file.
 * TO USE:
 * String filename = "C:/DATA/rosters/aetna.xlsx"
 * FileContext filecontext = new FileContext();
 * fileContext.setFileName(fileName).categorize().handle();
 * if(fileContext.isFallout(){
 *     //Do something here
 * }
 * else{
 *     //continue on your way.
 * }
 * The fallout files will return true on the isFallout method and be moved to C:/DATA/rosters/fallout/<fileName>
 * The fileState files will return false on the isFallout method and no further action shall take place.
 * The Categorization method will return the association of the file in integer form.
 */
public class FileContext {
    private String fileName;
    private int categorization;
    public State state;

    /**
     * Context sets the file name from an absolute path.
     * @param fileName String filename
     * @return this
     */
    public FileContext setFileName(String fileName){
        this.fileName = fileName;
        return this;
    }

    /**
     * Context categorizes via the CategorizeFileStrategy
     * @return this
     */
    public FileContext categorize(){
        this.categorization = (new CategorizeFileStrategy(fileName)).getCategorization();
        if(isFallout()) changeStateTo(new FalloutState());
        else changeStateTo(new FileState());
        return this;
    }

    /**
     * True if the file is in error and is part of the fallout else false.
     * @return boolean
     */
    public Boolean isFallout(){
        return (categorization == -1);
    }

    /**
     * Calls state handle.
     */
    public void handle(){
        this.state.handle(fileName);
    }

    /**
     * Changes the FileContxt State to either FalloutState or FileState.
     * @param newState Fallout State or FileState
     */
    private void changeStateTo(State newState){
      this.state = newState;
    }

    /**
     * Get the categorization as:
     * 0  -> Coventry
     * 1  -> Aetna
     * 2  -> Both
     * -1 -> Error
     * @return categorization int
     */
    public int getCategorization(){
        return categorization;
    }
}
