package ser215.final_project;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Created by Brian on 11/16/2015.
 */

public class CharacterType {
    private String name;
    private String associatedHolidayName;
    private LocalDate associatedHolidayDate;
    private int characterPoints;
    //private __Something__ characterImage;

    //Default constructor
    public CharacterType () {
        this.name = "";
        this.associatedHolidayName = "";
        this.associatedHolidayDate.now();
        this.characterPoints = 0;
    }

    public CharacterType(String name, String associatedHolidayName, int year, int month, int day, int characterPoints) {
        this.name = name;
        this.associatedHolidayName = associatedHolidayName;
        this.associatedHolidayDate.of(year,month,day);
        this.characterPoints = (5 + (int)(3 * (5 - ((Math.abs(ChronoUnit.DAYS.between(LocalDate.now(), this.associatedHolidayDate))) / 36.4))));
    }


    //Accessor Methods
    public String getName() {
        return name;
    }

    public String getAssociatedHolidayName() {
        return associatedHolidayName;
    }

    public LocalDate getAssociatedHolidayDate() {
        return associatedHolidayDate;
    }

    public int getCharacterPoints() {
        return characterPoints;
    }


    //Other methods
}