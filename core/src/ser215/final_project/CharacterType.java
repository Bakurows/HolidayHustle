package ser215.final_project;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Brian on 11/16/2015.
 */

public class CharacterType {
    private String name;
    private String associatedHolidayName;
    private Date associatedHolidayDate;
    private int characterPoints;

    //Default constructor
    public CharacterType () {
        this.name = "";
        this.associatedHolidayName = "";
        this.associatedHolidayDate = new Date();
        this.characterPoints = 0;
    }

    public CharacterType(String name, String associatedHolidayName, int year, int month, int day, int characterPoints) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);

        this.name = name;
        this.associatedHolidayName = associatedHolidayName;
        this.associatedHolidayDate = cal.getTime();
        this.characterPoints = characterPoints;
    }


    //Accessor Methods
    public String getName() {
        return name;
    }

    public String getAssociatedHolidayName() {
        return associatedHolidayName;
    }

    public Date getAssociatedHolidayDate() {
        return associatedHolidayDate;
    }

    public int getCharacterPoints() {
        return characterPoints;
    }


    //Other methods
}